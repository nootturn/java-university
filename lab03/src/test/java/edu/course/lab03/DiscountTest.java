package edu.course.lab03;

import edu.course.lab03.discount.NoDiscount;
import edu.course.lab03.discount.PercentDiscount;
import edu.course.lab03.discount.PriceCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiscountTest {

    @Test
    void noDiscount_shouldReturnSamePrice() {
        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        assertEquals(100.0, calculator.calculate(100.0));
    }

    @Test
    void percentDiscount_shouldApplyCorrectly() {
        PriceCalculator calculator = new PriceCalculator(new PercentDiscount(20));
        assertEquals(80.0, calculator.calculate(100.0));
    }

    @Test
    void shouldAllowReplacingPolicy() {
        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        assertEquals(100.0, calculator.calculate(100.0));

        calculator = new PriceCalculator(new PercentDiscount(50));
        assertEquals(50.0, calculator.calculate(100.0));
    }

    @Test
    void shouldThrowOnNegativePrice() {
        assertThrows(IllegalArgumentException.class,
                () -> new NoDiscount().apply(-10));
    }
}