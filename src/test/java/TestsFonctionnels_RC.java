
import fr.ufc.l3info.oprog.Abonne;
import fr.ufc.l3info.oprog.Station;
import fr.ufc.l3info.oprog.Exploitant;
import fr.ufc.l3info.oprog.FabriqueVelo;
import fr.ufc.l3info.oprog.Ville;
import fr.ufc.l3info.oprog.parser.StationParserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;


import java.text.ParseException;

public class TestsFonctionnels_RC {

    Ville ville;

    @Before
    public void before() throws IOException{
        this.ville = new Ville();
        ville.initialiser(new File("./target/classes/data/stationsOK.txt"));
    }

    @Test
    public void testCreationVille() throws IOException {
        /*ville.creerAbonne("Fred", "12345-55555-11111111111-47");
        ville.creerAbonne("FreD", "12345-55555-11111111111-47");
        ville.creerAbonne("FreD", "12345-55555-11111111111-47");*/
        Assert.assertEquals("21 - Avenue Fontaine Argent, Boulevard Diderot", ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot").getNom());
        Assert.assertEquals("Avenue du Maréchal Foch", ville.getStation("Avenue du Maréchal Foch").getNom());
    }
    @Test (expected = StationParserException.class)
    public void testVilleFichierInvalide() throws IOException {
        ville.initialiser(new File("./target/classes/data/stationsMultipleErreur.txt"));
        Assert.assertEquals("Avenue du Maréchal Foch", ville.getStation("Avenue du Maréchal Foch").getNom());
    }
    @Test (expected = IOException.class)
    public void testVilleFichierInexistant() throws IOException{
        ville.initialiser(new File("./target/inexistant"));
    }
    @Test
    public void testAbonneBloqueRibInvalide() throws IOException{
        ville.creerAbonne("Fred", "12345-55555-11111111111-47");
        ville.creerAbonne("FreD", "12345-55555-11111111111-47");
        ville.creerAbonne("FRED", "12345-55555-11111111111-47");
        Map<Abonne, Double> fact = ville.facturation(11,2022);
        Assert.assertTrue( false);
        //finir

    }

    @Test
    public void testRemplissageStation(){
        Exploitant exp = new Exploitant();
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        exp.acquerirVelo(fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE"));
        exp.acquerirVelo(fabrique.construire('f',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE"));
        exp.acquerirVelo(fabrique.construire('h',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "ASSISTANCE_ELECTRIQUE"));
        exp.acquerirVelo(fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE"));
        exp.acquerirVelo(fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE"));
        exp.acquerirVelo(fabrique.construire('f',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE"));
        exp.acquerirVelo(fabrique.construire('h',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "ASSISTANCE_ELECTRIQUE"));
        exp.acquerirVelo(fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE"));
        exp.acquerirVelo(fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE"));

        exp.ravitailler(ville);

        Station st1 = ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot");
        Station st2 = ville.getStation("Avenue du Maréchal Foch");
        Assert.assertEquals(2, st1.nbBornesLibres());
        Assert.assertEquals(3, st2.nbBornesLibres());

    }

    @Test
    public void testRemplissageStation1(){
        ville.setStationPrincipale("Avenue du Maréchal Foch");
        Exploitant exp = new Exploitant();
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        exp.acquerirVelo(fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE"));
        exp.acquerirVelo(fabrique.construire('f',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE"));
        exp.acquerirVelo(fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE"));
        exp.acquerirVelo(fabrique.construire('f',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE"));

        exp.ravitailler(ville);

        Station st1 = ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot");
        Station st2 = ville.getStation("Avenue du Maréchal Foch");

        Assert.assertEquals(1, st1.nbBornesLibres());
        Assert.assertEquals(3, st2.nbBornesLibres());

    }

    @Test
    public void testRemplissageStation2(){
        ville.setStationPrincipale("Avenue du Maréchal Foch");
        ville.creerAbonne("Fred", "12345-55555-11111111111-47");
        ville.creerAbonne("FreD", "12345-55555-11111111111-47");
        ville.creerAbonne("FRED", "12345-55555-11111111111-47");
        Exploitant exp = new Exploitant();
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        exp.acquerirVelo(fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE"));
        exp.acquerirVelo(fabrique.construire('f',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE"));
        exp.acquerirVelo(fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE"));
        exp.acquerirVelo(fabrique.construire('f',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE"));

        exp.ravitailler(ville);

        Station st1 = ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot");
        Station st2 = ville.getStation("Avenue du Maréchal Foch");

        Assert.assertEquals(1, st1.nbBornesLibres());
        Assert.assertEquals(3, st2.nbBornesLibres());


        //semble bien compliqué
        Map<Abonne, Double> fact = ville.facturation(05,2022);
        Set<Map.Entry<Abonne,Double>> paires = fact.entrySet();
        Iterator <Map.Entry<Abonne,Double>> iter = paires.iterator();
        Set<Abonne> abonneSet = new TreeSet<>();
        Abonne last = null;
        while (iter.hasNext()){
            Map.Entry<Abonne,Double> paire = iter.next();
            abonneSet.add(paire.getKey());
            last = paire.getKey();
        }
        Assert.assertEquals(3,  abonneSet.size());

        st1.emprunterVelo(last, 2);

    }

    @Test
    public void testRemplissageStation3(){}

    @Test
    public void testEmprunt1(){}

    @Test
    public void testEmprunt2(){}

    @Test
    public void testEmprunt3(){}

    @Test
    public void testEmprunt4(){}

    @Test
    public void testEmprunt5(){}

    @Test
    public void testEntretien1(){}

    @Test
    public void testEntretien2(){}

    @Test
    public void testEntretien3(){}

    @Test
    public void testEntretien4(){}

    @Test
    public void testEntretien5(){}

}
