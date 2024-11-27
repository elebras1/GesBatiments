package org.elebras.gesbatiments.verificateur;

public enum VerificationResultat {
    AUCUNE_ERREUR("Aucune erreur"),
    PIECE_ERREUR("Numérotation des pièces incorrecte"),
    ETAGE_ERREUR("Numérotation des étages incorrecte");

    private final String message;

    VerificationResultat(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageAvecNumero(int numero) {
        return message + numero;
    }
}
