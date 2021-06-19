package recipe.ch8

fun main(args: Array<String>) {

}

interface Dialable {
    fun dial(number: String): String
}

class Phone: Dialable {
    override fun dial(number: String): String =
        "Dialing $number..."
}

interface Snappable {
    fun takePicture(): String
}

class Camera: Snappable {
    override fun takePicture(): String =
        "Taking picture..."
}

class SmartPhone(
    private val phone: Dialable = Phone(),
    private val camera: Snappable = Camera()
) : Dialable by phone, Snappable by camera