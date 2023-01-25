package Lab4;

import Lab4.graph.BeeGraph;
import Lab4.algorithm.ABCAlgorithm;

public class Main {

    public static void main(String[] args) {
        BeeGraph beeGraph = new BeeGraph();
        ABCAlgorithm algorithm = new ABCAlgorithm(beeGraph);

        algorithm.getInitGraph().getNodes().forEach(x -> System.out.println(x.getDegree()));
    }
}
