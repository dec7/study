package com.bignerdranch.nyethack

import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
var menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split("\n")
val patronGold = mutableMapOf("Eli" to 10.5, "Mordoc" to 8.0, "Sophie" to 5.5)
//val com.bignerdranch.nyethack.getPatronGold = mapOf("Eli".to(10.5), "Mordoc".to(8.0), "Sophie".to(5.5))
//val com.bignerdranch.nyethack.getPatronGold = mapOf(Pair("Eli", 10.5), Pair("Mordoc", 8.0), Pair("Sophe", 5.5))
//val com.bignerdranch.nyethack.getPatronGold = mutableMapOf("Eli" to 5.0, "Sophie" to 1.0)
//val com.bignerdranch.nyethack.getPatronGold = mutableMapOf<String, Double>()

fun main(args: Array<String>) {
//    com.bignerdranch.nyethack.getPatronGold += "Sophie" to 6.0
//    println(com.bignerdranch.nyethack.getPatronGold)

    (0..9).forEach {
        val first = patronList.random()
        val last = lastName.random()
        val name = "$first $last"
        uniquePatrons += name
    }
    println(uniquePatrons)

    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }
    println(patronGold)

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(
            uniquePatrons.random(),
            menuList.random()
        )
        orderCount++
    }
    displayPatronBalances()
    println(patronGold)
}

fun displayPatronBalances() {
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}

private fun printPatron() {
    println(patronList)
    println(patronList[0])
    println(patronList.first())
    println(patronList.last())
    //println(com.bignerdranch.nyethack.getPatronList[4])
    println(patronList.getOrElse(4) { "Unknown Patron" })
    println(patronList.getOrNull(4) ?: "Unknown Patron")

    patronList.forEach({ patron -> println("좋은 밤입니다, $patron 님") })
    patronList.forEachIndexed { index, patron ->
        println("좋은 밤입니다, $patron 님 - 당신 #${index + 1} 번째입니다.")
        placeOrder(patron, "shandy,Dragon's Breath,5.91")
    }
}

fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiou]")) {
        when (it.value) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "-"
            "u" -> "|_|"
            else -> it.value
        }
    }

fun placeOrder(patronName: String, menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("${patronName} 은 $tavernMaster 에게 주문한다.")

    val (type, name, price) = menuData.split(",")
    val message = "${patronName}은 금화 $price 로 $name ($type)를 구입한다."
    println(message)

    performPurchase(price.toDouble(), patronName)

    val phrase = if (name == "Dragon's Breath") {
        "${patronName}이 감탄한다: ${toDragonSpeak("와, $name 진짜 좋구나.")}"
    } else {
        "${patronName}이 말한다: 감사합니다 $name"
    }
    println(phrase)
}