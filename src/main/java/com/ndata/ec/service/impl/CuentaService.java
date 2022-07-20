package com.ndata.ec.service.impl;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;
import com.ndata.ec.entities.Cuenta;
import com.ndata.ec.entities.Movimiento;
import com.ndata.ec.repositories.BaseRepository;
import com.ndata.ec.repositories.CuentaRepository;
import com.ndata.ec.service.ICuentaService;
import com.ndata.ec.utils.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CuentaService extends BaseService<Cuenta, Long> implements ICuentaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CuentaService.class);

    @Autowired
    CuentaRepository cuentaRepository;

    public CuentaService(BaseRepository<Cuenta, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Cuenta findByTipoCuentaAndNumero(String tipoCuenta, String numero) throws Exception {
        Cuenta cuenta = cuentaRepository.findByTipoCuentaAndNumero(tipoCuenta, numero);
        return cuenta;
    }

    @Override
    public boolean existsCuenta(String tipoCuenta, String numero) throws Exception {
        boolean isCuenta = false;

            Cuenta cuenta = this.findByTipoCuentaAndNumero(tipoCuenta, numero);
            if(cuenta!=null){
                return isCuenta = true;
            }
            else{
                throw new CustomException("no existe cuenta");
            }
    }

    @Override
    public Cuenta patch(Long id, Map<Object, Object> fields) throws Exception {
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(id);
        if (optionalCuenta.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Movimiento.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, optionalCuenta.get(), value);
            });
        }
        return cuentaRepository.save(optionalCuenta.get());
    }
}
