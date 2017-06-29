package com.example.demo.domain;

import lombok.*;

/**
 * Created by Naver on 2017. 6. 29..
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class User {
    @NonNull
    private String userId;

    @NonNull
    private String email;

    @NonNull
    private String password;


}
