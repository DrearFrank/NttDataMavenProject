package com.ndata.ec.service;

import com.ndata.ec.entities.Cuenta;

/**
 * Class for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
public interface ICuentaService extends IBaseService<Cuenta> {

    public Cuenta findByTipoCuentaAndNumero(String tipoCuenta, String numero) throws Exception;

    public boolean existsCuenta(String tipoCuenta, String numero) throws Exception;
}
