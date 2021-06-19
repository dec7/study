package recipe.ch9

import java.time.LocalDate

fun main(args: Array<String>) {

}

data class Book(
    val isbn: String,
    val title: String,
    val author: String,
    val published: LocalDate
)