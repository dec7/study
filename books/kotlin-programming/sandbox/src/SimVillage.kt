fun main(args: Array<String>) {
    runSimulation()
}

inline fun runSimulation() {
    var greetingFunction = configureGreetingFunction()
    println(greetingFunction("김선달"))
}

fun configureGreetingFunction(): (String) -> String {
    val structureType = "병원"
    var numBuildings = 5
    return { playerName: String ->
        val currentYear = 2019
        numBuildings += 1
        println("$numBuildings 채의 $structureType 이 추가됨")
        "SimVillage 방문을 환영합니다, $playerName! (copyright $currentYear)"
    }
}