package Lab4.graph;

import Lab4.entity.BeeGraph;
import lombok.Getter;

import java.util.ArrayList;

import static Lab4.Constants.*;
import static Lab4.Constants.MIN_NODE_DEGREE;

@Getter
public class ABCAlgorithm {

    private BeeGraph initGraph;
    private BeeGraph currentBeeGraph;
    private BeeGraph bestBeeGraph;
    private final int foragers = FORAGERS_NUMBER;
    private final int scouts = SCOUTS_NUMBER;

    private int[] allColors;
    public ABCAlgorithm(BeeGraph initialGraph) {
        initGraph = initialGraph;

        BeeGraph copy = initialGraph.deepCopy();
        System.out.println();
    }

    public void runAlgorithm(){

    }


}
