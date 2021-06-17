package recipe.ch3.r6

class LateInitDemo {
    lateinit var name: String

    fun initialzeName() {
        println("Before assignment: ${::name.isInitialized}")
        name = "World"
        println("After assignment: ${::name.isInitialized}")
    }
}