package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;


@RunWith(MockitoJUnitRunner.class)
public class StationWithMocksTest_RC {

    private Station st;

    @Before
    public void BeforeVelo() {
        this.st = new Station("Gare", 47.5, 6.2, 5);
    }

    @Test
    public void TestStation(){
        //Station st = new Station("Gare", 47.5,6.2, 5);
        Assert.assertEquals(st.getNom(), "Gare");
        Assert.assertEquals(5, st.capacite());
        Assert.assertEquals(5,st.nbBornesLibres());
    }

    @Test
    public void TestStationNegatifCapacite() {
        Station st = new Station("Gare", 47.5, 6.2, -5);
        Assert.assertEquals(st.getNom(), "Gare");
        Assert.assertEquals(5, st.capacite());
        Assert.assertEquals(5,st.nbBornesLibres());

    }

    @Test
    public void TestStationErreurNom() {
        Station st = new Station("", 47.5, 6.2, -5);
        //Assert.assertEquals(null, st);
        Assert.assertEquals(st.getNom(), "");
        Assert.assertEquals(5,st.nbBornesLibres());
    }

    @Test
    public void TestGetNom() {
        Assert.assertEquals("Gare", st.getNom());
    }

    @Test
    public void TestGetNomErreurCapacite() {
        st = new Station("Gare", 47.2, 6.2, -5);
        //Assert.assertEquals(null, st.getNom());
        Assert.assertEquals(st.getNom(), "Gare");
    }

    @Test
    public void TestGetNomErreurNom() {
        st = new Station("", 47.2, 6.2, -5);
        Assert.assertEquals("", st.getNom());
    }

    @Test
    public void TestCapacite() {
        Assert.assertEquals(5, st.capacite());
    }
    @Test
    public void TestCapaciteErreurCapacite() {
        st = new Station("Gare", 47.5, 6.2, -5);
        Assert.assertEquals(5, st.capacite());
    }

    @Test
    public void TestNbBornesLibres(){
        Assert.assertEquals(5, st.nbBornesLibres());
    }

    @Test
    public void TestNbBornesLibre() throws IncorrectNameException{
        //Abonne mockAbonne = Mockito.mock(Abonne.class);
        // définition du comportement attendu pour l'objet
        //Mockito.when(mockAbonne.estBloque()).thenReturn(false);

        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        st.setRegistre(mockRegistre);
        IVelo mockVelo1 = Mockito.mock(IVelo.class);
        Mockito.when(mockRegistre.retourner(mockVelo1, st.maintenant())).thenReturn(0);
        Mockito.when(mockVelo1.arrimer()).thenReturn(0);
        Assert.assertEquals(0,st.arrimerVelo(mockVelo1, 1));

        IVelo mockVelo2 = Mockito.mock(IVelo.class);
        Mockito.when(mockRegistre.retourner(mockVelo2, st.maintenant())).thenReturn(0);
        Mockito.when(mockVelo2.arrimer()).thenReturn(0);
        st.arrimerVelo(mockVelo2, 2);

        IVelo mockVelo3 = Mockito.mock(IVelo.class);
        Mockito.when(mockRegistre.retourner(mockVelo3, st.maintenant())).thenReturn(0);
        Mockito.when(mockVelo3.arrimer()).thenReturn(0);
        st.arrimerVelo(mockVelo3, 3);

        IVelo mockVelo4 = Mockito.mock(IVelo.class);
        Mockito.when(mockRegistre.retourner(mockVelo4, st.maintenant())).thenReturn(0);
        Mockito.when(mockVelo4.arrimer()).thenReturn(0);
        st.arrimerVelo(mockVelo4, 4);

        IVelo mockVelo5 = Mockito.mock(IVelo.class);
        Mockito.when(mockRegistre.retourner(mockVelo5, st.maintenant())).thenReturn(0);
        Mockito.when(mockVelo5.arrimer()).thenReturn(0);
        st.arrimerVelo(mockVelo5, 5);


        Assert.assertEquals(0, st.nbBornesLibres());
    }
    @Test
    public void TestNbBornesLibre2(){
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        st.setRegistre(mockRegistre);
        IVelo mockVelo1 = Mockito.mock(IVelo.class);
        Mockito.when(mockRegistre.retourner(mockVelo1, st.maintenant())).thenReturn(0);
        Mockito.when(mockVelo1.arrimer()).thenReturn(0);
        Assert.assertEquals(0,st.arrimerVelo(mockVelo1, 1));
        Assert.assertEquals(4, st.nbBornesLibres());
    }
    @Test
    public void TestVeloALaBorne(){
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        st.setRegistre(mockRegistre);
        IVelo mockVelo1 = Mockito.mock(IVelo.class);
        Mockito.when(mockRegistre.retourner(mockVelo1, st.maintenant())).thenReturn(0);
        Mockito.when(mockVelo1.arrimer()).thenReturn(0);
        Assert.assertEquals(0,st.arrimerVelo(mockVelo1, 2));
        Assert.assertEquals(mockVelo1, st.veloALaBorne(2));
    }
    @Test
    public void TestVeloALaBorneNegative(){
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        st.setRegistre(mockRegistre);
        IVelo mockVelo1 = Mockito.mock(IVelo.class);
        Mockito.when(mockRegistre.retourner(mockVelo1, st.maintenant())).thenReturn(0);
        Mockito.when(mockVelo1.arrimer()).thenReturn(0);
        Assert.assertEquals(0,st.arrimerVelo(mockVelo1, 2));
        Assert.assertEquals(null, st.veloALaBorne(6));
    }
    @Test
    public void TestVeloALaBorneNonExistante(){
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        st.setRegistre(mockRegistre);
        IVelo mockVelo1 = Mockito.mock(IVelo.class);
        Mockito.when(mockRegistre.retourner(mockVelo1, st.maintenant())).thenReturn(0);
        Mockito.when(mockVelo1.arrimer()).thenReturn(0);
        Assert.assertEquals(0,st.arrimerVelo(mockVelo1, 2));
        Assert.assertEquals(null, st.veloALaBorne(-1));
    }
    @Test
    public void TestVeloALaBorneVide(){
        Assert.assertEquals(null, st.veloALaBorne(2));
    }

