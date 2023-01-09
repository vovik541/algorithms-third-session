package secondLab.algorithms;

import lombok.Getter;
import secondLab.entity.Node;
import secondLab.entity.Result;

import java.util.Iterator;
import java.util.LinkedList;

import static secondLab.entity.ResultCodes.*;
import static secondLab.util.Utility.*;


@Getter
public class LimitDepthFirstSearch {
    private static final byte MAX_DEPTH = 8;

    public static void main(String[] args) {
        byte[] problem = createProblem();
        Node root = new Node(problem, (byte) 0);
        System.out.println("LEFS Before search");
        printSolutionBoard(root);

        var limitDepthFirstSearch = new LimitDepthFirstSearch();
        Result result = limitDepthFirstSearch.search(problem, MAX_DEPTH);

        System.out.println("LEFS After search");
        handleResult(result);
//        System.out.println(Node.);
    }

    public Result search(byte[] problem, int limit) {
        Result result = recursiveSearch(new Node(problem, (byte) 0), limit);
        if (result.isSolution()) {
            return result;
        } else {
            return Result.of(FAILURE, null);
        }
    }

    private Result recursiveSearch(Node parent, int limit) {

        boolean cutoffOccurred = false;
        if (parent.isSolution()) {
            return Result.of(SOLUTION, parent);
        } else if (parent.getDepth() == limit) {
            return Result.of(CUTOFF, null);
        } else {
            for (Iterator<Node> iterator = createChildren(parent).iterator(); iterator.hasNext(); ) {
                Node child = iterator.next();
                Result result = recursiveSearch(child, limit);

                if (result.isCutOff()) {
                    cutoffOccurred = true;
                } else if (!result.isFailure()) {
                    return result;
                }

                iterator.remove();
            }
        }

        if (cutoffOccurred)
            return Result.of(CUTOFF, null);
        else
            return Result.of(FAILURE, null);
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
