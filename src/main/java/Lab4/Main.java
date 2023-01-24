package Lab4;

import Lab4.graph.ABCAlgorithm;

import static Lab4.Constants.NODES_NUMBER;
import static Lab4.Constants.RAND;

public class Main {

    public static void main(String[] args) {
        ABCAlgorithm algorithm = new ABCAlgorithm();

        algorithm.getInitGraph().forEach(x-> System.out.println(x.getDegree()));
    }
}
