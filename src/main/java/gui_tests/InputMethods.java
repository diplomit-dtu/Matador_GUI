package gui_tests;

import gui_main.GUI;

public class InputMethods {

    public static void main(String[] args) {


        GUI gui = new GUI();

        while(true){
            String output = "";

            // Tag i mod valg via et array
            String[] valgmuligheder = {
                    "Knapper",
                    "Drop-down",
                    "Tekstfelt",
                    "Tal",
                    "Tal med grænser",
                    "Boolean-valg"
            };

            String valg = gui.getUserButtonPressed(
                    "Vælg input metode",
                    valgmuligheder
            );

            // Tager imod et valg af en knap
            // Returnere teksten på knappen der vælges
            if( valg.equals("Knapper") )
                output = gui.getUserButtonPressed(
                        "Metode: gui.getUserButtonPressed(..)  -  Vælg en knap",
                        "Knap 1", "Knap 2", "Knap 3"
                );

            // Tager imod én af flere muligheder ud fra en
            // drop down menu
            // Retunere teksten på elementet der vælges
            if( valg.equals("Drop-down") )
                output = gui.getUserSelection(
                        "Metode: gui.getUserSelection(..)  -  Tag et valg",
                        "Valg 1", "Valg 2", "Valg 3"
                );

            // Tager imod en tekst
            // Returnere teksten
            if( valg.equals("Tekstfelt") )
                output = gui.getUserString(
                        "Metode: gui.getUserString(..)  -  Skriv en tekst"
                );

            // Tager i mod et vilkårligt tal
            // Returneretaller
            if( valg.equals("Tal") )
                output = String.valueOf(gui.getUserInteger(
                        "Metode: gui.getUserInteger(..)  -  Indtast et tal"
                ));

            // OBS: Brug ikke denne - man kan forbi grænsen ved at trykke 'enter',
            // når man har skrevet sin værdi
            // Tager i mod et til mellem de to værdir
            if( valg.equals("Tal med grænser") )
                output = String.valueOf(gui.getUserInteger(
                        "Metode: gui.getUserInteger(.., 1, 10)  -  Indtast et mellem 1 og 10",
                        -10, 10
                ));

            // Viser to knapper, og returnere True hvis man vælger den til venstre
            if( valg.equals("Boolean-valg") )
                output = String.valueOf(gui.getUserLeftButtonPressed(
                        "Metode: gui.getUserLeftButtonPressed(..)  -  Vælg ja eller nej",
                        "Ja (True)", "Nej (False)"
                ));

            // Viser et given output indtil der trykkes på knappen 'Ok'
            gui.showMessage("Du valgte: " + output);
        }

    }

}
