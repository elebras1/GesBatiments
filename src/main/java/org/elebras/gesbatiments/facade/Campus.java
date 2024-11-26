package org.elebras.gesbatiments.facade;

import org.elebras.gesbatiments.model.Batiment;
import org.elebras.gesbatiments.factory.BatimentJsonFactory;
import org.elebras.gesbatiments.observer.Observable;
import org.elebras.gesbatiments.observer.Observer;
import org.elebras.gesbatiments.verificateur.BatimentVerificateur;
import org.elebras.gesbatiments.factory.BatimentFactory;
import org.elebras.gesbatiments.visiteur.Visiteur;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Campus implements Observable {
    Set<Batiment> batiments;
    BatimentFactory factory;
    BatimentJsonFactory jsonFactory;
    List<Observer> observers;

    public Campus() {
        this.batiments = new HashSet<>();
        this.factory = new BatimentFactory();
        this.jsonFactory = new BatimentJsonFactory();
        this.observers = new ArrayList<>();
    }

    public int construireBatiment(String nom, int nbPieceParEtage, int nbBureau, int surfacePiece) {
        Batiment batiment = this.factory.construire(nom, nbPieceParEtage, nbBureau, surfacePiece);
        this.batiments.add(batiment);
        this.notifyObservers();
        return batiment.getNumero();
    }

    public List<Integer> construireBatiments(File file) {
        List<Batiment> batimentsConstruits = this.jsonFactory.creerListeBatimentsByJson(file);

        List<Integer> batimentsNumero = new ArrayList<>();
        for(Batiment batiment : batimentsConstruits) {
            if(BatimentVerificateur.getInstance().verifier(batiment)) {
                return null;
            }

            batimentsNumero.add(batiment.getNumero());
        }

        this.batiments.addAll(batimentsConstruits);
        this.notifyObservers();

        return batimentsNumero;
    }


    public void setNumeroPremierePieceConstruction(int numeroPremierePiece) {
        this.factory.setNumeroPremierePiece(numeroPremierePiece);
    }

    public void setNumeroPremierEtageConstruction(int numeroPremierEtage) {
        this.factory.setNumeroPremierEtage(numeroPremierEtage);
    }

    public void setNombreEtagesConstruction(int nombreEtage) {
        this.factory.setNombreEtages(nombreEtage);
    }

    public void setUsageConstruction(String usage) {
        this.factory.setUsage(usage);
    }

    public String modifierNomBatiment(int numeroBatiment, String nomBatiment) {
        for (Batiment batiment : this.batiments) {
            if (batiment.getNumero().equals(numeroBatiment)) {
                for (Batiment batimentVoisin : this.batiments) {
                    if (batiment != batimentVoisin && batiment.getNom().equals(batimentVoisin.getNom())) {
                        return null;
                    }
                }
                batiment.setNom(nomBatiment);
                this.notifyObservers();
                return batiment.getNom();
            }
        }
        return null;
    }


    public boolean supprimeBatiment(int numeroBatiment) {
        for (Batiment batiment : this.batiments) {
            if (batiment.getNumero().equals(numeroBatiment)) {
                this.batiments.remove(batiment);
                this.notifyObservers();
                return true;
            }
        }
        return false;
    }


    public boolean verification(int numeroBatiment) {
        for (Batiment batiment : this.batiments) {
            if(batiment.getNumero().equals(numeroBatiment)) {
                this.notifyObservers();
                return BatimentVerificateur.getInstance().verifier(batiment);
            }
        }
        this.notifyObservers();

        return false;
    }

    public void afficherBatiments(Visiteur visiteur) {
        for (Batiment batiment : batiments) {
            batiment.accept(visiteur);
        }
    }

    public void afficherBatiment(Visiteur visiteur, int numeroBatiment) {
        for(Batiment batiment : batiments) {
            if(batiment.getNumero() == numeroBatiment) {
                batiment.accept(visiteur);
            }
        }
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : this.observers) {
            observer.update();
        }
    }

    @Override
    public String toString() {
        return "Campus{" +
                "batiments=" + batiments +
                ", factory=" + factory +
                '}';
    }
}
