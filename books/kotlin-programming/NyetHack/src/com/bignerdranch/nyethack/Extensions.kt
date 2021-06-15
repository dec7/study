package com.bignerdranch.nyethack

fun String.addEnthusiasm(amount: Int = 1) = this + "!".repeat(amount)

fun Any.easyPrint() = println(this)

fun main(args: Array<String>) {
    "마드리갈이 그 건물에서 나왔습니다".addEnthusiasm().easyPrint()
    42.easyPrint()
}