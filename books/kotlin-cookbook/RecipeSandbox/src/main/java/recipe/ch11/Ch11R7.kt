package recipe.ch11

import java.util.stream.IntStream
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    println("${Runtime.getRuntime().availableProcessors()} processors")

    var time = measureTimeMillis {
        IntStream.rangeClosed(1, 6)
            .map { doubleIt(it) }
            .sum()
    }

    println("Sequential stream took ${time}ms")

    time = measureTimeMillis {
        IntStream.rangeClosed(1, 6)
            .parallel()
            .map { doubleIt(it) }
            .sum()
    }

    println("Parallel stream took ${time}ms")
}

fun doubleIt(x: Int): Int {
    Thread.sleep(100L)
    println("doubling $x with no thread ${Thread.currentThread().name}")
    return x * 2
}