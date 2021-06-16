package recipe.ch2.r3;

import org.junit.jupiter.api.Test;
import recipe.ch2.OverloadsKt;

import static org.junit.jupiter.api.Assertions.assertAll;

public class OverloadsTest {

    @Test
    void checkOverloads() {
        assertAll("overloads called from Java",
                () -> System.out.println(OverloadsKt.addProduct("Name", 5.0, "Desc")),
                () -> System.out.println(OverloadsKt.addProduct("Name", 5.0)),
                () -> System.out.println(OverloadsKt.addProduct("Name"))
        );
    }

}
