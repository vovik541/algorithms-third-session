package Lab4.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Lab4.entity.Constants.*;

public class BeeGraph {
    private ArrayList<BeeNode> nodes = new ArrayList<>();
    private int[] coloredByNodes;

    public BeeGraph() {
        fullInGraph(nodes);
        createNodeRelations(nodes);

        coloredByNodes = new int[nodes.size()];
        Arrays.fill(coloredByNodes, INIT_COLOR);
    }

    private BeeGraph(int[] coloredByNodes) {
        this.coloredByNodes = coloredByNodes;
    }

    private void fullInGraph(ArrayList<BeeNode> graph) {
        for (int i = 0; i < NODES_NUMBER; i++) {
            graph.add(new BeeNode(i));
        }
    }

    private void createNodeRelations(ArrayList<BeeNode> graph) {

        int randomizedDegree;
        int randomRightNodeIndex;
        BeeNode leftNode;
        BeeNode rightNode;

        for (int i = 0; i < graph.size(); i++) {
            randomizedDegree = 1;
            first:
            while (randomizedDegree > 0) {
                randomRightNodeIndex = RAND.nextInt(NODES_NUMBER);
                rightNode = graph.get(randomRightNodeIndex);
                if (rightNode.getNeighbours().size() < MAX_NODE_DEGREE) {

                    leftNode = graph.get(i);

                    if (leftNode.getNeighbours().size() > 0) {
                        for (BeeNode child : leftNode.getNeighbours()) {
                            if (child.getIndex() == rightNode.getIndex()) {
                                continue first;
                            }
                        }
                    }
                    leftNode.addNeighbour(rightNode);
                    rightNode.addNeighbour(leftNode);
                    --randomizedDegree;

                }
            }
        }

        int randomNumberOfNodesToBeModified = RAND.nextInt(NODES_NUMBER);
        int leftNodeIndex;

        while (randomNumberOfNodesToBeModified > 0) {
            leftNodeIndex = RAND.nextInt(NODES_NUMBER);
            randomizedDegree = countRandomDegree(graph.get(leftNodeIndex).getNeighbours().size());
            second:
            while (randomizedDegree > 0) {
                randomRightNodeIndex = RAND.nextInt(NODES_NUMBER);
                rightNode = graph.get(randomRightNodeIndex);
                if (rightNode.getNeighbours().size() < MAX_NODE_DEGREE) {

                    leftNode = graph.get(leftNodeIndex);

                    if (leftNode.getNeighbours().size() > 0) {
                        for (BeeNode child : leftNode.getNeighbours()) {
                            if (child.getIndex() == rightNode.getIndex()) {
                                continue second;
                            }
                        }
                    }

                    leftNode.addNeighbour(rightNode);
                    rightNode.addNeighbour(leftNode);
                    --randomizedDegree;
                }
            }
            --randomNumberOfNodesToBeModified;
        }
    }

    private int countRandomDegree(int currentDegree) {
        int randomDegree = RAND.nextInt(MAX_NODE_DEGREE - MIN_NODE_DEGREE) + MIN_NODE_DEGREE;

        if (currentDegree < randomDegree) {
            return randomDegree - currentDegree;
        }
        return 0;
    }

    public ArrayList<BeeNode> getNodes() {
        return nodes;
    }

    public BeeGraph deepCopy() {
        int[] coloredByCopy = coloredByNodes.clone();
        BeeGraph copy = new BeeGraph(coloredByCopy);
        fullInGraph(copy.getNodes());
        copyRelationsTo(copy);

        return copy;
    }

    private void copyRelationsTo(BeeGraph copyGraph) {
        ArrayList<BeeNode> copyNodes = copyGraph.getNodes();
        BeeNode copyNode;
        BeeNode currentNode;
        List<BeeNode> currentNeighbours;
        int currentNeighbourIndex;
        BeeNode copyRightNode;
        boolean containsSuchIndex;

        for (int i = 0; i < copyNodes.size(); i++) {
            copyNode = copyNodes.get(i);
            currentNode = this.nodes.get(i);
            copyNode.setBooked(currentNode.isBooked());
            copyNode.setDegree(currentNode.getDegree());
            copyNode.setColor(currentNode.getColor());

            currentNeighbours = currentNode.getNeighbours();

            for (int j = 0; j < currentNeighbours.size(); j++) {
                currentNeighbourIndex = currentNeighbours.get(j).getIndex();
                containsSuchIndex = false;

                if (copyNode.getNeighbours().size() > 0) {
                    for (BeeNode copyNeighbour : copyNode.getNeighbours()) {
                        if (copyNeighbour.getIndex() == currentNeighbourIndex) {
                            containsSuchIndex = true;
                        }
                    }
                }

                if (!containsSuchIndex) {
                    copyRightNode = copyNodes.get(currentNeighbourIndex);
                    copyNode.addNeighbour(copyRightNode);
                    copyRightNode.addNeighbour(copyNode);
                }
            }
        }
    }
}
