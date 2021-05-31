fun main(args: Array<String>) {
    var beverage = readLine()
//    beverage = null

    beverage?.let {
        beverage = it.capitalize()
    } ?: println ("beverage is null")

//    println(beverage)
    val beverageServed: String = beverage ?: "맥주"
    println(beverageServed)
}