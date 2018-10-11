package c.piroton.pset2a1;

import java.util.Comparator;

public class Octagon implements Comparable<Octagon>{
    private double side;
    public Octagon(double sidelen){
        this.side = sidelen;
    }
    public double getSide(){
        return side;
    }


    @Override
    public int compareTo(Octagon octagon) {
        return Double.compare(this.side, octagon.getSide());
    }
}
