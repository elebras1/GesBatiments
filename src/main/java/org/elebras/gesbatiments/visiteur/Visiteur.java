package org.elebras.gesbatiments.visiteur;

import org.elebras.gesbatiments.model.Batiment;
import org.elebras.gesbatiments.model.Etage;
import org.elebras.gesbatiments.model.Piece;

public interface Visiteur {
    void visite(Batiment batiment);
    void visite(Etage etage);
    void visite(Piece piece);
    String getResult();
}
