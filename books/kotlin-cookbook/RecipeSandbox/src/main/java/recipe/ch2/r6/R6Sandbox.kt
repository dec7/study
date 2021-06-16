package recipe.ch2.r6

import kotlin.math.pow

fun main(args: Array<String>) {

}

fun Int.pow(x: Int): Int = toDouble().pow(x).toInt()
fun Long.pow(x: Int): Long = toDouble().pow(x).toLong()