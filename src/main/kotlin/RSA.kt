@file:Suppress("NonAsciiCharacters", "ClassName", "LocalVariableName", "FunctionName", "PropertyName")

package dev.lionk

import java.math.BigInteger
import java.security.SecureRandom

val zufallsGenerator = SecureRandom()

data class EuklidischerAlgorithmusErgebnis(
    val ggt: BigInteger,
    val s: BigInteger,
    val t: BigInteger,
)

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
        val d = erwEuklAlgorithmus(e, phi).s
        return SchlüsselPaar(
            ÖffentlicherSchlüssel(n, e),
            PrivaterSchlüssel(n, d)
        )
    }

    /** Die Funktion modPow führt die Rechnung klartextZahl hoch e modulo n aus */
    fun zahlVerschlüsseln(
        klartextZahl: BigInteger,
        öffentlicherSchlüssel: ÖffentlicherSchlüssel
    ) : BigInteger{
        return klartextZahl.modPow(
            öffentlicherSchlüssel.e,
            öffentlicherSchlüssel.n
        )
    }
    /** Die Funktion modPow führt die Rechnung verschlüsselteZahl hoch d modulo n aus */
    fun zahlEntschlüsseln(
        verschlüsselteZahl: BigInteger,
        privaterSchlüssel: PrivaterSchlüssel
    ) : BigInteger{
        return verschlüsselteZahl.modPow(
            privaterSchlüssel.d,
            privaterSchlüssel.n
        )
    }

    fun erwEuklAlgorithmus(
        a: BigInteger,
        b: BigInteger
    ) : EuklidischerAlgorithmusErgebnis {
        if(b == NULL) return EuklidischerAlgorithmusErgebnis(a, EINS, NULL)

        // Das %-Zeichen entspricht einer Division mit Rest
        val rest = a % b
        val ergebnisDarunter = erwEuklAlgorithmus(b, rest)

        return EuklidischerAlgorithmusErgebnis(
            ggt = ergebnisDarunter.ggt,
            s = ergebnisDarunter.t,
            t = ergebnisDarunter.s - (a / b) * ergebnisDarunter.t
        )
    }
}