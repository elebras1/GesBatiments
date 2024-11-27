package org.elebras.gesbatiments.vue;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.elebras.gesbatiments.facade.Campus;
import org.elebras.gesbatiments.observer.Observer;
import org.elebras.gesbatiments.visiteur.BatimentVisiteur;
import org.elebras.gesbatiments.visiteur.BureauxVisiteur;

public class VueBatiment implements Observer {
    private final Campus campus;
    private int numeroBatiment;
    @FXML
    public TextField nomBatimentField;
    @FXML
    public TextArea batimentInfoTextArea;

    public VueBatiment(Campus campus, int numeroBatiment) {
        this.campus = campus;
        this.numeroBatiment = numeroBatiment;
        this.campus.addObserver(this);
    }

    public void initialize() {
        this.batimentInfoTextArea.setText(this.campus.afficherBatiment(new BatimentVisiteur(), this.numeroBatiment));
        this.update();
    }

    @FXML
    public void afficherVisiteurBatiment() {
        this.batimentInfoTextArea.setText(this.campus.afficherBatiment(new BatimentVisiteur(), this.numeroBatiment));
    }

    @FXML
    public void afficherVisiteurBureaux() {
        this.batimentInfoTextArea.setText(this.campus.afficherBatiment(new BureauxVisiteur(), this.numeroBatiment));
    }

    @FXML
    public void modifierNomBatiment() {
        if(this.nomBatimentField.getText().isEmpty()) {
            this.afficherAlerte("Erreur", "Champ vide", "Veuillez remplir le champ.");
            return;
        }

        String nomBatiment = this.campus.modifierNomBatiment(this.numeroBatiment, this.nomBatimentField.getText());
        if(nomBatiment == null) {
            this.afficherAlerte("Erreur", "Nom de bâtiment déjà existant", "Veuillez choisir un autre nom.");
        }
    }

    @Override
    public void update() {
        this.nomBatimentField.setText(this.campus.getNomBatiment(this.numeroBatiment));
    }

    private void afficherAlerte(String titre, String enTete, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(enTete);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
}
