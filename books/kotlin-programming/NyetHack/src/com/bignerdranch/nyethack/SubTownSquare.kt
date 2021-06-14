package com.bignerdranch.nyethack

class SubTownSquare : TownSquare() {
    override val dangerLevel = super.dangerLevel - 1
}