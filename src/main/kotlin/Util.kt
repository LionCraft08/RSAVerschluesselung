package dev.lionk

import java.math.BigDecimal
import java.math.BigInteger

fun String.textZuZahlKonvertieren(): BigInteger{
    return BigInteger(1, this.toByteArray(Charsets.UTF_8))
}

fun BigInteger.zahlZuTextKonvertieren(): String {
    var bytes = this.toByteArray()
    if (bytes.isNotEmpty() && bytes[0] == 0.toByte()) {
        bytes = bytes.copyOfRange(1, bytes.size)
    }
    return String(bytes, Charsets.UTF_8)
}

operator fun BigInteger.minus(int:Int): BigInteger = this.minus(BigInteger.valueOf(int.toLong()))
operator fun BigInteger.plus(int:Int): BigInteger = this.add(BigInteger.valueOf(int.toLong()))

val NULL = BigInteger.ZERO
val EINS = BigInteger.ONE
val ZWEI = BigInteger.TWO