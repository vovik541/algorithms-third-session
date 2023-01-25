package Lab4.algorithm;

import Lab4.graph.BeeGraph;
import lombok.Getter;

import static Lab4.entity.Constants.FORAGERS_NUMBER;
import static Lab4.entity.Constants.SCOUTS_NUMBER;

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

    public void runAlgorithm() {

    }


}
