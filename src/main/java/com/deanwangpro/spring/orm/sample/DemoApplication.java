package com.deanwangpro.spring.orm.sample;

import com.deanwangpro.spring.orm.sample.entity.Address;
import com.deanwangpro.spring.orm.sample.entity.User;
import com.deanwangpro.spring.orm.sample.repository.AddressRepository;
import com.deanwangpro.spring.orm.sample.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(AddressRepository addressRepository, UserRepository userRepository) {
        return (args) -> {
            // save a few user
            userRepository.save(new User("Jack", 20));

            // fetch all users
            log.info("Users found with findAll():");
            log.info("-------------------------------");
            for (User user : userRepository.findAll()) {
                log.info(user.toString());
            }

            // save a few address
            addressRepository.save(new Address("NYC"));

            // fetch all users
            log.info("Addresses found with findAll():");
            log.info("-------------------------------");
            for (Address address : addressRepository.findAll()) {
                log.info(address.toString());
            }

            addressRepository.checkIsolationLevel();
        };
    }

}
