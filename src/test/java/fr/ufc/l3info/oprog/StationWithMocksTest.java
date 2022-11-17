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

/** Tests unitaires pour la station */

@RunWith(MockitoJUnitRunner.class)
public class StationWithMocksTest {

    /* test Constructor */
    @Test
    public void testStationConstructor(){
        Station s = new Station("test", 47.5, 6.02, 10);
        Assert.assertEquals("test", s.getNom());
        Assert.assertEquals(10, s.capacite());
    }



    /* test setRegistre */
    @Test
    public void testSetRegistre(){

    }

    /* test getNom */
    @Test
    public void testGetNom() {
        Station s = new Station("test", 47.5, 6.02, 10);
        Assert.assertEquals("test", s.getNom());
    }

    /* test capacite */
    @Test
    public void testCapacite(){
        Station s = new Station("test", 47.5, 6.02, 10);
        Assert.assertEquals(10, s.capacite());
    }

    /* test nbBornesLibres */
    @Test
    public void testNbBornesLibresDefaut(){
        Station s = new Station("test", 47.5, 6.02, 10);
        Assert.assertEquals(10, s.nbBornesLibres());
    }

    @Test
    public void testNbBornesLibres(){
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre mockRegistre = Mockito.mock((IRegistre.class));
        s.setRegistre(mockRegistre);
        IVelo mockIVelo = Mockito.mock((IVelo.class));
        IVelo mockIVelo2 = Mockito.mock((IVelo.class));
        s.arrimerVelo(mockIVelo, 1);
        s.arrimerVelo(mockIVelo2, 2);
        Assert.assertEquals(8, s.nbBornesLibres());
    }

