package recipe.ch2.r3

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test

internal class ProductKtTest {

    @Test
    internal fun `check overloaded Product constructor`() {
        assertAll("Overloads called from Kotlin",
            { println(Product("Name", 5.0, "Desc")) },
            { println(Product("Name", 5.0)) },
            { println(Product("Name")) }
        )
    }
}