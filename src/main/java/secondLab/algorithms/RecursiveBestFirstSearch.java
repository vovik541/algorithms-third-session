package secondLab.algorithms;

import secondLab.entity.Node;
import secondLab.entity.Result;
import secondLab.entity.Statistic;

import java.util.Comparator;
import java.util.LinkedList;

import static java.lang.Math.min;
import static secondLab.entity.ResultCodes.FAILURE;
import static secondLab.entity.ResultCodes.SOLUTION;
import static secondLab.util.Utility.*;

public class RecursiveBestFirstSearch {

    public static Statistic statistic;
    public static void runRecursiveBestFirstSearch() {
        statistic = new Statistic();

        byte[] problem = createProblem();
        Node root = new Node(problem, (byte) 0);
        System.out.println("RBFS Before search");
        printSolutionBoard(root);
        System.out.println(root.getF());

        RecursiveBestFirstSearch recursiveBestFirstSearch = new RecursiveBestFirstSearch();
        Result result = recursiveBestFirstSearch.search(root, 10000);

        System.out.println("RBFS After search");
        handleResult(result);
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
