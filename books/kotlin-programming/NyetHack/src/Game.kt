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
    println(auraColor)

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

    println(name + " " + healthStatus)

    val race = "gnome"
    val faction = when (race) {
        "dwarf" -> "Keepers of the Mines"
        "gnome" -> "Keepers of the Mines"
        "orc" -> "Free People of the Rolling Hills"
        "human" -> "Free People of the Rolling Hills"
        else -> ""
    }
}