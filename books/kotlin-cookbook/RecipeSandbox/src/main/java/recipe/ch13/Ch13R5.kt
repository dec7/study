package recipe.ch13

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        repeat(100) { i ->
            println("job: I'm waiting $i...")
            delay(100L)
        }
    }

    delay(500L)
    println("main: That's enough waiting")
    job.cancel()
    job.join()
    println("main: Done")
}