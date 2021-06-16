package recipe.ch2.r6

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import kotlin.math.pow

internal class R6SandboxKtTest {

    @Test
    fun `raise an Int to a power`() {
        assertThat(256, equalTo(2.toDouble().pow(8).toInt()))
    }

    @Test
    fun `raise an Int to a power as extension function`() {
        assertThat(256, equalTo(2.pow(8)))
        assertThat(256L, equalTo(2L.pow(8)))
    }
}