package gui.input;


import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.KeyEvent;


/**
 *  Wrapper class for a JButton, which also registers when enter
 *  is pressed while it's selected (i.e. by pressing tab)
 */
public class EnterButton {

    private final ActionCallback callback;
    private final JButton button;

    /**
     * Constructs a new button with a given label and a callback to run,
     * when its pressed, or enter is pressed while the button is selected.
     *
     * @param label Label to show on the button
     * @param callback  Callback to run when button is pressed (lambda with no parameters)
     */
    public EnterButton(@NotNull String label, @NotNull ActionCallback callback) {
        this.callback = callback;

        button = new JButton(label);
        button.addActionListener((e) -> this.doAction());
        button.addKeyListener(new KeyListener());
    }


    public void setEnabled(boolean enabled){
        button.setEnabled(enabled);
    }

    /**
     * @return  The actual JButton (required for some usages of this class)
     */
    public JButton getJButton(){
        return button;
    }

    /**
     * Runs the ActionCallback given when constructing this button
     */
    public void doAction(){
        callback.run();
    }


    public void focus() {
        button.requestFocusInWindow();
    }


    public interface ActionCallback {
        void run();
    }


    private class KeyListener implements java.awt.event.KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            if( e.getKeyChar() == KeyEvent.VK_ENTER ){
                doAction();
                e.consume();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) { }
    }
}
