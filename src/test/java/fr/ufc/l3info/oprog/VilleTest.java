package fr.ufc.l3info.oprog;

//import org.graalvm.compiler.hotspot.replacements.AssertionSnippets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class VilleTest {

    private Ville v;

    @Before
    public void beforeVille() {
        this.v = new Ville();
    }

    /* Constructeur Ville() */

    /* void initialiser(File f) throws IOException */
    @Test
    public void initialiserTestOk() throws IOException{
        v.initialiser(new File("./target/classes/data/stationsOK.txt"));
    }

    @Test(expected = IOException.class)
    public void initialiserTestMauvaisChemin() throws IOException {
        v.initialiser(new File("./target/classes/data/sta.txt"));
    }

    @Test (expected = IOException.class)
    public void initialiserTestErreurFichier() throws IOException{
        v.initialiser(new File("./target/classes/data/stationsMultipleErreur.txt"));
    }

    // manque les test pour "On notera que chaque appel à cette méthode aura pour effet de réinitialiser l'ensemble des stations existantes. "

    /* void setStationPrincipale(String st) */
    @Test
    public void setStationPrincipaleTest() {
        v.setStationPrincipale("Avenue du Maréchal Foch");
        ClosestStationIterator it = (ClosestStationIterator) v.iterator();
        Assert.assertEquals("Avenue du Maréchal Foch", it.next().getNom());
    }

    /* Station getStation(String nom) */
    @Test
    public void getStationTestOk() throws IOException{
        v.initialiser(new File("./target/classes/data/stationsOK.txt"));
        Station s = v.getStation("Avenue du Maréchal Foch");
        Assert.assertNotEquals(null, s);
        Assert.assertEquals("Avenue du Maréchal Foch", s.getNom());
    }

    @Test
    public void getStationTestStationInexistante() throws IOException{
        v.initialiser(new File("./target/classes/data/stationsOK.txt"));
        Station s = v.getStation("Avenue");
        Assert.assertNull(s);
    }

    /* Station getStationPlusProche(double lat, double lon) */
    @Test
    public void getStationPlusProcheTestPremiereStation() throws IOException {
        v.initialiser(new File("./target/classes/data/stationsOK.txt"));
        Station s = v.getStationPlusProche(47.247761, 5.983599);
        Assert.assertNotEquals(null, s);
        Assert.assertEquals("21 - Avenue Fontaine Argent, Boulevard Diderot", s.getNom());
    }

    @Test
    public void getStationPlusProcheTestDeuxiemeStation() throws IOException{
        v.initialiser(new File("./target/classes/data/stationsOK.txt"));
        Station s = v.getStationPlusProche(47.24651, 6.02267);
        //Assert.assertNotNull(s);
        Assert.assertEquals("Avenue du Maréchal Foch", s.getNom());
    }

    /* Abonne creerAbonne(String nom, String RIB) */
    @Test
    public void creerAbonneTestOk() {
        Abonne a = v.creerAbonne("Theo", "12345-55555-11111111111-47");
        Assert.assertEquals("Theo", a.getNom());
    }

    @Test
    public void creerAbonneTestNomVide() {
        Assert.assertNull(v.creerAbonne("", "12345-55555-11111111111-47"));
    }

    @Test
    public void creerAbonneTestRibVide() {
        Abonne a = v.creerAbonne("Theo", "");
        Assert.assertTrue(a.estBloque());
    }

    /* Iterator<Station> iterator() */
    @Test
    public void iteratorTestStationPrincipale() throws IOException{ //bizarre
        v.initialiser(new File("./target/classes/data/stationsOK.txt"));
        ClosestStationIterator it = (ClosestStationIterator) v.iterator();
        v.setStationPrincipale("Avenue du Maréchal Foch");
        Assert.assertEquals("Avenue du Maréchal Foch", it.next().getNom());
    }

    @Test
    public void iteratorTestNombre() throws IOException{ //bizarre
        v.initialiser(new File("./target/classes/data/stationsOK.txt"));
        ClosestStationIterator it = (ClosestStationIterator) v.iterator();
        int i = 0;
        while(it.hasNext()) {
            it.next();
            i++;
        }
        Assert.assertEquals(2, i);
    }

    /* Map<Abonne, Double> facturation(int mois, int annee)  */
    @Test
    public void facturationTestDateInvalide() {
        Assert.assertNull(v.facturation(0, 2022));
        Assert.assertNull(v.facturation(13, 2022));
        Assert.assertNull(v.facturation(1, 2023));
        Assert.assertNull(v.facturation(1, -1));
    }

    @Test
    public void facturationTest() throws IncorrectNameException{
        Abonne theo = new Abonne("Theo", "12345-55555-11111111111-47");
        Abonne romain = new Abonne("Romain", "12345-55555-22222222222-47");
        Abonne hugo = new Abonne("Hugo", "12345-55555-33333333333-47");

        IVelo ve [] = new IVelo [5];
        FabriqueVelo fab = FabriqueVelo.getInstance();
        for(int i = 0; i < 5; i++) {
            ve[i] = fab.construire('h', "CADRE_ALUMINIUM");
        }

        IRegistre r = new JRegistre();
        r.emprunter(theo, ve[0], System.currentTimeMillis());
        r.emprunter(romain, ve[1], System.currentTimeMillis());
        r.emprunter(hugo, ve[2], System.currentTimeMillis());
        r.emprunter(theo, ve[3], System.currentTimeMillis());
        r.emprunter(romain, ve[4], System.currentTimeMillis());

        for(int i = 0; i < 5; i++) r.retourner(ve[i], System.currentTimeMillis() + 3600000);
        // == emprunt velo cadre alu 1h (x2 pour Théo)

        Map<Abonne, Double> facturation_mois;
        facturation_mois = v.facturation(11, 2022);

        Assert.assertEquals(3, facturation_mois.size());
        Assert.assertEquals(4.4, facturation_mois.get(theo), 0.00);
        Assert.assertEquals(2.2, facturation_mois.get(romain), 0.00);
        Assert.assertEquals(2.2, facturation_mois.get(hugo), 0.00);
    }







}
