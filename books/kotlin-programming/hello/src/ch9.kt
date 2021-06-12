import java.io.File

fun main(args: Array<String>) {
    applyFu()
    letFn()
    runFn()
    withFn()
    alsoFn()
    takeIfFn()
    takeUnlessFn()
}

fun takeUnlessFn() {
    val fileContents = File("myfile.txt")
        .takeUnless { it.isHidden }
        ?.readText()
    println(fileContents)
}

fun takeIfFn() {
    val fileContents = File("myfile.txt")
        .takeIf { it.canRead() && it.canWrite() }
        ?.readText()
        .run(::println)
}

fun alsoFn() {
    var fileContents: List<String>
    File("file.txt")
        .also {
            println(it.name)
        }.also {
            fileContents = it.readLines()
        }
    println(fileContents)
}

fun withFn() {
    val nameTooLong = with("Polarcubis, Supreme of NyetHack") {
        length >= 20
    }
    println(nameTooLong)
}

fun runFn() {
    val menuFile = File("menu-file.txt")
    val servesDragonsBreath = menuFile.run {
        readText().contains("Dragon's Breath")
    }

    fun nameIsLong(name: String) = name.length >= 20
    println("Result: ${"Madrigal".run(::nameIsLong)}")
    println("Result: ${"Polarcubis, Supreme of NyetHack".run(::nameIsLong)}")

    fun playerCreateMessage(nameTooLong: Boolean): String {
        return if (nameTooLong) {
            "Name is too long, Please choose another name."
        } else {
            "Welcome, adventurer"
        }
    }

    "Polarcubis, Supreme of NyetHack"
        .run(::nameIsLong)
        .run(::playerCreateMessage)
        .run(::println)
    println(playerCreateMessage(nameIsLong("Polarcubis, Supreme of NyetHack")))
}

fun letFn() {
    val firstItemSquared = listOf(1, 2, 3).last().let {
        it * it
    }
    println("letFn: ${firstItemSquared}")
    println(formatGreeting(null))
    println(formatGreeting("Dec7"))
}

fun formatGreeting(vipGuest: String?): String {
    return vipGuest?.let {
        "오랜만입니다. $it. 테이블이 준비되어 있으니 들어오시죠."
    } ?: "저희 술집에 오신 것을 환경합니다. 곧 자리를 마련해 드리겠습니다."
}

private fun applyFu() {
    /*
    val menuFile = File("menu-file.txt")
    menuFile.setReadable(true)
    menuFile.setWritable(true)
    menuFile.setExecutable(false)
    */
    val menuFile = File("menu-file.txt").apply {
        setReadable(true)
        setWritable(true)
        setExecutable(false)
    }
}