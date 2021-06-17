package recipe.ch3.r8;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MySingletonTest {

    @Test
    void getSingleton() {
        assertAll(
                () -> assertThat(MySingleton.INSTANCE.getMyProperty(), equalTo(3)),
                () -> assertThat(MySingleton.INSTANCE.myFunction(), equalTo("Hello"))
        );

    }
}
