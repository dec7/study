package recipe.ch8

fun main(args: Array<String>) {
    val ultimateAnswer: Int by lazy {
        println("computing the answer")
        42
    }

    println("----")
    println(ultimateAnswer)
    println(ultimateAnswer)
}
