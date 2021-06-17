package recipe.ch3.r8

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MySingletonKtTest {

    @Test
    fun `get singleton`() {

        assertAll(
            { assertThat(MySingleton.myProperty, equalTo(3)) },
            { assertThat(MySingleton.myFunction(), equalTo("Hello")) },
        )
    }
}