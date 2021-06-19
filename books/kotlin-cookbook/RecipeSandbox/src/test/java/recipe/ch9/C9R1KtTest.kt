package recipe.ch9

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class C9R1KtTest {

    private val strings = listOf("this", "is", "a", "list", "of", "strings")

    private lateinit var modifiable: MutableList<Int>

    @BeforeEach
    fun setUp() {
        modifiable = mutableListOf(3, 1, 4, 1, 5)
        println("Before: $modifiable")
    }

    @AfterEach
    fun finish() {
        println("After: $modifiable")
    }

    @Test
    fun test1() {
        println("Test: test1")
        modifiable.add(11)
    }

    @Test
    fun test2() {
        println("Test: test2")
        modifiable.add(22)
    }

    @Test
    fun test3() {
        println("Test: test3")
        modifiable.add(33)
    }

}