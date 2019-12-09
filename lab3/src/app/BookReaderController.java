package app;

import javafx.application.Application;
import javafx.application.Platform;
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

    public void init(){
        System.out.println("Init code");
    }
    public void stop(){
        System.out.println("Stop save data");
    }
    public void start(Stage primaryStage) throws Exception{
        System.out.println("Start");

        /** data load and process **/
        GeneralWordCounter generalWordCounter = new GeneralWordCounter(getStopWords());
        process(generalWordCounter);

        /** get data from generalWordCounter **/
        words = FXCollections.observableArrayList(generalWordCounter.getWords());

        //listView
        ListView<Map.Entry<String,Integer>> listView = new ListView<>(words);

        //Alphabet button
        Button alphabet = new Button("Alphabet");
        alphabet.setPrefWidth(80);
        alphabet.setOnAction(actionEvent -> words.sort(Comparator.comparing(Map.Entry::getKey)));

        //Frequency button
        Button frequency = new Button("Frequency");
        frequency.setPrefWidth(80);
        frequency.setOnAction(actionEvent -> Collections.sort(words,(o1, o2) ->  o2.getValue()-o1.getValue()));

        //TextField
        TextField textField = new TextField("find");
        textField.setPrefWidth(280);

        //find button
        Button find = new Button("Find");
        find.setPrefWidth(60);
        find.setOnAction(actionEvent -> {

            String findWord = textField.getText();

            words.stream().filter(word -> word.getKey().equals(findWord)).forEach(word ->{
                //Main thread
                //int index = words.indexOf(word);
                listView.getSelectionModel().select(word);
                listView.scrollTo(word);
                return;
            });
        });



        HBox hBox = new HBox();
        hBox.getChildren().addAll(alphabet,frequency,textField,find);

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
