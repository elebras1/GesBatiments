package org.elebras.gesbatiments.facade;

import org.elebras.gesbatiments.model.AjouterBatimentResult;
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

    public AjouterBatimentResult ajouterBatiment(String nom, int nbPieceParEtage, int nbBureau, int surfacePiece) {
        for (Batiment batimentVoisin : this.batiments) {
            if (nom.equals(batimentVoisin.getNom())) {
                return AjouterBatimentResult.BATIMENT_DEJA_EXISTANT;
            }
        }

        Batiment batiment = this.factory.construire(nom, nbPieceParEtage, nbBureau, surfacePiece);
        if (batiment == null) {
            return AjouterBatimentResult.PARAMETRES_INVALIDES;
        }

        this.batiments.add(batiment);
        this.notifyObservers();
        return AjouterBatimentResult.SUCCESS;
    }


    public List<Integer> ajouterBatiments(File file) {
        List<Batiment> batimentsConstruits = this.jsonFactory.creerListeBatimentsByJson(file);
        List<Integer> batimentsNumero = new ArrayList<>();

        for (Batiment batiment : batimentsConstruits) {
            boolean nomExistant = this.batiments.stream()
                    .anyMatch(b -> b.getNom().equals(batiment.getNom()));

            if (!nomExistant && new BatimentVerificateur().verifier(batiment)) {
                this.factory.incrementNombreBatiment();
                batiment.setNumero(this.factory.getNombreBatiment());
                this.batiments.add(batiment);
                batimentsNumero.add(batiment.getNumero());
            }
        }

        this.notifyObservers();
        return batimentsNumero;
    }


    public void setNumeroPremierePiece(int numeroPremierePiece) {
        this.factory.setNumeroPremierePiece(numeroPremierePiece);
    }

    public void setNumeroPremierEtage(int numeroPremierEtage) {
        this.factory.setNumeroPremierEtage(numeroPremierEtage);
    }

    public void setNombreEtages(int nombreEtage) {
        this.factory.setNombreEtages(nombreEtage);
    }

    public void setUsage(String usage) {
        this.factory.setUsage(usage);
    }

    public String modifierNomBatiment(int numeroBatiment, String nomBatiment) {
        for (Batiment batimentVoisin : this.batiments) {
            if(!batimentVoisin.getNumero().equals(numeroBatiment) && nomBatiment.equals(batimentVoisin.getNom())) {
                return null;
            }
        }

        for (Batiment batiment : this.batiments) {
            if (batiment.getNumero().equals(numeroBatiment)) {
                batiment.setNom(nomBatiment);
                this.notifyObservers();
                return batiment.getNom();
            }
        }

        return null;
    }

    public int getNumeroBatiment(String nomBatiment) {
        for (Batiment batiment : this.batiments) {
            if (batiment.getNom().equals(nomBatiment)) {
                return batiment.getNumero();
            }
        }
        return -1;
    }

    public String getNomBatiment(int numeroBatiment) {
        for (Batiment batiment : this.batiments) {
            if (batiment.getNumero().equals(numeroBatiment)) {
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

    public List<String> afficherBatimentsNom(Visiteur visiteur) {
        List<String> noms = new ArrayList<>();
        for(Batiment batiment : batiments) {
            noms.add(batiment.getNom());
        }
        return noms;
    }

    public String afficherBatiment(Visiteur visiteur, int numeroBatiment) {
        for(Batiment batiment : batiments) {
            if(batiment.getNumero() == numeroBatiment) {
                batiment.accept(visiteur);
            }
        }

        return visiteur.getResult();
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
