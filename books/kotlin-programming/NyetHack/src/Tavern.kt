const val TAVERN_NAME = "Taernyl's Folly"
fun main(args: Array<String>) {
    placeOrder("shandy,Dragon's Breath,5.91")
}

fun placeOrder(menuData: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("마드리갈은 $tavernMaster 에게 주문한다.")

    val data = menuData.split(",")
    val type = data[0]
    val name = data[1]
    val price = data[2]
    val message = "마드리갈은 금화 $price 로 $name ($type)를 구입한다."
    println(message)
}
