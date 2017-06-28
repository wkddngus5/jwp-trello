package com.example.demo;

import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerTest {
    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setup() {
        System.out.println("port " + serverPort);
        RestAssured.port = serverPort;
    }

    @Test
    public void home() throws Exception {
        String body = given()
                .when()
                    .get("/board.html")
                .then()
                    .statusCode(200)
                    .extract()
                    .asString();
        System.out.println("body : " + body);
        GetterSetterExample gse = new GetterSetterExample();
        gse.setAge(100);
    }
}
