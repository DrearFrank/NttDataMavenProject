package com.ndata.ec.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import com.ndata.ec.entities.Movimiento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Class for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
@Repository
public interface MovimientoRepository extends BaseRepository<Movimiento, Long> {


    @Query("SELECT COALESCE(SUM(valor),0) FROM Movimiento WHERE cuentaId =?1 and feCreacion = ?2 AND valor < 0")
    Float getSaldoDiario(Long cuentaId, Date fecha);

   // @Query("SELECT m FROM Movimiento m inner join Cuenta c on c.idCuenta=m.cuentaId WHERE " + " c.clienteId = ?1 and m.feCreacion >=?2 and m.feCreacion <=?3 ")
    @Query("SELECT m FROM Movimiento m WHERE  m.cuenta.clienteId = ?1 and m.feCreacion >=?2 and m.feCreacion <=?3 ")
    List<Movimiento> getEstadoCuenta(Long idCliente, Date fechaDesde, Date fechaHasta);

    Optional<Movimiento> findFirstByCuentaIdOrderByIdMovimientoDesc(Long cuentaId);


}
