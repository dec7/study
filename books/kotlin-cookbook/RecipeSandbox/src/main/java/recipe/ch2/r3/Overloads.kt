package recipe.ch2

import java.text.NumberFormat

fun main(args: Array<String>) {
    println("test")
    var s: String = "hello world"
    var t: String? = null
}

@JvmOverloads
fun addProduct(name: String, price: Double = 0.0, desc: String? = null) =
    "Adding product with $name, ${desc ?: "None"}, and " +
            NumberFormat.getCurrencyInstance().format(price)
