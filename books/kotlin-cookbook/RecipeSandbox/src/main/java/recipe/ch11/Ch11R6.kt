package recipe.ch11

import com.google.gson.Gson
import java.net.URL

fun main(args: Array<String>) {
}

data class AstroResult(
    val message: String,
    val number: Number,
    val people: List<Assignment>
)

data class Assignment(
    val craft: String,
    val name: String
)

class AstroRequest {
    companion object {
        private const val ASTRO_URL = "http://api.open-notify.org/astros.json"
    }

    operator fun invoke(): AstroResult {
        val responseString = URL(ASTRO_URL).readText()
        return Gson().fromJson(responseString, AstroResult::class.java)
    }
}
