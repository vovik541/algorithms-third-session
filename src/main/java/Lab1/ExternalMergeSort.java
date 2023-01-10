package Lab1;

import Lab1.entity.FilesProperties;

import java.io.*;
import java.util.Scanner;

import static Lab1.entity.FilesProperties.*;
import static Lab1.modules.FileManager.*;

public class ExternalMergeSort {

    public static void main(String[] args) {
        FilesProperties filesProperties = createDirectMergeInputFile();
        mergeSort(filesProperties);
    }

    public static void mergeSort(FilesProperties filesProperties) {
        long start = System.nanoTime();

        while (filesProperties.getCurrentPower() < filesProperties.getMaxPower()) {
            splitFiles(filesProperties);
            mergeFiles(filesProperties);
            filesProperties.incrementCurrentPower();
        }
        long time = System.nanoTime() - start;

        File file = new File(DIRECT_MERGE_INPUT_PILE_PATH);

        System.out.printf("Sorting file speed: %.2fMb/%.2fmin", file.length() / 1000000., time / 1000000000. / 60);
    }

    public static void mergeFiles(FilesProperties filesProperties) {
        PrintStream inputFileWriter = createFileWriter(DIRECT_MERGE_INPUT_PILE_PATH);
        Scanner leftReader = createFileScanner(DIRECT_MERGE_LEFT_FILE_PATH);
        Scanner rightReader = createFileScanner(DIRECT_MERGE_RIGHT_FILE_PATH);

        int mergedNumbersCount = 0;
        double stepLength = Math.pow(2, filesProperties.getCurrentPower());
        double leftStep = 1;
        double rightStep = 1;
        double leftReadCount = 1;
        double rightReadCount = 1;

        int leftNumber;
        int rightNumber;

        if (leftReader.hasNext() && rightReader.hasNext()){
            leftNumber = leftReader.nextInt();
            rightNumber = rightReader.nextInt();
            while (mergedNumbersCount < filesProperties.TOTAL_INPUT_ARRAY_LENGTH) {
                if (leftStep == rightStep) {
                    if (leftNumber < rightNumber) {
                        inputFileWriter.println(leftNumber);
                        if (leftReader.hasNext()){
                            leftNumber = leftReader.nextInt();
                            ++leftReadCount;
                            leftStep = Math.ceil(leftReadCount / stepLength);
                        } else {
                            leftStep++;
                        }
                    } else {
                        inputFileWriter.println(rightNumber);
                        if (rightReader.hasNext()){
                            rightNumber = rightReader.nextInt();
                            ++rightReadCount;
                            rightStep = Math.ceil(rightReadCount / stepLength);
                        } else {
                            rightStep++;
                        }
                    }
                } else if (leftStep > rightStep) {
                    inputFileWriter.println(rightNumber);
                    if (rightReader.hasNext()){
                        rightNumber = rightReader.nextInt();
                        ++rightReadCount;
                        rightStep = Math.ceil(rightReadCount / stepLength);
                    }

                } else {
                    inputFileWriter.println(leftNumber);
                    if (leftReader.hasNext()){
                        leftNumber = leftReader.nextInt();
                        ++leftReadCount;
                        leftStep = Math.ceil(leftReadCount / stepLength);
                    } else {
                        leftStep++;
                    }

                }
                ++mergedNumbersCount;
            }
        } else {
            while (leftReader.hasNext()){
                inputFileWriter.println(leftReader.nextInt());
            }
            while (rightReader.hasNext()){
                inputFileWriter.println(rightReader.nextInt());
            }
        }

        inputFileWriter.close();
        leftReader.close();
        rightReader.close();
    }
    public static void splitFiles(FilesProperties filesProperties) {
        Scanner inputFileReader = createFileScanner(DIRECT_MERGE_INPUT_PILE_PATH);
        PrintStream leftWriter = createFileWriter(DIRECT_MERGE_LEFT_FILE_PATH);
        PrintStream rightWriter = createFileWriter(DIRECT_MERGE_RIGHT_FILE_PATH);

        filesProperties.nullLeftWriteArraysLength();

        int splitedNumbersCount = 0;

        int currentStep = 1;
        int toNextStepCounter = 0;
        int stepLength = (int) Math.pow(2, filesProperties.getCurrentPower());

        while (splitedNumbersCount < filesProperties.TOTAL_INPUT_ARRAY_LENGTH) {
            if (currentStep % 2 == 1) {
                leftWriter.println(inputFileReader.nextInt());
                filesProperties.incrementLeftLength();
            } else {
                rightWriter.println(inputFileReader.nextInt());
                filesProperties.incrementRightLength();
            }
            ++splitedNumbersCount;
            ++toNextStepCounter;
            if (toNextStepCounter != 0 && toNextStepCounter % stepLength == 0) {
                currentStep++;
                toNextStepCounter = 0;
            }
        }

        inputFileReader.close();
        leftWriter.close();
        rightWriter.close();
    }
}
