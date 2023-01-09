package secondLab.algorithms;

import secondLab.entity.Node;
import secondLab.entity.Result;

import java.util.Comparator;
import java.util.LinkedList;

import static java.lang.Math.min;
import static secondLab.entity.ResultCodes.FAILURE;
import static secondLab.entity.ResultCodes.SOLUTION;
import static secondLab.util.Utility.*;

public class RecursiveBestFirstSearch {
    static int called = 0;

    public static void main(String[] args) {
        byte[] problem = createProblem();
        Node root = new Node(problem, (byte) 0);
        System.out.println("RBFS Before search");
        printSolutionBoard(root);
        System.out.println(root.getF());

        RecursiveBestFirstSearch recursiveBestFirstSearch = new RecursiveBestFirstSearch();
        Result result = recursiveBestFirstSearch.search(root, 10000);

        System.out.println("RBFS After search");
        handleResult(result);
        System.out.println(called);
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
        called++;
        if (parent.isSolution())
            return Result.of(SOLUTION, parent);

        LinkedList<Node> children = createChildren(parent);

        while (true) {
            children.sort(Comparator.comparing(Node::getStepValue));

            Node best = children.get(0);

            if (best.getStepValue() > bestStepValue) {
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

    private static LinkedList<Node> createChildren(Node parent) {

        byte[] state = parent.getState();
        byte[] copy;

        for (int i = 0; i < state.length; i++) {
            for (int j = 1; j <= state.length - 1; j++) {
                copy = state.clone();
                copy[i] = (byte) ((copy[i] + j) % copy.length);
                parent.addChild(new Node(copy, (byte) (parent.getDepth() + 1)));
            }
        }

        return parent.getChildren();
    }

}
