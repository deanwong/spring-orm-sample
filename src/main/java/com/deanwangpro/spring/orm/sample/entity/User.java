package com.deanwangpro.spring.orm.sample.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, length = 36)
    private String id;
    @Column(nullable = false, unique = true)
    private String nickName;
    @Column(nullable = false)
    private int age;

    public User() {
    }

    public User(String nickName, int age) {
        this.nickName = nickName;
        this.age = age;
    }
}
