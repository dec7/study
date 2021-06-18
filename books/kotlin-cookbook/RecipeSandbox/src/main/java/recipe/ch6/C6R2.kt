package recipe.ch6

import java.lang.Math.ceil
import java.lang.Math.sqrt

fun main(args: Array<String>) {
    val numSequence1 = sequenceOf(3, 1, 4, 1, 5, 9)
    val numSequence2 = listOf(3, 1, 4, 1, 5, 9).asSequence()

    println(nextPrime(3))
}

fun Int.isPrime() =
    this == 2 || (2..ceil(sqrt(this.toDouble())).toInt())
        .none { divisor -> this % divisor == 0 }

fun nextPrime(num: Int) =
    generateSequence(num + 1) { it + 1 }
        .first(Int::isPrime)