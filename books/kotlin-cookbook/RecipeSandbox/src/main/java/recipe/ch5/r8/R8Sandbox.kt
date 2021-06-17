package recipe.ch5.r8

fun main(args: Array<String>) {
    val list = ('a'..'g').map { it.toString() }.toList()
    val (a, b, c, d, e) = list
    println("$a $b $c $d $e")
}