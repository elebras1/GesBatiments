package org.elebras.gesbatiments.factory;

import org.elebras.gesbatiments.model.Batiment;
import org.elebras.gesbatiments.model.Etage;
import org.elebras.gesbatiments.model.Piece;

import java.util.ArrayList;
import java.util.List;

public class BatimentFactory {
    /**
     * Le nombre de batiments créé.
     */
    private Integer nombreBatiment;

    /**
     * Le numéro de la premiere piece.
     */
    private Integer numeroPremierePiece;

    /**
     * Le numéro du premier étage.
     */
    private Integer numeroPremierEtage;

    /**
     * Le nombre d'étages.
     */
    private Integer nombreEtages;

    /**
     * L'usage du batiment (ex : Logement, Bureau, etc).
     */
    private String usage;

    /**
     * Contructeur de la classe BatimentFactory,
     * donne une valeur initial a tout les attributs.
     */
    public BatimentFactory() {
        this.nombreBatiment = 0;
        this.numeroPremierePiece = 0;
        this.numeroPremierEtage = 0;
        this.nombreEtages = 1;
        this.usage = "Logement";
    }

    /**
     * Construit un batiment avec un nombre d'étages, de pièces par étage et de bureaux.
     * La méthode génère une liste d'étages et de pièces, en fonction des paramètres fournis et des attributs de l'objet actuel.
     *
     * @param nom
     * @param nbPieceParEtage
     * @param nbBureau
     * @param surfacePiece
     * @return Un le batiment créer {@link Batiment}
     */
    public Batiment construire(final String nom, final Integer nbPieceParEtage, final Integer nbBureau, final Integer surfacePiece) {
        if(nbPieceParEtage < 1 || surfacePiece < 9 || nom == null) {
            return null;
        }

        List<Etage> etages = new ArrayList<>();
        List<Piece> pieces = new ArrayList<>();

        int nbBureauCreer = 0;
        boolean estBureau;
        int numeroPiece = this.numeroPremierePiece;
        for (int i = 0; i < this.nombreEtages; i++) {
            Etage etage = new Etage(this.numeroPremierEtage + i);
            etages.add(etage);
            for (int j = 0; j < nbPieceParEtage; j++) {
                if (nbBureauCreer < nbBureau) {
                    estBureau = true;
                } else {
                    estBureau = false;
                }
                pieces.add(new Piece(surfacePiece, estBureau, numeroPiece++, etage));
                nbBureauCreer++;
            }
        }

        return new Batiment(++this.nombreBatiment, nom, this.usage, pieces, etages);
    }

    /**
     * Définit le numéro de la première pièce à utiliser pour la numérotation des pièces.
     *
     * @param numero Le numéro de la première pièce.
     */
    public void setNumeroPremierePiece(final Integer numero) {
        this.numeroPremierePiece = numero;
    }

    /**
     * Définit le numéro du premier étage à utiliser pour la numérotation des étages.
     *
     * @param numero Le numéro du premier étage.
     */
    public void setNumeroPremierEtage(final Integer numero) {
        this.numeroPremierEtage = numero;
    }

    /**
     * Définit le nombre d'étages du bâtiment.
     *
     * @param nombre Le nombre d'étages à définir.
     */
    public void setNombreEtages(final Integer nombre) {
        this.nombreEtages = nombre;
    }

    /**
     * Définit l'usage du bâtiment.
     *
     * @param usage L'usage du bâtiment à définir.
     */
    public void setUsage(final String usage) {
        this.usage = usage;
    }

    /**
     * Récupère le numéro de la première pièce.
     *
     * @return Le numéro de la première pièce.
     */
    public Integer getNumeroPremierePiece() {
        return numeroPremierePiece;
    }

    /**
     * Récupère le numéro du premier étage.
     *
     * @return Le numéro du premier étage.
     */
    public Integer getNumeroPremierEtage() {
        return numeroPremierEtage;
    }

    /**
     * Récupère le nombre d'étages.
     *
     * @return Le nombre d'étages.
     */
    public Integer getNombreEtages() {
        return nombreEtages;
    }

    /**
     * Récupère l'usage du batiment.
     *
     * @return L'usage du batiment.
     */
    public String getUsage() {
        return usage;
    }


    /**
     * Récupère le nombre de batiments.
     *
     * @return le nombre de batiments.
     */
    public Integer getNombreBatiment() {
        return nombreBatiment;
    }

    /**
     * Incrémente le nombre de batiments.
     */
    public void incrementNombreBatiment() {
        this.nombreBatiment++;
    }
}
