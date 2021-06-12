import java.io.File

fun main(args: Array<String>) {
    applyFu()
    letFn()

}

fun letFn() {
    val firstItemSquared = listOf(1,2,3).last().let {
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