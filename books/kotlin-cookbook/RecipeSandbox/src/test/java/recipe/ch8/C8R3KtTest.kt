package recipe.ch8

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class C8R3KtTest {

    @Test
    fun `uninitialized value throws exception`() {
        assertThrows<IllegalStateException> { shouldNotBeNull }
    }

    @Test
    fun `initialize value then retrieve it`() {
        shouldNotBeNull = "Hello, World"
        assertDoesNotThrow { shouldNotBeNull }
        assertEquals("Hello, World", shouldNotBeNull)
    }
}