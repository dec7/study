package com.bignerdranch.nyethack

fun main(args: Array<String>) {
    val name = "마드리갈"

    val player = Player("Madrigal", 89, true, false)
    //player.name = "estragon "
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

