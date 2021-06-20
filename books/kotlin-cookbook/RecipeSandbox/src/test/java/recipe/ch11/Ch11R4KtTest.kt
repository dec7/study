package recipe.ch11

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Ch11R4KtTest {

    @Test
    fun `demonstrate replace with a string vs regex`() {
        assertAll(
            { assertEquals("one*two", "one.two".replace(".", "*")) },
            { assertEquals("*******", "one.two".replace(".".toRegex(), "*")) }
        )
    }
}