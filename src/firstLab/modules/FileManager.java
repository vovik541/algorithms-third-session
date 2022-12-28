package firstLab.modules;

import firstLab.entity.FilesProperties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class FileManager {
    public static Scanner createFileScanner(String filePath) {
        try {
            return new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static FilesProperties createDirectMergeInputFile() {

        int numbersCount = 0;

        try (PrintStream ps = createFileWriter(FilesProperties.DIRECT_MERGE_INPUT_PILE_PATH)) {
            for (int i = 0; i < 10000000; i++) {
                ps.println(new Random().nextInt());
                ++numbersCount;
            }
//            int[] ints = {8, 23, 5, 65, 44, 33, 1, 6};
//            for (int i = 0; i < ints.length; i++){
//                ps.println(ints[i]);
//                numbersCount++;
//            }
        }

        return new FilesProperties(numbersCount);
    }

    public static PrintStream createFileWriter(String filePath) {
        try {
            return new PrintStream(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
