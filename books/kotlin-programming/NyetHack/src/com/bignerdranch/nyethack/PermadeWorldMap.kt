package com.bignerdranch.nyethack

import java.io.File

class PermadeWorldMap {

    companion object {
        private const val MAPS_FILEPATH = "nyethack.maps"

        fun load() = File(MAPS_FILEPATH).readBytes()
    }
}

fun main(args: Array<String>) {
    PermadeWorldMap.load()
}