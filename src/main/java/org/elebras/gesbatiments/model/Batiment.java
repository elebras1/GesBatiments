package org.elebras.gesbatiments.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.elebras.gesbatiments.visiteur.Visitable;
import org.elebras.gesbatiments.visiteur.Visiteur;

import java.util.List;
import java.util.Objects;

/**
 * Représente un bâtiment dans le système, avec des informations telles que son numéro, son nom, son
 * usage, les pièces qu'il contient et les étages. Cette classe implémente l'interface
 * {@link Visitable} permettant l'application d'un visiteur sur le bâtiment et ses composants
 * (étages et pièces).
 */
public class Batiment implements Visitable {

  /**
   * Le numéro unique du bâtiment.
   */
  private Integer numero;

  /**
   * Le nom unique du bâtiment.
   */
  private String nom;

  /**
   * L'usage du bâtiment (par exemple, "résidentiel", "commercial", etc.).
   */
  private String usage;

  /**
   * La liste des pièces présentes dans le bâtiment.
   */
  private List<Piece> pieces;

  /**
   * La liste des étages du bâtiment.
   */
  private List<Etage> etages;

  /**
   * Constructeur de la classe Batiment.
   *
   * @param numero Le numéro d'identification du bâtiment.
   * @param nom Le nom du bâtiment.
   * @param usage L'usage du bâtiment.
   * @param pieces La liste des pièces du bâtiment.
   * @param etages La liste des étages du bâtiment.
   */
  @JsonCreator
  public Batiment(@JsonProperty("numero") Integer numero, @JsonProperty("nom") String nom,
      @JsonProperty("usage") String usage, @JsonProperty("pieces") List<Piece> pieces,
      @JsonProperty("etages") List<Etage> etages) {
    this.numero = numero;
    this.nom = nom;
    this.usage = usage;
    this.pieces = pieces;
    this.etages = etages;
  }

  /**
   * Retourne le numéro d'identification du bâtiment.
   *
   * @return Le numéro du bâtiment.
   */
  public Integer getNumero() {
    return numero;
  }

  /**
   * Définit le numéro d'identification du bâtiment.
   *
   * @param numero Le numéro du bâtiment.
   */
  public void setNumero(Integer numero) {
    this.numero = numero;
  }

  /**
   * Retourne le nom du bâtiment.
   *
   * @return Le nom du bâtiment.
   */
  public String getNom() {
    return nom;
  }

  /**
   * Définit le nom du bâtiment.
   *
   * @param nom Le nom du bâtiment.
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Retourne l'usage du bâtiment.
   *
   * @return L'usage du bâtiment.
   */
  public String getUsage() {
    return usage;
  }

  /**
   * Définit l'usage du bâtiment.
   *
   * @param usage L'usage du bâtiment.
   */
  public void setUsage(String usage) {
    this.usage = usage;
  }

  /**
   * Retourne la liste des pièces du bâtiment.
   *
   * @return La liste des pièces.
   */
  public List<Piece> getPieces() {
    return pieces;
  }

  /**
   * Définit la liste des pièces du bâtiment.
   *
   * @param pieces La liste des pièces à associer au bâtiment.
   */
  public void setPieces(List<Piece> pieces) {
    this.pieces = pieces;
  }

  /**
   * Retourne la liste des étages du bâtiment.
   *
   * @return La liste des étages.
   */
  public List<Etage> getEtages() {
    return etages;
  }

  /**
   * Définit la liste des étages du bâtiment.
   *
   * @param etages La liste des étages à associer au bâtiment.
   */
  public void setEtages(List<Etage> etages) {
    this.etages = etages;
  }

  /**
   * Accepte un visiteur pour appliquer un traitement sur ce bâtiment et ses composants (étages et
   * pièces).
   *
   * @param visiteur Le visiteur à accepter.
   */
  @Override
  public void accept(Visiteur visiteur) {
    visiteur.visite(this);
    for (Etage etage : etages) {
      etage.accept(visiteur);
      for (Piece piece : pieces) {
        if (piece.getEtage() != null && piece.getEtage().equals(etage)) {
          piece.accept(visiteur);
        }
      }
    }
  }

  /**
   * Compare ce bâtiment avec un autre objet pour vérifier s'ils sont égaux. Deux bâtiments sont
   * considérés égaux s'ils ont le même numéro et nom.
   *
   * @param o L'objet à comparer avec ce bâtiment.
   * @return {@code true} si les bâtiments sont égaux, {@code false} sinon.
   */
  @Override
  public final boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Batiment batiment))
      return false;

    return Objects.equals(numero, batiment.numero) && Objects.equals(nom, batiment.nom);
  }

  /**
   * Retourne le code de hachage pour ce bâtiment. Le code de hachage est basé sur le numéro et le
   * nom du bâtiment.
   *
   * @return Le code de hachage du bâtiment.
   */
  @Override
  public int hashCode() {
    int result = Objects.hashCode(numero);
    result = 31 * result + Objects.hashCode(nom);
    return result;
  }

  /**
   * Retourne une représentation sous forme de chaîne de caractères du bâtiment.
   *
   * @return Une chaîne de caractères représentant le bâtiment.
   */
  @Override
  public String toString() {
    return "Batiment{" + "numero=" + numero + ", nom='" + nom + '\'' + ", usage='" + usage + '\''
        + ", pieces=" + pieces + ", etages=" + etages + '}';
  }
}
