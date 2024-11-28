package org.elebras.gesbatiments.vue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.elebras.gesbatiments.facade.Campus;
import org.elebras.gesbatiments.facade.AjouterBatimentResultat;
import org.elebras.gesbatiments.observer.Observer;
import org.elebras.gesbatiments.verificateur.VerificationResultat;
import org.elebras.gesbatiments.visiteur.BatimentVisiteur;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Contrôleur de la vue de la liste des bâtiments. Cette classe gère l'affichage et les actions
 * liées à la gestion des bâtiments dans l'interface utilisateur, incluant l'ajout, la suppression,
 * et l'affichage des bâtiments à partir d'un fichier JSON.
 *
 * Elle implémente l'interface {@link Observer} pour mettre à jour l'affichage lorsque des
 * modifications sont effectuées sur l'état du {@link Campus}.
 */
public class VueListeBatiments implements Observer {
  /**
   * Instance du {@link Campus} qui contient les données et la logique métier pour les bâtiments.
   */
  private final Campus campus;

  /**
   * Fenêtre de l'application.
   */
  private final Stage stage;

  /**
   * Contrôleur principal de la vue.
   */
  private final VueController vueController;

  /**
   * Champs de texte pour le nom du bâtiment.
   */
  @FXML
  public TextField textNomBatiment;

  /**
   * Champs de texte pour le nombre de pièces par étage.
   */
  @FXML
  public TextField textNbPieceParEtage;

  /**
   * Champs de texte pour le nombre de bureaux.
   */
  @FXML
  public TextField textNbBureau;

  /**
   * Champs de texte pour la surface des pièces.
   */
  @FXML
  public TextField textSurfacePiece;

  /**
   * Champs de texte pour le numéro de la première pièce.
   */
  @FXML
  public TextField textNumeroPremierePiece;

  /**
   * Champs de texte pour le numéro du premier étage.
   */
  @FXML
  public TextField textNumeroPremierEtage;

  /**
   * Champs de texte pour le nombre d'étages.
   */
  @FXML
  public TextField textNombreEtage;

  /**
   * Champs de texte pour l'usage du campus.
   */
  @FXML
  public TextField textUsage;

  /**
   * Liste des bâtiments affichés dans l'interface utilisateur.
   */
  @FXML
  public ListView<String> listViewBatiments;

  /**
   * Constructeur de la vue de la liste des bâtiments.
   *
   * @param campus le campus sur lequel les bâtiments sont gérés.
   * @param stage la fenêtre de l'application.
   * @param vueController le contrôleur principal de la vue.
   */
  public VueListeBatiments(Campus campus, Stage stage, VueController vueController) {
    this.campus = campus;
    this.campus.addObserver(this);
    this.stage = stage;
    this.vueController = vueController;
  }

  /**
   * Initialise les paramètres de la creation d'un batiment.
   */
  public void initialize() {
    this.textNumeroPremierePiece.setText(String.valueOf(this.campus.getNumeroPremierePiece()));
    this.textNumeroPremierEtage.setText(String.valueOf(this.campus.getNumeroPremiereEtage()));
    this.textNombreEtage.setText(String.valueOf(this.campus.getNombreEtages()));
    this.textUsage.setText(this.campus.getUsage());
  }

