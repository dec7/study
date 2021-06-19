package recipe.ch9

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class C9R4KtClassLevelTest {

    private fun fibNumbers() = listOf(
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

    @ParameterizedTest(name = "fibonacci({0} == {1})")
    @MethodSource("fibNumbers")
    fun `first 10 Fibonacci numbers (instance method)`(n: Int, fib: Int) =
        assertThat(fibonacci(n), `is`(fib))
}