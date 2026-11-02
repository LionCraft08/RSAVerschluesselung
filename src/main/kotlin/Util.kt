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
operator fun Int.div(bigInt: BigInteger) : ModInvStorage = ModInvStorage(-this, bigInt) //BigDecimal.valueOf(this.toLong()).divide(bigInt.toBigDecimal())
operator fun BigDecimal.rem(bigInt: BigInteger) : BigInteger = this.remainder(bigInt.toBigDecimal()).toBigInteger()
operator fun ModInvStorage.rem(bigInt: BigInteger) : BigInteger = this.base.modPow(this.exponent.toBigInteger(), bigInt)

data class ModInvStorage(val exponent: Int, val base: BigInteger)