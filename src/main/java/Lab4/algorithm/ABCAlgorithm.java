package Lab4.algorithm;

import Lab4.graph.BeeGraph;
import Lab4.graph.BeeNode;
import Lab4.utility.BeeNodeComparator;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

import static Lab4.utility.Constants.*;
import static Lab4.utility.Util.createAllColors;
import static Lab4.utility.Util.createStartIndexes;

@Getter
public class ABCAlgorithm {

    private BeeGraph initGraph;
    private BeeGraph currentBeeGraph;
    private BeeGraph bestBeeGraph;
    private final int foragers = FORAGERS_NUMBER;
    private final int scouts = SCOUTS_NUMBER;

    private int[] allColors;

    public ABCAlgorithm(BeeGraph initialGraph) {
        this.initGraph = initialGraph;
        this.currentBeeGraph = initialGraph.deepCopy();
        this.allColors = createAllColors(MAX_NODE_DEGREE);
    }

    public void runAlgorithm() {
        int i = 0;

        ArrayList<Integer> unvisitedIndexes = createStartIndexes(NODES_NUMBER);
        LinkedList<BeeNode> scouted = new LinkedList<>();
        PriorityQueue<BeeNode> nodesToVisit;
        Iterator<BeeNode> iterator;
        BeeNode visitingNode;
        BeeNode scoutedNode;

        scoutNodes(unvisitedIndexes, scouted);
        nodesToVisit = findNodesToVisit(scouted);

        nodesToVisit = sortByPriority(nodesToVisit);

        beeProcess:
        while (scouted.size() != 0) {
            visitingNode = nodesToVisit.poll();

            paintNode(visitingNode);
            //paint

            Optional<BeeNode> done = checkIfNodeDone(nodesToVisit, scouted);

            if (done.isPresent()) {
                //paint done
                scouted.remove(done.get());

                if (unvisitedIndexes.size() != 0) {
                    System.out.println(++i);
                    scoutedNode = scoutNode(unvisitedIndexes, scouted);

                    nodesToVisit.addAll(scoutedNode.getNeighbours());
                    nodesToVisit = sortByPriority(nodesToVisit);
                }

                continue beeProcess;
            }

        }

        System.out.println();

    }
    private void paintNode(BeeNode node) {

    }

    private Optional<BeeNode> checkIfNodeDone(PriorityQueue<BeeNode> nodesToVisit, LinkedList<BeeNode> scoutedNodes) {

        boolean isPresent;
        for (BeeNode scouted : scoutedNodes) {
            isPresent = false;
            for (BeeNode toVisit : nodesToVisit) {
                if (scouted.hasNeighbour(toVisit)) {
                    isPresent = true;
                }
            }
            if (!isPresent) {
                return Optional.of(scouted);
            }
        }

        return Optional.empty();
    }

    private PriorityQueue<BeeNode> findNodesToVisit(LinkedList<BeeNode> discoveredNodes) {
        PriorityQueue<BeeNode> nodesToVisit = new PriorityQueue<>();

        float pollenSum = 0;

        for (BeeNode node : discoveredNodes) {
            nodesToVisit.addAll(node.getNeighbours());
        }

        for (BeeNode node : nodesToVisit) {
            pollenSum += node.getDegree();
        }

        for (BeeNode node : nodesToVisit) {
            node.setPollenValue((float) node.getDegree() / pollenSum);
        }

        return nodesToVisit;
    }

    private PriorityQueue<BeeNode> sortByPriority(PriorityQueue<BeeNode> nodesToVisit) {
        return nodesToVisit.stream().sorted(new BeeNodeComparator())
                .collect(Collectors.toCollection(PriorityQueue::new));
    }

    private BeeNode scoutNode(ArrayList<Integer> unvisitedIndexes, LinkedList<BeeNode> scoutedNodes) {
        BeeNode richestNode;
        int foundNodesNumber;
        int random;
        int index;

        foundNodesNumber = (NODES_NUMBER - unvisitedIndexes.size()) % 3;

        if (foundNodesNumber == 0) {
            richestNode = currentBeeGraph.findRichestNode(unvisitedIndexes);
            scoutedNodes.add(richestNode);
            unvisitedIndexes.remove((Integer) richestNode.getIndex());
            return richestNode;
        } else {
            random = RAND.nextInt(unvisitedIndexes.size());
            scoutedNodes.add(currentBeeGraph.getNodes().get(unvisitedIndexes.get(random)));
            index = unvisitedIndexes.get(random);
            unvisitedIndexes.remove(random);

            return currentBeeGraph.getNodes().get(index);
        }
    }

    private void scoutNodes(ArrayList<Integer> unvisitedIndexes, LinkedList<BeeNode> scoutedNodes) {
        ArrayList<BeeNode> currentNodes = currentBeeGraph.getNodes();

        BeeNode richestNode;
        int foundNodesNumber = (NODES_NUMBER - unvisitedIndexes.size()) % 3;
        int random;

        if (foundNodesNumber == 0) {
            richestNode = currentBeeGraph.findRichestNode(unvisitedIndexes);
            scoutedNodes.add(richestNode);
            unvisitedIndexes.remove((Integer) richestNode.getIndex());
            ++foundNodesNumber;
        }
        inside:
        while (foundNodesNumber != scouts) {
            if (unvisitedIndexes.size() == 0) {
                break inside;
            }
            random = RAND.nextInt(unvisitedIndexes.size());
            scoutedNodes.add(currentNodes.get(unvisitedIndexes.get(random)));
            unvisitedIndexes.remove(random);
            ++foundNodesNumber;
        }
    }

}
