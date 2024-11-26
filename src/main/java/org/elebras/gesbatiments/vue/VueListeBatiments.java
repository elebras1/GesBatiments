package org.elebras.gesbatiments.vue;

import org.elebras.gesbatiments.facade.Campus;
import org.elebras.gesbatiments.observer.Observer;
import org.elebras.gesbatiments.visiteur.BatimentVisiteur;

public class VueListeBatiments implements Observer {
    private Campus campus;

    public VueListeBatiments(Campus campus) {
        this.campus = campus;
        this.campus.addObserver(this);
    }

    public void afficherListeBatiments() {
        this.campus.afficherBatiments(new BatimentVisiteur());
    }

    @Override
    public void update() {
        this.afficherListeBatiments();
    }
}
