package recipe.ch4.r3

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.math.BigInteger

internal class R3SandboxKtTest {

    @Test
    fun `check recursive factorial`() {
        assertAll(
            { assertThat(recursiveFactorial(0), `is`(BigInteger.ONE)) },
            { assertThat(recursiveFactorial(1), `is`(BigInteger.ONE)) },
            { assertThat(recursiveFactorial(2), `is`(BigInteger.valueOf(2))) },
            { assertThat(recursiveFactorial(5), `is`(BigInteger.valueOf(120))) },
            { assertThrows<StackOverflowError> { recursiveFactorial(10_000) } }
        )
    }

    @Test
    fun `check recursive factorial using tail recursion`() {
        assertAll(
            { assertThat(factorial(0), `is`(BigInteger.ONE)) },
            { assertThat(factorial(1), `is`(BigInteger.ONE)) },
            { assertThat(factorial(2), `is`(BigInteger.valueOf(2))) },
            { assertThat(factorial(5), `is`(BigInteger.valueOf(120))) },
            { assertDoesNotThrow { factorial(10_000) } },
            { assertDoesNotThrow { factorial(80_000) } }
        )
    }
}