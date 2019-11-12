# Deploy ny version af MatadorGUI

1) Opdater versionsnummer i pom.xml!!!

2) Generer ny JavaDoc (IntelliJ: Tools-> Generate javaDoc). Placér den i Desktop_GUI\doc.

3) Kopier 'settings.xml' til dit lokale maven repository (eks. C:\Users\DITNAVN\.m2)

4) Download og udpak maven: 'https://maven.apache.org/download.html'

5) Sæt PATH variablen til: 'MAVEN_STI/bin'  (eks.: C:\apache-maven-3.5.2\bin)

6) JAVA_HOME SKAL være sat til at pege på en jdk (eks. C:\Program Files\Java\jdk1.8.0_73)

7) Gå til projektets rod (eks. C:\Users\DITNAVN\git\Matador_GUI\Desktop_GUI)

8) Du SKAL have sat dit Navn og email på din profil på GitHub - ellers får du en mystisk fejl (422)

9) Kør: 'mvn clean deploy'

10) Koden compileres automatisk  og og uploades til 'diplomit-dtu/Matador_GUI' på 'repository' branchen med det nye versionsnummer

11) Zip og Kopier Javadoc til googledrive mappen.