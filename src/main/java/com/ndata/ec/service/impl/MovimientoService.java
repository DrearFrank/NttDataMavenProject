package com.ndata.ec.service.impl;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.ndata.ec.entities.Cuenta;
import com.ndata.ec.entities.Movimiento;
import com.ndata.ec.repositories.BaseRepository;
import com.ndata.ec.repositories.CuentaRepository;
import com.ndata.ec.repositories.MovimientoRepository;
import com.ndata.ec.service.IMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

/**
 * Class for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
@Service
public class MovimientoService extends BaseService<Movimiento, Long> implements
    IMovimientoService {

    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    public MovimientoService(BaseRepository<Movimiento, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Optional<Movimiento> findFirstByCuentaIdOrderByIdMovimientoDesc(Long cuentaId)
        throws Exception {
        Optional<Movimiento> optionalMovimiento = movimientoRepository
            .findFirstByCuentaIdOrderByIdMovimientoDesc(cuentaId);
        return optionalMovimiento;
    }

    @Override
    public float getSaldoCuenta(Long cuentaId) throws Exception {
        float saldo = 0;
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(cuentaId);
        if (optionalCuenta.isPresent()) {
            saldo = optionalCuenta.get().getSaldoInicial();
        }
        Optional<Movimiento> movimiento = this.findFirstByCuentaIdOrderByIdMovimientoDesc(cuentaId);
        if (movimiento.isPresent()) {
            saldo = movimiento.get().getSaldo();
        }
        return saldo;
    }

    @Override
    public float getSaldoDiario(Long cuentaId, Date fecha) {
        return movimientoRepository.getSaldoDiario(cuentaId, fecha);
    }

    @Override
    public List<Movimiento> getEstadoCuenta(Long idCliente, Date fechaDesde, Date fechaHasta) {
        return this.movimientoRepository.getEstadoCuenta(idCliente, fechaDesde, fechaHasta);
    }

    @Override
    public Movimiento patch(Long id, Map<Object, Object> fields) throws Exception {
        Optional<Movimiento> optionalMovimiento = movimientoRepository.findById(id);
        if (optionalMovimiento.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Movimiento.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, optionalMovimiento.get(), value);
            });
        }
        return movimientoRepository.saveAndFlush(optionalMovimiento.get());
    }

}
