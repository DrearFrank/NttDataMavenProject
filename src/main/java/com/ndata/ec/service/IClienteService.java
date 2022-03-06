package com.ndata.ec.service;

import com.ndata.ec.entities.Cliente;

/**
 * Class for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
public interface IClienteService extends IBaseService<Cliente> {

    public Cliente findByIdentificacion(String identificacion) throws Exception;

    public boolean existsCliente(String identificacion) throws Exception;
}
