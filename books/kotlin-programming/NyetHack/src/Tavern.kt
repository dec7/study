fun main(args: Array<String>) {
    var beverage = readLine()
//    beverage = null

    if (beverage != null) {
        beverage = beverage.capitalize()
    } else {
        println("beverage is null")
    }

//    println(beverage)
    val beverageServed: String = beverage ?: "맥주"
    println(beverageServed)
}