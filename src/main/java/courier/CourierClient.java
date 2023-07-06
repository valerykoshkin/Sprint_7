package courier;

import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class CourierClient {
    private final String COURIER_API = "api/v1/courier";

    public ValidatableResponse createCourier(Courier courier) {
        return given().log().all()
                .contentType(JSON)
                .and()
                .body(courier)
                .when()
                .post(COURIER_API)
                .then().log().all();
    }

    public ValidatableResponse logIn(Credentials creds) {
        return given().log().all()
                .contentType(JSON)
                .body(creds)
                .when()
                .post(COURIER_API + "/login")
                .then().log().all();
    }

    public ValidatableResponse deleteCourier(String id) {
        return given().log().all()
                .contentType(JSON)
                .when()
                .delete(COURIER_API + "/" + id)
                .then().log().all();
    }
}