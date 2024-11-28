package org.elebras.gesbatiments;

import org.elebras.gesbatiments.facade.AjouterBatimentResultat;
import org.elebras.gesbatiments.model.*;
import org.elebras.gesbatiments.facade.Campus;
import org.elebras.gesbatiments.factory.BatimentFactory;
import org.elebras.gesbatiments.factory.BatimentJsonFactory;
import org.elebras.gesbatiments.verificateur.BatimentVerificateur;
import org.elebras.gesbatiments.verificateur.VerificationResultat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] t) {
        // Utilisation des classes Model
        System.out.println("--Model");
        Etage etage = new Etage(1);
        Piece piece = new Piece(11, true, 1, etage);
        ArrayList<Piece> piecesList = new ArrayList<>();
        piecesList.add(piece);
        ArrayList<Etage> etagesList = new ArrayList<>();
        etagesList.add(etage);
        Batiment batiment = new Batiment(10, "batiment info", "education", piecesList, etagesList);
        System.out.println(batiment);

        // Utilisation des classes Factory
        System.out.println("--Factory");
        BatimentFactory factory = new BatimentFactory();
        Batiment batimentA = factory.construire("A", 12, 1, 19);

        factory.setNumeroPremierePiece(2);
        factory.setNumeroPremierEtage(2);
        factory.setNombreEtages(3);
        factory.setUsage("Education");
        Batiment batimentB = factory.construire("B", 12, 8, 20);
        System.out.println(batimentB);
        System.out.println("Nombre de pieces : " + batimentB.getPieces().size());

        factory.setNombreEtages(18);
        factory.setUsage("Administration");
        Batiment batimentC = factory.construire("C", 11, 22, 18);
        System.out.println(batimentC);
        System.out.println("Nombre de pieces : " + batimentC.getPieces().size());

        BatimentJsonFactory jsonFactory = new BatimentJsonFactory();
        List<Batiment> batiments = new ArrayList<>();
        batiments.add(batimentA);
        batiments.add(batimentB);
        batiments.add(batimentC);
        jsonFactory.creerJsonListeBatiments(new File("batiments.json"), batiments);

        System.out.println("--Verificateur");
        //Utilisation des verificateurs
        BatimentVerificateur verificateur = new BatimentVerificateur();
        System.out.println("Batiment A est correct ? : " + verificateur.verifier(batimentA));

        System.out.println("--Campus");
        //Utilisation de Campus
        Campus campus = new Campus();
        AjouterBatimentResultat resultat = campus.ajouterBatiment("Batiment A", 12, 1, 19);
        System.out.println(resultat);
        Map<String, VerificationResultat> resultats = campus.ajouterBatiments(new File("batiments.json"));
        System.out.println(resultats);
    }
}