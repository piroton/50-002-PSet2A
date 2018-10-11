package c.piroton.pset2a1;

import java.util.ArrayList;
import java.util.Collections;

public class TestOctagon1 {
    public static void main(String[] args) {
        ArrayList<Octagon> l = new ArrayList<Octagon>();
        l.add(new Octagon(2));
        l.add(new Octagon(3));
        l.add(new Octagon(1));
        Collections.sort(l);
        for (Octagon o:l)
            System.out.println(o.getSide());

        ArrayList<Octagon> j = new ArrayList<Octagon>();
        j.add(new Octagon(2));
        j.add(new Octagon(3));
        j.add(new Octagon(1));
        Collections.sort(l, new OctagonComparator());
        for (Octagon o:l)
            System.out.println(o.getSide());
        return;
    }

}
