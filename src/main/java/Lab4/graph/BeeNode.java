package Lab4.graph;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static Lab4.utility.Constants.INIT_COLOR;

@Getter
@Setter
public class BeeNode implements Comparable<BeeNode> {
    private int index;
    private List<BeeNode> neighbours = new LinkedList<>();
    private int color = INIT_COLOR;

    private int degree = 0;

    private boolean isUsed = false;

    private float pollenValue;

    public BeeNode(int index) {
        this.index = index;
    }

    public void addNeighbour(BeeNode neighbour) {
        this.neighbours.add(neighbour);
        degree++;
    }

    public boolean hasNeighbour(BeeNode node) {
        return this.neighbours.contains(node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeeNode node = (BeeNode) o;
        return index == node.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }

    @Override
    public int compareTo(BeeNode o) {
        if (this.pollenValue > o.getPollenValue()) {
            return -1;
        } else if (this.pollenValue < o.getPollenValue()) {
            return 1;
        }

        return 0;
    }
}
