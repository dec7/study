package sandbox

class LootBox<T>(item: T) {
    private var loot: T = item
}

class Fedora(val name: String, val value: Int)

class Coin(val value: Int)

fun main(args: Array<String>) {
    val lootBoxOne: LootBox<Fedora> = LootBox(Fedora("평범한 중절모", 15))
    val lootBoxTwo: LootBox<Coin> = LootBox(Coin(15))
}