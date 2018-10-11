package c.piroton.testbed;

import java.util.ArrayList;
import java.util.List;

public class MailingList implements Subject {
    private List observers = new ArrayList<Observer>();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private int state = 0;

    public void register(Observer o){
        observers.add(o);
    }
    public void unregister(Observer o){
        observers.remove(o);
    }
    public void notifyObservers(){
        for(int i=0; i<observers.size(); i++){
            Observer o = (Observer) observers.get(i);
            o.update("State is: "+this.state);
        }
        System.out.println("Observers updated.");
        return;
    }
}
