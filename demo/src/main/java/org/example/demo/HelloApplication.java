package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/example/demo/hello-view.fxml")
        );

        Parent root = loader.load();

        Scene scene = new Scene(root);

        HelloController controller = loader.getController();

        stage.setTitle("Snake Game!");
        stage.setScene(scene);
        stage.show();

        controller.setupInput(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}
