package com.example.demo;

import com.example.demo.domain.Board;
import com.example.demo.domain.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

/**
 * Created by Naver on 2017. 6. 29..
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Slf4j
public class ApiBoardControllerTest {

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setup() {
        System.out.println("port " + serverPort);
        RestAssured.port = serverPort;
    }

    @Test
    public void 정상적인_보드_추가() throws Exception {
        Board board = new Board("TEST BOARD3");
        String body = getLogined("woohyeon@hanmail.com", "a")
                    .contentType(ContentType.JSON)
                    .body(board)
                .when()
                    .post("api/boards")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .asString();
        log.debug("body: {}", body);
    }

    @Test
    public void 보드_목록_가져오기() throws Exception {
        String body = getLogined("woohyeon@hanmail.com", "a")
                .when()
                    .get("api/boards/1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .asString();
        log.debug("body: {}", body);
    }

    @Test
    public void 정상적인_보드_삭제() throws Exception {
        String body = getLogined("woohyeon@hanmail.com", "a")
                .when()
                    .delete("api/boards/1")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .extract()
                .asString();
    }

    @Test
    public void 다른_사용자라서_보드_삭제_실패() throws Exception {
        String body = getLogined("a@EMAIL.COM", "b")
                .when()
                    .delete("api/boards/1")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .extract()
                .asString();
    }

    @Test
    public void 정상적인_보드_수정() throws Exception {
        Board board = new Board("TEST BOARD3");
        String body = getLogined("woohyeon@hanmail.com", "a")
                    .contentType(ContentType.JSON)
                    .body(board)
                .when()
                    .put("api/boards/1")
                .then()
                .statusCode(HttpStatus.RESET_CONTENT.value())
                .extract()
                .asString();
        log.debug("body: {}", body);
    }

    public RequestSpecification getLogined(String username, String password) {
        return given()
                .auth().preemptive().basic(username, password);
    }
}
