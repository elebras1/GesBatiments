package org.elebras.gesbatiments.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.elebras.gesbatiments.visiteur.Visitable;
import org.elebras.gesbatiments.visiteur.Visiteur;

public class Piece implements Visitable {
    private Integer surface;
    private Boolean estBureau;
    private final Integer numero;
    private Etage etage;

    @JsonCreator
    public Piece(@JsonProperty("surface") Integer surface,
                 @JsonProperty("estBureau") Boolean estBureau,
                 @JsonProperty("numero") Integer numero,
                 @JsonProperty("etage") Etage etage) {
        this.surface = surface;
        this.estBureau = estBureau;
        this.numero = numero;
        this.etage = etage;
    }

    public Integer getSurface() {
        return surface;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
    }

    public Boolean getEstBureau() {
        return estBureau;
    }

    public void setEstBureau(Boolean estBureau) {
        this.estBureau = estBureau;
    }

    public Integer getNumero() {
        return numero;
    }

    public Etage getEtage() {
        return etage;
    }

    public void setEtage(Etage etage) {
        this.etage = etage;
    }

    @Override
    public void accept(Visiteur visiteur) {
        visiteur.visite(this);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "surface=" + surface +
                ", estBureau=" + estBureau +
                ", numero=" + numero +
                ", etage=" + etage +
                '}';
    }
}
