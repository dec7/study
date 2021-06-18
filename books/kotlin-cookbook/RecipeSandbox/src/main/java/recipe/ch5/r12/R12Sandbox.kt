package recipe.ch5.r12

import java.time.LocalDate

fun main(args: Array<String>) {

}

class LocalDateProgression(
    override val start: LocalDate,
    override val endInclusive: LocalDate,
    val step: Long = 1
) : Iterable<LocalDate>, ClosedRange<LocalDate> {

    override fun iterator(): Iterator<LocalDate> =
        LocalDateProgressionIterator(start, endInclusive, step)

    infix fun step(days: Long) =
        LocalDateProgression(start, endInclusive, days)
}

operator fun LocalDate.rangeTo(other: LocalDate) =
    LocalDateProgression(this, other)

internal class LocalDateProgressionIterator(
    start: LocalDate,
    val endInclusive: LocalDate,
    val step: Long
) : Iterator<LocalDate> {
    private var current = start

    override fun hasNext(): Boolean = current <= endInclusive

    override fun next(): LocalDate {
        val next = current
        current = current.plusDays(step)
        return next
    }
}