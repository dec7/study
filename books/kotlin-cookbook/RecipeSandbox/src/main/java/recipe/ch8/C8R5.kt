package recipe.ch8

import com.google.gson.Gson

fun main(args: Array<String>) {

}

data class Project(val map: MutableMap<String, Any?>) {
    val name: String by map
    var priority: Int by map
    val completed: Boolean by map
}

fun getMapFromJSON() =
    Gson().fromJson<MutableMap<String, Any?>>(
        """{"name": "Learn Kotlin", "priority": 5, "completed": true}
        """.trimIndent(),
        MutableMap::class.java
    )