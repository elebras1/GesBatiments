package org.elebras.gesbatiments.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.elebras.gesbatiments.visiteur.Visitable;
import org.elebras.gesbatiments.visiteur.Visiteur;

import java.util.List;
import java.util.Objects;

public class Batiment implements Visitable {
    private Integer numero;
    private String nom;
    private String usage;
    private List<Piece> pieces;
    private List<Etage> etages;

    @JsonCreator
    public Batiment(@JsonProperty("numero") Integer numero,
                    @JsonProperty("nom") String nom,
                    @JsonProperty("usage") String usage,
                    @JsonProperty("pieces") List<Piece> pieces,
                    @JsonProperty("etages") List<Etage> etages
    ) {
        this.numero = numero;
        this.nom = nom;
        this.usage = usage;
        this.pieces = pieces;
        this.etages = etages;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public List<Etage> getEtages() {
        return etages;
    }

    public void setEtages(List<Etage> etages) {
        this.etages = etages;
    }

    @Override
    public void accept(Visiteur visiteur) {
        visiteur.visite(this);
        for (Etage etage : etages) {
            etage.accept(visiteur);
        }
        for (Piece piece : pieces) {
            piece.accept(visiteur);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Batiment batiment)) return false;
        return Objects.equals(numero, batiment.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "Batiment{" +
                "numero=" + numero +
                ", nom='" + nom + '\'' +
                ", usage='" + usage + '\'' +
                ", pieces=" + pieces +
                ", etages=" + etages +
                '}';
    }
}
