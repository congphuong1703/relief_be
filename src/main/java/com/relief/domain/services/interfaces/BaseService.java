package com.relief.domain.services.interfaces;

import java.util.Optional;

public interface BaseService<T, ID> {

    Optional<T> findById(ID id);

    T create(T entity);

    T checkExists(ID id);

    T update(ID id, T entity);

    void deleteById(ID id);

    void delete(T entity);
}