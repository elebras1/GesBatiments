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

    public void afficherBatiment() {
        this.campus.afficherBatiment(new BatimentVisiteur(), numeroBatiment);
    }

    @Override
    public void update() {

    }
}
