package Lab4.graph;

import java.util.ArrayList;

import static Lab4.Constants.*;
import static Lab4.Constants.MIN_NODE_DEGREE;

public class ABCAlgorithm {

    private ArrayList<BeeNode> initGraph = new ArrayList<>();

    public ABCAlgorithm() {
        fullInGraph(initGraph);
        createNodeRelations(initGraph);
    }

    private void fullInGraph(ArrayList<BeeNode> graph){
        for (int i = 0; i < NODES_NUMBER; i++){
            graph.add(new BeeNode(i));
        }
    }
    private void createNodeRelations(ArrayList<BeeNode> graph){

        int randomizedDegree;
        int randomRightNodeIndex;
        BeeNode leftNode;
        BeeNode rightNode;

        for (int i = 0; i < graph.size(); i++){
            randomizedDegree = 1;
            while (randomizedDegree > 0){
                randomRightNodeIndex = RAND.nextInt(NODES_NUMBER);
                rightNode = graph.get(randomRightNodeIndex);
                if (rightNode.getNeighbours().size() < MAX_NODE_DEGREE){
                    leftNode = graph.get(i);
                    leftNode.addNeighbour(rightNode);
                    rightNode.addNeighbour(leftNode);
                    --randomizedDegree;
                }
            }
        }

        int randomNumberOfNodesToBeModified = RAND.nextInt(NODES_NUMBER);
        int leftNodeIndex;

        while (randomNumberOfNodesToBeModified > 0){
            leftNodeIndex = RAND.nextInt(NODES_NUMBER);
            randomizedDegree = countRandomDegree(graph.get(leftNodeIndex).getNeighbours().size());
            while (randomizedDegree > 0){
                randomRightNodeIndex = RAND.nextInt(NODES_NUMBER);
                rightNode = graph.get(randomRightNodeIndex);
                if (rightNode.getNeighbours().size() < MAX_NODE_DEGREE){
                    leftNode = graph.get(leftNodeIndex);
                    leftNode.addNeighbour(rightNode);
                    rightNode.addNeighbour(leftNode);
                    --randomizedDegree;
                }
            }
            --randomNumberOfNodesToBeModified;
        }

    }
    private int countRandomDegree(int currentDegree){

        int randomDegree = RAND.nextInt(MAX_NODE_DEGREE - MIN_NODE_DEGREE) + MIN_NODE_DEGREE;;

        if (currentDegree < randomDegree){
            return randomDegree - currentDegree;
        }

        return 0;
    }

    public ArrayList<BeeNode> getInitGraph() {
        return initGraph;
    }
}
