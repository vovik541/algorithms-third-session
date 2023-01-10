package secondLab;

import secondLab.entity.Statistic;

import java.util.Arrays;

import static secondLab.algorithms.LimitDepthFirstSearch.runLimitDepthFirstSearch;
import static secondLab.algorithms.RecursiveBestFirstSearch.runRecursiveBestFirstSearch;
import static secondLab.util.Utility.printResult;

public class Main {
    public static void main(String[] args) {
        //LimitDepthFirstSearch LDFS
//        runLDFS();

        //RecursiveBestFirstSearch RBFS R1
//        runRBFS();

        //Test LDFS
        testLDFS();
        //Test RBFS
//        testRBFS();

    }

    private static void runLDFS() {
        Statistic statistic = runLimitDepthFirstSearch();
        printResult(statistic, "LDFS");

    }

    private static void runRBFS() {
        Statistic statistic = runRecursiveBestFirstSearch();
        printResult(statistic, "RBFS");
    }

    private static void testLDFS() {
        Statistic statistic;

        long iterations = 0;
        long endMet = 0;
        long childrenCreated = 0;
        long childrenInMemory = 0;

        for (int i = 0; i < 20; i++) {
            System.out.println("Execution: " + (i + 1));
            statistic = runLimitDepthFirstSearch();
            iterations += 0.05 * statistic.getIterations();
            endMet += 0.05 * statistic.getEndMet();
            childrenCreated += 0.05 * statistic.getChildrenCreated();
            childrenInMemory += 0.05 * statistic.getChildrenInMemory();

            System.out.println("initial state" + Arrays.toString(statistic.getInitialStateNode().getState()));
            System.out.println("Iterations: " + statistic.getIterations());
            System.out.println("States created: " + statistic.getChildrenCreated());
            System.out.println("States in memory: " + statistic.getChildrenInMemory());
            System.out.println("The end node met: " + statistic.getEndMet());
        }

        System.out.println("LDFS average statistic");
        System.out.println("Iterations: " + iterations);
        System.out.println("States created: " + childrenCreated);
        System.out.println("States in memory: " + childrenInMemory);
        System.out.println("The end node met: " + endMet);
    }

    private static void testRBFS() {

    }

}
