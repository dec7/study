package com.bignerdranch.nyethack

abstract class Monster(
    val name: String,
    val description: String,
    override var healthPoints: Int
) : Fightable {

    override fun attack(opponent: Fightable): Int {
        val damageDealt = damageRoll
        opponent.healthPoints -= damageDealt
        return damageDealt
    }
}

class Goblin(
    name: String = "Goblin",
    description: String = "추가게 생긴 고블린",
    healthPoints: Int = 30
) : Monster(name, description, healthPoints) {

    override val diceCount: Int = 2
    override val diceSides: Int = 8
}