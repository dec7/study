package com.bignerdranch.nyethack

fun main(args: Array<String>) {
    val name = "마드리갈"

    val player1 = Player(_name = "Madrigal", healthPoints = 100, isBlessed = false, isImmortal = false)
    val player2 = Player(_name = "Madrigal", healthPoints = 0, isBlessed = false, isImmortal = false)
    val player = Player("Madrigal")
    println(player.name + "TheBrave")
    player.castFireball()

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

