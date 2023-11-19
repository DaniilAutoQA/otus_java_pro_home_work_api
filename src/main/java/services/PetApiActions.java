package services;

import dto.PetDto;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PetApiActions {

    private final static String BASE_URL = "https://petstore.swagger.io/v2";
    private final static String PATH = "/pet";
    private RequestSpecification specification;

    public PetApiActions() {
        specification = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .log().all();
    }

    public ValidatableResponse createPet(PetDto pet) {
        return given(specification)
                .body(pet)
                .when()
                .post(PATH)
                .then().log().all();

    }

    public ValidatableResponse findPetByStatus(String status) {
        return given(specification)
                .queryParam("status ", status)
                .when()
                .get(PATH + "/findByStatus")
                .then().log().all();
    }

    public void deletePet(long id) {
        given(specification)
                .delete(PATH + "/" + id)
                .then().log().all();
    }

}
