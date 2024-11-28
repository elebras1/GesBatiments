package org.elebras.gesbatiments.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.elebras.gesbatiments.visiteur.Visitable;
import org.elebras.gesbatiments.visiteur.Visiteur;

import java.util.Objects;

/**
 * Représente un étage dans un bâtiment, identifié par son numéro. Cette classe implémente
 * l'interface {@link Visitable} permettant l'application d'un visiteur sur cet étage.
 */
public class Etage implements Visitable {

  /**
   * Le numéro de l'étage.
   */
  private Integer numero;

  /**
   * Constructeur de la classe Etage.
   *
   * @param numero Le numéro de l'étage.
   */
  @JsonCreator
  public Etage(@JsonProperty("numero") Integer numero) {
    this.numero = numero;
  }

  /**
   * Retourne le numéro de l'étage.
   *
   * @return Le numéro de l'étage.
   */
  public Integer getNumero() {
    return numero;
  }

  /**
   * Définit le numéro de l'étage.
   *
   * @param numero Le numéro de l'étage.
   */
  public void setNumero(Integer numero) {
    this.numero = numero;
  }

  /**
   * Accepte un visiteur pour appliquer un traitement sur cet étage.
   *
   * @param visiteur Le visiteur à accepter.
   */
  @Override
  public void accept(Visiteur visiteur) {
    visiteur.visite(this);
  }

  /**
   * Compare cet étage avec un autre objet pour vérifier s'ils sont égaux. Deux étages sont
   * considérés égaux s'ils ont le même numéro.
   *
   * @param o L'objet à comparer avec cet étage.
   * @return {@code true} si les étages sont égaux, {@code false} sinon.
   */
  @Override
  public final boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Etage etage))
      return false;

    return Objects.equals(numero, etage.numero);
  }

  /**
   * Retourne le code de hachage pour cet étage. Le code de hachage est basé sur le numéro de
   * l'étage.
   *
   * @return Le code de hachage de l'étage.
   */
  @Override
  public int hashCode() {
    return Objects.hashCode(numero);
  }

  /**
   * Retourne une représentation sous forme de chaîne de caractères de l'étage.
   *
   * @return Une chaîne de caractères représentant l'étage.
   */
  @Override
  public String toString() {
    return "Etage{" + "numero=" + numero + '}';
  }
}
