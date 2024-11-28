package org.elebras.gesbatiments.visiteur;

import org.elebras.gesbatiments.model.Batiment;
import org.elebras.gesbatiments.model.Etage;
import org.elebras.gesbatiments.model.Piece;

/**
 * La classe {@code BatimentVisiteur} implémente l'interface {@link Visiteur}
 * et fournit une représentation textuelle des éléments visités, tels que les bâtiments,
 * les étages, et les pièces.
 *
 * <p>Cette classe utilise le design pattern Visiteur pour parcourir la structure
 * des objets {@link Batiment}, {@link Etage} et {@link Piece} et générer une description formatée.
 */
public class BatimentVisiteur implements Visiteur {

    /**
     * Le {@code StringBuilder} utilisé pour construire la représentation textuelle
     * des éléments visités.
     */
    private StringBuilder result = new StringBuilder();

    /**
     * Ajoute les informations d'un bâtiment visité à la description.
     *
     * @param batiment Le bâtiment à visiter.
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
     * Ajoute les informations d'une pièce visitée à la description.
     *
     * @param piece La pièce à visiter.
     */
    @Override
    public void visite(Piece piece) {
        this.result.append("        Pièce ").append(piece.getNumero())
                .append(" - Surface: ").append(piece.getSurface()).append(" m²\n");
    }

    /**
     * Récupère le résultat formaté représentant la description des éléments visités.
     *
     * @return Une chaîne de caractères contenant la description des éléments visités.
     */
    @Override
    public String getResult() {
        return this.result.toString();
    }
}
