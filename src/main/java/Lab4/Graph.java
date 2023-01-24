package Lab4;

import java.util.Arrays;
import java.util.Random;

import static Lab4.Constants.*;

public class Graph {

    private int[][] initRelations;
    private int[][] currentRelations;
    private int[][] bestRelations;
    private int[] allColors;
    private int[] currentUsedColors;
    private int[] bestUsedColors;
    private int[] currentPaintedByColors;
    private int[] bestPaintedByColors;
    private final int foragers = FORAGERS_NUMBER;
    private final int scouts = SCOUTS_NUMBER;

    private Random rand = new Random();

    public Graph() {
        this.allColors = new int[MAX_NODE_DEGREE];
        createInitialRelations();
        this.currentPaintedByColors = new int[initRelations.length];
        Arrays.fill(this.currentPaintedByColors, INIT_COLOR);
    }

    public void saveCurrentSolutionAsTheBest(){

    }

    private void createInitialRelations(){
        initRelations = new int[NODES_NUMBER][NODES_NUMBER];

        int degree;
        int nodeRowIndex;

        for (int nodeColumnIndex = 0; nodeColumnIndex < initRelations.length; ++nodeColumnIndex){
            degree = countRandomDegree(nodeColumnIndex);

            while (degree > 0){
                nodeRowIndex = rand.nextInt(NODES_NUMBER - 1);
                if (initRelations[nodeColumnIndex][nodeRowIndex] != 1){
                    initRelations[nodeColumnIndex][nodeRowIndex] = 1;
                    initRelations[nodeRowIndex][nodeColumnIndex] = 1;
                    --degree;
                }

            }

        }
    }

    private int countRandomDegree(int nodeIndex){

        int alreadyInDegree = countInitNodeDegree(nodeIndex);
        int randomDegree = rand.nextInt(MAX_NODE_DEGREE - MIN_NODE_DEGREE) + MIN_NODE_DEGREE;

        if (alreadyInDegree < randomDegree){
            return randomDegree - alreadyInDegree;
        }

        return 0;
    }
    private int countInitNodeDegree(int nodeIndex){
        int currentDegree = 0;

        for(int i = 0; i < currentRelations.length; i++){
            if (currentRelations[nodeIndex][i] == 1){
                ++currentDegree;
            }
        }

        return currentDegree;
    }


}
