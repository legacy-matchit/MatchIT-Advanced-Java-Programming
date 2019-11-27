package app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

public class BookReaderController extends Application {

    private ObservableList<Map.Entry<String,Integer>> words;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        /** data load and process **/
        GeneralWordCounter generalWordCounter = new GeneralWordCounter(getStopWords());
        process(generalWordCounter);

        /** get data from generalWordCounter **/
        words = FXCollections.observableArrayList(generalWordCounter.getWords());

        ListView<Map.Entry<String,Integer>> listView = new ListView<>(words);

        Button alphabet = new Button("Alphabet");
        alphabet.setPrefWidth(80);
        alphabet.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //words = words.sorted();
                //Collections.sort(words,(o1, o2) -> o1.getKey().compareTo(o2.getKey()));
                words.sort((o1, o2) -> o1.getKey().compareTo(o2.getKey()));
                listView.setItems(words);
            }
        });

        Button frequency = new Button("Frequency");
        frequency.setPrefWidth(80);
        frequency.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //words = FXCollections.observableArrayList(generalWordCounter.orderByValue());
                Collections.sort(words,(o1, o2) ->  o2.getValue()-o1.getValue());
                listView.setItems(words);
            }
        });

        TextField textField = new TextField("find");
        textField.setPrefWidth(280);

        Button find = new Button("Find");
        find.setPrefWidth(60);
        /*find.setOnAction(actionEvent -> {
            String findWord = textField.getText();
            words.forEach(word ->{
                if(word.getKey().equals(findWord)){
                    int index = words.indexOf(word);
                    listView.getSelectionModel().select(index);
                    listView.scrollTo(index);
                    return;
                }
            });
        });*/

        find.setOnAction(actionEvent -> {
            String findWord = textField.getText();
            words.stream().filter(word -> word.getKey().equals(findWord)).forEach(word ->{
                int index = words.indexOf(word);
                listView.getSelectionModel().select(index);
                listView.scrollTo(index);
                return;
            });
        });







        HBox hBox = new HBox();
        hBox.getChildren().add(alphabet);
        hBox.getChildren().add(frequency);
        hBox.getChildren().add(textField);
        hBox.getChildren().add(find);

        BorderPane root = new BorderPane();
        root.setCenter(listView);
        root.setBottom(hBox);
        Scene scene = new Scene(root,500,500);
        primaryStage.setTitle("BookReader");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     *
     * @param generalWordCounter
     */
    private void process(GeneralWordCounter generalWordCounter){
        Scanner scan = loadFile("nilsholg.txt");
        scan.findWithinHorizon("\uFEFF", 1);
        scan.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");// se handledning
        while (scan.hasNext()) {
            String findWord = scan.next().toLowerCase();
            generalWordCounter.process(findWord);
        }
    }

    /**
     *
     * @return
     */
    private Set<String> getStopWords(){
        Set<String> stopwords = new HashSet<>();
        Scanner scan = loadFile("undantagsord.txt");
        while (scan.hasNext()){
            String word = scan.next().toLowerCase();
            stopwords.add(word);
        }
        return stopwords;
    }

    /**
     *
     * @param pathname
     * @return
     */
    private Scanner loadFile(String pathname){
        Scanner scan = null;
        try{
            scan = new Scanner(new File(pathname));
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        return scan;
    }
}
