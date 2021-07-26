package com.hidarisoft.bddautomationapi.steps;

import com.hidarisoft.bddautomationapi.support.model.BddModel;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class UserStepDefinitions {

    private static final String CREATE_USER_ENDPOINT = "/v3/user";
    private static final String USER_ENDPOINT = "/v3/user/{name}";

    private Map<String, String> expectedUser = new HashMap<>();
    private BddModel bddModel;

    @Quando("faço um POST para {word} com os seguinte valores:")
    public void façoUmPOSTParaVUserComOsSeguinteValores(String endpoint, Map<String, String> user) {
        expectedUser = user;

        given()
                .body(user)
        .when()
                .post(endpoint)
        .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Então("quando faço um GET para {word}, o usuário criado é retornado")
    public void quandoFaçoUmGETParaVUserMiuOUsuárioCriadoÉRetornado(String endpoint) {
        when()
                .get(endpoint)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("miu", is(expectedUser.get("miu")));
    }

    @Quando("crio um usuário")
    public void crioUmUsuário() {
        bddModel = new BddModel(1, "miu", "Miu", "Santos", "miu@email.com", "teste", "12345", 1);

        given()
                .body(bddModel)
        .when()
                .post(CREATE_USER_ENDPOINT)
        .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Então("o usuário é salvo no sistema")
    public void oUsuárioÉSalvoNoSistema() {
        System.out.println(bddModel.getUsername());
        given()
                .pathParam("name", "miu")
        .when()
                .get(USER_ENDPOINT)
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body("miu", is(bddModel.getUsername()));
    }
}
