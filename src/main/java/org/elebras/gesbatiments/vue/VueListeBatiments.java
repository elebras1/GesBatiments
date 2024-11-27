package org.elebras.gesbatiments.vue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.elebras.gesbatiments.facade.Campus;
import org.elebras.gesbatiments.model.AjouterBatimentResult;
import org.elebras.gesbatiments.observer.Observer;
import org.elebras.gesbatiments.visiteur.BatimentVisiteur;

import java.io.File;
import java.util.List;

public class VueListeBatiments implements Observer {
    private final Campus campus;
    private final Stage stage;
    private final VueController vueController;
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
    public ListView<String> listViewBatiments;

    public VueListeBatiments(Campus campus, Stage stage, VueController vueController) {
        this.campus = campus;
        this.campus.addObserver(this);
        this.stage = stage;
        this.vueController = vueController;
    }

    @FXML
    public void ajouterBatiment(ActionEvent actionEvent) {
        if (this.textNomBatiment.getText().isEmpty() || this.textNbPieceParEtage.getText().isEmpty() ||
                this.textNbBureau.getText().isEmpty() || this.textSurfacePiece.getText().isEmpty()) {
            this.afficherAlerte("Erreur", "Champs vides", "Veuillez remplir tous les champs.");
            return;
        }

        try {
            AjouterBatimentResult result = this.campus.ajouterBatiment(
                    this.textNomBatiment.getText(),
                    Integer.parseInt(this.textNbPieceParEtage.getText()),
                    Integer.parseInt(this.textNbBureau.getText()),
                    Integer.parseInt(this.textSurfacePiece.getText()));

            switch (result) {
                case BATIMENT_DEJA_EXISTANT:
                    this.afficherAlerte("Erreur", "Bâtiment déjà existant",
                            "Un bâtiment avec le nom " + this.textNomBatiment.getText() + " existe déjà.");
                    break;
                case PARAMETRES_INVALIDES:
                    this.afficherAlerte("Erreur", "Paramètres invalides",
                            """
                                    Les paramètres fournis ne sont pas valides :
                                    - Le nombre de pièces par étage doit être supérieur ou égal à 1.
                                    - La surface de chaque pièce doit être supérieure ou égale à 9 m².
                                    - Le nombre de bureaux ne peut pas être négatif.""");
                    break;
            }

        } catch (NumberFormatException e) {
            this.afficherAlerte("Erreur de saisie", "Valeur non valide",
                    "Veuillez entrer des valeurs numériques valides pour les champs 'nombre' et 'surface'.");
        }
    }



    @FXML
    public void configurerParametres(ActionEvent actionEvent) {
        if (this.textNombreEtage.getText().isEmpty() || this.textNumeroPremierEtage.getText().isEmpty() ||
                this.textNumeroPremierePiece.getText().isEmpty() || this.textUsage.getText().isEmpty()) {
            this.afficherAlerte("Erreur", "Champs vides", "Veuillez remplir tous les champs.");
            return;
        }

        try {
            int nombreEtages = Integer.parseInt(this.textNombreEtage.getText());
            int numeroPremierEtage = Integer.parseInt(this.textNumeroPremierEtage.getText());
            int numeroPremierePiece = Integer.parseInt(this.textNumeroPremierePiece.getText());

            this.campus.setNombreEtages(nombreEtages);
            this.campus.setNumeroPremierEtage(numeroPremierEtage);
            this.campus.setNumeroPremierePiece(numeroPremierePiece);
            this.campus.setUsage(this.textUsage.getText());

        } catch (NumberFormatException e) {
            this.afficherAlerte(
                    "Erreur de saisie",
                    "Valeur non valide",
                    """
                            Veuillez entrer des valeurs numériques valides pour les champs :
                            - 'nombre d'étages'
                            - 'numéro du premier étage'
                            - 'numéro de la première pièce'."""
            );

        }
    }


    @FXML
    public void ouvrirFichierJson(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner un fichier JSON");

        FileChooser.ExtensionFilter jsonFilter = new FileChooser.ExtensionFilter("Fichiers JSON (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(jsonFilter);

        File selectedFile = fileChooser.showOpenDialog(this.stage);

        if (selectedFile == null) {
            this.afficherAlerte("Erreur", "Fichier non sélectionné", "Veuillez sélectionner un fichier.");
        } else {
            System.out.println(this.campus.ajouterBatiments(selectedFile));
            }
    }

    @FXML
    public void ouvrirBatiment(ActionEvent actionEvent) {
        String nomBatiment = this.listViewBatiments.getSelectionModel().getSelectedItem();
        if (nomBatiment == null) {
            this.afficherAlerte("Erreur", "Bâtiment non sélectionné", "Veuillez sélectionner un bâtiment.");
            return;
        }

        int numeroBatiment = this.campus.getNumeroBatiment(nomBatiment);
        if(numeroBatiment == -1) {
            this.afficherAlerte("Erreur", "Bâtiment non trouvé", "Le bâtiment sélectionné n'existe pas.");
            return;
        }

        this.vueController.ouvrirVueBatiment(numeroBatiment);
    }

    @FXML
    public void supprimerBatiment(ActionEvent actionEvent) {
        String nomBatiment = this.listViewBatiments.getSelectionModel().getSelectedItem();
        if (nomBatiment == null) {
            this.afficherAlerte("Erreur", "Bâtiment non sélectionné", "Veuillez sélectionner un bâtiment.");
            return;
        }

        int numeroBatiment = this.campus.getNumeroBatiment(nomBatiment);
        if(numeroBatiment == -1) {
            this.afficherAlerte("Erreur", "Bâtiment non trouvé", "Le bâtiment sélectionné n'existe pas.");
            return;
        }

        this.campus.supprimeBatiment(numeroBatiment);
    }

    @Override
    public void update() {
        List<String> nomBatiments = this.campus.afficherBatimentsNom(new BatimentVisiteur());
        this.listViewBatiments.getItems().clear();
        this.listViewBatiments.getItems().addAll(nomBatiments);
    }

    private void afficherAlerte(String titre, String enTete, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(enTete);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
}
