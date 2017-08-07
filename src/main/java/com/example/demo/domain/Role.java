package com.example.demo.domain;

import lombok.*;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

/**
 * Created by Naver on 2017. 6. 29..
 */

@ToString
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    @Getter
    @Setter
    private Long id;

    @Column(name="role")
    @Getter
    @Setter
    private String role;
}
