package org.elebras.gesbatiments;

import org.elebras.gesbatiments.model.*;
import org.elebras.gesbatiments.facade.Campus;
import org.elebras.gesbatiments.factory.BatimentFactory;
import org.elebras.gesbatiments.factory.BatimentJsonFactory;
import org.elebras.gesbatiments.verificateur.BatimentVerificateur;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] t) {
        BatimentFactory factory = new BatimentFactory();

        Batiment batimentA = factory.construire("A", 12, 1, 19);
        System.out.println(batimentA);
        System.out.println("Nombre de pieces : " + batimentA.getPieces().size());

        factory.setNumeroPremierePiece(2);
        factory.setNumeroPremierEtage(2);
        factory.setNombreEtages(3);
        factory.setUsage("Education");
        Batiment batimentB = factory.construire("B", 12, 8, 20);
        System.out.println(batimentB);
        System.out.println("Nombre de pieces : " + batimentB.getPieces().size());

        factory.setNombreEtages(18);
        factory.setUsage("Administration");
        Batiment batimentC = factory.construire("B", 11, 22, 18);
        System.out.println(batimentC);
        System.out.println("Nombre de pieces : " + batimentC.getPieces().size());

        BatimentVerificateur verificateur = BatimentVerificateur.getInstance();
        System.out.println("Batiment A est correct ? : " + verificateur.verifier(batimentA));
        System.out.println("Batiment B est correct ? : " + verificateur.verifier(batimentB));
        System.out.println("Batiment C est correct ? : " + verificateur.verifier(batimentC));

        Campus campus = new Campus();
        campus.ajouterBatiment("Batiment A", 12, 1, 19);
        campus.ajouterBatiment("Batiment B", 11, 22, 18);
        System.out.println("Campus : " + campus);

        BatimentJsonFactory jsonFactory = new BatimentJsonFactory();
        List<Batiment> batiments = new ArrayList<>();
        batiments.add(batimentA);
        batiments.add(batimentB);
        batiments.add(batimentC);
        jsonFactory.creerJsonListeBatiments(new File("target/batiments2.json"), batiments);
        System.out.println("Batiments créer : " + jsonFactory.creerListeBatimentsByJson(new File("target/batiments.json")));
    }
}