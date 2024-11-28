package org.elebras.gesbatiments.observer;

/**
 * Interface pour un objet observable dans le patron de conception "Observer".
 */
public interface Observable {

  /**
   * Ajoute un observateur.
   *
   * @param observer l'observateur à ajouter.
   */
  void addObserver(Observer observer);

  /**
   * Supprime un observateur.
   *
   * @param observer l'observateur à retirer.
   */
  void removeObserver(Observer observer);

  /**
   * Notifie tous les observateurs d'un changement.
   */
  void notifyObservers();
}
