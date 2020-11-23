# Changelog
Oversigt over relevante ændringer mellem hver version af biblioteket.

### 3.2.0

 - __Input__:
    - Man kan ikke længere trykke _Enter_ for at forbigå begrænset input (integer input i bestemt interval og string input, hvor der _skal_ indtastes noget)

    - Integer input er ikke længere begrænset til positive tal, men man kan nu registrere alle tal mellem `Integer.MAX_VALUE` og `Integer.MIN_VALUE`

    - Ny overload af `GUI.getUserString(..)`, der gør det muligt at begrænse tekstinputtet ift. længde og whitespace (i.e. mellemrum).

 - __Car-position__:
    - Ny metode `Car.setPosition(..)`, der flytter bilen til et felt på brættet, og automatisk fjerner den fra andre felter. Dette er en ny primær metode til at sætte bilens position

    - Deprecated gamle metoder til at flytte bilen