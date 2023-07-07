package order;

import io.restassured.response.ValidatableResponse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderAssertions {

    public void createdOrderSuccesfully(ValidatableResponse response) {
        response
                .assertThat().log().all()
                .statusCode(201)
                .and()
                .body("track", is(notNullValue()));
    }

    public void getListOrders(ValidatableResponse response) {
        response
                .statusCode(200)
                .and()
                .body("orders", notNullValue());
    }
}