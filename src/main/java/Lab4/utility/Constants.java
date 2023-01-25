package Lab4.utility;

import java.util.Random;

public interface Constants {
    int TOTAL_BEES = 35;
    int SCOUTS_NUMBER = 3;
    int FORAGERS_NUMBER = TOTAL_BEES - SCOUTS_NUMBER;
    int ITERATIONS_NUMBER = 1000;

    int NODES_NUMBER = 150;
    int MIN_NODE_DEGREE = 1;
    int MAX_NODE_DEGREE = 30;
    int INIT_COLOR = -1;

    Random RAND = new Random();


}
