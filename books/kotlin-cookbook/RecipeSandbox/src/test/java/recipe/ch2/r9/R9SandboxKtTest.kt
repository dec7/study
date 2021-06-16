package recipe.ch2.r9

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test

internal class R9SandboxKtTest {

    @Test
    fun `create map using infix to function`() {
        val map = mapOf("a" to 1, "b" to 2, "c" to 2)
        assertAll(
            { assertThat(map, hasKey("a")) },
            { assertThat(map, hasKey("b")) },
            { assertThat(map, hasKey("c")) },
            { assertThat(map, hasValue(1)) },
            { assertThat(map, hasValue(2)) }
        )
    }

    @Test
    fun `create a Pair from constructor vs to function`() {
        val p1 = Pair("a", 1)
        val p2 = "a" to 1

        assertAll(
            { assertThat(p1.first, `is`("a")) },
            { assertThat(p1.second, `is`(1)) },
            { assertThat(p2.first, `is`("a")) },
            { assertThat(p2.second, `is`(1)) },
            { assertThat(p1, `is`(equalTo(p2))) },
        )
    }
}