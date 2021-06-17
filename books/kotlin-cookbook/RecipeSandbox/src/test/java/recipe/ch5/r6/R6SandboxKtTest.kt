package recipe.ch5.r6

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

internal class R6SandboxKtTest {

    @Test
    fun `coerceIn given a range`() {
        val range = 3..8

        assertThat(5, `is`(5.coerceIn(range)))
        assertThat(range.start, `is`(1.coerceIn(range)))
        assertThat(range.endInclusive, `is`(9.coerceIn(range)))
    }

    @Test
    fun `coerceIn given min and man`() {
        val min = 2
        val max = 6

        assertThat(5, `is`(5.coerceIn(min, max)))
        assertThat(min, `is`(1.coerceIn(min, max)))
        assertThat(max, `is`(9.coerceIn(min, max)))
    }
}