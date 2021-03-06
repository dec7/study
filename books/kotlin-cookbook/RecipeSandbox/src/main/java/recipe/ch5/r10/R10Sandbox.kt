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

    for (player in team) {
        println(player)
    }

    println(team.map { it.name }.joinToString())
}

data class Player(val name: String)

class Team(
    val name: String,
    val players: MutableList<Player> = mutableListOf()
) : Iterable<Player> {
    fun addPlayers(vararg people: Player) =
        players.addAll(people)

    override fun iterator(): Iterator<Player> = players.iterator()
}