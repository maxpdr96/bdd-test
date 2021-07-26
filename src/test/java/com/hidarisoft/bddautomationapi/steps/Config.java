package com.hidarisoft.bddautomationapi.steps;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import java.util.UUID;

public class Config {

    @Before
    public void setup(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/api";

        RestAssured.requestSpecification = new RequestSpecBuilder().
                addHeader("Authorization", getToken()).
                setContentType(ContentType.JSON).build();

        RestAssured.responseSpecification = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).build();
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }
}
