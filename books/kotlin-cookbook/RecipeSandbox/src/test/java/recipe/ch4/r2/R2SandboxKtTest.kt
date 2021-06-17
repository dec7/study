package recipe.ch4.r2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.UnsupportedOperationException

internal class R2SandboxKtTest {

    @Test
    fun `sum using reduce`() {
        val numbers = intArrayOf(3,1,4,1,5,9)
        assertAll(
            { assertEquals(numbers.sum(), sumReduce(*numbers)) },
            { assertThrows<UnsupportedOperationException> {
                sumReduce()
            }}
        )
    }

    @Test
    fun `illegal example using reduce`() {
        val numbers = intArrayOf(3,1,4,1,5,9)
        assertNotEquals(numbers.fold(0) { acc, i -> acc + 2 * i }, sumReduceDoubles(*numbers))
    }
}