package org.elebras.gesbatiments.observer;

/**
 * Interface pour un observateur dans le patron de conception "Observer".
 * Un observateur est notifié lorsqu'un objet observable change.
 */
public interface Observer {

    /**
     * Méthode appelée pour notifier l'observateur d'un changement.
     */
    void update();
}
