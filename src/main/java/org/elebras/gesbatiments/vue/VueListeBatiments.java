package org.elebras.gesbatiments.vue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.elebras.gesbatiments.facade.Campus;
import org.elebras.gesbatiments.observer.Observer;
import org.elebras.gesbatiments.visiteur.BatimentVisiteur;

import java.io.File;
import java.util.List;

public class VueListeBatiments implements Observer {
    private Campus campus;
    private Stage stage;
    @FXML
    public TextField textNomBatiment;
    @FXML
    public TextField textNbPieceParEtage;
    @FXML
    public TextField textNbBureau;
    @FXML
    public TextField textSurfacePiece;
    @FXML
    public TextField textNumeroPremierePiece;
    @FXML
    public TextField textNumeroPremierEtage;
    @FXML
    public TextField textNombreEtage;
    @FXML
    public TextField textUsage;
    @FXML
    public ListView listViewBatiments;

    public VueListeBatiments(Campus campus) {
        this.campus = campus;
        this.campus.addObserver(this);
    }

    @FXML
    public void handleAjouterBatiment(ActionEvent actionEvent) {
        this.campus.ajouterBatiment(
                this.textNomBatiment.getText(),
                Integer.valueOf(this.textNbPieceParEtage.getText()),
                Integer.valueOf(this.textNbBureau.getText()),
                Integer.valueOf(this.textSurfacePiece.getText()));
    }

    @FXML
    public void handleConfigurerParametres(ActionEvent actionEvent) {
        this.campus.setNombreEtages(Integer.valueOf(this.textNombreEtage.getText()));
        this.campus.setNumeroPremierEtage(Integer.valueOf(this.textNumeroPremierEtage.getText()));
        this.campus.setNumeroPremierePiece(Integer.valueOf(this.textNumeroPremierePiece.getText()));
        this.campus.setUsage(this.textUsage.getText());
    }

    @FXML
    public void handleChoisirFichier(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner un fichier JSON");

        FileChooser.ExtensionFilter jsonFilter = new FileChooser.ExtensionFilter("Fichiers JSON (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(jsonFilter);

        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun fichier sélectionné");
            alert.setContentText("Veuillez sélectionner un fichier JSON valide.");
            alert.showAndWait();
        }
    }

    @Override
    public void update() {
        List<String> nomBatiments = this.campus.afficherBatimentsNom(new BatimentVisiteur());
        this.listViewBatiments.getItems().addAll(nomBatiments);
    }

    public void initialize() {
        update();
    }
}
