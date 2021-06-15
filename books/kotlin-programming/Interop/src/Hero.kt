@file:JvmName("Hero")

import java.io.IOException
import kotlin.jvm.Throws

fun main(args: Array<String>) {
    val adversary = Jhava()
    println(adversary.utterGreeting())

    val friendshipLevel = adversary.determineFriendshipLevel()
    println(friendshipLevel?.toLowerCase() ?: "모르겠음")

    val adversaryHitPoints: Int = adversary.hitPoints
    println(adversaryHitPoints.dec())
    println(adversaryHitPoints.javaClass)

    adversary.greeting = "안녕하세요"
    println(adversary.utterGreeting())

    adversary.offerFood()

    val spellbook = Spellbook()
    spellbook.spells

    try {
        adversary.extendHandInFriendship()
    } catch (e: Exception) {
        println("잘가라, 못된 괴물아")
    }
}

fun makeProclamation() = "안녕, 괴물아!!"

@JvmOverloads
fun handOverFood(leftHand: String = "딸기", rightHand: String = "고기") {
    println("맛있는 $leftHand 와 $rightHand 를 넘겨주었습니다.")
}

@Throws(IOException::class)
fun acceptApology() {
    throw IOException()
}

class Spellbook {
    @JvmField
    val spells = listOf("Magic Ms. L", "Lay on Hans")

    companion object {
        @JvmField
        val MAX_SPELL_COUNT = 10

        @JvmStatic
        fun getSpellbookGreeting() = println("나는 위대한 그리모어다!")
    }
}