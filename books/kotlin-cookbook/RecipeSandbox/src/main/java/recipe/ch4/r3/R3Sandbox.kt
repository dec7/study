package recipe.ch4.r3

import java.math.BigInteger

fun recursiveFactorial(n: Long): BigInteger =
    when (n) {
        0L, 1L -> BigInteger.ONE
        else -> BigInteger.valueOf(n) * recursiveFactorial(n - 1)
    }

@JvmOverloads
tailrec fun factorial(n: Long, acc: BigInteger = BigInteger.ONE): BigInteger =
    when (n) {
        0L -> BigInteger.ONE
        1L -> acc
        else -> factorial(n - 1, acc * BigInteger.valueOf(n))
    }