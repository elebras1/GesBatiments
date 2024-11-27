package org.elebras.gesbatiments.facade;

/**
 * Représente les différents résultats possibles lors de l'ajout d'un bâtiment.
 * Cette énumération permet d'indiquer si l'ajout d'un bâtiment a réussi ou a échoué,
 * et si l'échec est dû à un bâtiment déjà existant ou à des paramètres invalides.
 */
public enum AjouterBatimentResultat {

    /**
     * Résultat indiquant que l'ajout du bâtiment a réussi.
     */
    SUCCESS,

    /**
     * Résultat indiquant que le bâtiment à ajouter existe déjà.
     */
    BATIMENT_DEJA_EXISTANT,

    /**
     * Résultat indiquant que les paramètres fournis pour l'ajout du bâtiment sont invalides.
     */
    PARAMETRES_INVALIDES
}