  /**
   * Ajoute un bâtiment en fonction des informations saisies dans les champs de texte. Vérifie que
   * les champs sont remplis et que les données sont valides avant d'ajouter le bâtiment.
   *
   * @param actionEvent l'événement déclenché par l'action de l'utilisateur.
   */
  @FXML
  public void ajouterBatiment(ActionEvent actionEvent) {
    if (this.textNomBatiment.getText().isEmpty() || this.textNbPieceParEtage.getText().isEmpty()
        || this.textNbBureau.getText().isEmpty() || this.textSurfacePiece.getText().isEmpty()) {
      this.afficherAlerte("Erreur", "Champs vides", "Veuillez remplir tous les champs.");
      return;
    }

    try {
      AjouterBatimentResultat result = this.campus.ajouterBatiment(this.textNomBatiment.getText(),
          Integer.parseInt(this.textNbPieceParEtage.getText()),
          Integer.parseInt(this.textNbBureau.getText()),
          Integer.parseInt(this.textSurfacePiece.getText()));

      switch (result) {
        case BATIMENT_DEJA_EXISTANT:
          this.afficherAlerte("Erreur", "Bâtiment déjà existant",
              "Un bâtiment avec le nom " + this.textNomBatiment.getText() + " existe déjà.");
          break;
        case PARAMETRES_INVALIDES:
          this.afficherAlerte("Erreur", "Paramètres invalides", """
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

  /**
   * Configure les paramètres du campus en fonction des données saisies dans les champs de texte.
   *
   * @param actionEvent l'événement déclenché par l'action de l'utilisateur.
   */
  @FXML
  public void configurerParametres(ActionEvent actionEvent) {
    if (this.textNombreEtage.getText().isEmpty() || this.textNumeroPremierEtage.getText().isEmpty()
        || this.textNumeroPremierePiece.getText().isEmpty() || this.textUsage.getText().isEmpty()) {
      this.afficherAlerte("Erreur", "Champs vides", "Veuillez remplir tous les champs.");
      return;
    }

    if (Integer.parseInt(this.textNombreEtage.getText()) < 1) {
      this.afficherAlerte("Erreur", "Nombre d'étages invalide",
          "Le nombre d'étages doit être supérieur ou égal à 1.");
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
      this.afficherAlerte("Erreur de saisie", "Valeur non valide", """
          Veuillez entrer des valeurs numériques valides pour les champs :
          - 'nombre d'étages'
          - 'numéro du premier étage'
          - 'numéro de la première pièce'.""");

    }
  }

  /**
   * Ouvre un fichier JSON pour ajouter des bâtiments en fonction de son contenu. Affiche un bilan
   * d'ajout des bâtiments après l'importation du fichier.
   *
   * @param actionEvent l'événement déclenché par l'action de l'utilisateur.
   */
  @FXML
  public void ouvrirFichierJson(ActionEvent actionEvent) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Sélectionner un fichier JSON");

    FileChooser.ExtensionFilter jsonFilter =
        new FileChooser.ExtensionFilter("Fichiers JSON (*.json)", "*.json");
    fileChooser.getExtensionFilters().add(jsonFilter);

    File selectedFile = fileChooser.showOpenDialog(this.stage);

    if (selectedFile == null) {
      this.afficherAlerte("Erreur", "Fichier non sélectionné", "Veuillez sélectionner un fichier.");
    } else {
      Map<String, VerificationResultat> batimentsResultat =
          this.campus.ajouterBatiments(selectedFile);
      this.afficherBilanAjoutBatiments(batimentsResultat);
    }
  }

  /**
   * Ouvre la vue d'un bâtiment spécifique sélectionné dans la liste des bâtiments.
   *
   * @param actionEvent l'événement déclenché par l'action de l'utilisateur.
   */
  @FXML
  public void ouvrirBatiment(ActionEvent actionEvent) {
    String nomBatiment = this.listViewBatiments.getSelectionModel().getSelectedItem();
    if (nomBatiment == null) {
      this.afficherAlerte("Erreur", "Bâtiment non sélectionné",
          "Veuillez sélectionner un bâtiment.");
      return;
    }

    int numeroBatiment = this.campus.getNumeroBatiment(nomBatiment);
    if (numeroBatiment == -1) {
      this.afficherAlerte("Erreur", "Bâtiment non trouvé", "Le bâtiment sélectionné n'existe pas.");
      return;
    }

    this.vueController.ouvrirVueBatiment(numeroBatiment);
  }

  /**
   * Supprime le bâtiment sélectionné de la liste des bâtiments.
   *
   * @param actionEvent l'événement déclenché par l'action de l'utilisateur.
   */
  @FXML
  public void supprimerBatiment(ActionEvent actionEvent) {
    String nomBatiment = this.listViewBatiments.getSelectionModel().getSelectedItem();
    if (nomBatiment == null) {
      this.afficherAlerte("Erreur", "Bâtiment non sélectionné",
          "Veuillez sélectionner un bâtiment.");
      return;
    }

    int numeroBatiment = this.campus.getNumeroBatiment(nomBatiment);
    if (numeroBatiment == -1) {
      this.afficherAlerte("Erreur", "Bâtiment non trouvé", "Le bâtiment sélectionné n'existe pas.");
      return;
    }

    this.campus.supprimeBatiment(numeroBatiment);
  }

  /**
   * Met à jour l'affichage de la liste des bâtiments lorsque des modifications sont effectuées sur
   * le campus.
   */
  @Override
  public void update() {
    List<String> nomBatiments = this.campus.afficherBatimentsNom();
    this.listViewBatiments.getItems().clear();
    this.listViewBatiments.getItems().addAll(nomBatiments);
  }

  /**
   * Affiche une alerte d'erreur avec le titre, l'en-tête et le contenu spécifiés.
   *
   * @param titre le titre de l'alerte.
   * @param enTete l'en-tête de l'alerte.
   * @param contenu le contenu de l'alerte.
   */
  private void afficherAlerte(String titre, String enTete, String contenu) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(titre);
    alert.setHeaderText(enTete);
    alert.setContentText(contenu);
    alert.showAndWait();
  }

  /**
   * Affiche un bilan détaillé des bâtiments ajoutés à partir d'un fichier JSON.
   *
   * @param batimentsResultat un mappage des résultats d'ajout pour chaque bâtiment.
   */
  private void afficherBilanAjoutBatiments(Map<String, VerificationResultat> batimentsResultat) {
    StringBuilder message = new StringBuilder();
    int erreurs = 0;

    if (batimentsResultat.keySet().isEmpty()) {
      this.afficherAlerte("Erreur", "Aucun bâtiment ajouté", "Aucun bâtiment n'a été ajouté.");
      return;
    }

    for (Map.Entry<String, VerificationResultat> entry : batimentsResultat.entrySet()) {
      String batiment = entry.getKey();
      VerificationResultat resultat = entry.getValue();

      if (resultat == VerificationResultat.AUCUNE_ERREUR) {
        message.append("Bâtiment ").append(batiment).append(" ajouté avec succès.\n");
      } else {
        erreurs++;
        message.append("Erreur pour le bâtiment ").append(batiment).append(": ")
            .append(resultat.getMessage()).append("\n");
      }
    }

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Bilan d'ajout des bâtiments");
    alert.setHeaderText("Ajout des bâtiments terminé");

    if (erreurs > 0) {
      alert.setContentText("Erreurs rencontrées :\n" + message);
    } else {
      alert.setContentText("Tous les bâtiments ont été ajoutés avec succès.\n" + message);
    }

    alert.showAndWait();
  }



}
