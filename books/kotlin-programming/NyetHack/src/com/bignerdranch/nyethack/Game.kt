package com.bignerdranch.nyethack

fun main(args: Array<String>) {
    val player = Player("Madrigal")
    println(player.name + "TheBrave")
    player.castFireball()

    var currentRoom = Room("Foyer")
    println(currentRoom.description())
    println(currentRoom.load())

    var currentTownSquare = TownSquare()
    println(currentTownSquare.description())
    println(currentTownSquare.load())

    printPlayerStatus(player)

    val numLetters = "Mississippi".count()
    println(numLetters)

    val numLettersOnlyS = "Mississippi".count({ letter -> letter == 's' })
    println(numLettersOnlyS)

    println("Mississippi".count { it == 's' })
}

private fun printPlayerStatus(player: Player) {
    println(
        "(Aura: ${player.auraColor()}) " +
                "(Blessed: ${if (player.isBlessed) "YES" else "NO"})"
    )
    println("${player.name} ${player.formatHealthStatus()}")
}

