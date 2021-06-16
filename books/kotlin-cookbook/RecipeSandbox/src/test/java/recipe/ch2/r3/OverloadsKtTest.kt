package recipe.ch2.r3

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import recipe.ch2.addProduct

internal class OverloadsKtTest {

    @Test
    fun `check all overloads`() {
        assertAll("Overloads called from Kotlin",
            { println(addProduct("Name", 5.0, "Desc")) },
            { println(addProduct("Name", 5.0, "Desc")) },
            { println(addProduct("Name")) }
        )
    }

}

