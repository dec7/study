package recipe.ch3.r3

data class Product(
    val name: String,
    val price: Double,
    val onSale: Boolean = false
)