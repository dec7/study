package recipe.ch3.r5

data class Point(val x: Int, val y: Int)

operator fun Point.unaryMinus() = Point(-x, -y)