package com.example.demo.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Naver on 2017. 6. 29..
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(length = 15, nullable = false, unique = true)
    private String userId;

    @NonNull
    @Column(length = 20, nullable = false)
    private String email;

    @NonNull
    @Column(length = 50, nullable = false)
    private String password;

    public boolean samePassword(User user) {
        return this.password.equals(user.password);
    }
}
