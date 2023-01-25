package Lab4.algorithm;

import Lab4.graph.BeeGraph;
import Lab4.graph.BeeNode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import static Lab4.utility.Constants.*;
import static Lab4.utility.Util.createAllColors;
import static Lab4.utility.Util.createStartIndexes;

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
        this.allColors = createAllColors(MAX_NODE_DEGREE);
    }

    public void runAlgorithm() {
        ArrayList<Integer> unvisitedIndexes = createStartIndexes(NODES_NUMBER);
        LinkedList<BeeNode> discoveredNodes;
        HashMap<Integer, LinkedList<BeeNode>> nodesToVisit;

        while (unvisitedIndexes.size() != 0) {
            discoveredNodes = scoutNodes(unvisitedIndexes);


        }

        System.out.println();

    }

    private LinkedList<BeeNode> scoutNodes(ArrayList<Integer> unvisitedIndexes){
        ArrayList<BeeNode> currentNodes = currentBeeGraph.getNodes();
        LinkedList<BeeNode> nodesToVisit = new LinkedList<>();

        BeeNode richestNode;
        int foundNodesNumber;
        int random;
        foundNodesNumber = 0;

        if (unvisitedIndexes.size() % scouts == 0) {
            richestNode = currentBeeGraph.findRichestNode(unvisitedIndexes);
            nodesToVisit.add(richestNode);
            unvisitedIndexes.remove((Integer) richestNode.getIndex());
            ++foundNodesNumber;
        }
        inside:
        while (foundNodesNumber < scouts) {
            if (unvisitedIndexes.size() == 0) {
                break inside;
            }
            random = RAND.nextInt(unvisitedIndexes.size());
            unvisitedIndexes.remove((Integer) random);
            nodesToVisit.add(currentNodes.get(random));
            ++foundNodesNumber;
        }

        return nodesToVisit;
    }

}
