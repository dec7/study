package recipe.ch8

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class C8R1KtTest {
    private val smartPhone: SmartPhone = SmartPhone()

    @Test
    fun `Dialing delegates to internal phone`() {
        assertEquals(
            "Dialing 555-1234...",
            smartPhone.dial("555-1234")
        )
    }

    @Test
    fun `Taking picture delegates to internal camera`() {
        assertEquals(
            "Taking picture...",
            smartPhone.takePicture()
        )
    }
}