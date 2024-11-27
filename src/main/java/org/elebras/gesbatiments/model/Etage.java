package org.elebras.gesbatiments.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.elebras.gesbatiments.visiteur.Visitable;
import org.elebras.gesbatiments.visiteur.Visiteur;

import java.util.Objects;

public class Etage implements Visitable {
    private Integer numero;

    @JsonCreator
    public Etage(@JsonProperty("numero") Integer numero) {
        this.numero = numero;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
    public void accept(Visiteur visiteur) {
        visiteur.visite(this);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Etage etage)) return false;

        return Objects.equals(numero, etage.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numero);
    }

    @Override
    public String toString() {
        return "Etage{" +
                "numero=" + numero +
                '}';
    }
}
