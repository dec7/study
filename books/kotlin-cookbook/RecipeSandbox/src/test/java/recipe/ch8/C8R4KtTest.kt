package recipe.ch8

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class C8R4KtTest {

    @Test
    fun `watched variable prints old and new values`() {
        assertEquals(1, watched)
        watched *= 2
        assertEquals(2, watched)
        watched *= 2
        assertEquals(4, watched)
    }

    @Test
    fun `veto values less than zero`() {
        assertAll(
            { assertEquals(0, checked) },
            { checked = 42; assertEquals(42, checked) },
            { checked = -1; assertEquals(42, checked) },
            { checked = 17; assertEquals(17, checked) },
        )
    }
}