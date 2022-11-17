package fr.ufc.l3info.oprog;


// Mockito
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

// JUnit
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/** Tests d'intégration pour la station */


public class StationIntegrationTest {

    /* test Constructor */
    @Test
    public void testStationConstructor() {
        Station s = new Station("test", 47.5, 6.02, 10);
        Assert.assertEquals("test", s.getNom());
        Assert.assertEquals(10, s.capacite());
    }


    /* test setRegistre */
    @Test
    public void testSetRegistre() {

    }

    /* test getNom */
    @Test
    public void testGetNom() {
        Station s = new Station("test", 47.5, 6.02, 10);
        Assert.assertEquals("test", s.getNom());
    }

    /* test capacite */
    @Test
    public void testCapacite() {
        Station s = new Station("test", 47.5, 6.02, 10);
        Assert.assertEquals(10, s.capacite());
    }

    /* test nbBornesLibres */
    @Test
    public void testNbBornesLibresDefaut() {
        Station s = new Station("test", 47.5, 6.02, 10);
        Assert.assertEquals(10, s.nbBornesLibres());
    }

    @Test
    public void testNbBornesLibres() {
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v1 = new Velo();
        IVelo v2 = new Velo();
        s.arrimerVelo(v1, 1);
        s.arrimerVelo(v2, 2);
        Assert.assertEquals(8, s.nbBornesLibres());
    }

    /* test veloALaBorne */
    @Test
    public void testVeloALaBorneBornesVides() {
        Station s = new Station("test", 47.5, 6.02, 10);
        for (int i = 1; i <= 10; i++)
            Assert.assertEquals(null, s.veloALaBorne(i));
    }

    @Test
    public void testVeloALaBorneMauvaisParam() {
        Station s = new Station("test", 47.5, 6.02, 10);
        Assert.assertEquals(null, s.veloALaBorne(0));
        Assert.assertEquals(null, s.veloALaBorne(-1));
        Assert.assertEquals(null, s.veloALaBorne(11));
    }

    @Test
    public void testVeloALaBorne() {
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v1 = new Velo();
        v1.decrocher();
        s.arrimerVelo(v1, 4);
        Assert.assertEquals(v1, s.veloALaBorne(4));
    }

    /* test emprunterVelo */
    @Test
    public void testEmprunterVeloNonSetRegistre() throws IncorrectNameException {
        Station s = new Station("test", 47.5, 6.02, 10);
        Abonne a = new Abonne("Theo", "999999999999999999999999999999999999999999999999");
        IVelo v1 = new Velo();
        v1.decrocher();
        s.arrimerVelo(v1, 1);
        Assert.assertEquals(null, s.emprunterVelo(a, 1));
    }

    @Test
    public void testEmprunterVeloBorneVide() throws IncorrectNameException {
        Station s = new Station("test", 47.5, 6.02, 10);
        Abonne a = new Abonne("Theo", "999999999999999999999999999999999999999999999999");
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        Assert.assertEquals(null, s.emprunterVelo(a, 1));
    }

    @Test
    public void testEmprunterVeloAbonne() throws IncorrectNameException {
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        Abonne a = new Abonne("Fred", "12345-55555-11111111111-47");
        IVelo v1 = new Velo();
        v1.decrocher();
        s.arrimerVelo(v1, 1);
        Assert.assertEquals(v1, s.emprunterVelo(a, 1));

        //puis test abonne déjà en train d'emprunter
        s.arrimerVelo(v1, 1);
        Assert.assertEquals(null, s.emprunterVelo(a, 1));
    }

    @Test
    public void testEmprunterVeloMauvaiseBorne() throws IncorrectNameException {
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        Abonne a = new Abonne("Theo", "999999999999999999999999999999999999999999999999");
        Assert.assertEquals(null, s.emprunterVelo(a, 0));
        Assert.assertEquals(null, s.emprunterVelo(a, 11));
        Assert.assertEquals(null, s.emprunterVelo(a, -1));
    }

    @Test
    public void testEmprunterVeloAbonneBloque() throws IncorrectNameException {
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        Abonne a = new Abonne("Fred", "12345-55555-11111111111-55"); //abonné bloqué
        IVelo v1 = new Velo();
        v1.decrocher();
        s.arrimerVelo(v1, 1);
        Assert.assertEquals(null, s.emprunterVelo(a, 1));
    }

    @Test
    public void testEmprunterVeloNonDecrochage() throws IncorrectNameException {
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        Abonne a = new Abonne("Theo", "999999999999999999999999999999999999999999999999");
        IVelo v1 = new Velo();
        v1.arrimer();
        s.arrimerVelo(v1, 1);
        Assert.assertEquals(null, s.emprunterVelo(a, 1));
    }

    /* test arrimerVelo */
    @Test
    public void testArrimerVeloNullVelo() {
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v = null;
        Assert.assertEquals(-1, s.arrimerVelo(v, 1));
    }

    @Test
    public void testArrimerVeloMauvaisesBornes() {
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        IVelo v1 = new Velo();
        Assert.assertEquals(-1, s.arrimerVelo(v1, 0));
        Assert.assertEquals(-1, s.arrimerVelo(v1, -1));
        Assert.assertEquals(-1, s.arrimerVelo(v1, 11));
    }

