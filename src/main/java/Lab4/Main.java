package Lab4;

import Lab4.algorithm.ABCAlgorithm;
import Lab4.graph.BeeGraph;

import static Lab4.utility.Util.printGraph;
import static Lab4.utility.Util.printGraphColorsDegrees;

public class Main {

    public static void main(String[] args) {
        BeeGraph beeGraph = new BeeGraph();
        ABCAlgorithm algorithm = new ABCAlgorithm(beeGraph);

        algorithm.runAlgorithm();
        printGraph(algorithm.getInitGraph(), "Initial graph");
        printGraphColorsDegrees(algorithm.getCurrentBeeGraph(), "Graph colors");
        System.out.println("Chromatic Number: " + algorithm.getCurrentBeeGraph().getChromaticNumber());
        algorithm.resetAlgorithm();

        algorithm.runAlgorithm();
        printGraph(algorithm.getInitGraph(), "Initial graph");
        printGraphColorsDegrees(algorithm.getCurrentBeeGraph(), "Graph colors");
        System.out.println("Chromatic Number: " + algorithm.getCurrentBeeGraph().getChromaticNumber());
        algorithm.resetAlgorithm();

        algorithm.runAlgorithm();
        printGraph(algorithm.getInitGraph(), "Initial graph");
        printGraphColorsDegrees(algorithm.getCurrentBeeGraph(), "Graph colors");
        System.out.println("Chromatic Number: " + algorithm.getCurrentBeeGraph().getChromaticNumber());
        algorithm.resetAlgorithm();

        algorithm.runAlgorithm();
        printGraph(algorithm.getInitGraph(), "Initial graph");
        printGraphColorsDegrees(algorithm.getCurrentBeeGraph(), "Graph colors");
        System.out.println("Chromatic Number: " + algorithm.getCurrentBeeGraph().getChromaticNumber());
        algorithm.resetAlgorithm();

        algorithm.runAlgorithm();
        printGraph(algorithm.getInitGraph(), "Initial graph");
        printGraphColorsDegrees(algorithm.getCurrentBeeGraph(), "Graph colors");
        System.out.println("Chromatic Number: " + algorithm.getCurrentBeeGraph().getChromaticNumber());
        algorithm.resetAlgorithm();

        algorithm.runAlgorithm();
        printGraph(algorithm.getInitGraph(), "Initial graph");
        printGraphColorsDegrees(algorithm.getCurrentBeeGraph(), "Graph colors");
        System.out.println("Chromatic Number: " + algorithm.getCurrentBeeGraph().getChromaticNumber());
        algorithm.resetAlgorithm();

        algorithm.runAlgorithm();
        printGraph(algorithm.getInitGraph(), "Initial graph");
        printGraphColorsDegrees(algorithm.getCurrentBeeGraph(), "Graph colors");
        System.out.println("Chromatic Number: " + algorithm.getCurrentBeeGraph().getChromaticNumber());
        algorithm.resetAlgorithm();

        algorithm.runAlgorithm();
        printGraph(algorithm.getInitGraph(), "Initial graph");
        printGraphColorsDegrees(algorithm.getCurrentBeeGraph(), "Graph colors");
        System.out.println("Chromatic Number: " + algorithm.getCurrentBeeGraph().getChromaticNumber());
        algorithm.resetAlgorithm();

    }
}
