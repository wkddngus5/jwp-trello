package com.example.demo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by Naver on 2017. 7. 4..
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findById(Long id);
    User findByEmail(String email);
}
