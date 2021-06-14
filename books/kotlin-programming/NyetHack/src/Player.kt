class Player {
    val name = "madrigal"
        get() = field.capitalize()
//    var healthPoints = 89

    fun castFireball(numFireballs: Int = 2) {
        println("한 덩이의 파이어볼이 나타난다. (x$numFireballs)")
    }

}