@file:Suppress("NonAsciiCharacters", "LocalVariableName", "FunctionName")

package dev.lionk

const val MAX_NUMBERS = 20

fun main() {
    val rsa = RSA()
    val schlüsselPaar = rsa.zufälligeSchlüsselGenerieren()
    println("-----------------------------------------------------------")
    println("Modul n:                   ${schlüsselPaar.öffentlicherSchlüssel.n.toString().take(MAX_NUMBERS)}...")
    println("Öffentlicher Schlüssel e:  ${schlüsselPaar.öffentlicherSchlüssel.e.toString().take(MAX_NUMBERS)}")
    println("Privater Schlüssel d:      ${schlüsselPaar.privaterSchlüssel.d.toString().take(MAX_NUMBERS)}...")
    println("-----------------------------------------------------------")
    verUndEntschlüsselungDurchführen("Das Leben ist Hart...", schlüsselPaar, rsa)
    println("-----------------------------------------------------------")
    verUndEntschlüsselungDurchführen("Dies ist ein sehr langer Text der insgesamt bei einer Konvertierung in eine Zahl eine Länge von über 1024 Bit erreicht und somit bei der Verschlüsselung Probleme verursacht.", schlüsselPaar, rsa)
    println("-----------------------------------------------------------")
}

fun verUndEntschlüsselungDurchführen(
    text: String,
    schlüsselPaar: SchlüsselPaar,
    rsa: RSA
){

    val nachrichtAlsZahl = text.textZuZahlKonvertieren()
    println("Klartext:                  ${text.kompakt().asString()}")
    println("Klartext als Zahl:         ${nachrichtAlsZahl.toString().take(MAX_NUMBERS)}...")
    println("Bit-Länge der Zahl:        ${nachrichtAlsZahl.bitLength()}")
    val verschlüsselteZahl = rsa.zahlVerschlüsseln(nachrichtAlsZahl, schlüsselPaar.öffentlicherSchlüssel)
    println("Verschlüsselte Zahl:       ${verschlüsselteZahl.toString().take(MAX_NUMBERS)}...")
    println("Verschlüsselter Text:      ${verschlüsselteZahl.zahlZuTextKonvertieren().sanitizeForConsole().take(MAX_NUMBERS)}...")
    val entschlüsselteZahl = rsa.zahlEntschlüsseln(verschlüsselteZahl, schlüsselPaar.privaterSchlüssel)
    println("Entschlüsselte Zahl:       ${entschlüsselteZahl.toString().take(MAX_NUMBERS)}...")
    println("Entschlüsselter Text:      ${entschlüsselteZahl.zahlZuTextKonvertieren().sanitizeForConsole().kompakt().asString()}")
}

fun println(text:String){
    kotlin.io.println("   $text")
}