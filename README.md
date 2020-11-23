# Matador GUI
Kildekoden til biblioteket der giver let adgang til at vise og manipulere en brugergrænseflade til spillet _Matador_ i Java.  Biblioteket benyttes til CDIO-projekterne på 1. semester.

## Guide til GUI
Guiden til brugen af GUI'en findes i et seperat repository her:  [github.com/diplomit-dtu/MatadorGUIGuide](https://github.com/diplomit-dtu/MatadorGUIGuide).

## Projektstrukturen
Biblioteket udgives som et offentligt _Maven dependency_, der gør det let for de studerende at hente det ind i deres eget projekt.  

Når man bygger og deployer en ny version uploades den til det offentlige _Maven-repository_ der er dedikeret til dette bibliotek. Repostoriet er branchen `repository` i dette GitHub-repository.

Vil man benytte biblioteket, skal man derfor forbinde til dette Maven-repository, udover at inkludere det som en dependency (se guiden).


## Udgiv ny version
Når der er lavet ændringer i biblioteket, skal det offentligøres som en ny version på følgende måde:

 1. __Hent projektet ned til IntelliJ__  
    Sørg for at projektet ses som et Maven-projekt af IntelliJ

 2. __Lav dine ændringer i kildekoden__

 3. __Notér ændringer for ny version i [CHANGELOG.md](https://github.com/diplomit-dtu/Matador_GUI/blob/master/CHANGELOG.md)__ 

 4. __Sørg for at Maven-projektet er opdateret__  
    Højre klik på `pom.xml` og tryk på `Maven → Reload project`

 5. __Opdatér versionsnummeret i pom.xml__  
    Følg versioneringskonventionen beskrevet i [Versionspolitik](#versionspolitik)

 6. __Opret en _Personal access token_ til GitHub__
    For at Maven kan pushe til til repoet, skal bruge nogle credentials til din GitHub profil. I stedet for at _password_ skal der benyttes en _token_.

    - Gå ind under [github.com/settings/tokens/](https://github.com/settings/tokens)
    
    - Tryk på `Generate new token`

    - Angiv note så du kan genkende den i fremtiden

    - Kryds følgende _scopes_ af for tokenen:
      - `repo`
      - `notifications`
      - `user`

    - Tryk på `Generate Token`

    - Kopiér den lange tekst i den grønne boks (kunne se ud som `3f403ba70cad9613242d1d5582cf2vb5bbea2es87`). Dette er din _token_ vi skal bruge.  
    _Bemærk:_ Du kan ikke fremskaffe den igen, hvis du smider den væk. I så fald opretter du bare en ny, og sletter den gamle.

 7. __Angiv GitHub credentials til Maven__  
    Du skal angive sit brugernavn og din nye _token_ til Maven, for at den kan offentliggøre den nye version til repoet

     -  _Åben din `setting.xml`-fil til Maven_  
        Dette gør du ved at højreklikke på `pom.xml` og tryk på `Maven → Open 'settings.xml'`
     
     -  _Indsæt dine GitHub Credentials_  
        Kopíer følgende ind i `settings.xml`, og erstart `GITHUB-USERNAME` og `GITHUB-PASSWORD` med dit GitHub brugernavn/password:

        ```xml
         <settings>
            <servers>
               <server>
                  <id>github</id>
                  <username>DIT_GITHUB_BRUGERNAVN</username>
                  <password>DIN_ACCESS_TOKEN</password>
               </server>
            </servers>
         </settings>
        ```

        __!! ADVARSEL:__  
        _Din token til GitHub vil ligge i _clear text_ i denne fil. Har man denne token kan den bruges til at tilgå nogle rettigheder i i dine repositories, så det anbefales at fjerne din token efter du har deployet_


 8. __Byg og deploy projektet__  
    
    - _Åben Maven-vinduet i IntelliJ_  
      I toolbaren trykkes `View → Tool Windows → Maven`, og i vinduet der åbnes gå ind under `matadorgui → Lifecycle`  

    - _Byg og deploy projektet_  
      Markér alle felterne under `Lifecycle` og tryk på den grønne pil i vinduet.  

      Projektet bygges og offentliggøres til det dedikerede Maven-repository for biblioteket, sammen med kildekoden og JavaDoc.
      

 9. __Opdatér GUI-guiden__  
    [Guiden til GUI'en](https://github.com/diplomit-dtu/MatadorGUIGuide) skal opdateres seperat. Hvis ikke den nye version, behøver ændringer i guiden, kan man blot lave en ny branch ud fra den nuværende version.


## Versionspolitik
Bibliotekets versionsnummer er beskrevet med 3 niveaue3: `major.minor.patch`. Hvor hvert niveau betyder følgende:

 - __`major`__  
   Der er laves markante ændringer, der ikke behøver at være kompatible med tidligere versioner. Deprecated metoder kan fjernes.

 - __`minor`__  
   Små nye features. Metoder kan deprecates, men må ikke fjernes og skal stadig fungere.

 - __`patch`__  
   Små bug fixes, og dokumentation af kode, der ikke bringer nye features til brugeren.
   
