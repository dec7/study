package recipe.ch8

import kotlin.properties.Delegates

fun main(args: Array<String>) {

}

var shouldNotBeNull: String by Delegates.notNull()
