package org.elebras.gesbatiments.verificateur;

import org.elebras.gesbatiments.model.Batiment;
import org.elebras.gesbatiments.model.Etage;
import org.elebras.gesbatiments.model.Piece;

public class BatimentVerificateur {

    /**
     * Vérification de la numérotation des pièces.
     *
     * @param batiment le batiment à vérifier.
     * @return un résultat de vérification avec un message.
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
     * Vérification de la numérotation des étages.
     *
     * @param batiment le batiment à vérifier.
     * @return un résultat de vérification avec un message.
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
     * Vérification complète d'un batiment.
     *
     * @param batiment le batiment à vérifier.
     * @return un résultat de vérification avec un message.
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
