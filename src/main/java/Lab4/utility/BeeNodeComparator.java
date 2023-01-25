package Lab4.utility;

import Lab4.graph.BeeNode;

import java.util.Comparator;

public class BeeNodeComparator implements Comparator<BeeNode> {

    @Override
    public int compare(BeeNode o1, BeeNode o2) {
        if (o1.getPollenValue() > o2.getPollenValue()) {
            return -1;
        } else if (o1.getPollenValue() < o2.getPollenValue()) {
            return 1;
        }

        return 0;
    }
}
