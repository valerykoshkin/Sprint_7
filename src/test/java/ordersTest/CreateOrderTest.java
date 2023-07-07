package ordersTest;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;

import order.Order;
import order.OrderAssertions;
import order.OrderClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private String firstName = "First";
    private String lastName = "Last";
    private String address = "Moscow";
    private int metroStation = 4;
    private String phone = "50511123";
    private int rentTime = 5;
    private String deliveryDate = "2024/05/05";
    private String comment = "comment";
    private String[] color;

    public CreateOrderTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getColor() {
        return new Object[][]{
                {new String[]{"BLACK", "GRAY"}},
                {new String[]{"GRAY"}},
                {new String[]{"BLACK"}},
                {new String[]{}}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Создание нового заказа с разными параметрами")
    public void createNewOrder() {
        OrderClient orderClient = new OrderClient();
        OrderAssertions checkOrder = new OrderAssertions();
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        checkOrder.createdOrderSuccesfully(orderClient.createOrder(order));
    }
}