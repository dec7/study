package recipe.ch5.r5

data class Product(
    val name: String,
    var price: Double,
    var onSale: Boolean = false
)

fun namesOfProductsOnSale(products: List<Product>) =
    products.filter { it.onSale }
        .map { it.name }
        .joinToString(separator = ", ")

fun onSaleProducts_ifEmptyCollection(products: List<Product>) =
    products.filter { it.onSale }
        .map { it.name }
        .ifEmpty { listOf("none") }
        .joinToString(separator = ", ")

fun onSaleProducts_ifEmptyString(products: List<Product>) =
    products.filter { it.onSale }
        .map { it.name }
        .joinToString(separator = ", ")
        .ifEmpty { "none" }