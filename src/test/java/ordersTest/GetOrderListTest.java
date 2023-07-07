package ordersTest;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import order.OrderAssertions;
import order.OrderClient;
import org.junit.Before;
import org.junit.Test;

public class GetOrderListTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Получние списка заказов")
    public void checkListOrderTest() {
        OrderClient orderClient = new OrderClient();
        OrderAssertions checkOrder = new OrderAssertions();
        checkOrder.getListOrders(orderClient.getOrdersList());
    }
}