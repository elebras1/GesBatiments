package org.elebras.gesbatiments.facade;

import org.elebras.gesbatiments.model.Batiment;
import org.elebras.gesbatiments.factory.BatimentJsonFactory;
import org.elebras.gesbatiments.observer.Observable;
import org.elebras.gesbatiments.observer.Observer;
import org.elebras.gesbatiments.verificateur.BatimentVerificateur;
import org.elebras.gesbatiments.factory.BatimentFactory;
import org.elebras.gesbatiments.verificateur.VerificationResultat;
import org.elebras.gesbatiments.visiteur.Visiteur;

import java.io.File;
import java.util.*;

import static org.elebras.gesbatiments.verificateur.VerificationResultat.AUCUNE_ERREUR;


/**
 * Représente un campus composé de plusieurs bâtiments.
 * Le campus permet d'ajouter, de modifier, de supprimer et d'afficher des bâtiments.
 * Il prend également en charge l'observation des changements et la vérification des détails des bâtiments.
 */
public class Campus implements Observable {

    /**
     * Ensemble des bâtiments du campus.
     */
    Set<Batiment> batiments;

    /**
     * Fabrique de bâtiments pour la création de nouveaux bâtiments.
     */
    BatimentFactory factory;

    /**
     * Fabrique de bâtiments pour la création de bâtiments à partir de fichiers JSON.
     */
    BatimentJsonFactory jsonFactory;

    /**
     * Liste des observateurs du campus.
     */
    List<Observer> observers;

    /**
     * Constructeur de la classe {@code Campus}.
     * Initialise l'ensemble des bâtiments, les fabriques de bâtiments et la liste des observateurs.
     */
    public Campus() {
        this.batiments = new HashSet<>();
        this.factory = new BatimentFactory();
        this.jsonFactory = new BatimentJsonFactory();
        this.observers = new ArrayList<>();
    }

    /**
     * Ajoute un bâtiment au campus.
     * Vérifie si le bâtiment existe déjà et si les paramètres sont valides.
     * Si le bâtiment est valide, il est ajouté à l'ensemble des bâtiments du campus.
     *
     * @param nom Nom du bâtiment à ajouter.
     * @param nbPieceParEtage Nombre de pièces par étage du bâtiment.
     * @param nbBureau Nombre de bureaux du bâtiment.
     * @param surfacePiece Surface d'une pièce du bâtiment.
     * @return Le résultat de l'ajout du bâtiment.
     */
    public AjouterBatimentResultat ajouterBatiment(String nom, int nbPieceParEtage, int nbBureau, int surfacePiece) {
        for (Batiment batimentVoisin : this.batiments) {
            if (nom.equals(batimentVoisin.getNom())) {
                return AjouterBatimentResultat.BATIMENT_DEJA_EXISTANT;
            }
        }

        Batiment batiment = this.factory.construire(nom, nbPieceParEtage, nbBureau, surfacePiece);
        if (batiment == null) {
            return AjouterBatimentResultat.PARAMETRES_INVALIDES;
        }

        this.batiments.add(batiment);
        this.notifyObservers();
        return AjouterBatimentResultat.SUCCESS;
    }

    /**
     * Ajoute un ensemble de bâtiments à partir d'un fichier JSON.
     * Vérifie si les bâtiments existent déjà et si les paramètres sont valides.
     * Si les bâtiments sont valides, ils sont ajoutés à l'ensemble des bâtiments du campus.
     *
     * @param file Fichier JSON contenant les bâtiments à ajouter.
     * @return La liste des résultats de l'ajout des bâtiments.
     */
    public Map<String, VerificationResultat> ajouterBatiments(File file) {
        BatimentVerificateur batimentVerificateur = new BatimentVerificateur();
        Map<String, VerificationResultat> batimentsResultat = new HashMap<>();
        List<Batiment> batimentsConstruits = this.jsonFactory.creerListeBatimentsByJson(file);

        for (Batiment batiment : batimentsConstruits) {
            boolean nomExistant = this.batiments.stream()
                    .anyMatch(b -> b.getNom().equals(batiment.getNom()));

            VerificationResultat batimentResultat = batimentVerificateur.verifier(batiment);
            if (!nomExistant && batimentResultat.equals(AUCUNE_ERREUR)) {
                this.factory.incrementNombreBatiment();
                batiment.setNumero(this.factory.getNombreBatiment());
                this.batiments.add(batiment);
                batimentsResultat.put(batiment.getNom(), AUCUNE_ERREUR);
            } else {
                batimentsResultat.put(batiment.getNom(), batimentResultat);
            }
        }

        this.notifyObservers();
        return batimentsResultat;
    }

