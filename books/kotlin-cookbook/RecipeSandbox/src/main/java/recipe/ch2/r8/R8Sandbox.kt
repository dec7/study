package recipe.ch2.r8

import java.awt.Color

fun main(args: Array<String>) {

}

fun intsFromColor(color: Color): List<Int> {
    val rgb = color.rgb
    val alpha = rgb shr 24 and 0xff
    val red = rgb shr 16 and 0xff
    val green = rgb shr 8 and 0xff
    val blue = rgb and 0xff
    return listOf(alpha, red, green, blue)
}

fun colorFromInts(alpha: Int, red: Int, green: Int, blue: Int) =
    (alpha and 0xff shl 24) or
            (red and 0xff shl 16) or
            (green and 0xff shl 8) or
            (blue and 0xff)