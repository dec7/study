package test

class Wheel {
    lateinit var alignment: String

    fun initAlignment() {
        alignment = "Good"
    }

    fun printAlignment() {
        if (::alignment.isInitialized) println(alignment)
        println(alignment)
    }
}

fun main(args: Array<String>) {
    val wheel = Wheel()
    wheel.printAlignment()
    wheel.initAlignment()
    wheel.printAlignment()
}