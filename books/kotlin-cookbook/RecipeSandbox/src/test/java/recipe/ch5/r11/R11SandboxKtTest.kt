package recipe.ch5.r11

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class R11SandboxKtTest {

    @Test
    fun `use filterIsInstance`() {
        val list = listOf("a", LocalDate.now(), 3, 1, 4, "b")
        val all = list.filterIsInstance<Any>()
        val strings = list.filterIsInstance<String>()
        val ints = list.filterIsInstance<Int>()
        val dates = list.filterIsInstance(LocalDate::class.java)

        assertThat(all, `is`(list))
        assertThat(strings, containsInAnyOrder("a", "b"))
        assertThat(ints, containsInAnyOrder(1, 3, 4))
        assertThat(dates, contains(LocalDate.now()))
    }

    @Test
    fun `use filterIsInstanceTo`() {
        val list = listOf("a", LocalDate.now(), 3, 1, 4, "b")
        val all = list.filterIsInstanceTo(mutableListOf<Any>())
        val strings = list.filterIsInstanceTo(mutableListOf<String>())
        val ints = list.filterIsInstanceTo(mutableListOf<Int>())
        val dates = list.filterIsInstanceTo(mutableListOf<LocalDate>())

        assertThat(all, `is`(list))
        assertThat(strings, containsInAnyOrder("a", "b"))
        assertThat(ints, containsInAnyOrder(1, 3, 4))
        assertThat(dates, contains(LocalDate.now()))
    }
}