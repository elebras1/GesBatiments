package org.elebras.gesbatiments.vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.elebras.gesbatiments.facade.Campus;

import java.io.IOException;

/**
 * Contrôleur principal de l'application qui gère l'affichage des vues du campus.
 * Cette classe étend {@link Application} et permet d'ouvrir différentes vues de l'application, telles que
 * la liste des bâtiments et les détails d'un bâtiment spécifique.
 */
public class VueController extends Application {

    /**
     * Instance du {@link Campus} qui contient les données et la logique métier pour les bâtiments.
     */
    private final Campus campus;

    /**
     * Constructeur de la classe {@code VueController}.
     * Initialise l'instance de {@code Campus}.
     */
    public VueController() {
        this.campus = new Campus();
    }

    /**
     * Méthode de démarrage de l'application. Elle est appelée lors du lancement de l'application JavaFX.
     * Elle appelle la méthode {@link #ouvrirVueListeBatiments(Stage)} pour afficher la vue des bâtiments.
     *
     * @param stage Le stage principal de l'application.
     */
    @Override
    public void start(Stage stage) {
        this.ouvrirVueListeBatiments(stage);
    }

    /**
     * Ouvre la vue contenant la liste des bâtiments.
     * Charge le fichier FXML associé et définit le contrôleur pour la vue des bâtiments.
     *
     * @param stage Le stage dans lequel afficher la vue des bâtiments.
     */
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

    /**
     * Ouvre la vue d'un bâtiment spécifique en fonction de son numéro.
     * Charge le fichier FXML associé et définit le contrôleur pour la vue du bâtiment.
     *
     * @param numeroBatiment Le numéro du bâtiment dont afficher les détails.
     */
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

    /**
     * Méthode principale pour lancer l'application JavaFX.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
