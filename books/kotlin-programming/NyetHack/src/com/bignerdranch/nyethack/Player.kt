package com.bignerdranch.nyethack

class Player(
    _name: String,
    var healthPoints: Int,
    val isBlessed: Boolean,
    private val isImmortal: Boolean
) {
    var name = _name
        get() = field.capitalize()
        private set(value) {
            field = value.trim()
        }

    constructor(name: String) : this(
        name,
        healthPoints = 100,
        isBlessed = true,
        isImmortal = false) {
        if (name.toLowerCase() == "kar") healthPoints = 40
    }

    fun castFireball(numFireballs: Int = 2) {
        println("한 덩이의 파이어볼이 나타난다. (x$numFireballs)")
    }

    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        val auraColor = if (auraVisible) "GREEN" else "NONE"
        return auraColor
    }

    fun formatHealthStatus() =
        when (healthPoints) {
            100 -> "최상의 상태임!"
            in 90..99 -> "약간의 찰과상만 있음"
            in 75..89 -> if (isBlessed) {
                "경미한 상처가 있지만 빨리 치유됨."
            } else {
                " 경미한 상처만 있음"
            }
            in 15..74 -> "많이 다친 것 같음"
            else -> "최악의 상태임!"
        }
}