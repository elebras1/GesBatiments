package org.elebras.gesbatiments.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.elebras.gesbatiments.visiteur.Visitable;
import org.elebras.gesbatiments.visiteur.Visiteur;

/**
 * Représente une pièce d'un bâtiment, avec des attributs tels que sa surface, son numéro et son
 * étage associé. Cette classe implémente l'interface {@link Visitable} permettant l'application
 * d'un visiteur sur la pièce.
 */
public class Piece implements Visitable {

  /**
   * La surface de la pièce, en mètres carrés.
   */
  private Integer surface;

  /**
   * Indique si la pièce est un bureau ou non.
   */
  private Boolean estBureau;

  /**
   * Le numéro de la pièce.
   */
  private Integer numero;

  /**
   * L'étage auquel appartient cette pièce.
   */
  private Etage etage;

  /**
   * Constructeur de la classe Piece.
   *
   * @param surface La surface de la pièce en mètres carrés.
   * @param estBureau {@code true} si la pièce est un bureau, {@code false} sinon.
   * @param numero Le numéro de la pièce.
   * @param etage L'étage auquel appartient la pièce.
   */
  @JsonCreator
  public Piece(@JsonProperty("surface") Integer surface,
      @JsonProperty("estBureau") Boolean estBureau, @JsonProperty("numero") Integer numero,
      @JsonProperty("etage") Etage etage) {
    this.surface = surface;
    this.estBureau = estBureau;
    this.numero = numero;
    this.etage = etage;
  }

  /**
   * Retourne la surface de la pièce.
   *
   * @return La surface de la pièce en mètres carrés.
   */
  public Integer getSurface() {
    return surface;
  }

  /**
   * Définit la surface de la pièce.
   *
   * @param surface La surface de la pièce en mètres carrés.
   */
  public void setSurface(Integer surface) {
    this.surface = surface;
  }

  /**
   * Retourne {@code true} si la pièce est un bureau, {@code false} sinon.
   *
   * @return {@code true} si la pièce est un bureau, {@code false} sinon.
   */
  public Boolean getEstBureau() {
    return estBureau;
  }

  /**
   * Définit si la pièce est un bureau ou non.
   *
   * @param estBureau {@code true} si la pièce est un bureau, {@code false} sinon.
   */
  public void setEstBureau(Boolean estBureau) {
    this.estBureau = estBureau;
  }

  /**
   * Retourne le numéro de la pièce.
   *
   * @return Le numéro de la pièce.
   */
  public Integer getNumero() {
    return numero;
  }

  /**
   * Définit le numéro de la pièce.
   *
   * @param numero Le numéro de la pièce.
   */
  public void setNumero(Integer numero) {
    this.numero = numero;
  }

  /**
   * Retourne l'étage auquel appartient la pièce.
   *
   * @return L'étage auquel appartient la pièce.
   */
  public Etage getEtage() {
    return etage;
  }

  /**
   * Définit l'étage auquel appartient la pièce.
   *
   * @param etage L'étage auquel appartient la pièce.
   */
  public void setEtage(Etage etage) {
    this.etage = etage;
  }

  /**
   * Accepte un visiteur pour appliquer un traitement sur cette pièce.
   *
   * @param visiteur Le visiteur à accepter.
   */
  @Override
  public void accept(Visiteur visiteur) {
    visiteur.visite(this);
  }

  /**
   * Retourne une représentation sous forme de chaîne de caractères de la pièce.
   *
   * @return Une chaîne de caractères représentant la pièce.
   */
  @Override
  public String toString() {
    return "Piece{" + "surface=" + surface + ", estBureau=" + estBureau + ", numero=" + numero
        + ", etage=" + etage + '}';
  }
}
