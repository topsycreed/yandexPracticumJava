import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeliveryCostCalculationTest {

    @Test
    @Tag("Positive")
    void testCheapestOrder() {
        Delivery delivery = new Delivery(1, CargoDimension.SMALL, false, ServiceWorkload.NORMAL);
        assertEquals(400, delivery.calculateDeliveryCost());
    }

    @Test
    @Tag("Positive")
    void testMoreThanMinimalOrderCost() {
        Delivery delivery = new Delivery(1, CargoDimension.SMALL, true, ServiceWorkload.NORMAL);
        assertEquals(450, delivery.calculateDeliveryCost());
    }

    @Test
    @Tag("Positive")
    void test5KmDistanceWithVeryHighWorkloadOrderCost() {
        Delivery delivery = new Delivery(5, CargoDimension.LARGE, true, ServiceWorkload.VERY_HIGH);
        assertEquals(960, delivery.calculateDeliveryCost());
    }

    @Test
    @Tag("Positive")
    void test15KmDistanceWithHighWorkloadOrderCost() {
        Delivery delivery = new Delivery(15, CargoDimension.LARGE, true, ServiceWorkload.HIGH);
        assertEquals(980, delivery.calculateDeliveryCost());
    }

    @Test
    @Tag("Positive")
    void test35KmDistanceWithIncreasedWorkloadOrderCost() {
        Delivery delivery = new Delivery(35, CargoDimension.LARGE, false, ServiceWorkload.INCREASED);
        assertEquals(600, delivery.calculateDeliveryCost());
    }

    @Test
    @Tag("Negative")
    void test31KmDistanceFragileOrderCost() {
        Delivery delivery = new Delivery(35, CargoDimension.SMALL, true, ServiceWorkload.NORMAL);
        Throwable exception = assertThrows(
                UnsupportedOperationException.class,
                delivery::calculateDeliveryCost
        );
        assertEquals("Fragile cargo cannot be delivered for the distance more than 30", exception.getMessage());
    }

    @Test
    @Tag("Negative")
    void testNegativeDistanceOrderCost() {
        Delivery delivery = new Delivery(-1, CargoDimension.SMALL, false, ServiceWorkload.NORMAL);
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                delivery::calculateDeliveryCost
        );
        assertEquals("destinationDistance should be a positive number!", exception.getMessage());
    }
}
