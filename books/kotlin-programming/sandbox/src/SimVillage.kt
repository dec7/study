fun main(args: Array<String>) {
    val greetingFunction: (String, Int) -> String = { playerName, numBildings ->
        val currentYear = 2019
        println("$numBildings 채의 건물이 추가됨.")
        "SimVillage 방문을 환영합니다, $playerName 님! (copyright $currentYear)"
    }
    println(greetingFunction("김선달", 2))
}