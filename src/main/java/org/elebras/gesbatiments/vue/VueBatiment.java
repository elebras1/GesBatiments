package org.elebras.gesbatiments.vue;

import org.elebras.gesbatiments.facade.Campus;
import org.elebras.gesbatiments.observer.Observer;
import org.elebras.gesbatiments.visiteur.BatimentVisiteur;

public class VueBatiment implements Observer {
    private Campus campus;
    private int numeroBatiment;

    public VueBatiment(Campus campus, int numeroBatiment) {
        this.campus = campus;
        this.numeroBatiment = numeroBatiment;
        this.campus.addObserver(this);
    }

    public String afficherBatiment() {
        return this.campus.afficherBatiment(new BatimentVisiteur(), numeroBatiment);
    }

    public boolean modifierNumeroBatiment(int nouveauNumero) {
        Integer numeroModifier = this.campus.modifierNumeroBatiment(numeroBatiment, nouveauNumero);
        if(numeroModifier != null) {
            this.numeroBatiment = numeroModifier;
            return true;
        }

        return false;
    }

    public boolean modifierNomBatiment(String nouveauNom) {
        String nomModifier = this.campus.modifierNomBatiment(numeroBatiment, nouveauNom);
        return nomModifier != null;
    }

    public boolean supprimerBatiment() {
        return this.campus.supprimeBatiment(numeroBatiment);
    }

    @Override
    public void update() {

    }
}
