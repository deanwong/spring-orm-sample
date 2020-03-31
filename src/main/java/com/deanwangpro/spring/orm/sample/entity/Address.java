package com.deanwangpro.spring.orm.sample.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "address")
public class Address {

    public Address() {
    }

    public Address(String city) {
        this.city = city;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, length = 36)
    private String id;
    @Column(nullable = false, unique = true)
    private String city;

}
