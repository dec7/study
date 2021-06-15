@file:JvmName("Hero")
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
}

fun makeProclamation() = "안녕, 괴물아!!"

@JvmOverloads
fun handOverFood(leftHand: String = "딸기", rightHand: String = "고기") {
    println("맛있는 $leftHand 와 $rightHand 를 넘겨주었습니다.")
}

class Spellbook {
    @JvmField
    val spells = listOf("Magic Ms. L", "Lay on Hans")

    companion object {
        val MAX_SPELL_COUNT = 10
    }
}