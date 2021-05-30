const val MAX_EXPERIENCE: Int = 5000

fun main(args: Array<String>) {
    val playerName = "에스트라곤"
    var experiencePoints = 50
    experiencePoints += 5
    var hasSteed = false
    var drink = listOf<String>("벌꿀주","포도주","라크루아")
    println(experiencePoints)
    println(playerName)

    if (!hasSteed) {
        println("The player does not have steed yet.")
    }
    println(playerName.reversed())
}