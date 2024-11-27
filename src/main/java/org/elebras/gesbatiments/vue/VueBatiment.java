package org.elebras.gesbatiments.vue;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.elebras.gesbatiments.facade.Campus;
import org.elebras.gesbatiments.observer.Observer;
import org.elebras.gesbatiments.visiteur.BatimentVisiteur;
import org.elebras.gesbatiments.visiteur.BureauxVisiteur;

/**
 * Classe représentant la vue d'un bâtiment dans l'application.
 * Elle gère l'affichage et la modification des informations liées à un bâtiment
 * ainsi que l'interaction avec l'utilisateur pour la mise à jour du nom du bâtiment.
 * Cette classe implémente le patron de conception Observer afin de réagir aux changements
 * dans l'état du campus.
 */
public class VueBatiment implements Observer {

    /**
     * Le campus auquel le bâtiment appartient.
     */
    private final Campus campus;

    /**
     * Le numéro du bâtiment à afficher.
     */
    private int numeroBatiment;

    /**
     * le champ de texte pour le nom du bâtiment.
     */
    @FXML
    public TextField nomBatimentField;

    /**
     * La zone de texte pour afficher les informations du bâtiment.
     */
    @FXML
    public TextArea batimentInfoTextArea;

    /**
     * Constructeur de la vue du bâtiment.
     * @param campus Le campus auquel le bâtiment appartient.
     * @param numeroBatiment Le numéro du bâtiment à afficher.
     */
    public VueBatiment(Campus campus, int numeroBatiment) {
        this.campus = campus;
        this.numeroBatiment = numeroBatiment;
        this.campus.addObserver(this);
    }

    /**
     * Initialise la vue du bâtiment, affiche les informations du bâtiment
     * et met à jour l'interface avec les données actuelles.
     */
    public void initialize() {
        this.batimentInfoTextArea.setText(this.campus.afficherBatiment(new BatimentVisiteur(), this.numeroBatiment));
        this.update();
    }

    /**
     * Méthode appelée pour afficher les informations du bâtiment via le visiteur BatimentVisiteur.
     * Met à jour la zone de texte avec les informations du bâtiment.
     */
    @FXML
    public void afficherVisiteurBatiment() {
        this.batimentInfoTextArea.setText(this.campus.afficherBatiment(new BatimentVisiteur(), this.numeroBatiment));
    }

    /**
     * Méthode appelée pour afficher les informations des bureaux via le visiteur BureauxVisiteur.
     * Met à jour la zone de texte avec les informations des bureaux du bâtiment.
     */
    @FXML
    public void afficherVisiteurBureaux() {
        this.batimentInfoTextArea.setText(this.campus.afficherBatiment(new BureauxVisiteur(), this.numeroBatiment));
    }

    /**
     * Méthode appelée pour modifier le nom du bâtiment. Si le champ est vide ou si le nom
     * est déjà pris, une alerte est affichée.
     */
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

    /**
     * Méthode de mise à jour appelée lorsque le campus change.
     * Elle met à jour le nom du bâtiment dans le champ texte.
     */
    @Override
    public void update() {
        this.nomBatimentField.setText(this.campus.getNomBatiment(this.numeroBatiment));
    }

    /**
     * Affiche une alerte d'erreur avec un titre, un en-tête et un contenu spécifique.
     * @param titre Le titre de l'alerte.
     * @param enTete L'en-tête de l'alerte.
     * @param contenu Le contenu de l'alerte.
     */
    private void afficherAlerte(String titre, String enTete, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(enTete);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
}
