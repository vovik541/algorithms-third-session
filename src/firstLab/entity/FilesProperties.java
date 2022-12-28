package firstLab.entity;

import java.nio.file.Paths;

public class FilesProperties {
    public static final String PROJECT_PATH = Paths.get("").toAbsolutePath().toString();
    public static final String DIRECT_MERGE_INPUT_PILE_PATH = PROJECT_PATH + "\\src\\firstLab\\resources\\directMergeInputFile.txt";
    public static final String DIRECT_MERGE_LEFT_FILE_PATH = PROJECT_PATH + "\\src\\firstLab\\resources\\directMergeLeftFile.txt";
    public static final String DIRECT_MERGE_RIGHT_FILE_PATH = PROJECT_PATH + "\\src\\firstLab\\resources\\directMergeRightFile.txt";
    public final int TOTAL_INPUT_ARRAY_LENGTH;
    private long leftArrayLength = 0;
    private long rightArrayLength = 0;
    private int currentPower = 0;

    private int maxPower = 0;

    public FilesProperties(int TOTAL_INPUT_ARRAY_LENGTH) {
        this.TOTAL_INPUT_ARRAY_LENGTH = TOTAL_INPUT_ARRAY_LENGTH;

        while (Math.pow(2, maxPower) < TOTAL_INPUT_ARRAY_LENGTH){
            maxPower++;
        }
    }

    public void nullLeftWriteArraysLength(){
        this.leftArrayLength = 0;
        this.rightArrayLength = 0;
    }

    public void incrementCurrentPower(){
        this.currentPower++;
    }

    public void incrementLeftLength(){
        this.leftArrayLength++;
    }
    public void incrementRightLength(){
        this.rightArrayLength++;
    }
    public long getLeftArrayLength() {
        return leftArrayLength;
    }

    public long getRightArrayLength() {
        return rightArrayLength;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public int getCurrentPower() {
        return currentPower;
    }
}
