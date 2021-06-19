package recipe.ch7

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class C7R3KtTest {

    @Test
    fun `process string`() {
        assertAll(
            { assertEquals(processString(""), "Empty") },
            { assertEquals(processString(" "), "Blank") },
            { assertEquals(processString("a"), "A") }
        )
    }

    @Test
    fun `process nullable string`() {
        assertAll(
            { assertEquals(processNullableString(null), "Null") },
            { assertEquals(processNullableString(""), "Empty") },
            { assertEquals(processNullableString(" "), "Blank") },
            { assertEquals(processNullableString("a"), "A") }
        )
    }
}