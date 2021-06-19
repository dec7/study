package recipe.ch7

import com.google.gson.Gson
import java.net.URL

fun main(args: Array<String>) {
    val numbers = mutableListOf("one", "two", "three", "four", "five")
    val resultList = numbers.map { it.length }.filter { it > 3 }
    println(resultList)

    numbers.map { it.length }.filter { it > 3 }.let {
        println(it)
    }

    numbers.map { it.length }.filter { it > 3 }.let(::println)

    printName()
}

data class AstroResult(
    val message: String,
    val number: Number,
    val people: List<Assignment>
)

data class Assignment(
    val craft: String,
    val name: String
)

fun printName() =
    Gson().fromJson(
        URL("http://api.open-notify.org/astros.json").readText(),
        AstroResult::class.java
    ).people.map { it.name }.let(::println)