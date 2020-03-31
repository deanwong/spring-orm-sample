package com.deanwangpro.spring.orm.sample.repository;

import com.deanwangpro.spring.orm.sample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    List<User> findAll();
}