    @Test
    public void testArrimerVeloRegistre() {
        Station s = new Station("test", 47.5, 6.02, 10);
        IVelo v1 = new Velo();
        Assert.assertEquals(-2, s.arrimerVelo(v1, 1));
    }



    @Test
    public void testArrimerVeloBorneOccupee2() throws IncorrectNameException {
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre r = new JRegistre();
        s.setRegistre(r);
        Abonne a = new Abonne("Theo", "999999999999999999999999999999999999999999999999");
        IVelo v1 = new Velo();
        v1.decrocher();
        IVelo v2 = new Velo();
        r.emprunter(a, v2, System.currentTimeMillis());
        Assert.assertEquals(0, s.arrimerVelo(v2, 1));
        Assert.assertEquals(-2, s.arrimerVelo(v1, 1));
    }


    // equilibrer

    @Test
    public void testEquilibrerStationFull() {
        Station s = new Station("test", 47.5, 6.02, 7);
        IRegistre r = new JRegistre();
        s.setRegistre(r);

        //la station
        IVelo v1 = new Velo();
        s.arrimerVelo(v1, 1);

        IVelo v2 = new Velo();
        s.arrimerVelo(v2, 2);

        IVelo v3 = new Velo();
        s.arrimerVelo(v3, 3);

        IVelo v4 = new Velo();
        s.arrimerVelo(v4, 4);

        IVelo v5 = new Velo();
        s.arrimerVelo(v5, 5);

        IVelo v6 = new Velo();
        s.arrimerVelo(v6, 6);

        IVelo v7 = new Velo();
        s.arrimerVelo(v7, 7);

        //set de velos neufs
        Set<IVelo> velos = new HashSet<IVelo>();
        velos.add(new Velo());
        velos.add(new Velo());
        velos.add(new Velo());
        velos.add(new Velo());

        s.equilibrer(velos);

        Assert.assertEquals(3, s.nbBornesLibres());
        Assert.assertEquals(7, velos.size());
    }

    @Test
    public void testEquilibrerAbime() {
        Station s = new Station("test", 47.5, 6.02, 7);
        IRegistre r = new JRegistre();
        s.setRegistre(r);

        //la station
        IVelo v1 = new Velo();
        s.arrimerVelo(v1, 1);

        IVelo v2 = new Velo();
        v2.abimer();
        s.arrimerVelo(v2, 2);

        IVelo v3 = new Velo();
        s.arrimerVelo(v3, 3);

        IVelo v4 = new Velo();
        s.arrimerVelo(v4, 4);

        IVelo v5 = new Velo();
        s.arrimerVelo(v5, 5);

        //set de velos neufs
        Set<IVelo> velos = new HashSet<IVelo>();
        velos.add(new Velo());
        velos.add(new Velo());
        velos.add(new Velo());
        velos.add(new Velo());

        s.equilibrer(velos);

        Assert.assertEquals(3, s.nbBornesLibres());
        Assert.assertEquals(5, velos.size());
    }

    @Test
    public void testEquilibrerSetVideStationVide() {
        Station s = new Station("test", 47.5, 6.02, 7);
        IRegistre r = new JRegistre();
        s.setRegistre(r);

        //la station

        //set de velos neufs
        Set<IVelo> velos = new HashSet<IVelo>();

        s.equilibrer(velos);

        Assert.assertEquals(s.capacite(), s.nbBornesLibres());
        Assert.assertEquals(0, velos.size());
    }


    @Test
    public void testEquilibrerStationReviser() {
        Station s = new Station("test", 47.5, 6.02, 7);
        IRegistre r = new JRegistre();
        s.setRegistre(r);

        IVelo v1 = new Velo();
        v1.parcourir(555);
        s.arrimerVelo(v1, 1);


        //set de velos neufs
        Set<IVelo> velos = new HashSet<IVelo>();
        velos.add(new Velo());
        velos.add(new Velo());
        velos.add(new Velo());
        velos.add(new Velo());

        s.equilibrer(velos);

        Assert.assertEquals(3, s.nbBornesLibres());
        Assert.assertEquals(1, velos.size());
    }

    @Test
    public void testEquilibrerStationReviser2() {
        Station s = new Station("test", 47.5, 6.02, 7);
        IRegistre r = new JRegistre();
        s.setRegistre(r);

        IVelo v1 = new Velo();
        v1.parcourir(555);
        s.arrimerVelo(v1, 1);


        //set de velos neufs
        Set<IVelo> velos = new HashSet<IVelo>();
        velos.add(new Velo());
        velos.add(new Velo());

        s.equilibrer(velos);

        Assert.assertEquals(4, s.nbBornesLibres());
        Assert.assertEquals(0, velos.size());
    }


    @Test
    public void testDistance(){
        Station a = new Station("a", 47.5, 6.02, 10);
        Station b = new Station("b", 47.5, 4, 10);
        Assert.assertEquals(151.7, a.distance(b), 0.0);
    }

    @Test
    public void testMaintenant() {
        Station a = new Station("a", 47.5, 6.02, 10);
        Assert.assertEquals(System.currentTimeMillis(), a.maintenant());
    }

}