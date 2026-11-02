@file:Suppress("NonAsciiCharacters", "LocalVariableName")

package dev.lionk


fun main() {
    val rsa = RSA()
    val schlüsselPaar = rsa.zufälligeSchlüsselGenerieren()

    println("Modul n: ${schlüsselPaar.öffentlicherSchlüssel.n.toString().take(32)}...")
    println("Öffentlicher Schlüssel e: ${schlüsselPaar.öffentlicherSchlüssel.e.toString().take(32)}")
    println("Privater Schlüssel d: ${schlüsselPaar.privaterSchlüssel.d.toString().take(32)}...")

    val geheimText = "Das Leben ist Hart..."
    val nachrichtAlsZahl = geheimText.textZuZahlKonvertieren()
    val verschlüsselteZahl = rsa.zahlVerschlüsseln(nachrichtAlsZahl, schlüsselPaar.öffentlicherSchlüssel)

    println("Verschlüsselte Zahl: ${verschlüsselteZahl.toString().take(32)}...")

    val entschlüsselteZahl = rsa.zahlEntschlüsseln(verschlüsselteZahl, schlüsselPaar.privaterSchlüssel)

    println("Entschlüsselter Text: ${entschlüsselteZahl.zahlZuTextKonvertieren()}")
}