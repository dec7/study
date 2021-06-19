package recipe.ch8

import kotlin.properties.Delegates

fun main(args: Array<String>) {

}

var watched: Int by Delegates.observable(1) { prop, old, new ->
    println("${prop.name} changed from $old to $new")
}

var checked: Int by Delegates.vetoable(0) { prop, old, new ->
    println("Trying to change ${prop.name} from $old to $new")
    new >= 0
}