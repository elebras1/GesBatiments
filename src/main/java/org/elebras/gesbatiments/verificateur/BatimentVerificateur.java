package org.elebras.gesbatiments.verificateur;

import org.elebras.gesbatiments.model.Batiment;
import org.elebras.gesbatiments.model.Etage;
import org.elebras.gesbatiments.model.Piece;

/**
 * Classe permettant de vérifier la conformité de la numérotation
 * des pièces et des étages dans un bâtiment.
 */
public class BatimentVerificateur {

    /**
     * Vérifie la numérotation des pièces d'un bâtiment.
     * Les numéros de pièces doivent être dans un ordre croissant.
     *
     * @param batiment le bâtiment à vérifier, contenant une liste de pièces.
     * @return un {@link VerificationResultat} indiquant si une erreur est détectée
     *         dans la numérotation des pièces, ou aucune erreur.
     */
    public VerificationResultat verificationPieces(final Batiment batiment) {
        int idPrecedent = -1;
        for (Piece piece : batiment.getPieces()) {
            if (piece.getNumero() < idPrecedent) {
                return VerificationResultat.PIECE_ERREUR;
            }
            idPrecedent = piece.getNumero();
        }

        return VerificationResultat.AUCUNE_ERREUR;
    }

    /**
     * Vérifie la numérotation des étages d'un bâtiment.
     * Les numéros des étages doivent être dans un ordre croissant.
     *
     * @param batiment le bâtiment à vérifier, contenant une liste d'étages.
     * @return un {@link VerificationResultat} indiquant si une erreur est détectée
     *         dans la numérotation des étages, ou aucune erreur.
     */
    public VerificationResultat verificationEtages(final Batiment batiment) {
        int idPrecedent = -1;
        for (Etage etage : batiment.getEtages()) {
            if (etage.getNumero() < idPrecedent) {
                return VerificationResultat.ETAGE_ERREUR;
            }
            idPrecedent = etage.getNumero();
        }

        return VerificationResultat.AUCUNE_ERREUR;
    }

    /**
     * Effectue une vérification complète d'un bâtiment.
     * Cette méthode vérifie successivement la numérotation des étages,
     * puis celle des pièces.
     *
     * @param batiment le bâtiment à vérifier, contenant une liste d'étages et de pièces.
     * @return un {@link VerificationResultat} indiquant si une erreur est détectée
     *         dans la numérotation des étages ou des pièces, ou aucune erreur.
     *         La vérification des étages est prioritaire sur celle des pièces.
     */
    public VerificationResultat verifier(final Batiment batiment) {
        VerificationResultat resultEtage = this.verificationEtages(batiment);
        if (!resultEtage.equals(VerificationResultat.AUCUNE_ERREUR)) {
            return resultEtage;
        }

        VerificationResultat resultPiece = this.verificationPieces(batiment);
        if (!resultPiece.equals(VerificationResultat.AUCUNE_ERREUR)) {
            return resultPiece;
        }

        return VerificationResultat.AUCUNE_ERREUR;
    }
}
