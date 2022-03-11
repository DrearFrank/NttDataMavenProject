package com.ndata.ec.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import com.ndata.ec.entities.Cliente;
import com.ndata.ec.entities.Cuenta;
import com.ndata.ec.entities.Movimiento;
import com.ndata.ec.service.impl.ClienteService;
import com.ndata.ec.service.impl.CuentaService;
import com.ndata.ec.service.impl.MovimientoService;
import com.ndata.ec.utils.CustomException;
import com.ndata.ec.utils.NdataConstans;
import com.ndata.ec.vo.ClienteVoResponse;
import com.ndata.ec.vo.CuentaVoResponse;
import com.ndata.ec.vo.MovimientoVoRequest;
import com.ndata.ec.vo.MovimientoVoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class NttDataController {

    @Autowired
    ClienteService clienteServiceImpl;

    @Autowired
    MovimientoService movimientoServiceImpl;

    @Autowired
    CuentaService mcuentaServiceImpl;

    @Autowired
    CuentaService cuentaServiceImpl;

    private LocalDate todaysDate = LocalDate.now();

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteVoResponse>> getAllClient() throws Exception {
        List<ClienteVoResponse> lstClienteResponse = new ArrayList<ClienteVoResponse>();

        clienteServiceImpl.findAll().forEach(new Consumer<Cliente>() {
            @Override
            public void accept(final Cliente cliente) {
                lstClienteResponse.add(new ClienteVoResponse(cliente.getNombre(), cliente
                    .getDireccion(),
                    cliente.getTelefono(), cliente.getClave(), cliente.getEstado()));
            }
        });
        if (lstClienteResponse.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lstClienteResponse, HttpStatus.OK);
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> newCliente(@RequestBody Cliente cliente) throws Exception {
        if (clienteServiceImpl.existsCliente(cliente.getIdentificacion())) {
            //throw new CustomException("Cliente ya Existe");
            throw new CustomException(HttpStatus.BAD_REQUEST, "Cliente ya Existe");
        }
        cliente = clienteServiceImpl.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable("id") long id,
        @RequestBody Cliente cliente) throws Exception {
        cliente.setIdCliente(id);
        Cliente clienteData = clienteServiceImpl.update(id, cliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteData);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable("id") long id) throws Exception {
        clienteServiceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cuentas")
    public ResponseEntity<List<CuentaVoResponse>> getAllcuentas() throws Exception {
        List<CuentaVoResponse> lstCuentaResponse = new ArrayList<CuentaVoResponse>();

        cuentaServiceImpl.findAll().forEach(new Consumer<Cuenta>() {
            @Override
            public void accept(final Cuenta cuenta) {
                lstCuentaResponse
                    .add(new CuentaVoResponse(cuenta.getNumero(), cuenta.getTipoCuenta(),
                        cuenta.getSaldoInicial(), cuenta
                        .getEstado(),
                        cuenta.getCliente().getNombre()));
            }

        });
        if (lstCuentaResponse.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lstCuentaResponse, HttpStatus.OK);
    }

    @PostMapping("/cuentas/add")
    public ResponseEntity<?> newCuenta(@RequestBody Cuenta cuenta) throws Exception {
        if (cuentaServiceImpl.existsCuenta(cuenta.getTipoCuenta(), cuenta.getNumero())) {
            throw new CustomException(HttpStatus.BAD_REQUEST,"Cuenta ya Existe");
        }
        cuenta = cuentaServiceImpl.save(cuenta);
        return ResponseEntity.status(HttpStatus.OK).body(cuenta);
    }

    @PutMapping("/cuentas/updated/{id}")
    public ResponseEntity<?> updateCuenta(@PathVariable("id") long id, @RequestBody Cuenta cuenta)
        throws Exception {
        Cuenta cuentaData = cuentaServiceImpl.update(id, cuenta);
        return ResponseEntity.status(HttpStatus.OK).body(cuentaData);
    }

    @DeleteMapping("/cuentas/deleted/{id}")
    public ResponseEntity<?> deleteCuentas(@PathVariable("id") long id) throws Exception {
        cuentaServiceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/movimientos/listByFecha/{fechaInicial}/{fechaFinal}/{id}")
    public ResponseEntity<List<MovimientoVoResponse>> getEstadoCuenta(
        @PathVariable("fechaInicial") String fechaInicial,
        @PathVariable("fechaFinal") String fechaFinal, @PathVariable("id") Long id)
        throws Exception {
        List<MovimientoVoResponse> lstMovimientoResponse = new ArrayList<MovimientoVoResponse>();
        log.info("Fechas {} {}", fechaInicial, fechaFinal);
        movimientoServiceImpl
            .getEstadoCuenta(id, Date.valueOf(fechaInicial), Date.valueOf(fechaFinal))
            .forEach(new Consumer<Movimiento>() {
                @Override
                public void accept(final Movimiento movimiento) {
                    lstMovimientoResponse.add(new MovimientoVoResponse(
                        movimiento.getFeCreacion().toString(),
                        movimiento.getCuenta().getCliente().getNombre(), movimiento
                        .getCuenta().getNumero(),
                        movimiento.getCuenta().getTipoCuenta(), movimiento
                        .getCuenta().getSaldoInicial(),
                        movimiento.getCuenta().getEstado(), movimiento.getValor(), movimiento
                        .getSaldo()));
                }
            });
        if (lstMovimientoResponse.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lstMovimientoResponse, HttpStatus.OK);
    }

    @PostMapping("/movimientos/add")
    public ResponseEntity<?> newMovimiento(@RequestBody MovimientoVoRequest movimientoDto)
        throws Exception {
        float monto = NdataConstans.MONTO_LIMITE_DIARIO;
        float saldo = movimientoServiceImpl.getSaldoCuenta(movimientoDto.getCuentaId());
        float saldoDiario =
            monto - movimientoServiceImpl.getSaldoDiario(movimientoDto.getCuentaId(),
                Date.valueOf(movimientoDto.getFeCreacion()));
        if (saldo == 0 && movimientoDto.getValor() < 0) {
            throw new CustomException(HttpStatus.BAD_REQUEST, NdataConstans.SALDO_N_DISPONIBLE);
        }
        saldo += movimientoDto.getValor();
        if (saldo < 0) {
            throw new CustomException(HttpStatus.BAD_REQUEST, NdataConstans.MONTO_SUPERADO);
        }
        if (movimientoDto.getValor() < 0) {
            saldoDiario += movimientoDto.getValor();
            if (saldoDiario < 0) {
                throw new CustomException(HttpStatus.BAD_REQUEST, NdataConstans.MONTO_EXCEDIDO);
            }
        }
        String tipoMovimiento = "Credito";
        Movimiento movimiento = new Movimiento();
        movimiento.setCuentaId(movimientoDto.getCuentaId());
        if (movimientoDto.getValor() < 0) {
            tipoMovimiento = "Debito";
        }
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setValor(movimientoDto.getValor());
        movimiento.setSaldo(saldo);
        movimiento.setUsuarioCreacion(movimientoDto.getUsuarioCreacion());
        movimiento.setFeCreacion(Date.valueOf(movimientoDto.getFeCreacion()));
        movimiento.setIpCreacion(movimientoDto.getIpCreacion());
        movimiento = movimientoServiceImpl.save(movimiento);
        movimientoDto.setIdMovimiento(movimiento.getIdMovimiento());
        return ResponseEntity.status(HttpStatus.OK).body(movimientoDto);
    }

    @PutMapping("/movimientos/update/{id}")
    public ResponseEntity<?> updateMovimiento(@PathVariable("id") long id,
        @RequestBody Movimiento movimiento)
        throws Exception {
        movimiento.setIdMovimiento(id);
        Movimiento movimientoData = movimientoServiceImpl.update(id, movimiento);
        return ResponseEntity.status(HttpStatus.OK).body(movimientoData);
    }

    @DeleteMapping("/movimientos/deleted/{id}")
    public ResponseEntity<?> deleteMovimientos(@PathVariable("id") long id) throws Exception {
        movimientoServiceImpl.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("movimientos/{id}")
    public ResponseEntity<?> patchMovimiento(@PathVariable("id") long id,
        @RequestBody Map<Object, Object> fields)
        throws Exception {
        Movimiento movimiento = movimientoServiceImpl.patch(id, fields);
        return ResponseEntity.status(HttpStatus.OK).body(movimiento);
    }

}
