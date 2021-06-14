class Player {
    var name = "madrigal"
        get() = field.capitalize()
        set(value) {
            field = value.trim()
        }
//    var healthPoints = 89

    fun castFireball(numFireballs: Int = 2) {
        println("한 덩이의 파이어볼이 나타난다. (x$numFireballs)")
    }

}