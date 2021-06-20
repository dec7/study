package recipe.ch11

import java.io.IOException

fun main(args: Array<String>) {

}

@Throws(IOException::class)
fun houstonWeHaveAProblem() {
    throw IOException("File or resource not found")
}