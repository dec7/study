package recipe.ch3.r3

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.closeTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class OrderItemKtTest {

    @Test
    fun `data copy function is shallow`() {
        val item1 = OrderItem(Product("baseball", 10.0), 5)
        val item2 = item1.copy()

        assertAll(
            { assertTrue(item1 == item2) },
            { assertFalse(item1 === item2) },
            { assertTrue(item1.product == item2.product) },
            { assertTrue(item1.product === item2.product) },
        )
    }

    @Test
    fun `destructure using component functions`() {
        val p = Product("baseball", 10.0)

        val (name, price, sale) = p
        assertAll(
            { assertEquals(p.name, name) },
            { assertThat(p.price, `is`(closeTo(price, 0.01))) },
            { assertFalse(sale) }
        )
    }
}