package recipe.ch5.r9

import org.junit.jupiter.api.Test

internal class R9SandboxKtTest {

    @Test
    fun `sort with`() {
        val golfers = listOf(
            Golfer(70, "Jack", "Nicklaus"),
            Golfer(68, "Tom", "Watson"),
            Golfer(68, "Bubba", "Watson"),
            Golfer(70, "Tiger", "Woods"),
            Golfer(68, "Ty", "Webb")
        )

        val sorted = golfers.sortedWith(
            compareBy({ it.score }, { it.last }, { it.first })
        )

        sorted.forEach { println(it) }
    }

    @Test
    fun `then by`() {
        val golfers = listOf(
            Golfer(70, "Jack", "Nicklaus"),
            Golfer(68, "Tom", "Watson"),
            Golfer(68, "Bubba", "Watson"),
            Golfer(70, "Tiger", "Woods"),
            Golfer(68, "Ty", "Webb")
        )

        var comparator = compareBy(Golfer::score)
            .thenBy(Golfer::last)
            .thenBy(Golfer::score)

        val sorted = golfers.sortedWith(comparator)

        sorted.forEach { println(it) }
    }
}