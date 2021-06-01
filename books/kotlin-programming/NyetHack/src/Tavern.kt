const val TAVERN_NAME = "Taernyl's Folly"
fun main(args: Array<String>) {
    placeOrder()
}

fun placeOrder() {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("마드리갈은 $tavernMaster 에게 주문한다.")
}
