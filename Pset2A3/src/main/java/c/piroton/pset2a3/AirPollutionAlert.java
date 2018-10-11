package c.piroton.pset2a3;

import java.util.ArrayList;
import java.util.List;

//**SEE BELOW** modify AirPollutionAlert

//TODO: modify AirPollutionAlert to implement interface Subject, under Observer design pattern
class AirPollutionAlert implements Subject{
    private double airPollutionIndex;
    private List observers = new ArrayList<Observer>();

    public void setAirPollutionIndex(double airPollutionIndex) {
        this.airPollutionIndex = airPollutionIndex;
        if (this.airPollutionIndex > 100){notifyObservers();}
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (int i=0; i<observers.size(); i++){
            Observer o = (Observer) observers.get(i);
            o.update(airPollutionIndex);
        }
    }
}


