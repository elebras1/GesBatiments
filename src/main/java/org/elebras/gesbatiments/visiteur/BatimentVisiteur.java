package org.elebras.gesbatiments.visiteur;

import org.elebras.gesbatiments.model.Batiment;
import org.elebras.gesbatiments.model.Etage;
import org.elebras.gesbatiments.model.Piece;

public class BatimentVisiteur implements Visiteur {
    private StringBuilder result = new StringBuilder();

    @Override
    public void visite(Batiment batiment) {
        this.result.append("Bâtiment ").append(batiment.getNom())
                .append(" (").append(batiment.getNumero()).append(")\n");
    }

    @Override
    public void visite(Etage etage) {
        this.result.append("    Etage ").append(etage.getNumero()).append(":\n");
    }

    @Override
    public void visite(Piece piece) {
        this.result.append("        Bureau ").append(piece.getNumero())
                .append(" - Surface: ").append(piece.getSurface()).append(" m²\n");
    }

    public String getResult() {
        return this.result.toString();
    }
}
