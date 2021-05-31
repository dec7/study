fun main(args: Array<String>) {
    var beverage = readLine()
//    beverage = null

    if (beverage != null) {
        beverage = beverage.capitalize()
    } else {
        println("beverage is null")
    }

    println(beverage)
}