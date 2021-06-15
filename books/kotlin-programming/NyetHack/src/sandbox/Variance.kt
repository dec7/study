package sandbox

class Barrel<in T>(item: T)

inline fun <reified T> randomOrBackupLoot(backupLoot: () -> T): T {
    val items = listOf(Coin(14), Fedora("고풍스런 중절모", 150))
    val randomLoot: Loot = items.shuffled().first()
    return if (randomLoot is T) {
        randomLoot
    } else {
        backupLoot()
    }
}

fun main(args: Array<String>) {
    var fedoraBarrel: Barrel<Fedora> = Barrel(Fedora("평범한 중절모", 15))
    var lootBarrel: Barrel<Loot> = Barrel(Coin(15))

    fedoraBarrel = lootBarrel

    val list = listOf(1,2)
    if (list is List) {
        println ("List<String> 타입니다.")
    }

    randomOrBackupLoot {
        Fedora("대체용 중절모", 15)
    }.run {
        println(name)
    }
}