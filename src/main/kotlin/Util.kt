package dev.lionk

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

val NULL: BigInteger = BigInteger.ZERO
val EINS: BigInteger = BigInteger.ONE

fun String.toSingleLine(): String {
    return this
        .replace("\r\n", "\\n") // Handle Windows line endings
        .replace("\n", "\\n")   // Handle Unix/Linux line endings
        .replace("\r", "\\r")   // Handle legacy Mac line endings
        .replace("\t", "\\t")   // Handle tabs
}
fun String.sanitizeForConsole(): String {
    return this.toSingleLine().map { char ->
        if (char in '\u0020'..'\u007E') {
            char
        } else {
            '?'
        }
    }.joinToString("")
}

fun String.kompakt(maxLength:Int = 32):List<String>{
    val text = this
    val result = mutableListOf<String>()
    var currentIndex = 0
    val length = this.length

    while (currentIndex < length) {
        // If the remaining text fits within the max length, take the rest and finish
        if (currentIndex + maxLength >= length) {
            result.add(text.substring(currentIndex).trim())
            break
        }

        val windowEnd = currentIndex + maxLength
        // Find the last whitespace within the current 32-character window
        val lastSpace = text.lastIndexOf(' ', windowEnd)

        if (lastSpace > currentIndex) {
            // Split at the last whitespace found
            result.add(text.substring(currentIndex, lastSpace).trim())

            // Advance past the whitespace (and any consecutive spaces)
            var nextIndex = lastSpace + 1
            while (nextIndex < length && text[nextIndex].isWhitespace()) {
                nextIndex++
            }
            currentIndex = nextIndex
        } else {
            // No whitespace found in the window (e.g., a long word), hard break at maxLength
            result.add(text.substring(currentIndex, windowEnd))
            currentIndex = windowEnd
        }
    }

    return result.filter { it.isNotEmpty() }
}


val tabs = "                              "

fun List<String>.asString():String{
    return joinToString(separator = "\n$tabs")
}