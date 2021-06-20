package recipe.ch11

fun main(args: Array<String>) {

}

fun isPal(string: String): Boolean {
    val testString = string.toLowerCase().replace("""[\W+]""".toRegex(), "")
    return testString == testString.reversed()
}

fun String.isPalindrome() =
    this.toLowerCase().replace("""[\W+]""".toRegex(), "")
        .let { it == it.reversed() }

