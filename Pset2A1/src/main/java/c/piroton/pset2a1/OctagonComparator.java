package c.piroton.pset2a1;

import java.util.Comparator;

public class OctagonComparator implements Comparator<Octagon> {
    @Override
    public int compare(Octagon octagon, Octagon t1) {
        if (octagon.getSide() > t1.getSide()) return 1;
        else if (octagon.getSide() < t1.getSide()) return -1;
        else return 0;
    }
}
