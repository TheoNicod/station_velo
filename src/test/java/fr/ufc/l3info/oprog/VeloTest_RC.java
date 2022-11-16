package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class VeloTest_RC {
    private Velo v;

    @Before
    public void BeforeVelo(){
        this.v = new Velo();
    }
    /**
     * test constructeur
     */
    @Test
    public void testVelo(){
        Assert.assertEquals("Vélo cadre mixte - 0.0 km", v.toString());
        Assert.assertEquals(2.0,v.tarif(),0.0);
    }
    @Test
    public void testVeloHomme(){
        v = new Velo('h');
        Assert.assertEquals("Vélo cadre homme - 0.0 km", v.toString());
    }
    @Test
    public void testVeloHomme2(){
        v = new Velo('H');
        Assert.assertEquals("Vélo cadre homme - 0.0 km", v.toString());
    }
    @Test
    public void testVeloFemme(){
        v = new Velo('f');
        Assert.assertEquals("Vélo cadre femme - 0.0 km", v.toString());
    }
    @Test
    public void testVeloFemme2(){
        v = new Velo('F');
        Assert.assertEquals("Vélo cadre femme - 0.0 km", v.toString());
    }
    @Test
    public void testVeloMixte(){
        v = new Velo('m');
        Assert.assertEquals("Vélo cadre mixte - 0.0 km", v.toString());
    }
    /**
     * test kilometrage()
     */
    @Test
    public void TestKilometrage(){
        //v.decrocher();
        v.parcourir(50);
        v.parcourir(100);
        Assert.assertEquals(150,v.kilometrage(),0);
    }
    @Test
    public void TestKilometrageNonDecrocher(){
        //v.parcourir(50);
        v.arrimer();
        v.parcourir(100);
        Assert.assertEquals(0,v.kilometrage(),0);
    }
    @Test
    public void TestKilometrageInvalide(){
        //v.decrocher();
        v.parcourir(50);
        v.parcourir(100);
        Assert.assertNotEquals(160,v.kilometrage(),0);
    }
    /**
     * test prochaineRevision()
     */
    @Test
    public void testProchaineRevisionValide(){
        //v.decrocher();
        v.parcourir(50);
        v.parcourir(100);
        Assert.assertEquals(350,v.prochaineRevision(), 0);
    }
    @Test
    public void testProchaineRevisionNegatif(){
        //v.decrocher();
        v.parcourir(600);
        v.arrimer();
        Assert.assertEquals(-100, v.prochaineRevision(), 0 );
    }
    @Test
    public void testProchaineRevisionInvalide(){
        v.parcourir(150);
        Assert.assertNotEquals(100, v.prochaineRevision());
    }
    /**
     * test tarif()
     */
    @Test
    public void testTarif(){
        Assert.assertEquals(2.0, v.tarif(), 0);
    }
    /**
     * test decrocher()
     */
    @Test
    public void testDecrocher(){
        v.arrimer();
        Assert.assertEquals(0,v.decrocher());
    }
    @Test
    public void testDecrocherFail(){
        //v.decrocher();
        Assert.assertEquals(-1, v.decrocher());
    }
    /**
     * test arrimer()
     */
    @Test
    public void testArrimer(){
        v.decrocher();
        Assert.assertEquals(0, v.arrimer());
    }
    @Test
    public void testArrimerFail(){
        v.arrimer();
        Assert.assertEquals(-1,v.arrimer());
    }
    /**
     *  test abimer()
     */
    @Test
    public void testAbimer(){
        v.abimer();
        Assert.assertTrue(v.estAbime());
    }
    /**
     * test estAbime()
     */
    @Test
    public void testEstAbimeNonAbime(){
        Assert.assertFalse(v.estAbime());
    }
    @Test
    public void testEstAbime(){
        v.abimer();
        Assert.assertTrue(v.estAbime());
    }
    /**
     * test reviser()
     */
    @Test
    public void testReviser(){
        v.decrocher();
        Assert.assertEquals(0, v.reviser());
        Assert.assertEquals(0, v.kilometrage(), 0);
        Assert.assertFalse(v.estAbime());
    }
    @Test
    public void testReviserAccrocher(){
        v.decrocher();
        v.parcourir(100);
        v.abimer();
        v.arrimer();
        Assert.assertEquals(-1, v.reviser());
        Assert.assertTrue(v.estAbime());
        Assert.assertEquals(100, v.kilometrage(), 0);
    }
    @Test
    public void testReviserAbimer(){
        v.decrocher();
        v.parcourir(600);
        v.abimer();
        Assert.assertEquals(0, v.reviser());
        Assert.assertEquals(0,v.kilometrage(),0);
        Assert.assertFalse(v.estAbime());
    }
    /**
     * Test reparer()
     */
    @Test
    public void testReparerSucces(){
        v.decrocher();
        v.abimer();
        Assert.assertEquals(0,v.reparer());
        Assert.assertFalse(v.estAbime());
    }
    @Test
    public void testReparerArrimer(){
        v.decrocher();
        v.abimer();
        v.arrimer();
        Assert.assertEquals(-1,v.reparer());
        Assert.assertTrue(v.estAbime());
    }
    @Test
    public void testReparerNonAbime(){
        v.decrocher();
        Assert.assertEquals(-2,v.reparer());
    }
    /**
     * Test toString()
     */
    @Test
    public void testToString(){
        v.decrocher();
        v.parcourir(20.0);
        Assert.assertEquals( "Vélo cadre mixte - 20.0 km", v.toString());
    }
    @Test
    public void testToStringArrondi(){
        v.decrocher();
        v.parcourir(20.138);
        Assert.assertEquals( "Vélo cadre mixte - 20.1 km", v.toString());
    }
    @Test
    public void testToStringRevision(){
        v.decrocher();
        v.parcourir(650.0);
        Assert.assertEquals( "Vélo cadre mixte - 650.0 km (révision nécessaire)", v.toString());
    }
    @Test
    public void testToSringFemme(){
        v = new Velo('f');
        v.decrocher();
        v.parcourir(450.0);
        Assert.assertEquals( "Vélo cadre femme - 450.0 km", v.toString());
    }
    @Test
    public void testToSringFemmeRevision(){
        v = new Velo('f');
        v.decrocher();
        v.parcourir(650.0);
        Assert.assertEquals( "Vélo cadre femme - 650.0 km (révision nécessaire)", v.toString());
    }
    @Test
    public void testToSringFemmeRevisionArrondie(){
        v = new Velo('f');
        v.decrocher();
        v.parcourir(650.56);
        Assert.assertEquals( "Vélo cadre femme - 650.6 km (révision nécessaire)", v.toString());
    }
    @Test
    public void testToSringFemmeArrondie(){
        v = new Velo('f');
        v.decrocher();
        v.parcourir(65.56);
        Assert.assertEquals( "Vélo cadre femme - 65.6 km", v.toString());
    }
    @Test
    public void testToSringHommeRevisionArrondie(){
        v = new Velo('h');
        v.decrocher();
        v.parcourir(650.54);
        Assert.assertEquals( "Vélo cadre homme - 650.5 km (révision nécessaire)", v.toString());
    }
    @Test
    public void testToSringMixteRevisionArrondie(){
        v = new Velo('x');
        v.decrocher();
        v.parcourir(650.56);
        Assert.assertEquals( "Vélo cadre mixte - 650.6 km (révision nécessaire)", v.toString());
    }
}
