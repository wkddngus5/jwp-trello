package com.example.demo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Naver on 2017. 6. 28..
 */
@ToString
public class GetterSetterExample {
    @Getter
    @Setter
    private int age = 10;

    @Setter(AccessLevel.PROTECTED) private String name;

}
