package recipe.ch5.r7

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.contains
import org.junit.jupiter.api.Test

internal class R7SandboxKtTest {

    @Test
    fun `chunked`() {
        val range = 0..10

        val chunked = range.chunked(3)
        assertThat(chunked, contains(listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), listOf(9, 10)))

        assertThat(range.chunked(3) { it.sum() }, `is`(listOf(3, 12, 21, 19)))

        assertThat(range.chunked(3) { it.average() }, `is`(listOf(1.0, 4.0, 7.0, 9.5)))
    }

    @Test
    fun `windowed`() {
        val range = 0..10

        assertThat(range.windowed(3, 3), contains(listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8)))

        assertThat(range.windowed(3, 3) { it.average() }, contains(1.0, 4.0, 7.0))

        assertThat(
            range.windowed(3, 1),
            contains(
                listOf(0, 1, 2),
                listOf(1, 2, 3),
                listOf(2, 3, 4),
                listOf(3, 4, 5),
                listOf(4, 5, 6),
                listOf(5, 6, 7),
                listOf(6, 7, 8),
                listOf(7, 8, 9),
                listOf(8, 9, 10)
            )
        )

        assertThat(
            range.windowed(3, 1) { it.average() },
            contains(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)
        )
    }
}