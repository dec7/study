package com.bignerdranch.nyethack

open class Room(val name: String) {
    protected open val dangerLevel = 5

    fun description() = "Room: $name\n위험수준: $dangerLevel"

    open fun load() = "아무도 여기에 오지 않았습니다..."
}
