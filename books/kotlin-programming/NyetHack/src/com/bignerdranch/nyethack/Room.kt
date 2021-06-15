package com.bignerdranch.nyethack

open class Room(val name: String) {
    protected open val dangerLevel = 5
    var monster: Monster? = Goblin()

    fun description() = "Room: $name\n위험수준: $dangerLevel\n" +
            "Creature: ${monster?.description ?: "none."}"

    open fun load() = "아무도 여기에 오지 않았습니다..."
}
