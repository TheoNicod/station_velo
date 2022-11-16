package fr.ufc.l3info.oprog;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class OptionTest_RC {
    private IVelo velo;

    @Before
    public void BeforeVelo(){
        this.velo = new Velo();
    }

    /**
     * Test OptionCadreAlu
     */
    @Test
    public void TestOptionCadreAlu(){
        velo = new OptCadreAlu(velo);
        Assert.assertEquals("Vélo cadre mixte, cadre aluminium - 0.0 km", velo.toString());
    }
    /**
     * Test OptionFreinADisque
     */
    @Test
    public void TestptionFreinDisque(){
        velo = new OptFreinsDisque(velo);
        Assert.assertEquals("Vélo cadre mixte, freins à disque - 0.0 km", velo.toString());
    }
    /**
     * Test OptionSuspensionAvant
     */
    @Test
    public void TestOptionSuspensionAvant(){
        velo = new OptSuspensionAvant(velo);
        Assert.assertEquals("Vélo cadre mixte, suspension avant - 0.0 km", velo.toString());
    }
    /**
     * Test OptionSuspensionArriere
     */
    @Test
    public void TestOptionSuspensionArriere(){
        velo = new OptSuspensionArriere(velo);
        Assert.assertEquals("Vélo cadre mixte, suspension arrière - 0.0 km", velo.toString());
    }

    /**
     * Test OptionAssistanceElectrique
     */
    @Test
    public void TestStringOptAsssitElec(){
        velo = new OptAssistanceElectrique(velo);
        Assert.assertEquals("Vélo cadre mixte, assistance électrique - 0.0 km", velo.toString());
    }

    /**
     * Test Option
     */
    @Test
    public void Test2Option(){
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        Assert.assertEquals("Vélo cadre mixte, cadre aluminium, freins à disque - 0.0 km", velo.toString());
    }
    @Test
    public void Test3Option(){
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        velo = new OptSuspensionArriere(velo);
        Assert.assertEquals("Vélo cadre mixte, cadre aluminium, freins à disque, suspension arrière - 0.0 km", velo.toString());
    }
    @Test
    public void Test4Option(){
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        velo = new OptSuspensionAvant(velo);
        velo = new OptSuspensionArriere(velo);
        Assert.assertEquals("Vélo cadre mixte, cadre aluminium, freins à disque, suspension avant, suspension arrière - 0.0 km", velo.toString());
    }
    @Test
    public void Test5Option(){
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        velo = new OptSuspensionAvant(velo);
        velo = new OptSuspensionArriere(velo);
        velo = new OptAssistanceElectrique(velo);
        Assert.assertEquals("Vélo cadre mixte, cadre aluminium, freins à disque, suspension avant, suspension arrière, assistance électrique - 0.0 km", velo.toString());
    }

    /**
     * Test kilometrage()
     */
    @Test
    public void TestOptionKilometrage(){
        OptCadreAlu cadreAlu = new OptCadreAlu(velo);
        cadreAlu.decrocher();
        cadreAlu.parcourir(75);
        Assert.assertEquals(75, velo.kilometrage(), 0);
        Assert.assertEquals(75, cadreAlu.kilometrage(), 0);

        OptFreinsDisque freinsDisque = new OptFreinsDisque(velo);
        freinsDisque.decrocher();
        freinsDisque.parcourir(75);
        Assert.assertEquals(150, velo.kilometrage(), 0);
        Assert.assertEquals(150, freinsDisque.kilometrage(), 0);

        OptSuspensionAvant suspensionAvant = new OptSuspensionAvant(velo);
        suspensionAvant.decrocher();
        suspensionAvant.parcourir(75);
        Assert.assertEquals(225, velo.kilometrage(), 0);
        Assert.assertEquals(225, suspensionAvant.kilometrage(), 0);

        OptSuspensionArriere suspensionArriere  = new OptSuspensionArriere(velo);
        suspensionArriere.decrocher();
        suspensionArriere.parcourir(75);
        Assert.assertEquals(300, velo.kilometrage(), 0);
        Assert.assertEquals(300, suspensionArriere.kilometrage(), 0);

        OptAssistanceElectrique assistanceElectrique  = new OptAssistanceElectrique(velo);
        assistanceElectrique.decrocher();
        assistanceElectrique.parcourir(75);
        Assert.assertEquals(375, velo.kilometrage(), 0);
        Assert.assertEquals(375, assistanceElectrique.kilometrage(), 0);
    }

    /**
     * Test prochaineRevision()
     */
    @Test
    public void TestProchaineRevision(){
        OptCadreAlu cadreAlu = new OptCadreAlu(velo);
        cadreAlu.decrocher();
        cadreAlu.parcourir(75);
        Assert.assertEquals(425, velo.prochaineRevision(), 0);
        Assert.assertEquals(425, cadreAlu.prochaineRevision(), 0);

        OptFreinsDisque freinsDisque = new OptFreinsDisque(velo);
        freinsDisque.decrocher();
        freinsDisque.parcourir(75);
        Assert.assertEquals(350, velo.prochaineRevision(), 0);
        Assert.assertEquals(350, freinsDisque.prochaineRevision(), 0);

        OptSuspensionAvant suspensionAvant = new OptSuspensionAvant(velo);
        suspensionAvant.decrocher();
        suspensionAvant.parcourir(75);
        Assert.assertEquals(275, velo.prochaineRevision(), 0);
        Assert.assertEquals(275, suspensionAvant.prochaineRevision(), 0);

        OptSuspensionArriere suspensionArriere  = new OptSuspensionArriere(velo);
        suspensionArriere.decrocher();
        suspensionArriere.parcourir(75);
        Assert.assertEquals(200, velo.prochaineRevision(), 0);
        Assert.assertEquals(200, suspensionArriere.prochaineRevision(), 0);

        OptAssistanceElectrique assistanceElectrique  = new OptAssistanceElectrique(velo);
        assistanceElectrique.decrocher();
        assistanceElectrique.parcourir(75);
        Assert.assertEquals(125, velo.prochaineRevision(), 0);
        Assert.assertEquals(125, assistanceElectrique.prochaineRevision(), 0);
    }

    /**
     * Test parcourir()
     */
    @Test
    public void TestParcourir(){
        OptCadreAlu cadreAlu = new OptCadreAlu(velo);
        cadreAlu.decrocher();
        cadreAlu.parcourir(75);
        Assert.assertEquals(75, velo.kilometrage(), 0);
        Assert.assertEquals(75, cadreAlu.kilometrage(), 0);

        OptFreinsDisque freinsDisque = new OptFreinsDisque(velo);
        freinsDisque.decrocher();
        freinsDisque.parcourir(75);
        Assert.assertEquals(150, velo.kilometrage(), 0);
        Assert.assertEquals(150, freinsDisque.kilometrage(), 0);

        OptSuspensionAvant suspensionAvant = new OptSuspensionAvant(velo);
        suspensionAvant.decrocher();
        suspensionAvant.parcourir(75);
        Assert.assertEquals(225, velo.kilometrage(), 0);
        Assert.assertEquals(225, suspensionAvant.kilometrage(), 0);

        OptSuspensionArriere suspensionArriere  = new OptSuspensionArriere(velo);
        suspensionArriere.decrocher();
        suspensionArriere.parcourir(75);
        Assert.assertEquals(300, velo.kilometrage(), 0);
        Assert.assertEquals(300, suspensionArriere.kilometrage(), 0);

        OptAssistanceElectrique assistanceElectrique  = new OptAssistanceElectrique(velo);
        assistanceElectrique.decrocher();
        assistanceElectrique.parcourir(75);
        Assert.assertEquals(375, velo.kilometrage(), 0);
        Assert.assertEquals(375, assistanceElectrique.kilometrage(), 0);
    }

    /**
     * Test tarif()
     */
    @Test
    public void TestTarifOptCadreAlu(){
        OptCadreAlu cadreAlu = new OptCadreAlu(velo);
        Assert.assertEquals(2.2, cadreAlu.tarif(), 0);
    }
    @Test
    public void TestTarifFreinADisque(){
        OptFreinsDisque freinsDisque = new OptFreinsDisque(velo);
        Assert.assertEquals(2.3, freinsDisque.tarif(), 0);
    }
    @Test
    public void TestTarifOptionSuspensionAvant(){
        OptSuspensionAvant suspensionAvant = new OptSuspensionAvant(velo);
        Assert.assertEquals(2.5, suspensionAvant.tarif(), 0);
    }
    @Test
    public void TestTarifOptSuspensionArriere(){
        OptSuspensionArriere suspensionArriere = new OptSuspensionArriere(velo);
        Assert.assertEquals(2.5, suspensionArriere.tarif(), 0);
    }
    @Test
    public void TestTarifOptAssistanceElectrique(){
        OptAssistanceElectrique assistanceElectrique = new OptAssistanceElectrique(velo);
        Assert.assertEquals(4, assistanceElectrique.tarif(), 0);
    }
    @Test
    public void TestTarif2Option(){
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        Assert.assertEquals(2.5, velo.tarif(), 0);
    }
    @Test
    public void TestTarif3Option(){
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        velo = new OptSuspensionArriere(velo);
        Assert.assertEquals(3, velo.tarif(), 0);
    }
    @Test
    public void TestTarif4Option(){
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        velo = new OptSuspensionAvant(velo);
        velo = new OptSuspensionArriere(velo);
        Assert.assertEquals(3.5, velo.tarif(), 0);
    }
    @Test
    public void TestTarif5Option(){
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        velo = new OptSuspensionAvant(velo);
        velo = new OptSuspensionArriere(velo);
        velo = new OptAssistanceElectrique(velo);
        Assert.assertEquals(5.5, velo.tarif(), 0);
    }

    /**
     * Test decrocher()
     */
    @Test
    public void TestDecrocherSuccess(){
        OptCadreAlu cadreAlu = new OptCadreAlu(velo);
        Assert.assertEquals(0, cadreAlu.decrocher());
        cadreAlu.arrimer();

        OptFreinsDisque freinsDisque = new OptFreinsDisque(velo);
        Assert.assertEquals(0, freinsDisque.decrocher());
        freinsDisque.arrimer();

        OptSuspensionAvant suspensionAvant = new OptSuspensionAvant(velo);
        Assert.assertEquals(0, suspensionAvant.decrocher());
        suspensionAvant.arrimer();

        OptSuspensionArriere suspensionArriere  = new OptSuspensionArriere(velo);
        Assert.assertEquals(0, suspensionArriere.decrocher());
        suspensionArriere.arrimer();

        OptAssistanceElectrique assistanceElectrique  = new OptAssistanceElectrique(velo);
        Assert.assertEquals(0, assistanceElectrique.decrocher());
    }
    @Test
    public void TestDecrocherFail(){
        OptCadreAlu cadreAlu = new OptCadreAlu(velo);
        cadreAlu.decrocher();
        Assert.assertEquals(-1, velo.decrocher());
        Assert.assertEquals(-1, cadreAlu.decrocher());
        cadreAlu.arrimer();

        OptFreinsDisque freinsDisque = new OptFreinsDisque(velo);
        freinsDisque.decrocher();
        Assert.assertEquals(-1, velo.decrocher());
        Assert.assertEquals(-1, freinsDisque.decrocher());
        freinsDisque.arrimer();

        OptSuspensionAvant suspensionAvant = new OptSuspensionAvant(velo);
        suspensionAvant.decrocher();
        Assert.assertEquals(-1, velo.decrocher());
        Assert.assertEquals(-1, suspensionAvant.decrocher());
        suspensionAvant.arrimer();

        OptSuspensionArriere suspensionArriere  = new OptSuspensionArriere(velo);
        suspensionArriere.decrocher();
        Assert.assertEquals(-1, velo.decrocher());
        Assert.assertEquals(-1, suspensionArriere.decrocher());
        suspensionArriere.arrimer();

        OptAssistanceElectrique assistanceElectrique  = new OptAssistanceElectrique(velo);
        assistanceElectrique.decrocher();
        Assert.assertEquals(-1, velo.decrocher());
        Assert.assertEquals(-1, assistanceElectrique.decrocher());
    }

    /**
     * Test arrimer()
     */
    @Test
    public void TestArrimerSucces(){
        OptCadreAlu cadreAlu = new OptCadreAlu(velo);
        cadreAlu.decrocher();
        Assert.assertEquals(0, cadreAlu.arrimer());

        OptFreinsDisque freinsDisque = new OptFreinsDisque(velo);
        freinsDisque.decrocher();
        Assert.assertEquals(0, freinsDisque.arrimer());

        OptSuspensionAvant suspensionAvant = new OptSuspensionAvant(velo);
        suspensionAvant.decrocher();
        Assert.assertEquals(0, suspensionAvant.arrimer());

        OptSuspensionArriere suspensionArriere  = new OptSuspensionArriere(velo);
        suspensionArriere.decrocher();
        Assert.assertEquals(0, suspensionArriere.arrimer());

        OptAssistanceElectrique assistanceElectrique  = new OptAssistanceElectrique(velo);
        assistanceElectrique.decrocher();
        Assert.assertEquals(0, assistanceElectrique.arrimer());
    }
    @Test
    public void TestArrimerFail(){
        OptCadreAlu cadreAlu = new OptCadreAlu(velo);
        cadreAlu.arrimer();
        Assert.assertEquals(-1, velo.arrimer());
        Assert.assertEquals(-1, cadreAlu.arrimer());
        cadreAlu.decrocher();

        OptFreinsDisque freinsDisque = new OptFreinsDisque(velo);
        freinsDisque.arrimer();
        Assert.assertEquals(-1, velo.arrimer());
        Assert.assertEquals(-1, freinsDisque.arrimer());
        freinsDisque.decrocher();

        OptSuspensionAvant suspensionAvant = new OptSuspensionAvant(velo);
        suspensionAvant.arrimer();
        Assert.assertEquals(-1, velo.arrimer());
        Assert.assertEquals(-1, suspensionAvant.arrimer());
        suspensionAvant.decrocher();

        OptSuspensionArriere suspensionArriere  = new OptSuspensionArriere(velo);
        suspensionArriere.arrimer();
        Assert.assertEquals(-1, velo.arrimer());
        Assert.assertEquals(-1, suspensionArriere.arrimer());
        suspensionArriere.decrocher();

        OptAssistanceElectrique assistanceElectrique  = new OptAssistanceElectrique(velo);
        assistanceElectrique.arrimer();
        Assert.assertEquals(-1, velo.arrimer());
        Assert.assertEquals(-1, assistanceElectrique.arrimer());
    }

    /**
     * Test abimer()
     */
    @Test
    public void TestAbimer(){
        OptCadreAlu cadreAlu = new OptCadreAlu(velo);
        cadreAlu.abimer();
        Assert.assertTrue( velo.estAbime());
        Assert.assertTrue( cadreAlu.estAbime());
        cadreAlu.reparer();

        OptFreinsDisque freinsDisque = new OptFreinsDisque(velo);
        freinsDisque.abimer();
        Assert.assertTrue( velo.estAbime());
        Assert.assertTrue( freinsDisque.estAbime());
        freinsDisque.reparer();

        OptSuspensionAvant suspensionAvant = new OptSuspensionAvant(velo);
        suspensionAvant.abimer();
        Assert.assertTrue( velo.estAbime());
        Assert.assertTrue( suspensionAvant.estAbime());
        suspensionAvant.reparer();

        OptSuspensionArriere suspensionArriere  = new OptSuspensionArriere(velo);
        suspensionArriere.abimer();
        Assert.assertTrue( velo.estAbime());
        Assert.assertTrue( suspensionArriere.estAbime());
        suspensionArriere.reparer();

        OptAssistanceElectrique assistanceElectrique  = new OptAssistanceElectrique(velo);
        assistanceElectrique.abimer();
        Assert.assertTrue( velo.estAbime());
        Assert.assertTrue( assistanceElectrique.estAbime());
        assistanceElectrique.reparer();
    }
    @Test
    public void TestAbimer5OPtion(){
        OptCadreAlu cadreAlu = new OptCadreAlu(velo);
        cadreAlu.abimer();
        Assert.assertTrue( velo.estAbime());
        Assert.assertTrue( cadreAlu.estAbime());

        OptFreinsDisque freinsDisque = new OptFreinsDisque(velo);
        Assert.assertTrue( velo.estAbime());
        Assert.assertTrue( freinsDisque.estAbime());

        OptSuspensionAvant suspensionAvant = new OptSuspensionAvant(velo);
        Assert.assertTrue( velo.estAbime());
        Assert.assertTrue( suspensionAvant.estAbime());

        OptSuspensionArriere suspensionArriere  = new OptSuspensionArriere(velo);
        Assert.assertTrue( velo.estAbime());
        Assert.assertTrue( suspensionArriere.estAbime());

        OptAssistanceElectrique assistanceElectrique  = new OptAssistanceElectrique(velo);
        Assert.assertTrue( velo.estAbime());
        Assert.assertTrue( assistanceElectrique.estAbime());
    }
    @Test
    public void TestAbimerReparation(){
        OptCadreAlu cadreAlu = new OptCadreAlu(velo);
        cadreAlu.decrocher();
        cadreAlu.abimer();
        Assert.assertTrue( velo.estAbime());
        Assert.assertTrue( cadreAlu.estAbime());
        velo.reparer();

        OptFreinsDisque freinsDisque = new OptFreinsDisque(velo);
        Assert.assertFalse( velo.estAbime());
        Assert.assertFalse( freinsDisque.estAbime());

        OptSuspensionAvant suspensionAvant = new OptSuspensionAvant(velo);
        suspensionAvant.abimer();
        Assert.assertTrue( velo.estAbime());
        Assert.assertTrue( suspensionAvant.estAbime());
        suspensionAvant.reparer();

        OptSuspensionArriere suspensionArriere  = new OptSuspensionArriere(velo);
        Assert.assertFalse( velo.estAbime());
        Assert.assertFalse( suspensionArriere.estAbime());

        OptAssistanceElectrique assistanceElectrique  = new OptAssistanceElectrique(velo);
        Assert.assertFalse( velo.estAbime());
        Assert.assertFalse( assistanceElectrique.estAbime());
    }

    /**
     * Test estAbime()
     */
    @Test
    public void TestEstAbimerNonAbimer(){
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        velo = new OptSuspensionAvant(velo);
        velo = new OptSuspensionArriere(velo);
        velo = new OptAssistanceElectrique(velo);
        Assert.assertFalse(velo.estAbime());
    }
    @Test
    public void TestEstAbimer(){
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        velo = new OptSuspensionAvant(velo);
        velo = new OptSuspensionArriere(velo);
        velo = new OptAssistanceElectrique(velo);
        velo.decrocher();
        velo.abimer();
        Assert.assertTrue(velo.estAbime());
    }
    @Test
    public void TestEstAbimerMiddle(){
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        velo = new OptSuspensionAvant(velo);
        velo.abimer();
        velo = new OptSuspensionArriere(velo);
        velo = new OptAssistanceElectrique(velo);
        Assert.assertTrue(velo.estAbime());
    }
    @Test
    public void TestEstAbimerMiddleReparation(){
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        velo = new OptSuspensionAvant(velo);
        velo.abimer();
        Assert.assertTrue(velo.estAbime());
        velo = new OptSuspensionArriere(velo);
        velo = new OptAssistanceElectrique(velo);
        velo.decrocher();
        velo.reparer();
        Assert.assertFalse(velo.estAbime());
    }
    /**
     * Test Reviser()
     */
    @Test
    public void TestReviser(){
        velo = new OptCadreAlu(velo);
        velo = new OptAssistanceElectrique(velo);
        velo = new OptSuspensionArriere(velo);
        velo = new OptSuspensionAvant(velo);
        velo = new OptFreinsDisque(velo);
        velo.decrocher();
        velo.parcourir(500);
        velo.abimer();
        Assert.assertEquals(500,velo.kilometrage(), 0);
        Assert.assertTrue(velo.estAbime());
        Assert.assertEquals(0, velo.reviser());
        Assert.assertEquals(0, velo.kilometrage(), 0);
        Assert.assertFalse(velo.estAbime());
    }
    @Test
    public void TestReparer(){
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        velo = new OptAssistanceElectrique(velo);
        velo = new OptSuspensionAvant(velo);
        velo = new OptSuspensionArriere(velo);
        velo.decrocher();
        velo.abimer();
        Assert.assertTrue(velo.estAbime());
        Assert.assertEquals(0, velo.reparer());
        Assert.assertFalse(velo.estAbime());
    }
    /**
     * Test toString()
     */
    @Test
    public void TestToString(){
        velo = new Velo('F');
        velo = new OptCadreAlu(velo);
        velo.decrocher();
        velo.parcourir(120.54);
        Assert.assertEquals("Vélo cadre femme, cadre aluminium - 120.5 km", velo.toString());
    }
    @Test
    public void TestToString5Option(){
        velo = new Velo('F');
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        velo = new OptSuspensionAvant(velo);
        velo = new OptSuspensionArriere(velo);
        velo = new OptAssistanceElectrique(velo);
        velo.decrocher();
        velo.parcourir(120.54);
        Assert.assertEquals("Vélo cadre femme, cadre aluminium, freins à disque, suspension avant, suspension arrière, assistance électrique - 120.5 km", velo.toString());
    }
    @Test
    public void TestToString4Option(){
        velo = new Velo('F');
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        velo = new OptSuspensionAvant(velo);
        velo = new OptSuspensionArriere(velo);
        velo.decrocher();
        velo.parcourir(120.54);
        Assert.assertEquals("Vélo cadre femme, cadre aluminium, freins à disque, suspension avant, suspension arrière - 120.5 km", velo.toString());
    }
    @Test
    public void TestToString3Option(){
        velo = new Velo('F');
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        velo = new OptSuspensionAvant(velo);
        velo.decrocher();
        velo.parcourir(120.54);
        Assert.assertEquals("Vélo cadre femme, cadre aluminium, freins à disque, suspension avant - 120.5 km", velo.toString());
    }
    @Test
    public void TestToString2Option(){
        velo = new Velo('F');
        velo = new OptCadreAlu(velo);
        velo = new OptFreinsDisque(velo);
        velo.decrocher();
        velo.parcourir(120.54);
        Assert.assertEquals("Vélo cadre femme, cadre aluminium, freins à disque - 120.5 km", velo.toString());
    }

    /**
     * Test fabriqueVelo
     */
    @Test
    public void TestFabriqueInstanceOptCadreAlu(){
        FabriqueVelo fb = FabriqueVelo.getInstance();
        velo = fb.construire('h', "CADRE_ALUMINIUM");
        Assert.assertEquals("Vélo cadre homme, cadre aluminium - 0.0 km",velo.toString());
    }
    @Test
    public void TestFabriqueInstanceOptSuspensionAvant(){
        FabriqueVelo fb = FabriqueVelo.getInstance();
        velo = fb.construire('h', "SUSPENSION_AVANT");
        Assert.assertEquals("Vélo cadre homme, suspension avant - 0.0 km",velo.toString());
    }
    @Test
    public void TestFabriqueInstance2OPtion(){
        FabriqueVelo fb = FabriqueVelo.getInstance();
        velo = fb.construire('h', "CADRE_ALUMINIUM","FREINS_DISQUE");
        Assert.assertEquals("Vélo cadre homme, cadre aluminium, freins à disque - 0.0 km",velo.toString());
    }
    @Test
    public void TestFabriqueInstance3OPtion(){
        FabriqueVelo fb = FabriqueVelo.getInstance();
        velo = fb.construire('h', "CADRE_ALUMINIUM","FREINS_DISQUE","SUSPENSION_ARRIERE");
        Assert.assertEquals("Vélo cadre homme, cadre aluminium, freins à disque, suspension arrière - 0.0 km",velo.toString());
    }
    @Test
    public void TestFabriqueInstance2CadreAlu(){
        FabriqueVelo fb = FabriqueVelo.getInstance();
        velo = fb.construire('h', "CADRE_ALUMINIUM","CADRE_ALUMINIUM");
        Assert.assertEquals("Vélo cadre homme, cadre aluminium - 0.0 km",velo.toString());
    }
    @Test
    public void TestFabriqueInstance2FreinADisque(){
        FabriqueVelo fb = FabriqueVelo.getInstance();
        velo = fb.construire('h', "FREINS_DISQUE","FREINS_DISQUE");
        Assert.assertEquals("Vélo cadre homme, freins à disque - 0.0 km",velo.toString());
    }
    @Test
    public void TestFabriqueInstance2SuspensionAvant(){
        FabriqueVelo fb = FabriqueVelo.getInstance();
        velo = fb.construire('h', "SUSPENSION_AVANT","SUSPENSION_AVANT");
        Assert.assertEquals("Vélo cadre homme, suspension avant - 0.0 km",velo.toString());
    }
    @Test
    public void TestFabriqueInstance2SuspensionArriere(){
        FabriqueVelo fb = FabriqueVelo.getInstance();
        velo = fb.construire('h', "SUSPENSION_ARRIERE","SUSPENSION_ARRIERE");
        Assert.assertEquals("Vélo cadre homme, suspension arrière - 0.0 km",velo.toString());
    }
    @Test
    public void TestFabriqueInstance2AssistanceElec(){
        FabriqueVelo fb = FabriqueVelo.getInstance();
        velo = fb.construire('h', "ASSISTANCE_ELECTRIQUE","ASSISTANCE_ELECTRIQUE");
        Assert.assertEquals("Vélo cadre homme, assistance électrique - 0.0 km",velo.toString());
    }
    @Test
    public void TestFabriqueInstanceErreurChaine(){
        FabriqueVelo fb = FabriqueVelo.getInstance();
        velo = fb.construire('h', "ASSISTANCE_ELECTRIQUE","AAAAAAAA", "SUSPENSION_ARRIERE");
        Assert.assertEquals("Vélo cadre homme, assistance électrique, suspension arrière - 0.0 km",velo.toString());
    }
    @Test
    public void TestFabriqueInstance2Instance(){
        FabriqueVelo fb = FabriqueVelo.getInstance();
        FabriqueVelo fb1 = FabriqueVelo.getInstance();
        Assert.assertEquals(fb1,fb);
    }


}
