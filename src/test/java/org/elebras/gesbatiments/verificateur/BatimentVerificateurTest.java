package org.elebras.gesbatiments.verificateur;

import org.elebras.gesbatiments.model.*;
import org.elebras.gesbatiments.factory.BatimentFactory;
import org.elebras.gesbatiments.verificateur.BatimentVerificateur;
import org.elebras.gesbatiments.verificateur.VerificationResultat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BatimentVerificateurTest {

    private BatimentFactory factory;

    @BeforeEach
    public void init() {
        this.factory = new BatimentFactory();
    }

    @Test
    public void verificationBatiment1Test() {
        int nombreBatiments = 14;
        String nomCommun = "Immeuble";

        for(int i = 0; i < nombreBatiments; i++) {
            Batiment batiment = this.factory.construire(nomCommun + i, 15, 3, 25);
            assertEquals(new BatimentVerificateur().verifier(batiment), VerificationResultat.AUCUNE_ERREUR);
        }
    }

    @Test
    public void verificationBatiment2Test() {
        List<Piece> pieces = new ArrayList<>();
        List<Etage> etages = new ArrayList<>();
        Etage etage = new Etage(1);
        etages.add(etage);
        pieces.add(new Piece(18, true, 1, etage));
        pieces.add(new Piece(15, true, 2, etage));
        Batiment batiment = new Batiment(2, "Batiment 2", "Education", pieces, etages);
        assertEquals(new BatimentVerificateur().verifier(batiment), VerificationResultat.AUCUNE_ERREUR);
    }

    @Test
    public void verificationBatimentPieceNonOrdonneeTest() {
        List<Piece> pieces = new ArrayList<>();
        List<Etage> etages = new ArrayList<>();
        Etage etage = new Etage(1);
        etages.add(etage);
        pieces.add(new Piece(18, true, 2, etage));
        pieces.add(new Piece(18, true, 4, etage));
        pieces.add(new Piece(18, true, 3, etage));
        Batiment batiment = new Batiment(2, "Batiment 2", "Education", pieces, etages);
        assertEquals(new BatimentVerificateur().verifier(batiment), VerificationResultat.PIECE_ERREUR);
    }

    @Test
    public void verificationBatimentEtageNonOrdonneeTest() {
        List<Piece> pieces = new ArrayList<>();
        List<Etage> etages = new ArrayList<>();
        Etage etage1 = new Etage(2);
        Etage etage2 = new Etage(1);
        etages.add(etage1);
        etages.add(etage2);
        pieces.add(new Piece(18, true, 1, etage1));
        pieces.add(new Piece(18, true, 2, etage1));
        pieces.add(new Piece(18, true, 3, etage1));
        pieces.add(new Piece(18, true, 4, etage2));
        pieces.add(new Piece(18, true, 5, etage2));
        pieces.add(new Piece(18, true, 6, etage2));
        Batiment batiment = new Batiment(2, "Batiment 2", "Education", pieces, etages);
        assertEquals(new BatimentVerificateur().verifier(batiment), VerificationResultat.ETAGE_ERREUR);
    }
}