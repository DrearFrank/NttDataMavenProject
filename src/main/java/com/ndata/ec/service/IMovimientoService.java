package com.ndata.ec.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import com.ndata.ec.entities.Movimiento;

/**
 * Class for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
public interface IMovimientoService extends IBaseService<Movimiento> {

    Optional<Movimiento> findFirstByCuentaIdOrderByIdMovimientoDesc(Long cuentaId) throws Exception;

    float getSaldoCuenta(Long cuentaId) throws Exception;

    float getSaldoDiario(Long cuentaId, Date fecha) throws Exception;

    List<Movimiento> getEstadoCuenta(Long idCliente, Date fechaDesde, Date fechaHasta);
}
