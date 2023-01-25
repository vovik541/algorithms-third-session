package Lab4.utility;

import static Lab4.utility.Constants.MAX_NODE_DEGREE;

public class Util {
    public static int[] createAllColors() {
        int[] allColors = new int[MAX_NODE_DEGREE];

        for (int i = 0; i < allColors.length; i++) {
            allColors[i] = i;
        }

        return allColors;
    }
}
