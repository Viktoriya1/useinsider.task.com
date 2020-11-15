package com.base.useinsider.api.client;

import com.base.useinsider.api.model.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static com.base.useinsider.utils.ConfigProperties.getTestProperty;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static io.restassured.RestAssured.*;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class PetApiClient {

    public static String PET_ENDPOINT = getTestProperty("BASE_URL_API") + getTestProperty("PET_ENDPOINT");

    static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(PET_ENDPOINT)
            .setAccept(JSON)
            .setContentType(JSON)
            .log(ALL)
            .build();

    @BeforeClass
    public void setupApiTests() {
        config = RestAssuredConfig
                .config()
                .objectMapperConfig(new ObjectMapperConfig()
                        .jackson2ObjectMapperFactory(
                                (aClass, s) -> {
                                    ObjectMapper objectMapper = new ObjectMapper();
                                    objectMapper.setSerializationInclusion(NON_NULL);
                                    objectMapper.setSerializationInclusion(NON_DEFAULT);
                                    return objectMapper;
                                }
                        ));
        requestSpecification = requestSpec;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Step
    public static Response createPet(Pet pet) {
        return given()
                .contentType(JSON)
                .accept(JSON)
                .body(pet)
                .when()
                .post();
    }

    @Step
    public static Response getPetById(long petId) {
        return given()
                .accept(JSON)
                .pathParam("petId", petId)
                .when()
                .get("/{petId}");
    }

    @Step
    public static Response updatePet(Pet pet) {
        return given()
                .contentType(JSON)
                .accept(JSON)
                .body(pet)
                .when()
                .put();
    }

    @Step
    public static Response deletePet(long petId) {
        return given()
                .accept(JSON)
                .pathParam("petId", petId)
                .when()
                .delete("/{petId}");
    }

}
