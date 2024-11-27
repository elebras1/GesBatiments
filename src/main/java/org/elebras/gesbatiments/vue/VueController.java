package org.elebras.gesbatiments.vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.elebras.gesbatiments.facade.Campus;

import java.io.IOException;

public class VueController extends Application {
    private final Campus campus;

    public VueController() {
        this.campus = new Campus();
    }

    @Override
    public void start(Stage stage) {
        this.ouvrirVueListeBatiments(stage);
    }

    public void ouvrirVueListeBatiments(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listeBatiments.fxml"));
            fxmlLoader.setController(new VueListeBatiments(this.campus, stage, this));
            Scene scene = new Scene(fxmlLoader.load(), 720, 520);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ouvrirVueBatiment(int numeroBatiment) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("batiment.fxml"));
            fxmlLoader.setController(new VueBatiment(this.campus, numeroBatiment));
            Scene scene = new Scene(fxmlLoader.load(), 720, 520);

            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}