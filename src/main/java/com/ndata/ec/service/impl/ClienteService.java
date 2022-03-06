package com.ndata.ec.service.impl;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;
import com.ndata.ec.entities.Cliente;
import com.ndata.ec.repositories.BaseRepository;
import com.ndata.ec.repositories.ClienteRepository;
import com.ndata.ec.service.IClienteService;
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
public class ClienteService extends BaseService<Cliente, Long> implements
    IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(BaseRepository<Cliente, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Cliente findByIdentificacion(String identificacion) throws Exception {
        Cliente cliente = clienteRepository.findByIdentificacion(identificacion);
        return cliente;
    }

    @Override
    public boolean existsCliente(String identificacion) throws Exception {
        boolean isCliente = false;
        Cliente cliente = this.findByIdentificacion(identificacion);
        if (cliente != null) {
            isCliente = true;
        }
        return isCliente;
    }

    @Override
    public Cliente patch(Long id, Map<Object, Object> fields) throws Exception {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Cliente.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, optionalCliente.get(), value);
            });
        }
        return clienteRepository.saveAndFlush(optionalCliente.get());
    }
}
