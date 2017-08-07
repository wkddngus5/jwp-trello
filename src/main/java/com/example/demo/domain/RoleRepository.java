package com.example.demo.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Naver on 2017. 7. 4..
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);
}
