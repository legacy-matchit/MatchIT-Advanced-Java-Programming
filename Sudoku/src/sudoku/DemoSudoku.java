package sudoku;

public class DemoSudoku {
    public static void main(String[] args){
        int [][] board = new int[][] {
                { 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 2, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 3, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 4, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 5, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 6, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
        Sudoku sudoku = new Sudoku(board);
        System.out.println(sudoku.solveSudoku());
    }
}
