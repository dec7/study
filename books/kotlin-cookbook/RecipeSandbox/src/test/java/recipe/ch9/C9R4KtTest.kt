package recipe.ch9

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

internal class C9R4KtTest {

    companion object {

        @JvmStatic
        fun fibs() = listOf(
            Arguments.of(1, 1),
            Arguments.of(2, 1),
            Arguments.of(3, 2),
            Arguments.of(4, 3),
            Arguments.of(5, 5),
            Arguments.of(6, 8),
            Arguments.of(7, 13),
            Arguments.of(8, 21),
            Arguments.of(9, 34),
            Arguments.of(10, 55),
        )
    }

    @Test
    fun `Fibonacci numbers`() {
        assertAll(
            { assertThat(fibonacci(4), `is`(3)) },
            { assertThat(fibonacci(9), `is`(34)) },
            { assertThat(fibonacci(2_000), `is`(1392522469)) },
        )
    }

    @ParameterizedTest
    @CsvSource("1, 1", "2, 1", "3, 2", "4, 3", "5, 5", "6, 8", "7, 13", "8, 21", "9, 34", "10, 55")
    fun `first 10 Fibonacci numbers (csv)`(n: Int, fib: Int) =
        assertThat(fibonacci(n), `is`(fib))


    @ParameterizedTest(name = "fibonacci({0} == {1})")
    @MethodSource("fibs")
    fun `first 10 Fibonacci numbers (instance method)`(n: Int, fib: Int) =
        assertThat(fibonacci(n), `is`(fib))
}