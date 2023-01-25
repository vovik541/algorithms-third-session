package Lab4;

import Lab4.entity.BeeGraph;
import Lab4.graph.ABCAlgorithm;

import static Lab4.Constants.NODES_NUMBER;
import static Lab4.Constants.RAND;

public class Main {

    public static void main(String[] args) {
        BeeGraph beeGraph = new BeeGraph();
        ABCAlgorithm algorithm = new ABCAlgorithm(beeGraph);

        algorithm.getInitGraph().getNodes().forEach(x-> System.out.println(x.getDegree()));
    }
}
