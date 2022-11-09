package fr.ufc.l3info.oprog;

import org.junit.Assert;
import org.junit.Test;

/** Tests unitaires pour la fabrique de vélo */

public class FabriqueVeloTest {

    @Test
    public void testGetInstanceMultipleInstance(){
        FabriqueVelo fv = FabriqueVelo.getInstance();
        Assert.assertNotEquals(null, fv);
        FabriqueVelo fv2 = FabriqueVelo.getInstance();
        Assert.assertNotEquals(null, fv2);
        Assert.assertEquals(fv, fv2);
    }

    @Test
    public void testConstruireDefault(){
        FabriqueVelo fv = FabriqueVelo.getInstance();
        IVelo v = fv.construire('a');
        Assert.assertTrue(v.toString().contains("mixte"));
    }

    @Test
    public void testConstruireVeloFemme(){
        FabriqueVelo fv = FabriqueVelo.getInstance();
        IVelo v = fv.construire('f');
        Assert.assertTrue(v.toString().contains("femme"));

        IVelo v2 = fv.construire('F');
        Assert.assertTrue(v2.toString().contains("femme"));
    }

    @Test
    public void testConstruireVeloHomme(){
        FabriqueVelo fv = FabriqueVelo.getInstance();
        IVelo v = fv.construire('h');
        Assert.assertTrue(v.toString().contains("homme"));

        IVelo v2 = fv.construire('H');
        Assert.assertTrue(v2.toString().contains("homme"));
    }

    @Test
    public void testConstruireAllOptions(){
        FabriqueVelo fv = FabriqueVelo.getInstance();
        IVelo v = fv.construire('a', "CADRE_ALUMINIUM", "SUSPENSION_AVANT", "SUSPENSION_ARRIERE", "FREINS_DISQUE", "ASSISTANCE_ELECTRIQUE");
        Assert.assertTrue(v.toString().contains("cadre aluminium, suspension avant, suspension arrière, freins à disque, assistance électrique"));
    }

    @Test
    public void testConstruireBadOptions(){
        FabriqueVelo fv = FabriqueVelo.getInstance();
        IVelo v = fv.construire('a', "salut", "SUSPENSION_AVANT", "bonjour", "FREINS_DISQUE", "ASSISTANCE_ELECTRIQUE");
        Assert.assertTrue(v.toString().contains("suspension avant, freins à disque, assistance électrique"));
        Assert.assertFalse(v.toString().contains("salut"));
        Assert.assertFalse(v.toString().contains("bonjour"));
    }

    @Test
    public void testConstruireSameOptions(){
        FabriqueVelo fv = FabriqueVelo.getInstance();
        IVelo v = fv.construire('a', "CADRE_ALUMINIUM", "SUSPENSION_AVANT", "SUSPENSION_AVANT", "SUSPENSION_ARRIERE", "FREINS_DISQUE", "ASSISTANCE_ELECTRIQUE", "ASSISTANCE_ELECTRIQUE", "CADRE_ALUMINIUM", "SUSPENSION_ARRIERE", "FREINS_DISQUE");
        Assert.assertTrue(v.toString().contains("cadre aluminium, suspension avant, suspension arrière, freins à disque, assistance électrique"));
        System.out.println(v);
    }

    @Test
    public void testConstruireNullOption(){
        FabriqueVelo fv = FabriqueVelo.getInstance();
        IVelo v = fv.construire('t', "CADRE_ALUMINIUM", null);
        Assert.assertTrue(v.toString().contains("cadre aluminium"));
        System.out.println(v);
    }

}