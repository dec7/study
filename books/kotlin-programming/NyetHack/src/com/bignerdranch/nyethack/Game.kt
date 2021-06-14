package com.bignerdranch.nyethack

fun main(args: Array<String>) {
    val abandonedTownSquare = object : TownSquare() {
        override fun load() = "환영받을 것을 예상했지만 여기는 아무도 없군요..."
    }
    println(abandonedTownSquare.load())
    Game.play()
}

object Game {
    private val player = Player("Mardrigal")
    private var currentRoom: Room = TownSquare()
    init {
        println("방문을 환영합니다.")
        player.castFireball()
    }

    fun play() {
        while (true) {
            println(currentRoom.description())
            println(currentRoom.load())

            printPlayerStatus(player)

            print("> 명령을 입력하세요: ")
            println(GameInput(readLine()).processCommand())
        }
    }

    private fun printPlayerStatus(player: Player) {
        println(
            "(Aura: ${player.auraColor()}) " +
                    "(Blessed: ${if (player.isBlessed) "YES" else "NO"})"
        )
        println("${player.name} ${player.formatHealthStatus()}")
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })

        fun processCommand() = when (command.toLowerCase()) {
            else -> commandNotFound()
        }

        private fun commandNotFound() = "접합하지 않는 명령입니다."
    }
}