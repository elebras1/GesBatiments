package org.elebras.gesbatiments.visiteur;

import org.elebras.gesbatiments.model.Batiment;
import org.elebras.gesbatiments.model.Etage;
import org.elebras.gesbatiments.model.Piece;

public class BatimentVisiteur implements Visiteur {
    @Override
    public void visite(Batiment batiment) {
        System.out.println("Bâtiment " + batiment.getNom() + " (" + batiment.getNumero() + ")");
        for (Etage etage : batiment.getEtages()) {
            etage.accept(this);
        }
    }

    @Override
    public void visite(Etage etage) {
        System.out.println(" Etage " + etage.getNumero() + ":");
    }

    @Override
    public void visite(Piece piece) {
        System.out.println(" Pièce " + piece.getNumero() + " - Type: " + (piece.getEstBureau() ? "Bureau" : "Autre"));
    }
}
