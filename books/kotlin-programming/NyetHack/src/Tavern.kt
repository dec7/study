import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"

var playerGold = 10
var playerSilver = 10
val patronList = mutableListOf("Eli", "Mordoc", "Sophie")

fun main(args: Array<String>) {
    //placeOrder("shandy,Dragon's Breath,5.91")
    printPatron()

    if (patronList.contains("Eli")) {
        println("술집 주인이 말한다: Eli는 안쪽 방에서 카드하고 있어요.")
    } else {
        println("술집 주인이 말한다: Eli는 여기 없어요.")
    }

    if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
        println("술집 주인이 말한다: 네, 모두 있어요.")
    } else {
        println("술집 주인이 말한다: 아니오, 나간 사람도 있습니다.")
    }
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