package secondLab.util;

import secondLab.entity.Node;
import secondLab.entity.Result;

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

    public static void printSolutionBoard(Node solutionNode) {
        int temp = 0;
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

    public static void handleResult(Result result) {
        Optional<Node> solution = result.getSolution();
        switch (result.getResultCode()) {
            case CUTOFF -> System.out.println("There is no solution on this depth level");
            case FAILURE -> System.out.println("Failure");
            case NOT_SOLVABLE -> System.out.println("Not solvable");
            case TERMINATED -> System.out.println("Terminated");
            case SOLUTION -> printSolutionBoard(solution.orElseThrow());
            default -> throw new IllegalArgumentException("Invalid indicator");
        }
    }
}
