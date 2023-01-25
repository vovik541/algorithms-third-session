package Lab4;

import Lab4.algorithm.ABCAlgorithm;
import Lab4.graph.BeeGraph;

import static Lab4.utility.Util.printGraph;

public class Main {

    public static void main(String[] args) {
        BeeGraph beeGraph = new BeeGraph();
        ABCAlgorithm algorithm = new ABCAlgorithm(beeGraph);
        algorithm.runAlgorithm();
        printGraph(algorithm.getInitGraph(), "Initial graph");


    }
}
