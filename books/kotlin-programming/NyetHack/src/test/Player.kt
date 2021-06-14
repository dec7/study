package test

class Player {
    val name: String
    private fun firstLetter() = name[0]

    init {
        name = "Madrigal"
        println(firstLetter())
    }
}

fun main(args: Array<String>) {
    val player = Player()
}