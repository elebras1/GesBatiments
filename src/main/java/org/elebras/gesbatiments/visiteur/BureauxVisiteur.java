package org.elebras.gesbatiments.visiteur;

import org.elebras.gesbatiments.model.Batiment;
import org.elebras.gesbatiments.model.Etage;
import org.elebras.gesbatiments.model.Piece;

public class BureauxVisiteur implements Visiteur {
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
        if (piece.getEstBureau()) {
            System.out.println(" Bureau " + piece.getNumero() + " - Surface: " + piece.getSurface() + " m²");
        }
    }
}
