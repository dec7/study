fun main(args: Array<String>) {
    val adversary = Jhava()
    println(adversary.utterGreeting())

    val friendshipLevel = adversary.determineFriendshipLevel()
    println(friendshipLevel?.toLowerCase() ?: "모르겠음")

    val adversaryHitPoints: Int = adversary.hitPoints
    println(adversaryHitPoints.dec())
    println(adversaryHitPoints.javaClass)
}