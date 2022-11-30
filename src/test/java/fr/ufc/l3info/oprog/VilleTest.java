package fr.ufc.l3info.oprog;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class VilleTest {

    private Ville v;

    @Before
    public void beforeVille()throws IOException {
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

    @Test
    public void initialiserTestErreurFichier() throws IOException{
        v.initialiser(new File("./target/classes/data/stationsMultipleErreur.txt"));
        Assert.assertNull(v.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot"));
    }

    // manque les test pour "On notera que chaque appel à cette méthode aura pour effet de réinitialiser l'ensemble des stations existantes. "

    /* void setStationPrincipale(String st) */
    @Test
    public void setStationPrincipaleTest() throws IOException {
        v.initialiser(new File("./target/classes/data/stationsOK.txt"));
        v.setStationPrincipale("Avenue du Maréchal Foch");
        ClosestStationIterator it = (ClosestStationIterator) v.iterator();
        Assert.assertEquals("Avenue du Maréchal Foch", it.next().getNom());
    }
    @Test
    public void TestSetStationPrincipaleInexistante() throws IOException {
        Ville v4 = new Ville();
        v4.initialiser(new File("./target/classes/data/stationsOK.txt"));
        v4.setStationPrincipale("");
        ClosestStationIterator it = (ClosestStationIterator) v4.iterator();
        Assert.assertEquals("21 - Avenue Fontaine Argent, Boulevard Diderot", it.next().getNom());
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
    public void getStationTestOk2() throws IOException {
        v.initialiser(new File("./target/classes/data/stationsOK.txt"));
        Station s = v.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot");
        Assert.assertNotEquals(null, s);
        Assert.assertEquals("21 - Avenue Fontaine Argent, Boulevard Diderot", s.getNom());
    }
    @Test
    public void getStationTestStationInexistante() throws IOException{
        //v.initialiser(new File("./target/classes/data/stationsOK.txt"));
        Station s = v.getStation("Avenue");
        Assert.assertNull(s);
    }

    /* Station getStationPlusProche(double lat, double lon) */
    @Test
    public void getStationPlusProcheTestPremiereStation() throws IOException {
        v.initialiser(new File("./target/classes/data/stationsOK.txt"));
        Station s = v.getStationPlusProche(47.247761, 5.983599);
        Assert.assertNotNull(s);
        Assert.assertEquals("21 - Avenue Fontaine Argent, Boulevard Diderot", s.getNom());
    }

    @Test
    public void getStationPlusProcheTestDeuxiemeStation() throws IOException{
        v.initialiser(new File("./target/classes/data/stationsOK.txt"));
        Station s = v.getStationPlusProche(47.24651, 6.02267);
        Assert.assertNotNull(s);
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
    public void iteratorTestStationPrincipale() throws IOException{
        Ville v3 = new Ville();
        v3.initialiser(new File("./target/classes/data/stationsOK.txt"));
        v3.setStationPrincipale("Avenue du Maréchal Foch");
        ClosestStationIterator it = (ClosestStationIterator) v3.iterator();
        Assert.assertEquals("Avenue du Maréchal Foch", it.next().getNom());
    }

    @Test
    public void iteratorTestNombre() throws IOException{
        Ville v2 = new Ville();
        v2.initialiser(new File("./target/classes/data/stationsOK.txt"));
        ClosestStationIterator it = (ClosestStationIterator) v2.iterator();
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
    public void facturationTest() throws IncorrectNameException, IOException {
        v.initialiser(new File("./target/classes/data/stationsOK.txt"));
        IVelo ve [] = new IVelo [5];
        FabriqueVelo fab = FabriqueVelo.getInstance();
        Station s = Mockito.spy(v.getStation("Avenue du Maréchal Foch"));
        for(int i = 0; i < 5; i++) {
            ve[i] = fab.construire('h', "CADRE_ALUMINIUM");
            s.arrimerVelo(ve[i],i+1);
        }


        Mockito.when(s.maintenant()).thenReturn(System.currentTimeMillis() + 10 * 60 * 1000);
        long time = s.maintenant();


        Abonne theo = v.creerAbonne("theo","12345-55555-11111111111-47");
        Abonne romain = v.creerAbonne("romain","26251-55151-15151121616-33");
        Abonne hugo = v.creerAbonne("hugo","25452-24525-35435435478-16");
        IVelo ret[] = new IVelo[3];
        ret[0] = s.emprunterVelo(theo,1);
        ret[1] = s.emprunterVelo(romain,2);
        ret[2] = s.emprunterVelo(hugo,3);


        Mockito.when(s.maintenant()).thenReturn(time+3600000);
        for(int i = 0; i < 3; i++){
            s.arrimerVelo(ret[i], i+1);
        }
        // == emprunt velo cadre alu 1h (x2 pour Théo)



        Map<Abonne, Double> facturation_mois;
        facturation_mois = v.facturation(11, 2022);

        Assert.assertEquals(3, facturation_mois.size());
        Assert.assertEquals(2.2, facturation_mois.get(theo), 0.00);
        Assert.assertEquals(2.2, facturation_mois.get(romain), 0.00);
        Assert.assertEquals(2.2, facturation_mois.get(hugo), 0.00);
    }



    @Test
    public void facturationVideTest() throws IncorrectNameException{
        IVelo ve [] = new IVelo [5];
        FabriqueVelo fab = FabriqueVelo.getInstance();
        for(int i = 0; i < 5; i++) {
            ve[i] = fab.construire('h', "CADRE_ALUMINIUM");
        }

        IRegistre r = new JRegistre();

        for(int i = 0; i < 5; i++) r.retourner(ve[i], System.currentTimeMillis() + 3600000);


        Map<Abonne, Double> facturation_mois;
        facturation_mois = v.facturation(11, 2022);

        Assert.assertEquals(0, facturation_mois.size());
    }

}
