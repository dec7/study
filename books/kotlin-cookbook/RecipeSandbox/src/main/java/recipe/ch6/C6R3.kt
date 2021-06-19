package recipe.ch6

fun main(args: Array<String>) {
    firstNPrime(10_000).also(::println)

    primesLessThan(100).also(::println)
    primesLessThanV2(100).also(::println)
}

fun firstNPrime(count: Int) =
    generateSequence(2, ::nextPrime)
        .take(count)
        .toList()

fun primesLessThan(max: Int): List<Int> =
    generateSequence(2) { n -> if (n < max) nextPrime(n) else null }
        .toList()
        .dropLast(1)

fun primesLessThanV2(max: Int): List<Int> =
    generateSequence(2, ::nextPrime)
        .takeWhile { it < max }
        .toList()