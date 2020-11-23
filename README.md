# Matador GUI
Kildekoden til biblioteket der giver let adgang til at vise og manipulere en brugergrænseflade til spillet _Matador_ i Java.  Biblioteket benyttes til CDIO-projekterne på 1. semester.

## Guide til GUI
Guiden til brugen af GUI'en findes i et seperat repository her:  [github.com/diplomit-dtu/MatadorGUIGuide](https://github.com/diplomit-dtu/MatadorGUIGuide).

## Projektstrukturen
Biblioteket udgives som et offentligt _Maven dependency_, der gør det let for de studerende at hente det ind i deres eget projekt.  

Når man bygger og deployer en ny version uploades den til det offentlige _Maven-repository_ der er dedikeret til dette bibliotek. Repostoriet er branchen `repository` i dette GitHub-repository.

Vil man benytte biblioteket, skal man derfor forbinde til dette Maven-repository, udover at inkludere det som en dependency (se guiden).


## Udgiv ny version
Når der er lavet ændringer i biblioteket, skal det offentligøres som en ny version af biblioteket

 1. __Hent projektete ned til IntelliJ__  
    Sørg for at projektet ses som et Maven-projekt at IntelliJ

 2. __Lav dine ændringer i kildekoden__

 2. __Sørg for at Maven er opdateret__  
    Højre klik på pom.xml og tryk på Maven → Reload project

 3. __Opdater versionsnummeret i pom.xml__  
    Følg versioneringskonventionen beskrevet i [Versionspolitik](#versionspolitik)

 4. __Opret GitHub credentials i Maven__  
    Man skal angive sine Github-loginoplysninger til Maven, for at den kan offentliggøre den nye version.

    -   _Find dit "lokale Maven-repository"_  
        Dette er en mappe, der ligger i din _Home_-mappe, der hedder `.m2`. Har man en bruger _Johnny_ i _Windows_  ligger den typisk i `C:/Users/Johnny/.m2`.

     -  _Opret / åben filen `setting.xml` i `.m2`-mappen_
     
     -  _Indsæt dine GitHub Credentials_  
        Kopíer følgende ind i `settings.xml`, og erstart `GITHUB-USERNAME` og `GITHUB-PASSWORD` med dit GitHub brugernavn/password:

        ```xml
        <settings>
        <servers>
            <server>
            <id>github</id>
            <username>GITHUB-USERNAME</username>
            <password>GITHUB-PASSWORD</password>
            </server>
        </servers>
        </settings>
        ```

        __! ADVARSEL !:__  
        _Din kode vil ligge i _clear text_ i denne fil, og har man adgang til din computer kan man bare gå ind og læse det! Sørg derfor for at fjerne koden så snart du har deployet._


 5. __Byg og deploy projektet__  
    
    - _Åben Maven-vinduet i IntelliJ_  
      I toolbaren trykkes `View → Tool Windows → Maven`, og i vinduet der åbnes gå ind under `matadorgui → Lifecycle`  

    - _Byg og deploy projektet_  
      Markér alle felterne under `Lifecycle` og tryk på den grønne pil i vinduet.  

      Projektet bygges og offentliggøres til det dedikerede Maven-repository for biblioteket, sammen med kildekoden og JavaDoc.
      

 6. __Opdatér GUI-guiden__  
    [Guiden til GUI'en](https://github.com/diplomit-dtu/MatadorGUIGuide) skal opdateres seperat. Hvis ikke den nye version, behøver ændringer i guiden, kan man blot lave en ny branch ud fra den nuværende version.


## Versionspolitik
Bibliotekets versionsnummer er beskrevet med 3 niveaue3: `major.minor.patch`. Hvor hvert niveau betyder følgende:

 - __`major`__  
   Der er laves markante ændringer, der ikke behøver at være kompatible med tidligere versioner. Deprecated metoder kan fjernes.

 - __`minor`__  
   Små nye features. Metoder kan deprecates, men må ikke fjernes og skal stadig fungere.

 - __`patch`__  
   Små bug fixes, og dokumentation af kode, der ikke bringer nye features til brugeren.
   
