package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Test;

/** Tests unitaires pour les vélos */

public class VeloTest {

    /** Test des constructeurs **/

    @Test
    public void testConstructeurValideSansParametre () {
        Velo v = new Velo();
        Assert.assertTrue(v.toString().contains("mixte"));
    }

    @Test
    public void testConstructeurValideAvecParametre () {
        Velo v = new Velo ('h');
        Assert.assertTrue(v.toString().contains("homme"));

    }

    @Test
    public void testConstructeurValideAvecParametre2 () {
        Velo v = new Velo ('H');
        Assert.assertTrue(v.toString().contains("homme"));
    }

    @Test
    public void testConstructeurValideAvecParametre3 () {
        Velo v = new Velo ('f');
        Assert.assertTrue(v.toString().contains("femme"));
    }

    @Test
    public void testConstructeurValideAvecParametre4 () {
        Velo v = new Velo ('F');
        Assert.assertTrue(v.toString().contains("femme"));
    }

    /** Test kilometrage() */

    @Test
    public void testKilometrageInit () {
        Velo v = new Velo();
        Assert.assertEquals(0, v.kilometrage(), 0.0);
    }

    @Test
    public void testKilometrageSet () {
        Velo v = new Velo();
        v.parcourir(100);
        Assert.assertEquals(100, v.kilometrage(), 0.0);
    }

    /** Test prochaineRevision() */

    @Test
    public void testProchaineRevisionInit() {
        Velo v = new Velo();
        Assert.assertEquals(500, v.prochaineRevision(), 0.0);
    }

    @Test
    public void testProchaineRevisionPositive() {
        Velo v = new Velo();
        v.parcourir(250.5);
        Assert.assertEquals(249.5, v.prochaineRevision(), 0.0);
    }

    @Test
    public void testProchaineRevisionNegative() {
        Velo v = new Velo();
        v.parcourir(789.4);
        Assert.assertEquals(-289.4, v.prochaineRevision(), 0.0);
    }

    /** Test parcourir(double) */

    @Test
    public void testParcourirDecroche() {
        Velo v = new Velo();
        v.decrocher();
        v.parcourir(200);
        Assert.assertEquals(200, v.kilometrage(), 0.0);
        Assert.assertEquals(300, v.prochaineRevision(), 0.0);
    }

    @Test
    public void testParcourirAccroche() {
        Velo v = new Velo();
        v.arrimer();
        v.parcourir(200);
        Assert.assertEquals(0, v.kilometrage(), 0.0);
        Assert.assertEquals(500, v.prochaineRevision(), 0.0);
    }

    @Test
    public void testParcourirIncrementeKilometrage() {
        Velo v = new Velo();
        v.decrocher();
        v.parcourir(100);
        v.parcourir(378);
        Assert.assertEquals(478, v.kilometrage(), 0.0);
    }

    @Test
    public void testParcourirDecrementeRevision() {
        Velo v = new Velo();
        v.decrocher();
        v.parcourir(378);
        Assert.assertEquals(122, v.prochaineRevision(), 0.0);
    }

    @Test
    public void testParcourirInvalideArg() {
        Velo v = new Velo();
        v.parcourir(-200);
        Assert.assertEquals(0, v.kilometrage(), 0.0);
        Assert.assertEquals(500, v.prochaineRevision(), 0.0);
    }

    /** Test tarif() */
    @Test
    public void testTarif(){
        Velo v = new Velo();
        Assert.assertEquals(2.0, v.tarif(), 0.0);
    }

    /** Test decrocher() */
    @Test
    public void testDecroche () {
        Velo v = new Velo();
        Assert.assertEquals(-1, v.decrocher());
    }

    @Test
    public void testDejaDecroche () {
        Velo v = new Velo();
        v.arrimer();
        Assert.assertEquals(0, v.decrocher());
    }

    /** Test arrimer() */

    @Test
    public void testArrime() {
        Velo v = new Velo();
        Assert.assertEquals(0, v.arrimer());
    }

    @Test
    public void testDejaArrime() {
        Velo v = new Velo();
        v.arrimer();
        Assert.assertEquals(-1, v.arrimer());
    }

    /** Test abime() */

    @Test
    public void testAbime() {
        Velo v = new Velo();
        Assert.assertFalse(v.estAbime());
        v.abimer();
        Assert.assertTrue(v.estAbime());
    }

    /** Test estAbime() */
    @Test
    public void testEstAbimeInit(){
        Velo v = new Velo();
        Assert.assertFalse(v.estAbime());
    }

    @Test
    public void testEstAbime(){
        Velo v = new Velo();
        v.abimer();
        Assert.assertTrue(v.estAbime());
    }

    /** Test reviser() */
    @Test
    public void testReviserAccroche (){
        Velo v = new Velo();
        v.arrimer();
        Assert.assertEquals(-1, v.reviser());
        Assert.assertEquals(500, v.prochaineRevision(), 0.0);
    }

    @Test
    public void testReviserDecrocheNonAbime() {
        Velo v = new Velo();
        v.parcourir(456);
        Assert.assertEquals(0, v.reviser());
        Assert.assertEquals(500, v.prochaineRevision(), 0.0);
    }

    @Test
    public void testReviserDecrocheAbime() {
        Velo v = new Velo();
        v.abimer();
        v.parcourir(456);
        Assert.assertEquals(0, v.reviser());
        Assert.assertEquals(500, v.prochaineRevision(), 0.0);
        Assert.assertFalse(v.estAbime());
    }

    /** Test reparer() */

    @Test
    public void testReparerDecrocheAbime(){
        Velo v = new Velo();
        v.abimer();
        Assert.assertEquals(0, v.reparer());
        Assert.assertFalse(v.estAbime());
    }

    @Test
    public void testReparerAccrocheAbime(){
        Velo v = new Velo();
        v.arrimer();
        v.abimer();
        Assert.assertEquals(-1, v.reparer());
        Assert.assertTrue(v.estAbime());
    }

    @Test
    public void testReparerDecrocheNonAbime(){
        Velo v = new Velo();
        Assert.assertEquals(-2, v.reparer());
        Assert.assertFalse(v.estAbime());
    }

    /**Test toString() */
    @Test
    public void testToStringCadreMixte() {
        Velo v = new Velo();
        Assert.assertTrue(v.toString().contains("mixte"));
    }

    @Test
    public void testToStringCadreHomme() {
        Velo v = new Velo('h');
        Assert.assertTrue(v.toString().contains("homme"));
    }

    @Test
    public void testToStringCadreFemme() {
        Velo v = new Velo('f');
        Assert.assertTrue(v.toString().contains("femme"));
    }

    @Test
    public void testToStringRevisionTrue () {
        Velo v = new Velo();
        v.parcourir(678);
        Assert.assertTrue(v.toString().contains("(révision nécessaire)"));
    }

    @Test
    public void testToStringRevisionFalse () {
        Velo v = new Velo();
        v.parcourir(398);
        Assert.assertFalse(v.toString().contains("(révision nécessaire)"));
    }

    @Test
    public void testToStringRoundKilometrage () {
        Velo v = new Velo('h');
        v.parcourir(398.47);
        Assert.assertTrue(v.toString().contains("398.5 km"));
    }












}