package com.example.sudokusolver.sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuIO {
    public static char[][] readSudoku(String input) {
        char[][] sudokuMatrix = new char[9][9];
        char[] chars = input.toCharArray();
        int count = 0;
        for (int y = 0; y < sudokuMatrix.length; y++) {
            for (int x = 0; x < sudokuMatrix[y].length; x++) {
                sudokuMatrix[x][y] = chars[count++];
            }
        }
        return sudokuMatrix;
    }

    public static LinkedHashMap<Cell, Set<Integer>> readSudokuToMap(String input) {
        LinkedHashMap<Cell, Set<Integer>> sudokuMap = new LinkedHashMap<>();
        char[] chars = input.toCharArray();
        int count = 0;

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Set<Integer> values = new HashSet<>();

                if (chars[count] == '0') {
                    // Add numbers 1-9, which represent all possibilities for an empty cell
                    values = Arrays.stream(IntStream.range(1, 10).toArray()).boxed().collect(Collectors.toSet());
                } else {
                    values.add(Character.getNumericValue(chars[count]));
                }
                sudokuMap.put(new Cell(x, y), values);

                count++;
            }
        }

        return sudokuMap;
    }

    static LinkedHashMap<Cell, Integer> readSudokuToMapZerosOnly(String input) {
        LinkedHashMap<Cell, Integer> sudokuMap = new LinkedHashMap<>();
        char[] chars = input.toCharArray();
        int count = 0;

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (chars[count++] == '0') {
                    sudokuMap.put(new Cell(x, y), 0);
                }
            }
        }
        return sudokuMap;
    }

    static LinkedHashMap<Cell, Integer> readSudokuToMapZerosOnlyComplete(String input) {
        LinkedHashMap<Cell, Integer> sudokuMap = new LinkedHashMap<>();
        char[] chars = input.toCharArray();
        int count = 0;

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                sudokuMap.put(new Cell(x, y), chars[count] == '0' ? 0 : Character.getNumericValue(chars[count]));
                count++;
            }
        }
        return sudokuMap;
    }

    public static void printSudoku(String input) {
        char[] chars = input.toCharArray();
        final int COUNT = 25;

        System.out.println("-".repeat(COUNT));
        for (int i = 0; i < chars.length; i++) {
            char num = chars[i];
            if (i % 9 == 0) {
                System.out.print("| ");
            }
            System.out.print(num + " ");
            if (i % 3 == 2) {
                System.out.print("| ");
            }
            if (i % 9 == 8) {
                System.out.println();
            }
            if (i % 27 == 26) {
                System.out.println("-".repeat(COUNT));
            }
        }
    }

    public static void printSudoku(char[][] sudokuMatrix) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int y = 0; y < sudokuMatrix.length; y++) {
            for (int x = 0; x < sudokuMatrix[y].length; x++) {
                stringBuilder.append(sudokuMatrix[x][y]);
            }
        }

        printSudoku(stringBuilder.toString());
    }
}
