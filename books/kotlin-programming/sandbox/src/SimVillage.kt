fun main(args: Array<String>) {
    val greetingFunction: () -> String = {
        val currentYear = 2019
        "SimVillage 방문을 환영합니다, 촌장님! (copyright $currentYear)"
    }
    println(greetingFunction())
}