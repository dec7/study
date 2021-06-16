package recipe.ch2.r5

fun main(args: Array<String>) {
    println(42.toString(2))
    (Character.MIN_RADIX..Character.MAX_RADIX).forEach { redix ->
        println("$redix: ${42.toString(redix)}")
    }
}