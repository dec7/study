package recipe.ch13

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking {
    //withTimeout(1000L) {  ==> TimeoutCancellationException
    val rst = withTimeoutOrNull(1000L) {
        repeat(50) { i ->
            println("job: I'm waiting $i...")
            delay(100L)
        }
    }
    println(rst)
}