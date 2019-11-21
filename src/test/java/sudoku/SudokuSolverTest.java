package sudoku;

import com.example.sudokusolver.sudoku.Cell;
import com.example.sudokusolver.sudoku.SudokuSolver;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.sudokusolver.sudoku.SudokuIO.printSudoku;
import static com.example.sudokusolver.sudoku.SudokuIO.readSudokuToMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuSolverTest {

    static final String ALL_NUMBERS = "123456789".repeat(9);
    static final String ALL_NUMBERS_WITH_SOME_ZEROS = "08390261".repeat(10) + "0";
    static final String ALL_NUMBERS_WITH_MANY_ZEROS = "0004050017003000".repeat(5) + "0";
    static final String ALL_NUMBERS_REVERSED = "987654321".repeat(9);
    static final String ALL_ZEROS = "0".repeat(81);
    static final String ALL_ONES = "1".repeat(81);
    static final String INPUT1 = "000000200080007090602000500070060000000901000000020040005000603090400070006000000";
    static final String INPUT2 = "876900000010006000040305800400000210090500000050040306029000008004690173000001004";
    static final String SOLUTION2 = "876914532315286749942375861438769215691523487257148396129437658584692173763851924";

    @Test
    public void solveSudoku() {
        assertEquals(SOLUTION2, SudokuSolver.solveSudoku(INPUT2));
    }

    @Test
    public void getColumnRowAndBoxCells() {
        LinkedHashSet<Cell> columnsRowAndBoxCells = new LinkedHashSet<>();
        columnsRowAndBoxCells.add(new Cell(0, 0));  // Example of columns, rows and box for cell (1,2)
        columnsRowAndBoxCells.add(new Cell(0, 1));
        columnsRowAndBoxCells.add(new Cell(0, 2));  // O O O | . . . | . . .
        columnsRowAndBoxCells.add(new Cell(1, 0));  // O O X | O O O | O O O
        columnsRowAndBoxCells.add(new Cell(1, 1));  // O O O | . . . | . . .
        columnsRowAndBoxCells.add(new Cell(2, 0));  // ------+-------+------
        columnsRowAndBoxCells.add(new Cell(2, 1));  // . . O | . . . | . . .
        columnsRowAndBoxCells.add(new Cell(2, 2));  // . . O | . . . | . . .
        columnsRowAndBoxCells.add(new Cell(3, 2));  // . . O | . . . | . . .
        columnsRowAndBoxCells.add(new Cell(4, 2));  // ------+-------+------
        columnsRowAndBoxCells.add(new Cell(5, 2));  // . . O | . . . | . . .
        columnsRowAndBoxCells.add(new Cell(6, 2));  // . . O | . . . | . . .
        columnsRowAndBoxCells.add(new Cell(7, 2));  // . . O | . . . | . . .
        columnsRowAndBoxCells.add(new Cell(8, 2));
        columnsRowAndBoxCells.add(new Cell(1, 3));
        columnsRowAndBoxCells.add(new Cell(1, 4));
        columnsRowAndBoxCells.add(new Cell(1, 5));
        columnsRowAndBoxCells.add(new Cell(1, 6));
        columnsRowAndBoxCells.add(new Cell(1, 7));
        columnsRowAndBoxCells.add(new Cell(1, 8));

        LinkedHashSet<Cell> actual = SudokuSolver.getColumnRowAndBoxCells(new Cell(1, 2));
        assertEquals(20, actual.size());
        assertTrue(columnsRowAndBoxCells.containsAll(actual));
    }

    @Test
    public void getExistingNumbers() {
        final LinkedHashMap<Cell, Set<Integer>> SUDOKU_MAP = readSudokuToMap(ALL_NUMBERS_WITH_MANY_ZEROS);
        LinkedHashSet<Cell> columnRowAndBoxCells = SudokuSolver.getColumnRowAndBoxCells(new Cell(1, 4));
        Set<Integer> actualExistingNumbers = SudokuSolver.getExistingNumbers(columnRowAndBoxCells, SUDOKU_MAP);
        Set<Integer> expectedExistingNumbers = new HashSet<>();
        expectedExistingNumbers.add(1);
        expectedExistingNumbers.add(3);
        expectedExistingNumbers.add(4);
        expectedExistingNumbers.add(5);
        expectedExistingNumbers.add(7);
        assertEquals(expectedExistingNumbers, actualExistingNumbers);

        final LinkedHashMap<Cell, Set<Integer>> SUDOKU_MAP2 = readSudokuToMap(ALL_ZEROS);
        LinkedHashSet<Cell> columnRowAndBoxCells2 = SudokuSolver.getColumnRowAndBoxCells(new Cell(0, 0));
        Set<Integer> actualExistingNumbers2 = SudokuSolver.getExistingNumbers(columnRowAndBoxCells2, SUDOKU_MAP2);
        assertEquals(new HashSet<>(), actualExistingNumbers2);

        final LinkedHashMap<Cell, Set<Integer>> SUDOKU_MAP3 = readSudokuToMap(INPUT1);
        LinkedHashSet<Cell> columnRowAndBoxCells3 = SudokuSolver.getColumnRowAndBoxCells(new Cell(3, 3));
        Set<Integer> actualExistingNumbers3 = SudokuSolver.getExistingNumbers(columnRowAndBoxCells3, SUDOKU_MAP3);
        Set<Integer> expectedExistingNumbers3 = new HashSet<>();
        expectedExistingNumbers3.add(1);
        expectedExistingNumbers3.add(2);
        expectedExistingNumbers3.add(4);
        expectedExistingNumbers3.add(6);
        expectedExistingNumbers3.add(7);
        expectedExistingNumbers3.add(9);
        assertEquals(expectedExistingNumbers3, actualExistingNumbers3);
    }

    @Test
    public void solveSudokuWithBacktracking() {
        assertEquals(SOLUTION2, SudokuSolver.solveSudokuWithBacktracking(INPUT2));
        printSudoku(INPUT2.replaceAll("0", "."));
        printSudoku(SudokuSolver.solveSudokuWithBacktracking(INPUT2));
    }
}
