package gui_input;

import gui_fields.GUI_Board;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.CountDownLatch;


/**
 *  Class to which displays a message, a input text field and an OK button
 *  to the user, and awaits until the user has entered a valid integer in
 *  the text field.
 *
 *  The class ensures that the user may only return valid integers, and as such
 *  the returned value does not need to be checked.
 */
public class IntegerInput {

    private final JTextField inputField;
    private final JButton  okButton;
    private final CountDownLatch latch;

    private final int minValue;
    private final int maxValue;
    private int inputValue = 0;

    private boolean validInput = false;

    private boolean inputActive = false;

    public IntegerInput(GUI_Board board, String msg, int minValue, int maxValue) {

        if( maxValue < minValue )
            throw new IllegalArgumentException("Maximum value must be larger than minimum");

        this.minValue = minValue;
        this.maxValue = maxValue;

        latch = new CountDownLatch(1);
        okButton = new JButton("OK");
        okButton.addActionListener((e) -> this.returnResult());
        okButton.setEnabled(false);

        inputField = new JTextField(20);
        inputField.setHorizontalAlignment(SwingConstants.RIGHT);
        inputField.getDocument().addDocumentListener(new InputFieldValueListener());
        inputField.addKeyListener(new InputFieldKeyListener());

        inputActive = true;

        board.getUserInput(msg, inputField, okButton);
    }


    public int getResult() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("Was interrupted while waiting for input result - this shouldn't happen (contact developer)!");
        }
        return inputValue;
    }


    public boolean validateInput(String input) {
        int value = 0;
        try{
            value = Integer.parseInt(input);
        }catch(NumberFormatException e){ return false; }
        return value >= minValue && value <= maxValue;
    }


    public void returnResult() {
        if( validInput && inputActive ){
            inputActive = false;
            latch.countDown();
        }
    }


    public void inputChanged() {
        if( !inputActive ) return;
        validInput = validateInput(inputField.getText());
        if( validInput ){
            inputValue = Integer.parseInt(inputField.getText());
            inputField.setForeground(Color.BLACK);
        } else {
            inputField.setForeground(Color.RED);
        }
        okButton.setEnabled(validInput);
    }


    class InputFieldKeyListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER){
                returnResult();
                e.consume();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}
    }

    class InputFieldValueListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) { inputChanged(); }

        @Override
        public void removeUpdate(DocumentEvent e) { inputChanged(); }

        @Override
        public void changedUpdate(DocumentEvent e) { inputChanged(); }
    }


}
