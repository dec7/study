const val TAVERN_NAME = "Taernyl's Folly"
fun main(args: Array<String>) {
    placeOrder("shandy,Dragon's Breath,5.91")
}

fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("마드리갈은 $tavernMaster 에게 주문한다.")

    val (type, name, price) = menuData.split(",")
    val message = "마드리갈은 금화 $price 로 $name ($type)를 구입한다."
    println(message)
}
