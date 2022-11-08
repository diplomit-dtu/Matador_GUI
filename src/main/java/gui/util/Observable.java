package gui.util;

import java.util.ArrayList;

public abstract class Observable {
    private final ArrayList<Observer> observers = new ArrayList<Observer>();
    
    public void addObserver(Observer obs){
        observers.add(obs);
    }
    protected void notifyObservers(){
        for(Observer obs : observers){
            obs.onUpdate();
        }
    }
}
