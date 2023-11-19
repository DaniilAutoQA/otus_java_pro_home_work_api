package services;

import dto.PetDto;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PetApiActions {

    public final static String BASE_URL = "https://petstore.swagger.io/v2";

    private RequestSpecification specification;

    public PetApiActions() {
        specification = given()
                .baseUri(BASE_URL)
                .basePath("/pet")
                .contentType(ContentType.JSON)
                .log().all();
    }

    public ValidatableResponse createPet(PetDto pet) {
        return given(specification)
                .body(pet)
                .when()
                .post()
                .then().log().all()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/createPet.json"));
    }

    public ValidatableResponse findPetByStatus(String status) {
        return given(specification)
                .queryParam("status ", status)
                .when()
                .get("/findByStatus")
                .then().log().all();
    }

    public void deletePet(long id) {
        given(specification)
                .delete("/" + id)
                .then().log().all();
    }

    public ValidatableResponse getPet(long id) {
        return given(specification)
                .get("/" + id)
                .then().log().all();
    }

}
