package recipe.ch2.r3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

public class ProductTest {

    @Test
    void checkOverloadedProductCtor() {
        assertAll("overloads called from Java",
                () -> System.out.println(new Product("Name", 5.0, "Desc")),
                () -> System.out.println(new Product("Name", 5.0)),
                () -> System.out.println(new Product("Name"))
        );
    }
}
