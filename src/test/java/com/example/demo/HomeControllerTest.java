package com.example.demo;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class HomeControllerTest {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(HomeControllerTest.class);

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
                .auth().preemptive().basic("woohyeon@hanmail.com", "a")
                .when()
                    .get("/boards")
                .then()
                    .statusCode(HttpStatus.OK.value())
                    .extract()
                    .asString();
        log.debug("body: {}", body);
    }
}
