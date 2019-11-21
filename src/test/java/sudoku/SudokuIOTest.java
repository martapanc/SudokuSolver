package sudoku;

import org.junit.jupiter.api.Test;

import static com.example.sudokusolver.sudoku.SudokuIO.printSudoku;
import static com.example.sudokusolver.sudoku.SudokuIO.readSudoku;
import static sudoku.SudokuSolverTest.ALL_NUMBERS;
import static sudoku.SudokuSolverTest.ALL_NUMBERS_REVERSED;
import static sudoku.SudokuSolverTest.ALL_NUMBERS_WITH_MANY_ZEROS;
import static sudoku.SudokuSolverTest.ALL_NUMBERS_WITH_SOME_ZEROS;
import static sudoku.SudokuSolverTest.ALL_ONES;

public class SudokuIOTest {

    @Test
    public void testPrintSudoku() {
        printSudoku(ALL_ONES);
        printSudoku(ALL_NUMBERS);
        printSudoku(ALL_NUMBERS_WITH_SOME_ZEROS);
        printSudoku(ALL_NUMBERS_WITH_MANY_ZEROS);
    }

    @Test
    public void testReadSudoku() {
        printSudoku(readSudoku(ALL_NUMBERS_REVERSED));
    }
}