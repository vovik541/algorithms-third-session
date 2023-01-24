package Lab4.graph;

import java.util.LinkedList;
import java.util.List;

import static Lab4.Constants.INIT_COLOR;

public class BeeNode {

    private int nodeIndex;

    private List<BeeNode> neighbours = new LinkedList<>();
    private int color = INIT_COLOR;

    private int degree = 0;

    public BeeNode(int nodeIndex) {
        this.nodeIndex = nodeIndex;
    }

    public void addNeighbour(BeeNode neighbour){
        this.neighbours.add(neighbour);
        degree++;
    }

    public int getDegree() {
        return degree;
    }

    public List<BeeNode> getNeighbours() {
        return neighbours;
    }
}
