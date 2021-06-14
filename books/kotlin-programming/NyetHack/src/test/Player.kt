package test

class Player(_name: String, val health: Int) {
    val race = "DWARF"
    var town = "Bavaria"
    val name = _name
    val alignment: String
    private val age = 0

    init {
        println("initializing player")
        alignment = "GOOD"
    }

    constructor(_name: String) : this(_name, 100) {
        town = "The Shire"
    }
}

fun main(args: Array<String>) {
    Player("Madrigal")
}
