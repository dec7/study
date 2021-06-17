package recipe.ch5.r4

fun main(args: Array<String>) {
    val keys = 'a'..'f'
    val map = keys.associate { it to it.toString().repeat(5).capitalize() }
    println(map)

    val map1 = keys.associateWith { it.toString().repeat(5).capitalize() }
    println(map1)
}