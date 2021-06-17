package recipe.ch5.r10

fun main(args: Array<String>) {

    val team = Team("Warriors")
    team.addPlayers(
        Player("Curry"),
        Player("Thompson"),
        Player("Durant"),
        Player("Green"),
        Player("Cousins")
    )

    for (player in team.players) {
        println(player)
    }
}

data class Player(val name: String)

class Team(
    val name: String,
    val players: MutableList<Player> = mutableListOf()
) {
    fun addPlayers(vararg people: Player) =
        players.addAll(people)
}