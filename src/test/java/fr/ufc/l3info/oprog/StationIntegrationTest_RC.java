package fr.ufc.l3info.oprog;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class StationIntegrationTest_RC {



    private Station st;
    private IRegistre registre;
    private Abonne abonne;
    private IVelo velo;

    @Before
    public void before()throws  IncorrectNameException{
        st = new Station("Gare", 51.5007, 0.1246, 5);
        registre = new JRegistre();
        abonne = new Abonne("Fred", "12345-55555-11111111111-47");
        velo = new Velo();
        st.setRegistre(registre);
    }

    /**
     * Test constructeur
     */
    @Test
    public void TestStation(){
        Assert.assertEquals(st.getNom(), "Gare");
        Assert.assertEquals(5, st.capacite());
        Assert.assertEquals(5,st.nbBornesLibres());
    }
    @Test
    public void TestStationCapaciteNegative() {
        Station st = new Station("Gare", 51.5007, 0.1246, -5);
        Assert.assertEquals(st.getNom(), "Gare");
        Assert.assertEquals(0, st.capacite());  //mettre a 0 ??
        Assert.assertEquals(0,st.nbBornesLibres());
    }

    /**
     * Test getNom()
     */
    @Test
    public void TestGetNom() {
        Assert.assertEquals("Gare", st.getNom());
    }

    @Test
    public void TestGetNomErreurCapacite() {
        st = new Station("Gare", 47.2, 6.2, -5);
        Assert.assertEquals(st.getNom(), "Gare");
    }
    /**
     * Test capacite()
     */
    @Test
    public void TestCapacite() {
        Assert.assertEquals(5, st.capacite());
    }
    @Test
    public void TestCapaciteErreurCapacite() {
        st = new Station("Gare", 47.5, 6.2, -5);
        Assert.assertEquals(0, st.capacite());
    }
    /**
     * Test nbBornesLibres()
     */
    @Test
    public void TestNbBornesLibres(){
        Assert.assertEquals(5, st.nbBornesLibres());
    }
    @Test
    public void TestNbBornesLibresErreurCapacite(){
        st = new Station("Gare",47.5,5,-5);
        Assert.assertEquals(0, st.nbBornesLibres());
    }
    @Test
    public void TestNbBornesLibresArrimageSimple(){
        registre.emprunter(abonne,velo, st.maintenant());
        Assert.assertEquals(0,st.arrimerVelo(velo,1));
        Assert.assertEquals(4, st.nbBornesLibres());
    }
    @Test
    public void TestNbBornesLibresArrimageMultiple() throws IncorrectNameException{
        Abonne abonne = new Abonne("fred");
        registre.emprunter(abonne,velo,st.maintenant());
        Assert.assertEquals(0,st.arrimerVelo(velo, 1));

        Abonne abonne2 = new Abonne("fred");
        IVelo velo2 = new Velo();
        registre.emprunter(abonne2,velo2,st.maintenant());
        Assert.assertEquals(0,st.arrimerVelo(velo2, 2));
        //Assert.assertEquals(0,registre.retourner(velo2,st.maintenant()));

        Abonne abonne3 = new Abonne("fred");
        IVelo velo3 = new Velo();
        registre.emprunter(abonne3,velo3,st.maintenant());
        Assert.assertEquals(0,st.arrimerVelo(velo3, 3));

        Abonne abonne4 = new Abonne("fred");
        IVelo velo4 = new Velo();
        registre.emprunter(abonne4,velo4,st.maintenant());
        Assert.assertEquals(0,st.arrimerVelo(velo4, 4));

        IVelo velo5 = new Velo();
        registre.emprunter(abonne,velo5,st.maintenant());
        Assert.assertEquals(0,st.arrimerVelo(velo5, 5));

        Assert.assertEquals(0, st.nbBornesLibres());
    }

    /**
     * Test veloALaBorne
     */
    @Test
    public void TestVeloALaBorne(){
        Assert.assertEquals(-4,st.arrimerVelo(velo, 2));
        Assert.assertEquals(velo, st.veloALaBorne(2));
    }
    @Test
    public void TestVeloALaBorneAprèsEmprunt(){
        Assert.assertEquals(-4,st.arrimerVelo(velo, 2));
        Assert.assertEquals(velo, st.emprunterVelo(abonne,2));
        Assert.assertEquals(null, st.veloALaBorne(2));
    }
    @Test
    public void TestVeloALaBorneAprèsRetour(){
        Assert.assertEquals(-4,st.arrimerVelo(velo, 2));
        Assert.assertEquals(velo, st.emprunterVelo(abonne,2));
        Assert.assertEquals(0,st.arrimerVelo(velo,2));
        Assert.assertEquals(velo, st.veloALaBorne(2));
    }
    @Test
    public void TestVeloALaBorneNonExistante(){
        Assert.assertEquals(null, st.veloALaBorne(6));
    }
    @Test
    public void TestVeloALaBorneNonNegative(){
        Assert.assertEquals(null, st.veloALaBorne(-1));
    }
    @Test
    public void TestVeloALaBorneVide(){
        Assert.assertEquals(null, st.veloALaBorne(2));
    }

    /**
     * Test emprunter()
     */
    @Test
    public void TestEmprunterVelo()throws IncorrectNameException{
        Assert.assertEquals(-4,st.arrimerVelo(velo,2));
        Assert.assertEquals(velo, st.veloALaBorne(2));
        Assert.assertEquals(velo,st.emprunterVelo(abonne,2));
        Assert.assertEquals(null, st.veloALaBorne(2));
    }
    @Test
    public void TestEmprunterVeloMultiple()throws IncorrectNameException{
        Assert.assertEquals(-4,st.arrimerVelo(velo,2));
        Assert.assertEquals(velo, st.veloALaBorne(2));
        Assert.assertEquals(velo,st.emprunterVelo(abonne,2));
        Assert.assertEquals(null, st.veloALaBorne(2));
        Assert.assertEquals(0,st.arrimerVelo(velo,1));
        Assert.assertEquals(velo, st.veloALaBorne(1));
        Assert.assertEquals(null,st.emprunterVelo(abonne,2));
    }
    @Test
    public void TestEmprunterAbonneInvalide(){
        st.arrimerVelo(velo,2);
        Assert.assertEquals(null,st.emprunterVelo(null,2));
    }
    @Test
    public void TestEmprunterEmpruntEnCours() {
        Assert.assertEquals(-4, st.arrimerVelo(velo, 2));
        Assert.assertEquals(velo, st.veloALaBorne(2));
        Assert.assertEquals(velo, st.emprunterVelo(abonne, 2));
        Assert.assertEquals(null, st.emprunterVelo(abonne, 2));
    }
    @Test
    public void TestEmprunteBorneInexistante(){
        Assert.assertEquals(null,st.emprunterVelo(abonne,6));
    }
    @Test
    public void TestEmprunteBorneNegative(){
        Assert.assertEquals(null,st.emprunterVelo(abonne,-1));
    }
    @Test
    public void TestEmprunterAbonneBloque() throws IncorrectNameException{
        abonne = new Abonne("Fred", "12345-55555-11111111111-");
        Assert.assertTrue(abonne.estBloque());
        Assert.assertEquals(-4,st.arrimerVelo(velo,2));
        Assert.assertEquals(velo, st.veloALaBorne(2));
        Assert.assertEquals(null,st.emprunterVelo(abonne,2));
    }
    @Test
    public void TestEmprunterErreurRegistre(){
        Assert.assertEquals(-4, st.arrimerVelo(velo,1));
        st.setRegistre(null);
        Assert.assertEquals(null,st.emprunterVelo(abonne,1));
    }
    @Test
    public void TestEmprunterBorneVide(){
        Assert.assertEquals(null, st.veloALaBorne(2));
        Assert.assertEquals(null,st.emprunterVelo(abonne,2));
    }
    /**
     * test arrimer()
     */
    @Test
    public void TestArrimer(){
        Assert.assertEquals(5, st.nbBornesLibres());
        Assert.assertEquals(-4, st.arrimerVelo(velo, 2));
        Assert.assertEquals(4, st.nbBornesLibres());
    }
    @Test
    public void TestArrimerVeloNull(){
        Assert.assertEquals(-1, st.arrimerVelo(null, 2));
    }
    @Test
    public void TestArrimerBorneInexistante(){
        Assert.assertEquals(-1, st.arrimerVelo(velo, 6));
    }
    @Test
    public void TestArrimerBorneNegative(){
        Assert.assertEquals(-1, st.arrimerVelo(velo, -2));
    }
    @Test
    public void TestArrimerBorneOccupe(){
        IVelo velo2 = new Velo();
        Assert.assertEquals(5,st.nbBornesLibres());
        Assert.assertEquals(-4,st.arrimerVelo(velo,2));
        Assert.assertEquals(-2, st.arrimerVelo(velo2, 2));
    }
    @Test
    public void TestArrimerRegistreNull(){
        st.setRegistre(null);
        Assert.assertEquals(5,st.nbBornesLibres());
        Assert.assertEquals(-2, st.arrimerVelo(velo, 2));
    }
    @Test
    public void TestArrimerDejaArrimer(){
        Assert.assertEquals(-4, st.arrimerVelo(velo,2));
        Assert.assertEquals(-3, st.arrimerVelo(velo,3));
    }

    /**
     * Test equilibrer
     */
    @Test
    public void TestEquilibrer(){
        /*Creation d'un set de velo en bon etat*/
        Set<IVelo> velos = new HashSet<IVelo>();
        IVelo velo1 = new Velo();
        velos.add(velo1);
        /*Ajout de velo abimer aux bornes de la station*/
        IVelo veloAbimer = new Velo();   //velo arrimer a la borne
        st.arrimerVelo(veloAbimer, 2);
        Assert.assertEquals(veloAbimer,st.veloALaBorne(2));
        veloAbimer.abimer();

        Assert.assertTrue(veloAbimer.estAbime());
        st.equilibrer(velos);
        Assert.assertEquals(4,st.nbBornesLibres());
        Assert.assertTrue(velos.contains(veloAbimer));
    }

    /**
     * Test equilibrer() : 1 velo a reviser, capacite optimal non atteinte a l'aide du set
     */
    @Test
    public void TestEquilibrer1(){
        IVelo veloUsed = new Velo();
        veloUsed.parcourir(400);
        st.arrimerVelo(veloUsed, 2);
        Assert.assertEquals(veloUsed,st.veloALaBorne(2));

        Set<IVelo> velos = new HashSet<IVelo>();
        IVelo velo1 = new Velo();
        velos.add(velo1);

        st.equilibrer(velos);

        Assert.assertEquals(3,st.nbBornesLibres());
        Assert.assertFalse(velos.contains(veloUsed));
    }

    /**
     * Test equilibre - capacite optimal atteinte a l'aide du set avec velo a reviser
     */
    @Test
    public void TestEquilibrer2(){
        Set<IVelo> velos = new HashSet<IVelo>();
        IVelo newVelo = new Velo();
        IVelo newVelo1 = new Velo();
        velos.add(newVelo);
        velos.add(newVelo1);

        IVelo veloAbimer = new Velo();
        veloAbimer.abimer();
        IVelo veloAReviser = new Velo();
        Assert.assertTrue(veloAbimer.estAbime());
        veloAReviser.parcourir(600);
        Assert.assertEquals(-100.0,veloAReviser.prochaineRevision(),0);
        st.arrimerVelo(veloAbimer, 2);
        st.arrimerVelo(veloAReviser, 1);

        Assert.assertEquals(3,st.nbBornesLibres());
        st.equilibrer(velos);
        Assert.assertEquals(2,st.nbBornesLibres());
        Assert.assertTrue(velos.contains(veloAbimer));
        Assert.assertFalse(velos.contains(veloAReviser));
    }

    /**
     * Test equilibrer - capacite optimal atteignable avec le Set
     */
    @Test
    public void TestEquilibrer3(){
        Set<IVelo> velos = new HashSet<IVelo>();
        IVelo newVelo = new Velo();
        IVelo newVelo1 = new Velo();
        IVelo newVelo2 = new Velo();
        velos.add(newVelo);
        velos.add(newVelo1);
        velos.add(newVelo2);

        IVelo veloAbimer = new Velo();
        veloAbimer.abimer();
        IVelo veloAReviser = new Velo();
        Assert.assertTrue(veloAbimer.estAbime());
        veloAReviser.parcourir(600);
        st.arrimerVelo(veloAbimer, 2);
        st.arrimerVelo(veloAReviser, 1);
        Assert.assertEquals(3,st.nbBornesLibres());
        Assert.assertEquals(-100.0,veloAReviser.prochaineRevision(),0);

        st.equilibrer(velos);
        Assert.assertEquals(2,st.nbBornesLibres());
        Assert.assertTrue(velos.contains(veloAbimer));
        Assert.assertTrue(velos.contains(veloAReviser));
    }

    /**
     * Test equilbrer - le nombre de velo contenu dans le set depasse la capcite optimal
     */
    @Test
    public void TestEquilibrer4(){
        Set<IVelo> velos = new HashSet<IVelo>();
        IVelo newVelo = new Velo();
        IVelo newVelo1 = new Velo();
        IVelo newVelo2 = new Velo();
        IVelo newVelo3 = new Velo();
        velos.add(newVelo);
        velos.add(newVelo1);
        velos.add(newVelo2);
        velos.add(newVelo3);

        IVelo veloAbimer = new Velo();
        veloAbimer.abimer();
        IVelo veloAReviser = new Velo();
        Assert.assertTrue(veloAbimer.estAbime());
        veloAReviser.parcourir(600);
        st.arrimerVelo(veloAbimer, 2);
        st.arrimerVelo(veloAReviser, 1);
        Assert.assertEquals(3,st.nbBornesLibres());
        Assert.assertEquals(-100.0,veloAReviser.prochaineRevision(),0);

        st.equilibrer(velos);
        Assert.assertEquals(2,st.nbBornesLibres());
        Assert.assertTrue(velos.contains(veloAbimer));
        Assert.assertTrue(velos.contains(veloAReviser));
    }

    /**
     * Test equilibrer - capacite optimal non atteingable avec l'aide du set
     * Mais atteignable en conservant des velos uses
     */
    @Test
    public void TestEquilibrer5(){
        Set<IVelo> velos = new HashSet<IVelo>();
        IVelo newVelo = new Velo();
        velos.add(newVelo);

        IVelo veloAReviser = new Velo();
        veloAReviser.parcourir(600);
        IVelo veloAReviser1 = new Velo();
        veloAReviser1.parcourir(600);
        IVelo veloAReviser2 = new Velo();
        veloAReviser2.parcourir(600);
        st.arrimerVelo(veloAReviser, 1);
        st.arrimerVelo(veloAReviser1, 2);
        st.arrimerVelo(veloAReviser2, 3);
        Assert.assertEquals(2,st.nbBornesLibres());
        Assert.assertEquals(-100.0,veloAReviser.prochaineRevision(),0);

        st.equilibrer(velos);
        Assert.assertEquals(2,st.nbBornesLibres());
        Assert.assertTrue(velos.contains(veloAReviser1) || velos.contains(veloAReviser2) || velos.contains(veloAReviser));
        Assert.assertFalse(velos.contains(newVelo));
    }
    /**
     * Test equilbrer Set empty
     * Bornes remplie a plus de la capacite optimal par des velos a reviser
     * On s'assure que les velos en trop sont recuperes dans le set
     */
    @Test
    public void TestEquilbrerEmptySet(){
        Set<IVelo> velos = new HashSet<IVelo>();
        Assert.assertTrue(velos.isEmpty());

        IVelo veloAReviser = new Velo();
        veloAReviser.parcourir(600);
        IVelo veloAReviser1 = new Velo();
        veloAReviser1.parcourir(600);
        IVelo veloAReviser2 = new Velo();
        veloAReviser2.parcourir(600);
        IVelo veloAReviser3 = new Velo();
        veloAReviser3.parcourir(600);

        st.arrimerVelo(veloAReviser, 1);
        st.arrimerVelo(veloAReviser1, 2);
        st.arrimerVelo(veloAReviser2, 3);
        st.arrimerVelo(veloAReviser3, 4);

        Assert.assertEquals(1,st.nbBornesLibres());

        st.equilibrer(velos);
        Assert.assertEquals(2,st.nbBornesLibres());
        Assert.assertEquals(1,velos.size());
    }
    /**
     * Test equilbrer Set empty
     * Bornes remplie a plus de la capacite optimal par des velos abimes
     * On s'assure que tous les velos sont recuperes dans le set
     */
    @Test
    public void TestEquilbrerFullBroken(){
        Set<IVelo> velos = new HashSet<IVelo>();
        Assert.assertTrue(velos.isEmpty());

        IVelo veloAbime1 = new Velo();
        veloAbime1.abimer();
        IVelo veloAbime2 = new Velo();
        veloAbime2.abimer();
        IVelo veloAbime3 = new Velo();
        veloAbime3.abimer();
        IVelo veloAbime4 = new Velo();
        veloAbime4.abimer();

        st.arrimerVelo(veloAbime1, 1);
        st.arrimerVelo(veloAbime2, 2);
        st.arrimerVelo(veloAbime3, 3);
        st.arrimerVelo(veloAbime4, 4);

        Assert.assertEquals(1,st.nbBornesLibres());

        st.equilibrer(velos);
        Assert.assertEquals(5,st.nbBornesLibres());
        Assert.assertEquals(4,velos.size());
    }
    /**
     * Test equilbrer Station empty
     * set remplie a plus de la capacite optimal
     * On s'assure que les velos le bon nombre de velos est ajoute
     */
    @Test
    public void TestEquilbrerEmptyStation(){
        Set<IVelo> velos = new HashSet<IVelo>();
        IVelo newVelo = new Velo();
        IVelo newVelo1 = new Velo();
        IVelo newVelo2 = new Velo();
        IVelo newVelo3 = new Velo();
        velos.add(newVelo);
        velos.add(newVelo1);
        velos.add(newVelo2);
        velos.add(newVelo3);

        Assert.assertEquals(5,st.nbBornesLibres());

        st.equilibrer(velos);
        Assert.assertEquals(2,st.nbBornesLibres());
        Assert.assertEquals(1,velos.size());
    }


    /**
     * Test distance
     */
    @Test
    public void TestDistance(){
        Station st2 = new Station("8 Septembre", 40.6892,74.0445, 10);
        Assert.assertEquals(5574.8, st.distance(st2), 0);
    }
    @Test
    public void TestDistanceStationNull(){
        Assert.assertEquals(0, st.distance(null), 0);
    }
    /**
     * Test Maintenant
     */
    @Test
    public void TestMaintenant(){
        Assert.assertEquals(System.currentTimeMillis(), st.maintenant());
    }
}
