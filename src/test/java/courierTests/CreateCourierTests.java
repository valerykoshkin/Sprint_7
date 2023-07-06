package courierTests;

import courier.CourierAssertions;
import courier.CourierClient;
import courier.CourierGeerator;
import courier.Credentials;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateCourierTests {
    String courierId;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    private final CourierGeerator generator = new CourierGeerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    @Test
    @DisplayName("Успешное создание курьера")
    public void createCourierSuccessfulTest() {
        var courier = generator.random();
        check.createdSuccessfully(client.createCourier(courier));
    }

    @Test
    @DisplayName("Попытка создания существующего курьера")
    public void createDoubleCouriers() {

        var courier = generator.random();
        check.createdSuccessfully(client.createCourier(courier));
        check.createdUnsuccesfullyDouble(client.createCourier(courier));

        Credentials creds = Credentials.from(courier);
        int courierId = check.loggedInSuccessfully(client.logIn(creds));
        assert courierId != 0;
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    public void createCourierWithoutPassword() {
        var courier = generator.random();
        courier.setPassword("");
        check.createdUnsuccesfullyPassword(client.createCourier(courier));
    }

    @Test
    @DisplayName("Создание курьера без логина")
    public void createCourierWithoutLogin() {
        var courier = generator.random();
        courier.setLogin("");
        check.createdUnsuccesfullyPassword(client.createCourier(courier));
    }

    @After
    public void deleteCourier() {
        if (courierId != null) {
            client.deleteCourier(courierId);
        }
    }
}
