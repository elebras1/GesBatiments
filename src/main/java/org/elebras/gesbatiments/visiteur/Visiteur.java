package org.elebras.gesbatiments.visiteur;

import org.elebras.gesbatiments.model.Batiment;
import org.elebras.gesbatiments.model.Etage;
import org.elebras.gesbatiments.model.Piece;

/**
 * L'interface {@code Visiteur} définit les méthodes nécessaires pour implémenter le design pattern
 * Visiteur dans le contexte des bâtiments, étages et pièces.
 *
 * Un visiteur permet d'effectuer des opérations sur une structure composée d'éléments
 * ({@link Batiment}, {@link Etage}, {@link Piece}) sans modifier leurs classes.
 */
public interface Visiteur {

  /**
   * Applique une opération spécifique sur un objet {@link Batiment}.
   *
   * @param batiment Le bâtiment à visiter.
   */
  void visite(Batiment batiment);

  /**
   * Applique une opération spécifique sur un objet {@link Etage}.
   *
   * @param etage L'étage à visiter.
   */
  void visite(Etage etage);

  /**
   * Applique une opération spécifique sur un objet {@link Piece}.
   *
   * @param piece La pièce à visiter.
   */
  void visite(Piece piece);

  /**
   * Récupère le résultat des opérations effectuées par le visiteur.
   *
   * @return Une chaîne de caractères représentant le résultat de la visite.
   */
  String getResult();
}
