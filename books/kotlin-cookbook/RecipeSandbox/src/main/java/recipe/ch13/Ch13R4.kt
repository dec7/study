package recipe.ch13

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

fun main() = runBlocking {
    val dispatcher = Executors.newFixedThreadPool(10)
        .asCoroutineDispatcher()


    (1..12).forEach {
        withContext(dispatcher) {
            delay(100L)
            println("$it - ${Thread.currentThread().name}")
        }
    }

    dispatcher.close()
}