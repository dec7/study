package recipe.ch4.r1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class R1SandboxKtTest {

    @Test
    fun `sum using fold`() {
        val numbers = intArrayOf(3,1,4,1,5,9)
        assertEquals(numbers.sum(), sumWithTrace(*numbers))
    }

    @Test
    fun `factorial using recursive`() {
        assertEquals(recursiveFactorial(3), 6)
    }

    @Test
    fun `factorial using fold`() {
        assertEquals(factorialFold(3), 6)
    }
}