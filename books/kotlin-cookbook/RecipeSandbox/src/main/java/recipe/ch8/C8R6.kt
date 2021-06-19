package recipe.ch8

import kotlin.reflect.KProperty

fun main(args: Array<String>) {
    val e = DelegateExample()
    println(e.p)
    e.p = "NEW"
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String =
        "$thisRef, thank you for delegating '${property.name}' to me!"

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef")
    }
}

class DelegateExample {
    var p: String by Delegate()
}