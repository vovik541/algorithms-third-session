package Lab1.resources;

import java.io.*;
import java.util.Arrays;

import static Lab1.entity.FilesProperties.DIRECT_MERGE_INPUT_PILE_PATH;

public class ExternalSort {

    public static void sort(String f1, String f2) throws Exception {
        RandomAccessFile raf1 = new RandomAccessFile(f1, "rw");
        RandomAccessFile raf2 = new RandomAccessFile(f2, "rw");
        int fileByteSize = (int) (raf1.length() / 4);
        int size = Math.min(1000000, fileByteSize);
        externalSort(f1, f2, size);
        boolean writeToOriginal = true;
        DataOutputStream dos;
        while (size <= fileByteSize) {
            if (writeToOriginal) {
                raf1.seek(0);
                dos = new DataOutputStream(new BufferedOutputStream(
                        new FileOutputStream(raf1.getFD())));
            } else {
                raf2.seek(0);
                dos = new DataOutputStream(new BufferedOutputStream(
                        new FileOutputStream(raf2.getFD())));
            }
            for (int i = 0; i < fileByteSize; i += 2 * size) {
                if (writeToOriginal) {
                    dos = merge(f2, dos, i, size);
                } else {
                    dos = merge(f1, dos, i, size);
                }
            }
            dos.flush();
            writeToOriginal = !writeToOriginal;
            size *= 2;
        }
        if (writeToOriginal) {
            raf1.seek(0);
            raf2.seek(0);
            dos = new DataOutputStream(new BufferedOutputStream(
                    new FileOutputStream(raf1.getFD())));
            int i = 0;
            while (i < raf2.length() / 4) {
                dos.writeInt(raf2.readInt());
                i++;
            }
            dos.flush();
        }
    }

    public static void externalSort(String f1, String f2, int size) throws Exception {

        RandomAccessFile raf1 = new RandomAccessFile(f1, "rw");
        RandomAccessFile raf2 = new RandomAccessFile(f2, "rw");

        int fileByteSize = (int) (raf1.length() / 4);

        int[] array = new int[size];
        DataInputStream dis = new DataInputStream(new BufferedInputStream(
                new FileInputStream(raf1.getFD())));
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream(raf2.getFD())));

        int count = 0;
        while (count < fileByteSize) {
            for (int k = 0; k < size; ++k) {
                array[k] = dis.readInt();
            }
            count += size;
            Arrays.sort(array);
            for (int k = 0; k < size; ++k) {
                dos.writeInt(array[k]);
            }
        }
        dos.flush();
        raf1.close();
        raf2.close();
        dis.close();
        dos.close();
    }

    public static DataOutputStream merge(String file,
                                         DataOutputStream dos, int start, int size) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        RandomAccessFile raf2 = new RandomAccessFile(file, "rw");

        int fileByteSize = (int) (raf.length() / 4);
        raf.seek(4 * start);
        raf2.seek(4 * start);
        DataInputStream dis = new DataInputStream(new BufferedInputStream(
                new FileInputStream(raf.getFD())));
        DataInputStream dis3 = new DataInputStream(new BufferedInputStream(
                new FileInputStream(raf2.getFD())));
        int i = 0;
        int j = 0;
        int max = size * 2;

        int a = dis.readInt();

        int b;
        if (start + size < fileByteSize) {
            dis3.skip(4 * size);
            b = dis3.readInt();
        } else {
            b = Integer.MAX_VALUE;
            j = size;
        }
        while (i + j < max) {
            if (j == size || (a <= b && i != size)) {
                dos.writeInt(a);
                i++;
                if (start + i == fileByteSize) {
                    i = size;
                } else if (i != size) {
                    a = dis.readInt();
                }
            } else {
                dos.writeInt(b);
                j++;
                if (start + size + j == fileByteSize) {
                    j = size;
                } else if (j != size) {

                    b = dis3.readInt();
                }
            }
        }
        raf.close();
        raf2.close();
        return dos;
    }

    public static void main(String[] args) throws Exception {
        String f1 = DIRECT_MERGE_INPUT_PILE_PATH;
        String f2 = "output.txt";

        sort(f1, f2);

    }
}