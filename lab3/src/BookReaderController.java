import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.*;
public class BookReaderController extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        Map<String,Integer> map = new HashMap<>();
        map.put("ABC",123);
        map.put("as",123);
        map.put("AdeBC",123);
        map.put("ABse1C",123);
        map.put("ABa24C",123);
        map.put("AfbBC",123);
        map.put("AdsdeBC",123);
/*
        ObservableList<String> words
                = FXCollections.observableArrayList("asd","dfd","dessd");
        ListView<String> listView
                = new ListView<>(words);*/
/*
        ListView<String> listView = new ListView<String>();
        ObservableList<String> items =FXCollections.observableArrayList (
                "Single", "Double", "Suite", "Family App");
        listView.setItems(items);

        root.setCenter(listView);*/

        Scene scene = new Scene(root,500,500);
        primaryStage.setTitle("BookReader");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
