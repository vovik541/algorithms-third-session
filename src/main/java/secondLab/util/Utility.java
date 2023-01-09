package secondLab.util;

import secondLab.entity.Node;
import secondLab.entity.Result;
import secondLab.entity.Statistic;

import java.util.*;

public class Utility {

    public static byte[] createProblem() {

        byte[] queens = new byte[8];
        List<Byte> positions = new LinkedList<Byte>() {{
            Random random = new Random();
            for (int i = 0; i < queens.length; i++) {
                this.add((byte) (random.nextInt(queens.length)));
            }
        }};

        Collections.shuffle(positions);

        int index = 0;
        for (Byte position : positions) {
            queens[index] = position;
            index++;
        }

        return queens;
    }

    public static void printBoard(Node solutionNode) {
        byte[] queens = solutionNode.getState();

        for (int i = 0; i < queens.length; i++) {
            for (int j = 0; j < queens[i] + 1; j++) {
                System.out.print("□ ");
            }
            System.out.print("Q ");
            for (int j = queens[i]; j < queens.length; j++) {
                System.out.print("□ ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printResult(Statistic statistic, String algorithmName) {
        Result result = statistic.getResult();

        switch (result.getResultCode()) {
            case CUT_OFF -> System.out.println("No solution using current level");
            case FAILURE -> System.out.println("Failure");
            case TERMINATED -> System.out.println("Terminated");
            case SOLUTION -> {
                System.out.println();
                System.out.println(algorithmName + ": beginning state");
                printBoard(statistic.getInitialStateNode());
                System.out.println("After search run:");
                printBoard(result.getSolution().orElseThrow());
                System.out.println();

                System.out.println("Iterations: " + statistic.getIterations());
                System.out.println("States created: " + statistic.getChildrenCreated());
                System.out.println("States in memory: " + statistic.getChildrenInMemory());
                System.out.println("The end node met: " + statistic.getEndMet());
            }

            default -> throw new IllegalArgumentException("Invalid indicator");
        }
    }

    public static LinkedList<Node> createChildren(Node parent, Statistic statistic) {

        byte[] state = parent.getState();
        byte[] copy;

        for (int i = 0; i < state.length; i++) {
            for (int j = 1; j <= state.length - 1; j++) {
                copy = state.clone();
                copy[i] = (byte) ((copy[i] + j) % copy.length);
                parent.addChild(new Node(copy, (byte) (parent.getDepth() + 1)));

                statistic.incrementChildrenCreated();
                statistic.incrementChildrenInMemory();
            }
        }

        return parent.getChildren();
    }
}
