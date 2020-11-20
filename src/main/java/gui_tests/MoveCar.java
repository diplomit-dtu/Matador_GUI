package gui_tests;

import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.util.ArrayList;

/**
 *  Simple test setup, to easily move cars around the Board
 */
public class MoveCar {

    private static final int NUM_FIELDS = 24;
    private static final int NUM_PLAYERS = 4;

    public static void main(String[] args) {

        // Construct fields
        ArrayList<String> fieldNames = new ArrayList<>();
        GUI_Field[] fields = new GUI_Field[NUM_FIELDS];
        for( int i=0; i<NUM_FIELDS; i++){
            fields[i] = new GUI_Street();
            fields[i].setTitle("Field " + i);
            fieldNames.add("Field " + i);
        }

        GUI gui = new GUI(fields);

        // Setup player
        ArrayList<GUI_Player> players = new ArrayList<>();
        ArrayList<String> playerNames = new ArrayList<>();
        for( int i=0; i<NUM_PLAYERS; i++ ) {
            String playerName = "Player " + (i+1);
            playerNames.add(playerName);

            GUI_Player player = new GUI_Player(playerName);
            players.add(player);

            gui.addPlayer(player);
        }

        while(true) {
            String button = gui.getUserButtonPressed("Choose player to move",
                playerNames.toArray(new String[0]));

            GUI_Player playerToMove = players.get( playerNames.indexOf(button) );

            String field = gui.getUserSelection("Choose field to move to",
                    fieldNames.toArray(new String[0])
            );

            playerToMove.getCar().setPosition(gui.getFields()[fieldNames.indexOf(field)]);
        }

    }

}
