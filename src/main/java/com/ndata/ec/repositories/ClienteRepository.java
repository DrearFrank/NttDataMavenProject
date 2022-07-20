package com.ndata.ec.repositories;

import com.ndata.ec.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByIdentificacion(String identificacion);
}
