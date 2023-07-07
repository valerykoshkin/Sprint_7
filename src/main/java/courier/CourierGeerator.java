package courier;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class CourierGeerator {

    public Courier random() {
        return new Courier(randomAlphanumeric(5, 10), "132456", "first");
    }
}