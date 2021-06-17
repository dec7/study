package recipe.ch3.r5

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PointKtTest {

    @Test
    fun `unary point`() {
        assertAll(
            { assertThat(Point(10, 20).unaryMinus(), equalTo(Point(-10, -20))) },
            { assertThat(-Point(10, 20), equalTo(Point(-10, -20))) }
        )
    }
}