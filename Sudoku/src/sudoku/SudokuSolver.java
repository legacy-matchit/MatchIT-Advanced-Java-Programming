package sudoku;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class SudokuSolver extends Application {


    private OneLetterTextField[][] boardLayout;
    private int[][] sudokuValues;

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) {

        /**
         * Set UI for center grid
         */
        TilePane grid = new TilePane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(2);
        grid.setVgap(2);
        setGrid(grid);

        /**
         * Set UI for bottom
         */
        Button solveButton = new Button("Solve");
        solveButton.setPrefWidth(80);
        solveButton.setOnAction(event->clickedSolveButton());

        Button clearButton = new Button("Clear");
        clearButton.setPrefWidth(80);
        clearButton.setOnAction(event->clickedClearButton());

        HBox buttons = new HBox();
        buttons.getChildren().addAll(solveButton,clearButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);

        /**
         * Set UI for window
         */
        BorderPane root = new BorderPane();
        root.setCenter(grid);
        root.setBottom(buttons);
        Scene scene = new Scene(root,400,450);
        primaryStage.setTitle("Sudoku Solver");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

    }

    @Override
    public void init() throws Exception {
        super.init();
        boardLayout = new OneLetterTextField[9][9];
        sudokuValues = new int[9][9];
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    /**
     * Helper for center grid
     * @param grid
     */
    private void setGrid(TilePane grid){
        for(int r = 0; r < 9; r++){

            TilePane rowGrid = new TilePane();

            for(int c = 0; c < 9; c++){
                OneLetterTextField field = new OneLetterTextField();
                //field.setStyle("-fx-background-color: #f0ffff;");
                field.setMaxSize(35,35);
                field.setAlignment(Pos.CENTER);
                rowGrid.getChildren().add(field);
                rowGrid.setPrefTileWidth(35);
                rowGrid.setPrefTileHeight(35);
                //pane.setPrefSize(35,35);
                rowGrid.setVgap(2);
                rowGrid.setHgap(2);
                rowGrid.setPrefColumns(9);
                if(r/3*3 != 3 && c/3*3 !=3 || r/3*3 == 3 && c/3*3 ==3){
                    field.setStyle("-fx-background-color: #ff7f50;");
                }
                System.out.print((r/3*3)+","+(c/3*3)+"\n");
                boardLayout[r][c] = field;
            }
            System.out.println();
            grid.getChildren().add(rowGrid);
        }
    }

    /**
     * Helper for button event when clciked solve button
     */
    private void clickedSolveButton(){

        //collect sudokuSolver values from textfield if textfield is empty set zero
        for (int r = 0; r < boardLayout.length; r++){
            for(int c = 0; c < boardLayout[r].length; c++){
                if(!boardLayout[r][c].getText().isEmpty()){
                    sudokuValues[r][c] = Integer.parseInt(boardLayout[r][c].getText());
                }else{
                    sudokuValues[r][c] = 0;
                }
            }
        }

        // if sudokuSolver can solve sudoku display answer if not show alert
        Sudoku sudokuSolver = new Sudoku(sudokuValues);
        if(sudokuSolver.solveSudoku()){
            sudokuValues = sudokuSolver.getBoard();
            for (int r = 0; r < sudokuValues.length; r++){
                for(int c = 0; c < sudokuValues[r].length; c++){
                    boardLayout[r][c].setText(Integer.toString(sudokuValues[r][c]));
                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Failed to solve the sudokuSolver");
            alert.setContentText("Please try again!!");

            alert.showAndWait();
        }

        System.out.println(sudokuSolver);
    }

    /**
     * Helper for button event when clicked clear button
     */
    private void clickedClearButton(){
        //Clear textfield
        for (int r = 0; r < boardLayout.length; r++){
            for(int c = 0; c < boardLayout[r].length; c++){
                boardLayout[r][c].clear();
            }
        }
    }
}
