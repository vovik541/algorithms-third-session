package Lab4.graph;

import lombok.Getter;

import java.util.*;

import static Lab4.utility.Constants.*;

@Getter
public class BeeGraph {
    private ArrayList<BeeNode> nodes = new ArrayList<>();
    private int[] paintedNodesColors;

    public BeeGraph() {
        fullInGraph(nodes);
        createNodeRelations(nodes);

        paintedNodesColors = new int[nodes.size()];
        Arrays.fill(paintedNodesColors, INIT_COLOR);
    }

    private BeeGraph(int[] paintedNodesColors) {
        this.paintedNodesColors = paintedNodesColors;
    }

    public BeeNode findRichestNode(ArrayList<Integer> indexes) {
        Integer bestIndex = 0;
        int bestDegree = 0;

        for (Integer index : indexes) {
            if (nodes.get(index).getDegree() > bestDegree) {
                bestIndex = index;
            }
        }

        return nodes.get(bestIndex);
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

    public BeeGraph deepCopy() {
        int[] coloredByCopy = paintedNodesColors.clone();
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
            copyNode.setUsed(currentNode.isUsed());
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

    public void updatePaintedNodesColors() {
        for (int i = 0; i < NODES_NUMBER; i++) {
            paintedNodesColors[i] = nodes.get(i).getColor();
        }
    }

    public int getChromaticNumber() {
        Set<Integer> uniqueColors = new HashSet<>();

        for (BeeNode node : nodes) {
            uniqueColors.add(node.getColor());
        }

        return uniqueColors.size();
    }
}
