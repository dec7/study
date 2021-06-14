package test

class Player(_name: String) {
    val name: String = _name
    val playerName: String = initPlayerName()

    private fun initPlayerName() = name
}

fun main(args: Array<String>) {
    println(Player("Madrigal").playerName)
}