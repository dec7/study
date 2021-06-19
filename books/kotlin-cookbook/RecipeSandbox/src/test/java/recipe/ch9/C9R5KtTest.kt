package recipe.ch9

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class C9R5KtTest {

    companion object {

        @JvmStatic
        fun fibonacciTestData() = Stream.of(
            FibonacciTestData(number = 1, expected = 1),
            FibonacciTestData(number = 2, expected = 1),
            FibonacciTestData(number = 3, expected = 2),
            FibonacciTestData(number = 4, expected = 3),
            FibonacciTestData(number = 5, expected = 5),
            FibonacciTestData(number = 6, expected = 8),
            FibonacciTestData(number = 7, expected = 13),
            FibonacciTestData(number = 8, expected = 21),
            FibonacciTestData(number = 9, expected = 34),
            FibonacciTestData(number = 10, expected = 55),
        )
    }

    @ParameterizedTest
    @MethodSource("fibonacciTestData")
    fun `check fibonacci using data class`(data: FibonacciTestData) {
        assertThat(fibonacci(data.number), `is`(data.expected))
    }
}

data class FibonacciTestData(val number: Int, val expected: Int)

