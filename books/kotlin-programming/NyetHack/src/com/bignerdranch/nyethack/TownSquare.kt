package com.bignerdranch.nyethack

open class TownSquare : Room("Town Square") {
    override val dangerLevel = super.dangerLevel - 3
    private var bellSound = "댕댕"

    final override fun load() = "당신의 참여를 주민들이 다 함께 환영합니다.\n${ringBell()}"

    private fun ringBell() = "당신의 도착을 종탑에서 알립니다. $bellSound"
}