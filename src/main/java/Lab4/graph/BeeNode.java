package Lab4.graph;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

import static Lab4.entity.Constants.INIT_COLOR;

@Getter
@Setter
public class BeeNode {

    private int index;

    private List<BeeNode> neighbours = new LinkedList<>();
    private int color = INIT_COLOR;

    private int degree = 0;

    private boolean isBooked = false;

    public BeeNode(int index) {
        this.index = index;
    }

    public void addNeighbour(BeeNode neighbour) {
        this.neighbours.add(neighbour);
        degree++;
    }
}
