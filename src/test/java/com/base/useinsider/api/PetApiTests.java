package com.base.useinsider.api;

import com.base.useinsider.api.factory.PetCreator;
import com.base.useinsider.api.client.PetApiClient;
import com.base.useinsider.api.model.Pet;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetApiTests extends PetApiClient {

    @Test(description = "Create a new Pet", priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Story("CRUD Operations for PET Swagger project")
    public void postNewPetTest() {
        Pet pet = PetCreator.defaultPet();
        Response petResponse = createPet(pet);
        petResponse.then().statusCode(200);
        Assert.assertEquals(petResponse.as(Pet.class), pet);
    }

    @Test(description = "Update the Pet", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Story("CRUD Operations for PET Swagger project")
    public void putPetTest() {
        Pet newPet = PetCreator.petWithCategoryAndTag("custom_category", "custom_tag");
        Response updateResponse = updatePet(newPet);
        updateResponse.then().statusCode(200);
        Assert.assertEquals(updateResponse.as(Pet.class), newPet, "after update");
    }

    @Test(description = "Create and Get the previously updated Pet", priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Story("CRUD Operations for PET Swagger project")
    public void createAndGetPetTest() {
        Pet newPet = PetCreator.defaultPet();
        Response petResponse = createPet(newPet);
        Response readResponse = getPetById(newPet.getId());
        readResponse.then().statusCode(200);
        Assert.assertEquals(readResponse.as(Pet.class), petResponse.as(Pet.class));
    }

    @Test(description = "Delete the Pet", priority = 4)
    @Severity(SeverityLevel.CRITICAL)
    @Story("CRUD Operations for PET Swagger project")
    public void deletePetTest() {
        Pet pet = PetCreator.defaultPet();
        Response deleteResponse = deletePet(pet.getId());
        deleteResponse.then().statusCode(200);
    }

    @Test(description = "get 404 error when delete not existed pat", priority = 5)
    @Severity(SeverityLevel.NORMAL)
    @Story("CRUD Operations for PET Swagger project")
    public void deleteNotExistedPetTest() {
        Pet pet = PetCreator.defaultPet();
        Response createResponse = createPet(pet);
        createResponse.then().statusCode(200);

        Response deleteResponseFirst = deletePet(pet.getId());
        deleteResponseFirst.then().statusCode(200);

        Response deleteResponseSecond = deletePet(pet.getId());
        deleteResponseSecond.then().statusCode(404);
    }

    @Test(description = "get 404 error when read not existed pat", priority = 6)
    @Severity(SeverityLevel.MINOR)
    @Story("CRUD Operations for PET Swagger project")
    public void getNotExistedPetTest() {
        Pet cat = PetCreator.defaultPet();
        Response createResponse = createPet(cat);
        createResponse.then().statusCode(200);

        Response deleteResponseFirst = deletePet(cat.getId());
        deleteResponseFirst.then().statusCode(200);

        Response deleteResponseSecond = getPetById(cat.getId());
        deleteResponseSecond.then().statusCode(404);
    }

}
