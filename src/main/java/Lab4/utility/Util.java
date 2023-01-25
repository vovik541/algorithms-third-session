package Lab4.utility;

import Lab4.graph.BeeGraph;
import Lab4.graph.BeeNode;

import java.util.ArrayList;

public class Util {

    public static ArrayList<Integer> createStartIndexes(int size) {
        ArrayList<Integer> set = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            set.add(i);
        }

        return set;
    }

    public static int[] createAllColors(int size) {
        int[] allColors = new int[size];

        for (int i = 0; i < allColors.length; i++) {
            allColors[i] = i;
        }

        return allColors;
    }

    public static void printGraph(BeeGraph graph, String message) {
        System.out.println(message + " Node Negrees");
        printGraphNodeDegrees(graph);

        System.out.println("\n" + message + " Node Colors:");
        printGraphColorsDegrees(graph);
    }

    public static void printGraphNodeDegrees(BeeGraph graph) {
        int i = 0;
        for (BeeNode node : graph.getNodes()) {
            i++;
            System.out.printf("%4d", node.getDegree());
            if (i / 30 == 1) {
                System.out.println();
                i = 0;
            }
        }
    }

    public static void printGraphColorsDegrees(BeeGraph graph, String message) {
        System.out.println(message);
        printGraphColorsDegrees(graph);
    }

    public static void printGraphColorsDegrees(BeeGraph graph) {
        int i = 0;
        int[] colors = graph.getPaintedNodesColors();

        for (int j = 0; j < colors.length; j++) {
            i++;
            System.out.printf("%4d", colors[j]);
            if (i / 30 == 1) {
                System.out.println();
                i = 0;
            }
        }
    }
}
