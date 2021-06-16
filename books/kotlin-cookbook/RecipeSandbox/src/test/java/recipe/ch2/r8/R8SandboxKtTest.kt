package recipe.ch2.r8

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import java.awt.Color

internal class R8SandboxKtTest {

    @Test
    fun `and, or, xor`() {
        val n1 = 0b0000_1100
        val n2 = 0b0001_1001

        val n1_and_n2 = n1 and n2
        val n1_or_n2 = n1 or n2
        val n1_xor_n2 = n1 xor n2

        assertThat(n1_and_n2, equalTo(0b0000_1000))
        assertThat(n1_or_n2, equalTo(0b0001_1101))
        assertThat(n1_xor_n2, equalTo(0b0001_0101))
    }

    @Test
    fun `colors as ints`() {
        val color = Color.MAGENTA
        val (a, r, g, b) = intsFromColor(color)

        assertThat(color.alpha, equalTo(a))
        assertThat(color.red, equalTo(r))
        assertThat(color.green, equalTo(g))
        assertThat(color.blue, equalTo(b))
    }

    @Test
    fun `ints as colors`() {
        val color = Color.MAGENTA
        val intColor = colorFromInts(color.alpha, color.red, color.green, color.blue)
        val color1 = Color(intColor, true)
        assertThat(color1, equalTo(color))
    }
}