package com.relief.domain.services.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
public abstract class AbstractBaseService<T, ID> implements BaseService<T, ID> {

    protected abstract JpaRepository<T, ID> getRepository();

    @Override
    public Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public T create(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public T checkExists(ID id) {
       return getRepository().getReferenceById(id);
    }

    @Override
    public T update(ID id, T entity) {
        if (getRepository().existsById(id)) {
            return getRepository().save(entity);
        }
        throw new RuntimeException("Entity not found with id " + id);
    }

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public void delete(T entity){
        getRepository().delete(entity);
    }

}
