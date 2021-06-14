import java.io.File
import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"

var playerGold = 10
var playerSilver = 10
val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
var menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split("\n")
//val patronGold = mapOf("Eli" to 10.5, "Mordoc" to 8.0, "Sophie" to 5.5)
//val patronGold = mapOf("Eli".to(10.5), "Mordoc".to(8.0), "Sophie".to(5.5))
//val patronGold = mapOf(Pair("Eli", 10.5), Pair("Mordoc", 8.0), Pair("Sophe", 5.5))
//val patronGold = mutableMapOf("Eli" to 5.0, "Sophie" to 1.0)
val patronGold = mutableMapOf<String, Double>()

fun main(args: Array<String>) {
//    patronGold += "Sophie" to 6.0
//    println(patronGold)

    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }
    println(uniquePatrons)

    var orderCount = 0
    while (orderCount <= 9) {
        placeOrder(
            uniquePatrons.shuffled().first(),
            menuList.shuffled().first()
        )
        orderCount++
    }
    println(patronGold)

    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }
    println(patronGold)
}

private fun printPatron() {
    println(patronList)
    println(patronList[0])
    println(patronList.first())
    println(patronList.last())
    //println(patronList[4])
    println(patronList.getOrElse(4) { "Unknown Patron" })
    println(patronList.getOrNull(4) ?: "Unknown Patron")

    patronList.forEach({ patron -> println("좋은 밤입니다, $patron 님") })
    patronList.forEachIndexed { index, patron ->
        println("좋은 밤입니다, $patron 님 - 당신 #${index + 1} 번째입니다.")
        placeOrder(patron, "shandy,Dragon's Breath,5.91")
    }
}

fun performPurchase(price: Double) {
    displayBalance()
    var totalPurse = playerGold + (playerSilver / 100.0)
    println("지갑 전체 금액: 금화 $totalPurse")
    println("금화 $price 로 술을 구입함")

    val remainingBalance = totalPurse - price
    println("남은 잔액: ${"%.2f".format(remainingBalance)}")

    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
    playerGold = remainingGold
    playerSilver = remainingSilver
    displayBalance()
}

fun displayBalance() {
    println("플레이어의 지갑 잔액: 금화: $playerGold 개, 은화: $playerSilver 개")
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

    performPurchase(price.toDouble())

    val phrase = if (name == "Dragon's Breath") {
        "${patronName}이 감탄한다: ${toDragonSpeak("와, $name 진짜 좋구나.")}"
    } else {
        "${patronName}이 말한다: 감사합니다 $name"
    }
    println(phrase)
}