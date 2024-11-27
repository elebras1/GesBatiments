package org.elebras.gesbatiments.vue;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.elebras.gesbatiments.facade.Campus;
import org.elebras.gesbatiments.observer.Observer;
import org.elebras.gesbatiments.visiteur.BatimentVisiteur;

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
    }

    @FXML
    public void modifierNomBatiment() {
        this.campus.modifierNomBatiment(this.numeroBatiment, this.nomBatimentField.getText());
    }

    @Override
    public void update() {
        this.nomBatimentField.setText(this.campus.getNomBatiment(this.numeroBatiment));
    }
}
