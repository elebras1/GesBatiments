package org.elebras.gesbatiments.visiteur;

/**
 * L'interface {@code Visitable} représente le contrat pour les éléments pouvant être visités
 * par un objet implémentant l'interface {@link Visiteur}.
 *
 * En utilisant le design pattern Visiteur, cette interface permet de découpler les opérations
 * appliquées aux éléments de leur structure.
 */
public interface Visitable {

    /**
     * Accepte un visiteur et lui permet d'exécuter une opération sur l'élément actuel.
     *
     * @param visiteur L'objet visiteur qui exécutera une opération sur cet élément.
     * @see Visiteur
     */
    void accept(Visiteur visiteur);
}
