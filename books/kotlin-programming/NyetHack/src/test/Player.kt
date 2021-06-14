package test

class Player {
    val name: String
    private fun firstLetter() = name[0]

    init {
        println(firstLetter())
        name = "Madrigal"
    }
}

fun main(args: Array<String>) {
    val player = Player()
}