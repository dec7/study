package recipe.ch3.r4

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CustomerKtTest {

    @Test
    fun `load messages`() {
        val customer = Customer("Fred").apply { messages }
        assertEquals(3, customer.messages.size)
    }
}