    /* test veloALaBorne */
    @Test
    public void testVeloALaBorneBornesVides() {
        Station s = new Station("test", 47.5, 6.02, 10);
        for(int i = 1; i <= 10; i++)
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
    public void testVeloALaBorne(){
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        s.setRegistre(mockRegistre);
        IVelo mockIVelo = Mockito.mock(IVelo.class);
        //Mockito.when(mockIVelo.decrocher()).thenReturn(0);
        s.arrimerVelo(mockIVelo, 4);
        Assert.assertEquals(mockIVelo, s.veloALaBorne(4));
    }

    /* test emprunterVelo */
    @Test
    public void testEmprunterVeloNonSetRegistre() {
        Station s = new Station("test", 47.5, 6.02, 10);
        Abonne mockABonne = Mockito.mock(Abonne.class);
        IVelo mockIVelo = Mockito.mock(IVelo.class);
        //Mockito.when(mockIVelo.decrocher()).thenReturn(0);
        s.arrimerVelo(mockIVelo, 1);
        Assert.assertEquals(null, s.emprunterVelo(mockABonne, 1));
    }

    @Test
    public void testEmprunterVeloBorneVide() {
        Station s = new Station("test", 47.5, 6.02, 10);
        Abonne mockABonne = Mockito.mock(Abonne.class);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        s.setRegistre(mockRegistre);
        Assert.assertEquals(null, s.emprunterVelo(mockABonne, 1));
    }

    @Test
    public void testEmprunterVeloAbonne() {
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        s.setRegistre(mockRegistre);
        Abonne mockABonne = Mockito.mock(Abonne.class);
        IVelo mockIVelo = Mockito.mock(IVelo.class);
        Mockito.when(mockIVelo.decrocher()).thenReturn(0);
        s.arrimerVelo(mockIVelo, 1);
        Assert.assertEquals(mockIVelo, s.emprunterVelo(mockABonne, 1));

        //puis test abonne déjà en train d'emprunter
        IVelo mockIVelo2 = Mockito.mock(IVelo.class);
        //Mockito.when(mockIVelo.decrocher()).thenReturn(0);
        s.arrimerVelo(mockIVelo, 1);
        Assert.assertEquals(null, s.emprunterVelo(mockABonne, 1));
    }

    @Test
    public void testEmprunterVeloMauvaiseBorne(){
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        s.setRegistre(mockRegistre);
        Abonne mockABonne = Mockito.mock(Abonne.class);
        Assert.assertEquals(null, s.emprunterVelo(mockABonne, 0));
        Assert.assertEquals(null, s.emprunterVelo(mockABonne, 11));
        Assert.assertEquals(null, s.emprunterVelo(mockABonne, -1));
    }

    @Test
    public void testEmprunterVeloAbonneBloque() {
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        s.setRegistre(mockRegistre);
        Abonne mockABonne = Mockito.mock(Abonne.class);
        Mockito.when(mockABonne.estBloque()).thenReturn(true);
        IVelo mockIVelo = Mockito.mock(IVelo.class);
        //Mockito.when(mockIVelo.decrocher()).thenReturn(0);
        s.arrimerVelo(mockIVelo, 1);
        Assert.assertEquals(null, s.emprunterVelo(mockABonne, 1));
    }

    @Test
    public void testEmprunterVeloNonDecrochage() {
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        s.setRegistre(mockRegistre);
        Abonne mockABonne = Mockito.mock(Abonne.class);
        IVelo mockIVelo = Mockito.mock(IVelo.class);
        Mockito.when(mockIVelo.decrocher()).thenReturn(-1);
        s.arrimerVelo(mockIVelo, 1);
        Assert.assertEquals(null, s.emprunterVelo(mockABonne, 1));
    }

    /* test arrimerVelo */
    @Test
    public void testArrimerVeloNullVelo(){
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        s.setRegistre(mockRegistre);
        IVelo v = null;
        Assert.assertEquals(-1, s.arrimerVelo(v, 1));
    }

    @Test
    public void testArrimerVeloMauvaisesBornes(){
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        s.setRegistre(mockRegistre);
        IVelo mockIVelo = Mockito.mock(IVelo.class);
        Assert.assertEquals(-1, s.arrimerVelo(mockIVelo, 0));
        Assert.assertEquals(-1, s.arrimerVelo(mockIVelo, -1));
        Assert.assertEquals(-1, s.arrimerVelo(mockIVelo, 11));
    }

    @Test
    public void testArrimerVeloRegistre(){
        Station s = new Station("test", 47.5, 6.02, 10);
        IVelo mockIVelo = Mockito.mock(IVelo.class);
        Assert.assertEquals(-2, s.arrimerVelo(mockIVelo, 1));
    }

    @Test
    public void testArrimerVeloBorneOccupee(){
        Station s = new Station("test", 47.5, 6.02, 10);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        s.setRegistre(mockRegistre);
        IVelo mockIVelo = Mockito.mock(IVelo.class);
        IVelo mockIVelo2 = Mockito.mock(IVelo.class);
        Assert.assertEquals(0, s.arrimerVelo(mockIVelo2, 1));
        Assert.assertEquals(-2, s.arrimerVelo(mockIVelo, 1));

    }


    // equilibrer

    @Test
    public void testEquilibrerStationFull() {
        Station s = new Station("test", 47.5, 6.02, 7);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        s.setRegistre(mockRegistre);

        //la station
        IVelo mockVelo1 = Mockito.mock(IVelo.class);
        s.arrimerVelo(mockVelo1, 1);

        IVelo mockVelo2 = Mockito.mock(IVelo.class);
        s.arrimerVelo(mockVelo2, 2);

        IVelo mockVelo3 = Mockito.mock(IVelo.class);
        s.arrimerVelo(mockVelo3, 3);

        IVelo mockVelo4 = Mockito.mock(IVelo.class);
        s.arrimerVelo(mockVelo4, 4);

        IVelo mockVelo5 = Mockito.mock(IVelo.class);
        s.arrimerVelo(mockVelo5, 5);

        IVelo mockVelo6 = Mockito.mock(IVelo.class);
        s.arrimerVelo(mockVelo6, 6);

        IVelo mockVelo7 = Mockito.mock(IVelo.class);
        s.arrimerVelo(mockVelo7, 7);

        //set de velos neufs
        Set<IVelo> velos = new HashSet<IVelo>();
        velos.add(Mockito.mock(IVelo.class));
        velos.add(Mockito.mock(IVelo.class));
        velos.add(Mockito.mock(IVelo.class));
        velos.add(Mockito.mock(IVelo.class));

        s.equilibrer(velos);

        Assert.assertEquals(3, s.nbBornesLibres());
        Assert.assertEquals(7, velos.size());
    }

    @Test
    public void testEquilibrerAbime() {
        Station s = new Station("test", 47.5, 6.02, 7);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        s.setRegistre(mockRegistre);

        //la station
        IVelo mockVelo1 = Mockito.mock(Velo.class);
        Mockito.when(mockVelo1.estAbime()).thenReturn(true);
        s.arrimerVelo(mockVelo1, 1);

        IVelo mockVelo2 = Mockito.mock(Velo.class);
        s.arrimerVelo(mockVelo2, 2);

        IVelo mockVelo3 = Mockito.mock(Velo.class);
        Mockito.when(mockVelo2.estAbime()).thenReturn(true);
        s.arrimerVelo(mockVelo3, 3);

        IVelo mockVelo4 = Mockito.mock(Velo.class);
        s.arrimerVelo(mockVelo4, 4);

        IVelo mockVelo5 = Mockito.mock(Velo.class);
        s.arrimerVelo(mockVelo5, 5);


        //set de velos neufs
        Set<IVelo> velos = new HashSet<IVelo>();
        velos.add(Mockito.mock(IVelo.class));
        velos.add(Mockito.mock(IVelo.class));
        velos.add(Mockito.mock(IVelo.class));
        velos.add(Mockito.mock(IVelo.class));

        s.equilibrer(velos);

        Assert.assertEquals(3, s.nbBornesLibres());
        Assert.assertEquals(5, velos.size());
    }

    @Test
    public void testEquilibrerSetVideStationVide() {
        Station s = new Station("test", 47.5, 6.02, 7);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        s.setRegistre(mockRegistre);

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
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        s.setRegistre(mockRegistre);

/*         Mockito.doCallRealMethod().when(mockVelo2).parcourir(501); ???????????
           Mockito.doNothing.when(mockVelo2).parcourir(501); ????????????


 NE MARCHE PAS ?????????????????????????????????????????????????????????????????????????????????????
        //la station
        IVelo mockVelo1 = Mockito.mock(Velo.class);
        s.arrimerVelo(mockVelo1, 1);

        IVelo mockVelo2 = Mockito.mock(Velo.class);
        Mockito.doCallRealMethod().when(mockVelo2).parcourir(501);
        s.arrimerVelo(mockVelo2, 2);

        IVelo mockVelo3 = Mockito.mock(Velo.class);
        s.arrimerVelo(mockVelo3, 3);

        IVelo mockVelo4 = Mockito.mock(Velo.class);
        s.arrimerVelo(mockVelo4, 4);

        IVelo mockVelo5 = Mockito.mock(Velo.class);
        s.arrimerVelo(mockVelo5, 5);

        IVelo mockVelo6 = Mockito.mock(Velo.class);
        s.arrimerVelo(mockVelo6, 6);

        IVelo mockVelo7 = Mockito.mock(Velo.class);
        s.arrimerVelo(mockVelo7, 7);
*/

        IVelo v1 = new Velo();
        v1.parcourir(555);
        s.arrimerVelo(v1, 1);


        //set de velos neufs
        Set<IVelo> velos = new HashSet<IVelo>();
        velos.add(Mockito.mock(IVelo.class));
        velos.add(Mockito.mock(IVelo.class));
        velos.add(Mockito.mock(IVelo.class));
        velos.add(Mockito.mock(IVelo.class));

        s.equilibrer(velos);

        Assert.assertEquals(3, s.nbBornesLibres());
        Assert.assertEquals(1, velos.size());
    }

    @Test
    public void testEquilibrerStationReviser2() {
        Station s = new Station("test", 47.5, 6.02, 7);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        s.setRegistre(mockRegistre);

        IVelo v1 = new Velo();
        v1.parcourir(555);
        s.arrimerVelo(v1, 1);


        //set de velos neufs
        Set<IVelo> velos = new HashSet<IVelo>();
        velos.add(Mockito.mock(IVelo.class));
        velos.add(Mockito.mock(IVelo.class));

        s.equilibrer(velos);

        Assert.assertEquals(4, s.nbBornesLibres());
        Assert.assertEquals(0, velos.size());
    }

    @Test
    public void testDistance(){
        Station a = new Station("a", 47.5, 6.02, 10);
        Station b = new Station("b", 47.5, 8, 10);
        Assert.assertEquals(148.7, a.distance(b), 0.0);
    }

    @Test
    public void testMaintenant() {
        Station a = new Station("a", 47.5, 6.02, 10);
        Assert.assertEquals(System.currentTimeMillis(), a.maintenant());
    }




}