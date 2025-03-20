package benchmark;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

public class Skib {
    final static int TRIALS = 5_000;
    final static int MULT = 2;
    final static int ATTEMPTS = 3;
    final static Random rand = new Random();

    public static void main(String[] args) throws IOException {
        double[][] trials = new double[SORT.values().length][TRIALS];
        int sortCount = 0;

        for (SORT sort : SORT.values()) {
            for (int i = 1; i < TRIALS + 1; i++) {
                double maxTime = 0;
                for (int attempt = 0; attempt < ATTEMPTS; attempt++) {
                    double[] arr = new double[i * MULT];
                    for (int j = 0; j < i * MULT; j++) {
                        arr[j] = rand.nextDouble();
                    }
                    double time = getTime(arr, sort);
                    if (time > maxTime) {
                        trials[sortCount][i - 1] = time;
                        maxTime = time;
                    }
                }
            }
            System.out.println("done with " + sort.name());
            sortCount++;
        }

        for (int i = 0; i < SORT.values().length; i++) {
            BufferedWriter outfile = new BufferedWriter(new FileWriter("output" + SORT.values()[i].name() + ".csv"));
            for (int j = 0; j < TRIALS; j++) {
                outfile.write(((j + 1) * MULT) + ", " + (BigDecimal.valueOf(trials[i][j]).toPlainString()));
                outfile.newLine();
            }
            outfile.close();
        }
    }

    public static double getTime(double[] arr, SORT sort) {
        long start = 0;
        long end = 0;
        switch (sort) {
            case SELECTION -> {
                start = System.nanoTime();
                SelectionSort.sort(arr);
                end = System.nanoTime();
            }
            case INSERTION -> {
                start = System.nanoTime();
                InsertionSort.sort(arr);
                end = System.nanoTime();
            }
            case MERGE -> {
                start = System.nanoTime();
                Mergesort.sort(arr);
                end = System.nanoTime();
            }
            case QUICK -> {
                start = System.nanoTime();
                Quicksort.sort(arr);
                end = System.nanoTime();
            }
            case BUILTIN -> {
                start = System.nanoTime();
                Arrays.sort(arr);
                end = System.nanoTime();
            }
        }
        return end - start;
    }

    public enum SORT {
        SELECTION,
        INSERTION,
        MERGE,
        QUICK,
        BUILTIN
    }
}
