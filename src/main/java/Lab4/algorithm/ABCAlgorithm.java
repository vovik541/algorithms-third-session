package Lab4.algorithm;

import Lab4.graph.BeeGraph;
import lombok.Getter;

import static Lab4.utility.Constants.FORAGERS_NUMBER;
import static Lab4.utility.Constants.SCOUTS_NUMBER;
import static Lab4.utility.Util.createAllColors;

@Getter
public class ABCAlgorithm {

    private BeeGraph initGraph;
    private BeeGraph currentBeeGraph;
    private BeeGraph bestBeeGraph;
    private final int foragers = FORAGERS_NUMBER;
    private final int scouts = SCOUTS_NUMBER;

    private int[] allColors;

    public ABCAlgorithm(BeeGraph initialGraph) {
        this.initGraph = initialGraph;
        this.currentBeeGraph = initialGraph.deepCopy();
        this.allColors = createAllColors();
    }

    public void runAlgorithm() {

    }


}
