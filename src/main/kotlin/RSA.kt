@file:Suppress("NonAsciiCharacters", "ClassName", "LocalVariableName", "FunctionName", "PropertyName")

package dev.lionk

import java.math.BigInteger
import java.security.SecureRandom

val zufallsGenerator = SecureRandom()

data class ÖffentlicherSchlüssel(
    val n: BigInteger,
    val e: BigInteger
)
data class PrivaterSchlüssel(
    val n: BigInteger,
    val d: BigInteger
)
data class SchlüsselPaar(
    val öffentlicherSchlüssel: ÖffentlicherSchlüssel,
    val privaterSchlüssel: PrivaterSchlüssel
)

class RSA {
    fun zufälligeSchlüsselGenerieren(
        schlüsselLänge: Int = 1024
    ) : SchlüsselPaar{

        val p = BigInteger.probablePrime(
            schlüsselLänge / 2,
            zufallsGenerator
        )
        val q = BigInteger.probablePrime(
            schlüsselLänge / 2,
            zufallsGenerator
        )
        val n = p * q
        val phi = (p - 1) * (q - 1)
        val e = BigInteger("65537")
        val d = (1 / e) % phi
        return SchlüsselPaar(
            ÖffentlicherSchlüssel(n, e),
            PrivaterSchlüssel(n, d)
        )
    }

    /** Die Funktion modPow führt die Rechnung klartextZahl hoch e modulo n aus */
    fun zahlVerschlüsseln(klartextZahl: BigInteger, öffentlicherSchlüssel: ÖffentlicherSchlüssel) : BigInteger{
        return klartextZahl.modPow(öffentlicherSchlüssel.e, öffentlicherSchlüssel.n)
    }
    /** Die Funktion modPow führt die Rechnung verschlüsselteZahl hoch d modulo n aus */
    fun zahlEntschlüsseln(verschlüsselteZahl: BigInteger, privaterSchlüssel: PrivaterSchlüssel) : BigInteger{
        return verschlüsselteZahl.modPow(privaterSchlüssel.d, privaterSchlüssel.n)
    }
}