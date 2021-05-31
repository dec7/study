fun main(args: Array<String>) {
    val name = "마드리갈"
    val healthPoints = 87
    val isBlessed = true
    val isImmortal = false

    val auraColor = if (isBlessed && healthPoints > 50 || isImmortal) {
        "Green"
    } else {
        "None"
    }
    println(
        "(Aura: $auraColor) " +
                "(Blessed: ${if (isBlessed) "YES" else "NO"})"
    )

    val healthStatus = formatHealthStatus(healthPoints, isBlessed)

    println("$name $healthStatus")

    val race = "gnome"
    val faction = when (race) {
        "dwarf" -> "Keepers of the Mines"
        "gnome" -> "Keepers of the Mines"
        "orc" -> "Free People of the Rolling Hills"
        "human" -> "Free People of the Rolling Hills"
        else -> ""
    }

    val karma = (Math.pow(Math.random(), (110 - healthPoints) / 100.0) * 20).toInt()
    val karmaColor = when (karma) {
        in 0..5 -> "red"
        in 6..10 -> "orance"
        in 11..15 -> "purple"
        in 16..20 -> "green"
        else -> ""
    }
    println("$karma -> $karmaColor")
}

private fun formatHealthStatus(healthPoints: Int, isBlessed: Boolean): String {
    val healthStatus = when (healthPoints) {
        100 -> "최상의 상태임!"
        in 90..99 -> "약간의 찰과상만 있음"
        in 75..89 -> if (isBlessed) {
            "경미한 상처가 있지만 빨리 치유됨."
        } else {
            " 경미한 상처만 있음"
        }
        in 15..74 -> "많이 다친 것 같음"
        else -> "최악의 상태임!"
    }
    return healthStatus
}