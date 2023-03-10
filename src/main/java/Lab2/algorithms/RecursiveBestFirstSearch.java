package Lab2.algorithms;

import Lab2.entity.Node;
import Lab2.entity.Result;
import Lab2.entity.Statistic;

import java.util.Comparator;
import java.util.LinkedList;

import static java.lang.Math.min;
import static Lab2.entity.ResultCodes.FAILURE;
import static Lab2.entity.ResultCodes.SOLUTION;
import static Lab2.util.Utility.*;

public class RecursiveBestFirstSearch {
    private static long startTime;
    private static Statistic statistic;
    public static Statistic runRecursiveBestFirstSearch() {
        statistic = new Statistic();

        byte[] problem = createProblem();
        Node root = new Node(problem, (byte) 0);

        statistic.setInitialStateNode(root);

        RecursiveBestFirstSearch recursiveBestFirstSearch = new RecursiveBestFirstSearch();
        startTime = System.nanoTime();
        Result result = recursiveBestFirstSearch.search(root, 10000);

        statistic.setConsumedTime(startTime - System.nanoTime());
        statistic.setResult(result);
        return statistic;
    }
    public Result search(Node parent, int bestStepValue) {

        Result result = recursiveSearch(parent, bestStepValue);
        if (result.isSolution()) {
            return result;
        } else {
            return Result.of(FAILURE, null);
        }
    }

    private Result recursiveSearch(Node parent, int bestStepValue) {
        statistic.incrementIteration();

        if (parent.isSolution())
            return Result.of(SOLUTION, parent);

        LinkedList<Node> children = createChildren(parent, statistic);

        while (true) {
            children.sort(Comparator.comparing(Node::getStepValue));

            Node best = children.get(0);

            if (best.getStepValue() > bestStepValue) {
                statistic.incrementEndMeet();
                return Result.of(best.getStepValue(), FAILURE, null);
            }

            Node alternative = children.get(1);

            Result result = recursiveSearch(best, min(bestStepValue, alternative.getStepValue()));
            best.setStepValue(result.getBestF());

            if (!result.isFailure()) {
                return result;
            }
        }
    }
}
