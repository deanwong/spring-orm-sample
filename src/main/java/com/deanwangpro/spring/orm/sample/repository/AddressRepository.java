package com.deanwangpro.spring.orm.sample.repository;

import com.deanwangpro.spring.orm.sample.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long>, AddressRepositoryCustom {

}
