package recipe.ch11

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.contains
import org.junit.jupiter.api.Test

internal class Ch11R5KtTest {

    @Test
    fun toBinaryStringAndBack() {
        val str = 42.toString(radix = 2)
        assertThat(str, `is`("101010"))

        val num = "101010".toInt(radix = 2)
        assertThat(num, `is`(42))
    }

    @Test
    fun paddedBinaryString() {
        val strings = (0..15).map {
            it.toString(2).padStart(4, '0')
        }

        assertThat(
            strings, contains(
                "0000", "0001", "0010", "0011",
                "0100", "0101", "0110", "0111",
                "1000", "1001", "1010", "1011",
                "1100", "1101", "1110", "1111",
            )
        )

        val nums = strings.map { it.toInt(2) }
        assertThat(
            nums, contains(
                0, 1, 2, 3,
                4, 5, 6, 7,
                8, 9, 10, 11,
                12, 13, 14, 15
            )
        )
    }
}