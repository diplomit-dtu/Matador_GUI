package gui.input;

import gui.core.Board;

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
public class StringInput {

    private final JTextField inputField;
    private final EnterButton  okButton;
    private final CountDownLatch latch;


    private final int minLength;
    private final int maxLength;
    private final boolean allowWhiteSpace;
    private String input = "";

    private boolean validInput;

    private boolean inputActive;


    public StringInput(Board board, String msg, int minLength, int maxLength, boolean allowWhiteSpace) {

        if( minLength < 0 )
            throw new IllegalArgumentException("Minimum input length must be zero or positive");

        if( maxLength <= 0 )
            throw new IllegalArgumentException("Maximum input length must be larger than 0");

        if( maxLength < minLength )
            throw new IllegalArgumentException("Maximum input length cannot be less than  minimum input length");

        this.allowWhiteSpace = allowWhiteSpace;
        this.minLength = minLength;
        this.maxLength = maxLength;

        // Check if the empty string is valid input
        validInput = minLength == 0;

        latch = new CountDownLatch(1);
        okButton = new EnterButton("OK", this::returnResult );
        okButton.setEnabled(validInput);

        inputField = new JTextField(20);
        inputField.setHorizontalAlignment(SwingConstants.RIGHT);
        inputField.getDocument().addDocumentListener(new InputFieldValueListener());
        inputField.addKeyListener(new InputFieldKeyListener());

        inputActive = true;

        board.getUserInput(msg, inputField, okButton.getJButton());

        inputField.requestFocusInWindow();
    }


    public String getResult() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException("Was interrupted while waiting for input result - this shouldn't happen (contact developer)!");
        }
        return input;
    }


    public boolean validateInput(String input) {
        if( input.length() < minLength || input.length() > maxLength )
            return false;

        if( !allowWhiteSpace && input.length() > 0 && (
                input.contains(" ") ||
                input.contains("\t") ||
                input.contains("\n") ||
                input.contains("\r") ))
            return false;

        return true;
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
            input = inputField.getText();
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
