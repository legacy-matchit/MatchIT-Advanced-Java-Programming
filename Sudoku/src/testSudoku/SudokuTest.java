package testSudoku;

import sudoku.Sudoku;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SudokuTest {

    private int[][] board;
    private Sudoku sudoku;

    @org.junit.Before
    public void setUp() throws Exception {
        board = new int[9][9];
        sudoku = new Sudoku(board);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        board = null;
        sudoku = null;
    }

    @org.junit.Test
    public void solveSudoku() {
        board = new int[][] { { 2, 0, 0, 4, 5, 6, 7, 8, 9 },
                { 4, 5, 7, 0, 8, 0, 2, 3, 6 },
                { 6, 8, 9, 2, 3, 7, 0, 4, 0 },
                { 0, 0, 5, 3, 6, 2, 9, 7, 4 },
                { 2, 7, 4, 0, 9, 0, 6, 5, 3 },
                { 3, 9, 6, 5, 7, 4, 8, 0, 0 },
                { 0, 4, 0, 6, 1, 8, 3, 9, 7 },
                { 7, 6, 1, 0, 4, 0, 5, 2, 8 },
                { 9, 3, 8, 7, 2, 5, 0, 6, 3 } };
        sudoku = new Sudoku(board);
        assertFalse(sudoku.solveSudoku());
    }

    @org.junit.Test
    public void isValidSudoku() {
        board = new int[][] { { 0, 0, 8, 0, 0, 9, 0, 6, 2 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 5 },
                { 1, 0, 2, 5, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 2, 1, 0, 0, 9, 0 },
                { 0, 5, 0, 0, 0, 0, 6, 0, 0 },
                { 6, 0, 0, 0, 0, 0, 0, 2, 8 },
                { 4, 1, 0, 6, 0, 8, 0, 0, 0 },
                { 8, 6, 0, 0, 3, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 4, 0, 0 } };
        sudoku = new Sudoku(board);
        assertTrue(sudoku.solveSudoku());
    }
}