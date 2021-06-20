package recipe.ch11

fun main(args: Array<String>) {
    printMod3(10)
    printMod3SingleStatement(10)
    printMod3Exhaustive(10)
}

fun printMod3(n: Int) {
    when (n % 3) {
        0 -> println("$n % 3 == 0")
        1 -> println("$n % 3 == 1")
        2 -> println("$n % 3 == 2")
    }
}

fun printMod3SingleStatement(n: Int) = when (n % 3) {
    0 -> println("$n % 3 ==0")
    1 -> println("$n % 3 ==1")
    2 -> println("$n % 3 ==2")
    else -> println("Houston, we have a problem...")
}

val <T> T.exhaustive: T
     get() = this

fun printMod3Exhaustive(n: Int) {
    when (n % 3) {
        0 -> println("$n % 3 == 0")
        1 -> println("$n % 3 == 1")
        2 -> println("$n % 3 == 2")
        else -> println("Houston, we have a problem...")
    }.exhaustive
}