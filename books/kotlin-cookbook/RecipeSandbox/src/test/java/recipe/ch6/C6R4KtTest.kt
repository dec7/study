package recipe.ch6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class C6R4KtTest {

    @Test
    fun `first 10 Fibonacci numbers from sequence`() {
        val fibs = fibonacciSequence()
            .take(10)
            .toList()

        assertEquals(listOf(0, 1, 1, 2, 3, 5, 8, 13, 21, 34), fibs)
    }

    @Test
    fun `yieldAllExample`() {
        val fibs = yieldAllSequence()
            .take(10)
            .toList()

        assertEquals(listOf(0, 1, 3, 5, 8, 24, 72, 216, 648, 1944), fibs)
    }
}