    /**
     * Définit le numéro de la première pièce à construire.
     *
     * @param numeroPremierePiece le numéro de la première pièce.
     */
    public void setNumeroPremierePiece(int numeroPremierePiece) {
        this.factory.setNumeroPremierePiece(numeroPremierePiece);
    }

    /**
     * Retourne le numéro de la première pièce à construire.
     *
     * @return le numéro de la première pièce.
     */
    public int getNumeroPremierePiece() {
        return this.factory.getNumeroPremierePiece();
    }

    /**
     * Définit le numéro du premier étage à construire.
     *
     * @param numeroPremierEtage le numéro du premier étage.
     */
    public void setNumeroPremierEtage(int numeroPremierEtage) {
        this.factory.setNumeroPremierEtage(numeroPremierEtage);
    }

    /**
     * Retourne le numéro du premier étage à construire.
     *
     * @return le numéro du premier étage.
     */
    public int getNumeroPremiereEtage() {
        return this.factory.getNumeroPremierEtage();
    }

    /**
     * Définit le nombre d'étages à construire.
     *
     * @param nombreEtage le nombre d'étages
     */
    public void setNombreEtages(int nombreEtage) {
        this.factory.setNombreEtages(nombreEtage);
    }

    /**
     * Retourne le nombre d'étages à construire.
     *
     * @return le nombre d'étages.
     */
    public int getNombreEtages() {
        return this.factory.getNombreEtages();
    }

    /**
     * Définit l'usage du bâtiment.
     *
     * @param usage l'usage du bâtiment.
     */
    public void setUsage(String usage) {
        this.factory.setUsage(usage);
    }

    public String getUsage() {
        return this.factory.getUsage();
    }

    /**
     * Modifie le nom d'un bâtiment existant.
     * Vérifie si le nom du bâtiment est déjà pris.
     * Si le nom est valide, il est modifié pour le bâtiment correspondant.
     *
     * @param numeroBatiment Numéro du bâtiment à modifier.
     * @param nomBatiment Nouveau nom du bâtiment.
     * @return Le nom du bâtiment modifié.
     */
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

    /**
     * Récupère le numéro d'un bâtiment à partir de son nom.
     *
     * @param nomBatiment Nom du bâtiment à rechercher.
     * @return Le numéro du bâtiment correspondant.
     */
    public int getNumeroBatiment(String nomBatiment) {
        for (Batiment batiment : this.batiments) {
            if (batiment.getNom().equals(nomBatiment)) {
                return batiment.getNumero();
            }
        }
        return -1;
    }


    /**
     * Récupère le nom d'un bâtiment à partir de son numéro.
     *
     * @param numeroBatiment Numéro du bâtiment à rechercher.
     * @return Le nom du bâtiment correspondant.
     */
    public String getNomBatiment(int numeroBatiment) {
        for (Batiment batiment : this.batiments) {
            if (batiment.getNumero().equals(numeroBatiment)) {
                return batiment.getNom();
            }
        }
        return null;
    }

    /**
     * Supprime un bâtiment du campus.
     * Vérifie si le bâtiment existe et le supprime de l'ensemble des bâtiments du campus.
     *
     * @param numeroBatiment Numéro du bâtiment à supprimer.
     * @return {@code true} si le bâtiment a été supprimé, {@code false} sinon.
     */
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

    /**
     * Affiche les noms des bâtiments du campus.
     *
     * @return La liste des noms des bâtiments du campus.
     */
    public List<String> afficherBatimentsNom() {
        List<String> noms = new ArrayList<>();
        for(Batiment batiment : batiments) {
            noms.add(batiment.getNom());
        }
        return noms;
    }

    /**
     * Affiche les détails d'un bâtiment du campus.
     *
     * @param visiteur Visiteur pour afficher les détails du bâtiment.
     * @param numeroBatiment Numéro du bâtiment à afficher.
     * @return Les détails du bâtiment.
     */
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