    @Test
    public void TestEmprunterVelo(){
        Abonne mockAbonne = Mockito.mock(Abonne.class);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        st.setRegistre(mockRegistre);

        Velo mockVelo = Mockito.mock(Velo.class);
        Mockito.when(mockRegistre.retourner(mockVelo,st.maintenant())).thenReturn(0);
        st.arrimerVelo(mockVelo,2);
        Assert.assertEquals(mockVelo, st.veloALaBorne(2));

        Mockito.when(mockRegistre.nbEmpruntsEnCours(mockAbonne)).thenReturn(0);
        Mockito.when(mockAbonne.estBloque()).thenReturn(false);
//        Mockito.when((mockVelo.decrocher())).thenReturn(0);
        Mockito.when(mockRegistre.emprunter(mockAbonne,mockVelo,st.maintenant())).thenReturn(0);
        Assert.assertEquals(mockVelo,st.emprunterVelo(mockAbonne,2));
    }
    @Test
    public void TestEmprunterAbonneBloque(){
        Abonne mockAbonne = Mockito.mock(Abonne.class);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        st.setRegistre(mockRegistre);

        Velo mockVelo = Mockito.mock(Velo.class);
        Mockito.when(mockRegistre.retourner(mockVelo,st.maintenant())).thenReturn(0);
        st.arrimerVelo(mockVelo,2);
        Assert.assertEquals(mockVelo, st.veloALaBorne(2));

        Mockito.when(mockRegistre.nbEmpruntsEnCours(mockAbonne)).thenReturn(0);
        Mockito.when(mockAbonne.estBloque()).thenReturn(true);
//        Mockito.when((mockVelo.decrocher())).thenReturn(0);
//        Mockito.when(mockRegistre.emprunter(mockAbonne,mockVelo,st.maintenant())).thenReturn(0);
        Assert.assertEquals(null,st.emprunterVelo(mockAbonne,2));
    }
    @Test
    public void TestEmprunterAbonneInvalide(){
        /*Abonne mockAbonne = Mockito.mock(Abonne.class);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        st.setRegistre(mockRegistre);

        Velo mockVelo = Mockito.mock(Velo.class);
        Mockito.when(mockRegistre.retourner(mockVelo,st.maintenant())).thenReturn(0);
        st.arrimerVelo(mockVelo,2);
        Assert.assertEquals(mockVelo, st.veloALaBorne(2));

        Mockito.when(mockRegistre.nbEmpruntsEnCours(mockAbonne)).thenReturn(0);
        Mockito.when(mockAbonne.estBloque()).thenReturn(true);
        Mockito.when((mockVelo.decrocher())).thenReturn(0);
        Mockito.when(mockRegistre.emprunter(mockAbonne,mockVelo,st.maintenant())).thenReturn(0);*/
        Assert.assertEquals(null,st.emprunterVelo(null,2));
    }
    @Test
    public void TestEmprunteBorneVide(){
        Abonne mockAbonne = Mockito.mock(Abonne.class);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        st.setRegistre(mockRegistre);

        Velo mockVelo = Mockito.mock(Velo.class);
        Assert.assertEquals(null, st.veloALaBorne(2));

        Mockito.when(mockAbonne.estBloque()).thenReturn(false);
//        Mockito.when((mockVelo.decrocher())).thenReturn(-1);

        //Mockito.when(mockRegistre.emprunter(mockAbonne,mockVelo,st.maintenant())).thenReturn(-2);
        //Assert.assertEquals(null,st.veloALaBorne(2));
        Assert.assertEquals(null,st.emprunterVelo(mockAbonne,2));
    }
    @Test
    public void TestEmprunteBorneInexistante(){
        Abonne mockAbonne = Mockito.mock(Abonne.class);
        IRegistre mockRegistre = Mockito.mock(IRegistre.class);
        st.setRegistre(mockRegistre);

        Velo mockVelo = Mockito.mock(Velo.class);
        Mockito.when(mockRegistre.retourner(mockVelo,st.maintenant())).thenReturn(0);
        st.arrimerVelo(mockVelo,2);
        Assert.assertEquals(mockVelo, st.veloALaBorne(2));

        Mockito.when(mockRegistre.nbEmpruntsEnCours(mockAbonne)).thenReturn(0);
//        Mockito.when(mockAbonne.estBloque()).thenReturn(false);
//        Mockito.when((mockVelo.decrocher())).thenReturn(0);
//        Mockito.when(mockRegistre.emprunter(mockAbonne,mockVelo,st.maintenant())).thenReturn(0);
        Assert.assertEquals(null,st.emprunterVelo(mockAbonne,6));
    }


