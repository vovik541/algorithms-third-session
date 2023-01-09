package secondLab;

import secondLab.entity.Statistic;

import static secondLab.algorithms.LimitDepthFirstSearch.runLimitDepthFirstSearch;
import static secondLab.algorithms.RecursiveBestFirstSearch.runRecursiveBestFirstSearch;
import static secondLab.util.Utility.printResult;

public class Main {

    public static void main(String[] args) {
        //LimitDepthFirstSearch LDFS
//        runLDFS();
        //LimitDepthFirstSearch RBFS R1
        runRBFS();
    }

    private static void runLDFS(){
        Statistic statistic = runLimitDepthFirstSearch();
        printResult(statistic, "LDFS");

    }

    private static void runRBFS(){
        Statistic statistic = runRecursiveBestFirstSearch();
        printResult(statistic, "RBFS");
    }

}
