package Lab4;

import Lab4.algorithm.ABCAlgorithm;
import Lab4.graph.BeeGraph;

import static Lab4.utility.Constants.ITERATIONS_NUMBER;
import static Lab4.utility.Constants.NODES_NUMBER;
import static Lab4.utility.Util.printGraph;
import static Lab4.utility.Util.printGraphColorsDegrees;

public class Main {

    public static void main(String[] args) {
        BeeGraph beeGraph = new BeeGraph();
        ABCAlgorithm algorithm = new ABCAlgorithm(beeGraph);
        int best = NODES_NUMBER;
        int current;

        printGraph(algorithm.getInitGraph(), "Initial graph");

        for (int i = 1; i <= ITERATIONS_NUMBER; i++) {
            algorithm.runAlgorithm();
            current = algorithm.getCurrentBeeGraph().getChromaticNumber();
            if (best > current) {
                System.out.println(i + " Better Chromatic Number found: " + best);
                printGraphColorsDegrees(algorithm.getCurrentBeeGraph(), "Graph colors");
                best = current;
                algorithm.setBestBeeGraph(algorithm.getCurrentBeeGraph());
            } else {
                System.out.println(i + " Chromatic Number: " + best);
            }

            algorithm.resetAlgorithm();
        }
    }
}
