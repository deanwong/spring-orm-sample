package com.deanwangpro.spring.orm.sample.repository;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.persistence.sessions.UnitOfWork;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Slf4j
public class AddressRepositoryCustomImpl implements AddressRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(isolation = Isolation.DEFAULT)
    @Override
    public void checkIsolationLevel() {
        UnitOfWork uow = entityManager.unwrap(UnitOfWork.class);
        // should be -1 but still 8
        log.info("isolation level {}", uow.getLogin().getTransactionIsolation());
    }
}
