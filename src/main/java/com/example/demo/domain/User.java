package com.example.demo.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @Column(length = 20)
    private String email;

    @NonNull
    @Column(length = 100)
//    @NotEmpty(message = "*Please provide your password")
    @Length(min = 5, message = "*Your password must have at leaset 5 chracters")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "userRole", joinColumns =  @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "userId")
    private Set<Deck> decks;

    public User(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public boolean samePassword(User user, BCryptPasswordEncoder passwordEncoder) {
        return this.password.equals(user.password) || passwordEncoder.matches(user.password, this.password);
    }

    public void serializePassword(BCryptPasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }
}
