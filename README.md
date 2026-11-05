# RSA Verschlüsselung - Programmierung
In diesem Github-Repoitory ist die Vollständige Programmierung der RSA-Verschlüsselung zu finden. 
Zum Ausführen des Codes wird eine installierte Java-Umgebung benötigt. Empfohlen wird die Verwendung einer IDE wie IntelliJ zum Kompilieren des Programmcodes, oder - bei installiertem Java - der Download der Datei RSAExecutable.jar
Folgende Dateien befinden sich in diesem Projekt:

## [RSA](/src/main/kotlin/RSA.kt)
> Beinhaltet die zentralen Algorithmen für die Schlüsselgenerierung, sowie Ver- und Entschlüsselung. 

## [Main](/src/main/kotlin/Main.kt)
> Beinhaltet die beiden Beispiele, eines mit normalem Text und eines mit zu langem Text, um die fehlerhafte Entschlüsselung zu demonstrieren. 

## [Util](/src/main/kotlin/Util.kt)
> Beinhaltet Tools zum Formatieren der Konsolenausgabe und zur Verschönerung des Codes in der Klasse [RSA](/src/main/kotlin/RSA.kt), sowie die Funktionen zum Konvertieren eines Textes in eine Zahl und umgekehrt
