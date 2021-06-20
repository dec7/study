package recipe.ch13

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

fun main() = runBlocking {
    Executors.newFixedThreadPool(10)
        .asCoroutineDispatcher().use {
            (1..12).forEach { index ->
                withContext(it) {
                    delay(100L)
                    println("$index - ${Thread.currentThread().name}")
                }
            }
        }
}