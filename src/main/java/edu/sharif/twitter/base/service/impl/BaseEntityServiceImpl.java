package edu.sharif.twitter.base.service.impl;

import edu.sharif.twitter.base.BaseEntity;
import edu.sharif.twitter.base.repository.BaseEntityRepository;
import edu.sharif.twitter.base.service.BaseEntityService;

import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;

public class BaseEntityServiceImpl<E extends BaseEntity<ID>, ID extends Serializable,
        R extends BaseEntityRepository<E, ID>> implements BaseEntityService<E, ID> {

    protected final R repository;

    public BaseEntityServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E e) {
        try {
            repository.getEntityManger().getTransaction().begin();
            e = repository.save(e);
            repository.getEntityManger().getTransaction().commit();
            return e;

        } catch (PersistenceException persistenceException) {
            System.out.println("Your submit can not saved...");
        }

        return e;
    }

    @Override
    public E findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(E e) {
        repository.getEntityManger().getTransaction().begin();
        repository.delete(e);
        repository.getEntityManger().getTransaction().commit();
    }

    @Override
    public Boolean existById(ID id) {
        return repository.existById(id);
    }
}
