package secondLab.algorithms;

import lombok.Getter;
import secondLab.entity.Node;
import secondLab.entity.Result;
import secondLab.entity.Statistic;

import java.util.Iterator;

import static secondLab.entity.ResultCodes.*;
import static secondLab.util.Utility.createChildren;
import static secondLab.util.Utility.createProblem;

@Getter
public class LimitDepthFirstSearch {
    private static final byte MAX_DEPTH = 8;
    private static Statistic statistic;
    private static int iterations;

    public static Statistic runLimitDepthFirstSearch() {
        statistic = new Statistic();
        iterations = 0;

        byte[] problem = createProblem();
        Node root = new Node(problem, (byte) 0);
        statistic.setInitialStateNode(root);

        var limitDepthFirstSearch = new LimitDepthFirstSearch();
        Result result = limitDepthFirstSearch.search(problem, MAX_DEPTH);

        statistic.setResult(result);
        statistic.setIterations(iterations);
        return statistic;
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
        iterations++;

        boolean cutoffOccurred = false;
        if (parent.isSolution()) {
            return Result.of(SOLUTION, parent);
        } else if (parent.getDepth() == limit) {
            statistic.incrementEndMeet();
            return Result.of(CUT_OFF, null);
        } else {
            for (Iterator<Node> iterator = createChildren(parent, statistic).iterator(); iterator.hasNext(); ) {
                Node child = iterator.next();
                Result result = recursiveSearch(child, limit);

                if (result.isCutOff()) {
                    cutoffOccurred = true;
                } else if (!result.isFailure()) {
                    return result;
                }

                statistic.decrementChildrenInMemory();
                iterator.remove();
            }
        }

        if (cutoffOccurred)
            return Result.of(CUT_OFF, null);
        else
            return Result.of(FAILURE, null);
    }

}
