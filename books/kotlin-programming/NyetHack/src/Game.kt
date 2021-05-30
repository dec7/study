fun main(args: Array<String>) {
    var name = "마드리갈"
    var healthPoints = 87
    val isBlessed = true
    val isImmortal = false

    val auraVisible = isBlessed && healthPoints > 50 || isImmortal
    if (auraVisible) {
        println("Green")
    } else {
        println("None")
    }

    if (healthPoints == 100) {
        println(name + " 최상의 상태임!")
    } else if (healthPoints >= 90) {
        println(name + " 약간의 찰과상만 있음")
    } else if (healthPoints >= 75) {
        if (isBlessed) {
            println(name + " 경미한 상처가 있지만 빨리 치유됨.")
        } else {
            println(name + " 경미한 상처만 있음")
        }
    } else if (healthPoints >= 15) {
        println(name + " 많이 다친 것 같음")
    } else {
        println(name + " 최악의 상태임!")
    }
}