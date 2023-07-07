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
import static java.lang.Integer.parseInt;

public class AuthorizationCourierTests {
    String courierId;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    private final CourierGeerator generator = new CourierGeerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();

    @Test
    @DisplayName("Успешная авторизация курьера")
    public void authorizationCourierSuccessfully() {
        var courier = generator.random();
        check.createdSuccessfully(client.createCourier(courier));
        Credentials creds = Credentials.from(courier);
        courierId = check.loggedInSuccessfully(client.logIn(creds));
        assert parseInt(courierId) != 0;
    }

    @Test
    @DisplayName("Попытка авторизации курьера без логина")
    public void authorizationCourierWithoutLogin() {
        var courier = generator.random();
        client.createCourier(courier);

        String password = courier.getPassword();

        Credentials creds = new Credentials(password);
        check.loggedInUnsuccessfully(client.logIn(creds));
    }

    @Test
    @DisplayName("Попытка авторизации курьера с некорректным логином")
    public void authorizationCourieraWithIncorrectCreds() {
        var courier = generator.random();
        client.createCourier(courier);

        courier.setLogin("incorrectLogin");
        Credentials creds = Credentials.from(courier);
        check.loggedInUnsuccesfullyUserNotExist(client.logIn(creds));
    }

    @After
    public void deleteCourier() {
        if (courierId != null) {
            client.deleteCourier(courierId);
        }
    }
}