    @Test
    public void TestArrimer(){
        IVelo velo = Mockito.mock(IVelo.class);
        Assert.assertEquals(5, st.nbBornesLibres());
        IRegistre reg = Mockito.mock(IRegistre.class);
        st.setRegistre(reg);
        Mockito.when(reg.retourner(velo,st.maintenant())).thenReturn(0);
        Assert.assertEquals(0, st.arrimerVelo(velo, 2));
        Assert.assertEquals(4, st.nbBornesLibres());
    }
    @Test
    public void TestArrimerNoVelo(){
        /*IVelo velo = Mockito.mock(IVelo.class);
        Assert.assertEquals(5,st.nbBornesLibres());
        IRegistre reg = Mockito.mock(IRegistre.class);
        st.setRegistre(reg);
        Mockito.when(reg.retourner(velo,st.maintenant())).thenReturn(0);*/
        Assert.assertEquals(-1, st.arrimerVelo(null, 2));
    }
    @Test
    public void TestArrimerNoBorne(){
        IVelo velo = Mockito.mock(IVelo.class);
        /*Assert.assertEquals(5,st.nbBornesLibres());
        IRegistre reg = Mockito.mock(IRegistre.class);
        st.setRegistre(reg);
        Mockito.when(reg.retourner(velo,st.maintenant())).thenReturn(0);*/
        Assert.assertEquals(-1, st.arrimerVelo(velo, 6));
    }
    @Test
    public void TestArrimerBorneOccupe(){
        IVelo velo1 = Mockito.mock(IVelo.class);
        IVelo velo2 = Mockito.mock(IVelo.class);
        Assert.assertEquals(5,st.nbBornesLibres());
        IRegistre reg = Mockito.mock(IRegistre.class);
        st.setRegistre(reg);
        Mockito.when(reg.retourner(velo1,st.maintenant())).thenReturn(0);
        //Mockito.when(reg.retourner(velo2,st.maintenant())).thenReturn(0);
        Assert.assertEquals(0,st.arrimerVelo(velo1,2));
        Assert.assertEquals(-2, st.arrimerVelo(velo2, 2));
    }
    @Test
    public void TestArrimerNoRegistre(){
        IVelo velo1 = Mockito.mock(IVelo.class);
        Assert.assertEquals(5,st.nbBornesLibres());
        //IRegistre reg = Mockito.mock(IRegistre.class);
        //st.setRegistre(reg);
        //Mockito.when(reg.retourner(velo,st.maintenant())).thenReturn(0);*/
        Assert.assertEquals(-2, st.arrimerVelo(velo1, 2));
    }
    @Test
    public void TestArrimerErreurRegistre1(){
        IVelo velo = Mockito.mock(IVelo.class);
        Assert.assertEquals(5,st.nbBornesLibres());
        IRegistre reg = Mockito.mock(IRegistre.class);
        st.setRegistre(reg);
        Mockito.when(reg.retourner(velo,st.maintenant())).thenReturn(-2);
        Assert.assertEquals(-3, st.arrimerVelo(velo, 2));
    }
    /*@Test
    public void TestEquilibrer(){
        IVelo veloAbimer = Mockito.mock(IVelo.class);

        //veloAbimer.abimer();
        Set<IVelo> velos = new TreeSet<IVelo>();
        IVelo velo1 = Mockito.mock(IVelo.class);
        st.arrimerVelo(veloAbimer, 2);
        Mockito.when(veloAbimer.estAbime()).thenReturn(true);
        velos.add(velo1);
        st.equilibrer(velos);
        Assert.assertEquals(velo1,st.veloALaBorne(2));


    }*/


    @Test
    public void TestMaintenant(){
        Assert.assertEquals(System.currentTimeMillis(), st.maintenant());
    }
}