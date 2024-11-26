package org.elebras.gesbatiments.vue;

import org.elebras.gesbatiments.facade.Campus;
import org.elebras.gesbatiments.observer.Observer;
import org.elebras.gesbatiments.visiteur.BatimentVisiteur;

import java.io.File;
import java.util.List;

public class VueListeBatiments implements Observer {
    private Campus campus;

    public VueListeBatiments(Campus campus) {
        this.campus = campus;
        this.campus.addObserver(this);
    }

    public List<String> afficherListeBatiments() {
        return this.campus.afficherBatimentsNom(new BatimentVisiteur());
    }

    public void setNumeroPremierePiece(int numeroPremierePiece) {
        this.campus.setNumeroPremierePiece(numeroPremierePiece);
    }

    public void setNumeroPremierEtage(int numeroPremierEtage) {
        this.campus.setNumeroPremierEtage(numeroPremierEtage);
    }

    public void setNombreEtage(int nombreEtage) {
        this.campus.setNombreEtages(nombreEtage);
    }

    public void setUsage(String usage) {
        this.campus.setUsage(usage);
    }

    public boolean ajouterBatiment(String nom, int nbPieceParEtage, int nbBureau, int surfacePiece) {
        return this.campus.ajouterBatiment(nom, nbPieceParEtage, nbBureau, surfacePiece) != -1;
    }

    public boolean ajouterBatiments(File file) {
        return this.campus.ajouterBatiments(file) != null;
    }

    @Override
    public void update() {
    }
}
