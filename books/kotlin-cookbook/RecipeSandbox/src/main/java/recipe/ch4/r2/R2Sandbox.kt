package recipe.ch4.r2

fun main(args: Array<String>) {

}

fun sumReduce(vararg nums: Int) =
    nums.reduce { acc, i -> acc + i }

fun sumReduceDoubles(vararg nums: Int) =
    nums.reduce { acc, i -> acc + 2 * i }