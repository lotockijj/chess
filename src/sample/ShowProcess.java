package sample;/**
 * Created by Роман Лотоцький on 28.01.2017.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowProcess extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Process is running. ");

        ProgressIndicator pi = new ProgressIndicator(-1);

        VBox vBox = new VBox();
        vBox.getChildren().add(pi);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);

        borderPane.setPadding(new Insets(20, 20, 20, 20));

        Scene scene = new Scene(borderPane, 350, 80);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
