package recipe.ch6

fun main(args: Array<String>) {
    val resultSolution1 = (100 until 200).map { it * 2 }
        .filter { it % 3 == 0 }
        .first()
    println("$resultSolution1")

    val resultSolution2 = (100 until 200).map { it * 2 }
        .first { it % 3 == 0 }
    println("$resultSolution2")

    val resultSolution3 = (100 until 2_000_000).asSequence()
        .map { println("doubling $it"); it * 2 }
        .filter { println("filtering $it"); it % 3 == 0 }
        .first()
    println("$resultSolution3")
}