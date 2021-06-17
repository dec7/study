package recipe.ch3.r6

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class LateInitKtTest {

    @Test
    fun `unitialized lateinit property throws exception`() {
        assertThrows<UninitializedPropertyAccessException> {
            LateInitDemo().name
        }
    }

    @Test
    fun `set the lateinit property and no exception is thrown`() {
        assertDoesNotThrow { LateInitDemo().apply { name = "Dolly" } }
    }

    @Test
    fun `initialized property`() {
        val lateInitDemo = LateInitDemo()
        lateInitDemo.initialzeName()
        assertThat(lateInitDemo.name, equalTo("World"))
    }
}