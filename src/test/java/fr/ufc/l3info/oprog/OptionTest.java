package fr.ufc.l3info.oprog;

//import org.jetbrains.annotations.TestOnly;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

/** Tests unitaires pour les options */

public class OptionTest {
    /**
     * Test des méthodes de IVelo non re-implémenté dans la classe Option
     */

    @Test
    public void testAllOptionsKilometrage() {
        IVelo v = new Velo();
        v = new OptAssistanceElectrique(v);
        v = new OptCadreAlu(v);
        v = new OptSuspensionAvant(v);
        v = new OptSuspensionArriere(v);
        v = new OptFreinsDisque(v);
        Assert.assertEquals(0, v.kilometrage(), 0.0);
        v.parcourir(400);
        Assert.assertEquals(400, v.kilometrage(), 0.0);
        v.arrimer();
        v.parcourir(400);
        Assert.assertEquals(400, v.kilometrage(), 0.0);
    }

    @Test
    public void testAllOptionsRevision() {
        IVelo v = new Velo();
        v = new OptAssistanceElectrique(v);
        v = new OptCadreAlu(v);
        v = new OptSuspensionAvant(v);
        v = new OptSuspensionArriere(v);
        v = new OptFreinsDisque(v);
        Assert.assertEquals(500, v.prochaineRevision(), 0.0);
        v.parcourir(250);
        Assert.assertEquals(250, v.prochaineRevision(), 0.0);
    }

    @Test
    public void testAllOptionsParcourir() {
        IVelo v = new Velo();
        v = new OptAssistanceElectrique(v);
        v = new OptCadreAlu(v);
        v = new OptSuspensionAvant(v);
        v = new OptSuspensionArriere(v);
        v = new OptFreinsDisque(v);
        v.decrocher();
        v.parcourir(-1);
        Assert.assertEquals(0, v.kilometrage(), 0.0);
        v.parcourir(200);
        Assert.assertEquals(200, v.kilometrage(), 0.0);
    }

    @Test
    public void testAllOptionsDecroche() {
        IVelo v = new Velo();
        v = new OptAssistanceElectrique(v);
        v = new OptCadreAlu(v);
        v = new OptSuspensionAvant(v);
        v = new OptSuspensionArriere(v);
        v = new OptFreinsDisque(v);
        Assert.assertEquals(-1, v.decrocher());
        v.arrimer();
        Assert.assertEquals(0, v.decrocher());
    }

    @Test
    public void testAllOptionsArrimer() {
        IVelo v = new Velo();
        v = new OptAssistanceElectrique(v);
        v = new OptCadreAlu(v);
        v = new OptSuspensionAvant(v);
        v = new OptSuspensionArriere(v);
        v = new OptFreinsDisque(v);
        Assert.assertEquals(0, v.arrimer());
        Assert.assertEquals(-1, v.arrimer());
    }

    @Test
    public void testAllOptionsAbime() {
        IVelo v = new Velo();
        v = new OptAssistanceElectrique(v);
        v = new OptCadreAlu(v);
        v = new OptSuspensionAvant(v);
        v = new OptSuspensionArriere(v);
        v = new OptFreinsDisque(v);
        Assert.assertFalse(v.estAbime());
        v.abimer();
        Assert.assertTrue(v.estAbime());
    }

    @Test
    public void testAllOptionsReviser() {
        IVelo v = new Velo();
        v = new OptAssistanceElectrique(v);
        v = new OptCadreAlu(v);
        v = new OptSuspensionAvant(v);
        v = new OptSuspensionArriere(v);
        v = new OptFreinsDisque(v);

        v.parcourir(456);
        Assert.assertEquals(0, v.reviser());
        Assert.assertEquals(500, v.prochaineRevision(), 0.0);

        v.abimer();
        v.parcourir(456);
        Assert.assertEquals(0, v.reviser());
        Assert.assertEquals(500, v.prochaineRevision(), 0.0);
        Assert.assertFalse(v.estAbime());

        v.parcourir(13);
        v.arrimer();
        Assert.assertEquals(-1, v.reviser());
        Assert.assertEquals(487, v.prochaineRevision(), 0.0);
    }

    @Test
    public void testAllOptionsReparer() {
        IVelo v = new Velo();
        v = new OptAssistanceElectrique(v);
        v = new OptCadreAlu(v);
        v = new OptSuspensionAvant(v);
        v = new OptSuspensionArriere(v);
        v = new OptFreinsDisque(v);

        v.abimer();
        Assert.assertEquals(0, v.reparer());
        Assert.assertFalse(v.estAbime());

        Assert.assertEquals(-2, v.reparer());
        Assert.assertFalse(v.estAbime());

        v.arrimer();
        v.abimer();
        Assert.assertEquals(-1, v.reparer());
        Assert.assertTrue(v.estAbime());
    }

