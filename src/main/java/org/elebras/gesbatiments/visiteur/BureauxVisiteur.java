package org.elebras.gesbatiments.visiteur;

import org.elebras.gesbatiments.model.Batiment;
import org.elebras.gesbatiments.model.Etage;
import org.elebras.gesbatiments.model.Piece;

/**
 * La classe {@code BureauxVisiteur} implémente l'interface {@link Visiteur}
 * et fournit une représentation textuelle des éléments visités, tels que les bâtiments,
 * les étages, et les pièces.
 *
 * <p>Cette classe utilise le design pattern Visiteur pour parcourir la structure
 * des objets {@link Batiment}, {@link Etage} et {@link Piece} et générer une description formatée.
 */
public class BureauxVisiteur implements Visiteur {
    private StringBuilder result = new StringBuilder();

    /**
     * Le {@code StringBuilder} utilisé pour construire la représentation textuelle
     * des éléments visités.
     */
    @Override
    public void visite(Batiment batiment) {
        this.result.append("Bâtiment ").append(batiment.getNom())
                .append(" (").append(batiment.getNumero()).append(")\n");
    }

    /**
     * Ajoute les informations d'un étage visité à la description.
     *
     * @param etage L'étage à visiter.
     */
    @Override
    public void visite(Etage etage) {
        this.result.append("    Etage ").append(etage.getNumero()).append(":\n");
    }

    /**
     * Ajoute l'information si la pièce est un bureau ou non.
     *
     * @param piece La pièce à visiter.
     */
    @Override
    public void visite(Piece piece) {
        this.result.append("        Pièce ").append(piece.getNumero())
                .append(" - Type: ").append(piece.getEstBureau() ? "Bureau" : "Autre").append("\n");
    }

    /**
     * Récupère le résultat formaté représentant la description des éléments visités.
     *
     * @return Une chaîne de caractères contenant la description des éléments visités.
     */
    public String getResult() {
        return this.result.toString();
    }
}
