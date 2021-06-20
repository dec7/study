package recipe.ch11

import kotlin.concurrent.thread
import kotlin.random.Random

fun main(args: Array<String>) {

    /*(0..5).forEach { n ->
        val sleepTime = Random.nextLong(0, 1000L)
        thread {
            Thread.sleep(sleepTime)
            println("${Thread.currentThread().name} for $n after ${sleepTime}ms")
        }
    }*/

    (0..5).forEach { n ->
        val sleepTime = Random.nextLong(0, 1000L)
        thread(isDaemon = true) {
            Thread.sleep(sleepTime)
            println("${Thread.currentThread().name} for $n after ${sleepTime}ms (deamon)")
        }.join()
    }
}

