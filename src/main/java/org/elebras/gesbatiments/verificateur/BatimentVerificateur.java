package org.elebras.gesbatiments.verificateur;

import org.elebras.gesbatiments.model.Batiment;
import org.elebras.gesbatiments.model.Etage;
import org.elebras.gesbatiments.model.Piece;

public class BatimentVerificateur {
    private static BatimentVerificateur verificateur = null;

    private BatimentVerificateur() {
    }

    /**
     * Retourne l'instance unique de BatimentVerificateur (singleton).
     * Si l'instance n'existe pas encore, elle est créée.
     *
     * @return l'instance unique de BatimentVerificateur
     */
    public static BatimentVerificateur getInstance() {
        if(verificateur == null) {
            verificateur = new BatimentVerificateur();
        }

        return verificateur;
    }

    /**
     * Vérification de la numérotation des pièces.
     *
     * @param batiment
     * @return true si le la numérotation est correcte, sinon false.
     */
    public boolean verificationPieces(final Batiment batiment) {
        int idPrecedent = -1;
        for (Piece piece : batiment.getPieces()) {
            if (piece.getNumero() < idPrecedent) {
                return false;
            }

            idPrecedent = piece.getNumero();
        }

        return true;
    }

    /**
     * Vérification de la numérotation des étages.
     *
     * @param batiment
     * @return true si le la numérotation est correcte, sinon false
     */
    public boolean verificationEtages(final Batiment batiment) {
        int idPrecedent = -1;
        for (Etage etage : batiment.getEtages()) {
            if (etage.getNumero() < idPrecedent) {
                return false;
            }

            idPrecedent = etage.getNumero();
        }

        return true;
    }

    /**
     * Vérification d'un batiment.
     *
     * @param batiment
     * @return true si le batiment est correct sinon false.
     */
    public boolean verifier(final Batiment batiment) {
        return this.verificationEtages(batiment) && this.verificationPieces(batiment);
    }
}
