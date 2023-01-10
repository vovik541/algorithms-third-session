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
//        runRBFS();

        //Test LDFS
        testLDFS();
        //Test RBFS
//        testRBFS();

    }

    private static void runLDFS(){
        Statistic statistic = runLimitDepthFirstSearch();
        printResult(statistic, "LDFS");

    }

    private static void runRBFS(){
        Statistic statistic = runRecursiveBestFirstSearch();
        printResult(statistic, "RBFS");
    }

    private static void testLDFS(){
//        Statistic statistic;
//
//        private int iterations = 0;
//        private int endMet = 0;
//        private int childrenCreated = 0;
//        private int childrenInMemory = 0;
//
//        for (int i = 0; i < 20; i++){
//            statistic = runLimitDepthFirstSearch();
//        }
    }
    private static void testRBFS(){

    }

}
