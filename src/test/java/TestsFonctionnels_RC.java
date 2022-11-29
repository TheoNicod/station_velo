
import fr.ufc.l3info.oprog.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TestsFonctionnels_RC {

    Ville ville;

    @Before
    public void before() throws IOException{
        this.ville = new Ville();
        ville.initialiser(new File("./target/classes/data/stationsOK.txt"));
    }

    @Test
    public void testCreationVille() throws IOException {
        Assert.assertEquals("21 - Avenue Fontaine Argent, Boulevard Diderot", ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot").getNom());
        Assert.assertEquals("Avenue du Maréchal Foch", ville.getStation("Avenue du Maréchal Foch").getNom());
    }
    @Test
    public void testVilleFichierInvalide() throws IOException {
        ville.initialiser(new File("./target/classes/data/stationsMultipleErreur.txt"));
        Assert.assertEquals(null, ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot"));
    }
    @Test (expected = IOException.class)
    public void testVilleFichierInexistant() throws IOException{
        ville.initialiser(new File("./target/inexistant"));
    }
    @Test
    public void testAbonneBloqueRibInvalide() throws IOException{
        Abonne a = ville.creerAbonne("Fred", "12345-55555-11111111111-47");
        Station st1 = ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot");
        Exploitant exp = new Exploitant();
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        IVelo v = fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE");
        exp.acquerirVelo(v);
        exp.ravitailler(ville);
        Assert.assertEquals(4,st1.capacite());
        Assert.assertEquals(3, st1.nbBornesLibres());
        //L'abonne est bloque il ne peut emprunter a aucune borne
        Assert.assertEquals(null, st1.emprunterVelo(a,1));
        Assert.assertEquals(null, st1.emprunterVelo(a,2));
        Assert.assertEquals(null, st1.emprunterVelo(a,3));
        Assert.assertEquals(null, st1.emprunterVelo(a,4));
        Assert.assertEquals(3, st1.nbBornesLibres());
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
        Assert.assertEquals(2, st2.nbBornesLibres());

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

        //station 2 est ravitailler en premiere
        Assert.assertEquals(3, st1.nbBornesLibres());
        Assert.assertEquals(2, st2.nbBornesLibres());

    }

    @Test
    public void testRemplissageStation2(){


    }

    @Test
    public void testRemplissageStation3(){}

    @Test
    public void testEmprunt1(){
        ville.setStationPrincipale("Avenue du Maréchal Foch");

        Abonne a = ville.creerAbonne("Fred", "12345-55555-11111111111-47");
        Abonne b = ville.creerAbonne("FreD", "12345-55555-11111111111-47");
        Abonne c = ville.creerAbonne("FRED", "12345-55555-11111111111-47");
        Exploitant exp = new Exploitant();
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        IVelo v1 = fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE");
        IVelo v2 = fabrique.construire('f',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE");
        IVelo v3 = fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE");
        IVelo v4 = fabrique.construire('f',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE");
        exp.acquerirVelo(v1);
        exp.acquerirVelo(v2);
        exp.acquerirVelo(v3);
        exp.acquerirVelo(v4);

        exp.ravitailler(ville);

        Station st1 = ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot");
        Station st2 = ville.getStation("Avenue du Maréchal Foch");

        Assert.assertEquals(3, st1.nbBornesLibres());
        Assert.assertEquals(2, st2.nbBornesLibres());

        IRegistre reg1 = new JRegistre();
        st1.setRegistre(reg1);
        IRegistre reg2 = new JRegistre();
        st2.setRegistre(reg2);

        v1.arrimer();
        st1.emprunterVelo(a, 1);    //le velo n'est pas accroché ??? Erreur de test ???
        Assert.assertEquals(4, st1.nbBornesLibres());
    }

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

    @Test
    public void testFacturation1(){}

    @Test
    public void testFacturation2(){}
    @Test
    public void testFacturation3(){}

}
