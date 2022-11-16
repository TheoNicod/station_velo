/**
 * CHUAT ROMAIN
 */

package fr.ufc.l3info.oprog;


import org.junit.Assert;
import org.junit.Test;

/**
 * Test unitaire pour les abonnés.
 */
public class AbonneTest_RC {

    /**
     * Test du nom
     */
    @Test
    public void testNom() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        // vérification de son nom
        Assert.assertEquals("Fred", a.getNom());
    }
    /**Test nom contenant le caractere '-'  */
    @Test
    public void testNomTiretValide() throws IncorrectNameException{
        Abonne a = new Abonne("Fred-Jean");
        Assert.assertEquals("Fred-Jean", a.getNom());
    }
    @Test(expected = IncorrectNameException.class)
    public void testNomTiretInvalideBefore() throws IncorrectNameException{
        new Abonne("-Fred");
        new Abonne("-Fred",  "12345-55555-11111111111-47");
    }
    @Test(expected = IncorrectNameException.class)
    public void testTiretInvalideAfter() throws IncorrectNameException{
        new Abonne("Fred-");
        new Abonne("Fred-", "12345-55555-11111111111-47");
    }
    @Test(expected = IncorrectNameException.class)
    public void testTiretInvalideMultipleBefore() throws IncorrectNameException{
        new Abonne("--Jean");
        new Abonne("--Jean", "12345-55555-11111111111-47");
    }
    @Test(expected = IncorrectNameException.class)
    public void testTiretInvalideMultipleAfter() throws IncorrectNameException{
        new Abonne("Fred--");
        new Abonne("Fred----");
        new Abonne("Fred--" , "12345-55555-11111111111-47");
    }
    @Test(expected = IncorrectNameException.class)
    public void testTiretInvalideMultilpleMiddle() throws IncorrectNameException{
        new Abonne("Fred--Jean");
        new Abonne("Fred--Jean", "12345-55555-11111111111-47");
    }

    /** Test Longueur du nom*/

    @Test(expected = IncorrectNameException.class)
    public void testLongeurNom() throws IncorrectNameException{
        new Abonne("");
        new Abonne("", "1234");
    }

    /**Test nom erreur de charactere*/
    @Test (expected = IncorrectNameException.class)
    public void testNomNoLetter() throws IncorrectNameException{
        new Abonne("*12345");
        new Abonne("12345", "1234");
    }

    /** Test nom avec espace */
    @Test
    public void testNomSpaceMiddle() throws IncorrectNameException{
        Abonne a = new Abonne("Fred Jean");
        Assert.assertEquals("Fred Jean", a.getNom());
    }
    @Test
    public void testNomEspaceBefore() throws  IncorrectNameException{
        Abonne a = new Abonne("   Fred");
        Assert.assertEquals("Fred",a.getNom());
        Abonne b = new Abonne (" Fred");
        Assert.assertEquals("Fred",a.getNom());
    }
    @Test
    public void testNomEspaceAfter() throws  IncorrectNameException{
        Abonne a = new Abonne("Fred   ");
        Assert.assertEquals("Fred",a.getNom());
        Abonne b = new Abonne ("Fred ");
        Assert.assertEquals("Fred",a.getNom());
    }
    @Test
    public void testNomEspaceMultipleError() throws  IncorrectNameException{
        Abonne a = new Abonne("  Fred  Jean  ");
        Assert.assertEquals("Fred Jean",a.getNom());
    }
    /**
     * test RIB
     */
    @Test
    public void testRibValidCle() throws IncorrectNameException{
        Abonne a = new Abonne("Fred", "12345-55555-11111111111-47");
        Assert.assertFalse(a.estBloque());
    }
    @Test
    public void testRibInvalideFormat() throws IncorrectNameException{
        Abonne a = new Abonne("Fred", "12345555555511111111111");
        Assert.assertTrue(a.estBloque());
    }
    @Test
    public void testRibInvalideCle() throws IncorrectNameException{
        Abonne a = new Abonne("Fred", "12345-55555-11111111111-48");
        Assert.assertTrue(a.estBloque());
    }
    @Test
    public void testRibInvalideLongueur() throws IncorrectNameException{
        Abonne a = new Abonne("Fred", "12345-55555-11111111111");
        Assert.assertTrue(a.estBloque());
    }
    @Test
    public void testRibInvalideCaractere() throws IncorrectNameException{
        Abonne a = new Abonne("Fred", "ABCDE-55555-11111111111-48");
        Assert.assertTrue(a.estBloque());
    }
    /**
     * Test getID
     */
    @Test
    public void testGetIDValide() throws  IncorrectNameException{
        Abonne a = new Abonne("Fred");
        Abonne b = new Abonne("Fred-Jean");
        Abonne c = new Abonne("Jean");
        Assert.assertEquals(b.getID(), a.getID()+1);
        Assert.assertEquals(c.getID(), b.getID()+1);
    }
    @Test
    public void testGetIDInvalide() throws  IncorrectNameException{
        Abonne a = new Abonne("Fred");
        Abonne b = new Abonne("Fred-Jean");
        Assert.assertNotEquals(b.getID(), a.getID()+3);
    }
    /**
     * Test mise a jour du RIB
     */
    @Test
    public void testMiseAJourValidetoValide() throws IncorrectNameException{
        Abonne a = new Abonne("Fred", "12345-55555-11111111111-47");
        Assert.assertFalse(a.estBloque());
        a.miseAJourRIB("12345-55555-11111111111-47");
        Assert.assertFalse(a.estBloque());
    }
    @Test
    public void testMiseAJourInvalideToValide() throws IncorrectNameException{
        Abonne a = new Abonne("Fred", "ABCDE-55555-11111111111-48");
        Assert.assertTrue(a.estBloque());
        a.miseAJourRIB("12345-55555-11111111111-47");
        Assert.assertFalse(a.estBloque());
    }
    @Test
    public void testMiseAJourInvalideLongueur() throws IncorrectNameException{
        Abonne a = new Abonne("Fred", "ABCDE-55555-11111111111-48");
        Assert.assertTrue(a.estBloque());
        a.miseAJourRIB("12345-55555-11111111111-47555");
        Assert.assertTrue(a.estBloque());
    }
    @Test
    public void testMiseAJourInvalideCle() throws IncorrectNameException{
        Abonne a = new Abonne("Fred", "ABCDE-55555-11111111111-48");
        Assert.assertTrue(a.estBloque());
        a.miseAJourRIB("12345-55555-11111111111-55");
        Assert.assertTrue(a.estBloque());
    }
    @Test
    public void testMiseAJourInvalideCaractere() throws IncorrectNameException{
        Abonne a = new Abonne("Fred", "ABCDE-55555-11111111111-48");
        Assert.assertTrue(a.estBloque());
        a.miseAJourRIB("ABCDE-55555-11111111111-55");
        Assert.assertTrue(a.estBloque());
    }
    /**
     * test equals()
     */
    @Test
    public void testEqualsTrue() throws IncorrectNameException{
        Abonne a = new Abonne("Fred", "12345-55555-11111111111-47");
        Assert.assertTrue(a.equals(a));
    }
    @Test
    public void testEqualsFalse() throws IncorrectNameException{
        Abonne a = new Abonne("Fred", "12345-55555-11111111111-47");
        Abonne b = new Abonne("Fred-Jean", "45678-12345-55555555555-81");
        Assert.assertFalse(a.equals(b));
    }
    @Test
    public void testEqualsDifferentClass() throws IncorrectNameException{
        Abonne a = new Abonne("Fred", "12345-55555-11111111111-47");
        Integer b = new Integer(1);
        Assert.assertFalse(a.equals(b));
    }
    @Test
    public void testHashCode() throws IncorrectNameException{
        Abonne a = new Abonne("Fred", "12345-55555-11111111111-47");
        Abonne b = new Abonne("Fred", "12345-55555-11111111111-47");
        Assert.assertNotEquals(a.hashCode(), b.hashCode());
    }

}

