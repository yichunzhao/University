package com.ynz.university.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * Read-Only repository pattern:
 *
 * For limiting clients so as to only read or write.
 *
 * @param <T> Entity type
 * @param <ID> Primary key type
 */

@NoRepositoryBean//tell Spring this is a regular interface
public interface ReadOnlyRepository<T, ID> extends Repository<T, ID> {
    //inside declare the read only interfaces.
    Optional<T> findById(ID id);

    Iterable<T> findAll();

    Iterable<T> findAllById(Iterable<ID> ids);

    Iterable<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);

    long count();

    boolean existsById(ID id);

}
