
import fr.ufc.l3info.oprog.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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
    public void testAbonneBloqueRibInvalide(){
        Abonne a = ville.creerAbonne("Fred", "12345-55555-1111111111");
        Station st1 = ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot");
        ville.setStationPrincipale("21 - Avenue Fontaine Argent, Boulevard Diderot");
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
        st2.emprunterVelo(a, 1);
        Assert.assertEquals(3, st2.nbBornesLibres());
    }

    @Test
    public void testEmpruntAbimer(){
        ville.setStationPrincipale("Avenue du Maréchal Foch");

        Abonne a = ville.creerAbonne("Fred", "12345-55555-11111111111-47");
        Exploitant exp = new Exploitant();
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        IVelo v1 = fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE");
        exp.acquerirVelo(v1);

        /**
         * Pas obligé d'utilisé le ravitaillement ici
         */
        exp.ravitailler(ville);

        Station st1 = ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot");
        Station st2 = ville.getStation("Avenue du Maréchal Foch");

        Assert.assertEquals(4, st1.nbBornesLibres());
        Assert.assertEquals(4, st2.nbBornesLibres());

        IRegistre reg1 = new JRegistre();
        st1.setRegistre(reg1);
        IRegistre reg2 = new JRegistre();
        st2.setRegistre(reg2);

        v1.arrimer();

        /*for(int i =1; i<=6; i++){
            if(st2.emprunterVelo(a,i) == v1){
                System.out.println("trouvé "+i);
            }
        }*/
        Assert.assertEquals(v1,st2.emprunterVelo(a,1));
        Assert.assertEquals(5, st2.nbBornesLibres());
        v1.abimer();

        Assert.assertEquals(0, st2.arrimerVelo(v1,1));
        Assert.assertTrue(st2.veloALaBorne(1).estAbime());
        //l'abonne a abime le velo, il est bloque
        Assert.assertNull(st2.emprunterVelo(a,1));


    }

    @Test
    public void testEntretien1(){
        Exploitant exp = new Exploitant();
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        Abonne a = ville.creerAbonne("Fred", "12345-55555-11111111111-47");
        Abonne a1 = ville.creerAbonne("FreD", "12345-55555-11111111111-47");

        IVelo v1 = fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE");
        IVelo v2 = fabrique.construire('f',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE");
        IVelo v3 = fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE");
        IVelo v4 = fabrique.construire('f',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE");
        IVelo v5 = fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE");
        IVelo v6 = fabrique.construire('f',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE");
        exp.acquerirVelo(v5);
        exp.acquerirVelo(v6);

        Station st1 = ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot");  //station principale
        Station st2 = ville.getStation("Avenue du Maréchal Foch");

        JRegistre reg1 = new JRegistre();
        st1.setRegistre(reg1);
        JRegistre reg2 = new JRegistre();
        st1.setRegistre(reg2);

        Assert.assertEquals(-4,st1.arrimerVelo(v1,1));
        Assert.assertEquals(-4,st1.arrimerVelo(v2,2));
        Assert.assertEquals(-4,st2.arrimerVelo(v3,1));
        Assert.assertEquals(-4,st2.arrimerVelo(v4,2));

        Assert.assertEquals(2, st1.nbBornesLibres());
        Assert.assertEquals(3, st2.nbBornesLibres());

        Assert.assertEquals(v1,st1.emprunterVelo(a,1));
        Assert.assertEquals(v2,st1.emprunterVelo(a1,2));
        v2.parcourir(600);
        v1.abimer();
        Assert.assertEquals(0,st1.arrimerVelo(v1,1));
        Assert.assertEquals(0,st1.arrimerVelo(v2,2));


        Assert.assertNull(st2.emprunterVelo(a,1));  //Abonne a est bloque
        Assert.assertEquals(v4,st2.emprunterVelo(a1,2));
        v4.abimer();
        Assert.assertEquals(0,st2.arrimerVelo(v4,2));

        exp.ravitailler(ville);

        Assert.assertFalse(st1.veloALaBorne(1).estAbime());
        Assert.assertEquals(500,st1.veloALaBorne(2).prochaineRevision(),0);
        Assert.assertTrue(st2.veloALaBorne(2).estAbime());
    }

    @Test
    public void testEntretien2(){
        Exploitant exp = new Exploitant();
        FabriqueVelo fabrique = FabriqueVelo.getInstance();
        Abonne a = ville.creerAbonne("Fred", "12345-55555-11111111111-47");
        Abonne a1 = ville.creerAbonne("FreD", "12345-55555-11111111111-47");

        IVelo v1 = fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE");
        IVelo v2 = fabrique.construire('f',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE");
        IVelo v3 = fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE");
        IVelo v4 = fabrique.construire('f',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE");
        IVelo v5 = fabrique.construire('m',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE");
        IVelo v6 = fabrique.construire('f',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE");
        IVelo v7 = fabrique.construire('f',"CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE");
        exp.acquerirVelo(v5);
        exp.acquerirVelo(v6);
        exp.acquerirVelo(v7);


        Station st1 = ville.getStation("21 - Avenue Fontaine Argent, Boulevard Diderot");  //station principale
        Station st2 = ville.getStation("Avenue du Maréchal Foch");

        JRegistre reg1 = new JRegistre();
        st1.setRegistre(reg1);
        JRegistre reg2 = new JRegistre();
        st1.setRegistre(reg2);

        Assert.assertEquals(-4,st1.arrimerVelo(v1,1));
        Assert.assertEquals(-4,st1.arrimerVelo(v2,2));
        Assert.assertEquals(-4,st2.arrimerVelo(v3,1));
        Assert.assertEquals(-4,st2.arrimerVelo(v4,2));

        Assert.assertEquals(2, st1.nbBornesLibres());
        Assert.assertEquals(3, st2.nbBornesLibres());

        Assert.assertEquals(v1,st1.emprunterVelo(a,1));
        Assert.assertEquals(v2,st1.emprunterVelo(a1,2));
        v2.parcourir(600);
        v1.abimer();
        Assert.assertEquals(0,st1.arrimerVelo(v1,1));
        Assert.assertEquals(0,st1.arrimerVelo(v2,2));


        Assert.assertNull(st2.emprunterVelo(a,1));  //Abonne a est bloque
        Assert.assertEquals(v4,st2.emprunterVelo(a1,2));
        v4.abimer();
        Assert.assertEquals(0,st2.arrimerVelo(v4,2));

        exp.ravitailler(ville);

        Assert.assertFalse(st1.veloALaBorne(1).estAbime());
        Assert.assertEquals(500,st1.veloALaBorne(2).prochaineRevision(),0);
        Assert.assertFalse(st2.veloALaBorne(2).estAbime());
    }

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
