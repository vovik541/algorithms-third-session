package Lab4;

import Lab4.graph.BeeGraph;
import Lab4.algorithm.ABCAlgorithm;

import static Lab4.utility.Util.printGraph;
import static Lab4.utility.Util.printGraphNodeDegrees;

public class Main {

    public static void main(String[] args) {
        BeeGraph beeGraph = new BeeGraph();
        ABCAlgorithm algorithm = new ABCAlgorithm(beeGraph);

        printGraph(algorithm.getInitGraph(), "Initial graph");


    }
}
