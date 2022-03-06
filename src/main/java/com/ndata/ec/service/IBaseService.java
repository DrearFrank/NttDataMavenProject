package com.ndata.ec.service;

import java.util.List;
import java.util.Map;

/**
 * Class for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
public interface IBaseService<E> {

    public List<E> findAll() throws Exception;

    public E findById(Long id) throws Exception;

    public E save(E entity) throws Exception;

    public E update(Long id, E entity) throws Exception;

    public boolean delete(Long id) throws Exception;

    public E patch(Long id, Map<Object, Object> fields) throws Exception;
}
