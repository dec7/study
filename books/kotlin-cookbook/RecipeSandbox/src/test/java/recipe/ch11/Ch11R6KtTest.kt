package recipe.ch11

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.junit.jupiter.api.Test

internal class Ch11R6KtTest {
    val request = AstroRequest()

    @Test
    fun `get people in space`() {
        val result = request()

        assertThat(result.message, `is`("success"))
        assertThat(result.number.toInt(), `is`(greaterThanOrEqualTo(0)))
        assertThat(result.people.size, `is`(result.number.toInt()))

        println(result)
    }
}