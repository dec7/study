package sandbox

class Barrel<T>(var item: T)

fun main(args: Array<String>) {
    var fedoraBarrel: Barrel<Fedora> = Barrel(Fedora("평범한 중절모", 15))
    var lootBarrel: Barrel<Loot> = Barrel(Coin(15))

    lootBarrel = fedoraBarrel
    lootBarrel.item = Coin(15)
    val myFedora: Fedora = fedoraBarrel.item
}