    @Test
    public void testAllOptionsToString() {
        IVelo v = new Velo();
        v = new OptAssistanceElectrique(v);
        v = new OptCadreAlu(v);
        v = new OptSuspensionAvant(v);
        v = new OptSuspensionArriere(v);
        Assert.assertTrue(v.toString().contains("mixte"));

        IVelo v2 = new Velo('h');
        v2 = new OptAssistanceElectrique(v2);
        v2 = new OptCadreAlu(v2);
        v2 = new OptSuspensionArriere(v2);
        v2 = new OptSuspensionArriere(v2);
        Assert.assertTrue(v2.toString().contains("homme"));

        IVelo v3 = new Velo('H');
        v3 = new OptAssistanceElectrique(v3);
        v3 = new OptCadreAlu(v3);
        v3 = new OptSuspensionAvant(v3);
        v3 = new OptSuspensionArriere(v3);
        Assert.assertTrue(v3.toString().contains("homme"));

        IVelo v4 = new Velo('f');
        v4 = new OptAssistanceElectrique(v4);
        v4 = new OptCadreAlu(v4);
        v4 = new OptSuspensionAvant(v4);
        v4 = new OptSuspensionArriere(v4);
        Assert.assertTrue(v4.toString().contains("femme"));

        IVelo v5 = new Velo('F');
        v5 = new OptAssistanceElectrique(v5);
        v5 = new OptCadreAlu(v5);
        v5 = new OptSuspensionAvant(v5);
        v5 = new OptSuspensionArriere(v5);
        Assert.assertTrue(v5.toString().contains("femme"));

        v.parcourir(398);
        Assert.assertFalse(v.toString().contains("(révision nécessaire)"));

        v.parcourir(670.87);
        Assert.assertTrue(v.toString().contains("(révision nécessaire)"));

        Assert.assertTrue(v.toString().contains("1068.9 km"));
    }


    /**
     * test des tarifs pour chaque option
     */
    @Test
    public void testOptCadreAluTarif() {
        OptCadreAlu cadreAlu = new OptCadreAlu(new Velo());
        Assert.assertEquals(2.2, cadreAlu.tarif(), 0.0);
    }

    @Test
    public void testOptSuspensionAvantTarif() {
        OptSuspensionAvant suspensionAvant = new OptSuspensionAvant(new Velo());
        Assert.assertEquals(2.5, suspensionAvant.tarif(), 0.0);
    }
    @Test
    public void testOptSuspensionArriereTarif() {
        OptSuspensionArriere suspensionArriere = new OptSuspensionArriere(new Velo());
        Assert.assertEquals(2.5, suspensionArriere.tarif(), 0.0);
    }
    @Test
    public void testOptAssistanceElectriqueTarif() {
        OptAssistanceElectrique assistanceElectrique = new OptAssistanceElectrique(new Velo());
        Assert.assertEquals(4, assistanceElectrique.tarif(), 0.0);
    }

    @Test
    public void testAllOptionsTarif() {
        IVelo v = new Velo();
        v = new OptAssistanceElectrique(v);
        v = new OptCadreAlu(v);
        v = new OptSuspensionAvant(v);
        v = new OptSuspensionArriere(v);
        Assert.assertEquals(5.2, v.tarif(), 0.0);
    }

    /**
     * test des toString pour chaque option
     */
    @Test
    public void testOptAssitanceElectriqueToString() {
        OptAssistanceElectrique assistanceElectrique = new OptAssistanceElectrique(new Velo());
        Assert.assertTrue(assistanceElectrique.toString().contains("assistance électrique"));
    }

    @Test
    public void testOptCadreAluToString() {
        OptCadreAlu cadreAlu = new OptCadreAlu(new Velo());
        Assert.assertTrue(cadreAlu.toString().contains("cadre aluminium"));
    }

    @Test
    public void testOptSuspensionAvantToString() {
        OptSuspensionAvant suspensionAvant = new OptSuspensionAvant(new Velo());
        Assert.assertTrue(suspensionAvant.toString().contains("suspension avant"));
    }

    @Test
    public void testOptSuspensionArriereToString() {
        OptSuspensionArriere suspensionArriere = new OptSuspensionArriere(new Velo());
        Assert.assertTrue(suspensionArriere.toString().contains("suspension arrière"));
    }

    @Test
    public void testOptFreinsDisqueToString() {
        OptFreinsDisque freinsDisque = new OptFreinsDisque(new Velo());
        Assert.assertTrue(freinsDisque.toString().contains("freins à disque"));
    }

    @Test
    public void testAllOptionsToString2() {
        IVelo v = new Velo();
        v = new OptAssistanceElectrique(v);
        v = new OptCadreAlu(v);
        v = new OptSuspensionAvant(v);
        v = new OptSuspensionArriere(v);
        v = new OptFreinsDisque(v);
        Assert.assertTrue(v.toString().contains("Vélo cadre mixte, assistance électrique, cadre aluminium, suspension avant, suspension arrière, freins à disque - 0.0 km"));
    }
}