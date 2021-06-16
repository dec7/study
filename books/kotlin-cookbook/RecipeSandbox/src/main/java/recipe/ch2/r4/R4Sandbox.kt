package recipe.ch2.r4

fun main(args: Array<String>) {
    val intVal: Int = 3
    //val longVal: Long = intVal
    val longVar: Long = intVal.toLong()
    println(longVar)

    val longSum = 3L + intVal
    println(longSum)
}