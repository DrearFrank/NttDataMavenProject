package com.ndata.ec.service.impl;

import java.util.List;
import java.util.Optional;
import com.ndata.ec.repositories.BaseRepository;
import com.ndata.ec.service.IBaseService;
import com.ndata.ec.utils.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for external process.
 *
 * @author fleon on 2022/03/06
 * @version 1.0
 */
public abstract class BaseService<E, id> implements IBaseService<E> {

    protected BaseRepository<E, Long> baseRepository;

    public BaseService(BaseRepository<E, Long> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        List<E> entities = baseRepository.findAll();
        return entities;
    }

    @Override
    @Transactional
    public E findById(Long id) throws Exception {
        Optional<E> entityOptional = baseRepository.findById(id);
        return entityOptional.get();
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        entity = baseRepository.saveAndFlush(entity);
        return entity;
    }

    @Override
    @Transactional
    public E update(Long id, E entity) throws Exception {
        Optional<E> entityOptional = baseRepository.findById(id);
        if (!entityOptional.isPresent()) {
            throw new CustomException(HttpStatus.NO_CONTENT, "Registro no Existe");
        }
        E entityUpdate = entityOptional.get();
        entityUpdate = baseRepository.saveAndFlush(entity);
        return entityUpdate;
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        if (baseRepository.existsById(id)) {
            baseRepository.deleteById(id);
            return true;
        } else {
            throw new CustomException(HttpStatus.NO_CONTENT, "Registro no Existe");
        }
    }


}
