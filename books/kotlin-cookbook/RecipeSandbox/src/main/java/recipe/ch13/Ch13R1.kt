package recipe.ch13

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    println("Before creating coroutine")
    runBlocking {
        println("Before launch")
        launch {
            print("Hello, ")
            delay(200L)
            println("World!")
        }
        println("After launch")
    }
    println("After coroutine is finished")

}