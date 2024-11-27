package org.elebras.gesbatiments.verificateur;

/**
 * Enumération représentant les différents résultats possibles
 * lors de la vérification d'un bâtiment.
 */
public enum VerificationResultat {
    /**
     * Résultat indiquant qu'aucune erreur n'a été détectée.
     */
    AUCUNE_ERREUR("Aucune erreur"),

    /**
     * Résultat indiquant une erreur dans la numérotation des pièces.
     */
    PIECE_ERREUR("Numérotation des pièces incorrecte"),

    /**
     * Résultat indiquant une erreur dans la numérotation des étages.
     */
    ETAGE_ERREUR("Numérotation des étages incorrecte");

    private final String message;

    /**
     * Constructeur pour initialiser un résultat de vérification avec un message descriptif.
     *
     * @param message le message décrivant le résultat.
     */
    VerificationResultat(String message) {
        this.message = message;
    }

    /**
     * Retourne le message descriptif associé au résultat de vérification.
     *
     * @return le message associé à ce résultat.
     */
    public String getMessage() {
        return message;
    }
}
