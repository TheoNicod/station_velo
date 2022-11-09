package fr.ufc.l3info.oprog;


import org.junit.Assert;
import org.junit.Test;

/**
 * Test unitaire pour les abonnés. 
 */
public class AbonneTest {

/*    @Test
    public void testNom() throws IncorrectNameException {
        // création d'un nouvel abonné
        Abonne a = new Abonne("Fred");
        // vérification de son nom
        Assert.assertEquals(null, a.getNom());
    }

 /** Constructeur Abonne(string) **/
    @Test
    public void testConstructeurNomValide() throws IncorrectNameException{
        Abonne a = new Abonne("Theo");
        Assert.assertEquals("Theo", a.getNom());
    }

    @Test
    public void testConstructeurNomValide2() throws IncorrectNameException{
        Abonne a = new Abonne("Theo-Jacques nicod");
        Assert.assertEquals("Theo-Jacques nicod", a.getNom());
    }

    @Test
    public void testConstructeurNomValideWithSpace () throws IncorrectNameException{
        Abonne a = new Abonne("Theo    ");
        Assert.assertEquals("Theo", a.getNom());
    }

    @Test
    public void testConstructeurNomValideWithSpace2 () throws IncorrectNameException{
        Abonne a = new Abonne("Theo Jacques   ");
        Assert.assertEquals("Theo Jacques", a.getNom());
    }

    @Test (expected = IncorrectNameException.class)
    public void testConstructeurNomInvalideChar () throws IncorrectNameException{
        Abonne a = new Abonne("Théo25000@");
    }

    @Test (expected = IncorrectNameException.class)
    public void testConstructeurNomInvalideLength () throws IncorrectNameException{
        Abonne a = new Abonne("");
    }

    @Test (expected = IncorrectNameException.class)
    public void testConstructeurNomInvalideNull () throws IncorrectNameException{
        Abonne a = new Abonne(null);
    }

/** Constructeur Abonne(string, string) **/
    //je ne mets pas de tests sur le nom de ce constructeur puisque c'est du copier coller
    @Test
    public void testConstructeurRibValide() throws IncorrectNameException{
        Abonne a = new Abonne ("Theo", "12345678909876543212");
        Assert.assertEquals("12345678909876543212", a.getRib());
    }

    @Test
    public void testConstructeurRibInvalideTooShort() throws IncorrectNameException{
        Abonne a = new Abonne ("Theo", "12345");
        Assert.assertEquals("", a.getRib());
    }

    @Test
    public void testConstructeurRibInvalideTooLong() throws IncorrectNameException{
        Abonne a = new Abonne ("Theo", "12345897986856565544596756776458448484");
        Assert.assertEquals("", a.getRib());
    }

    @Test
    public void testConstructeurRibInvalideChar() throws IncorrectNameException{
        Abonne a = new Abonne ("Theo", "75kughj76 UKHGè_-");
        Assert.assertEquals("", a.getRib());
    }

    /** Test des guetters **/
    @Test
    public void testGetIDValide () throws IncorrectNameException{
        Abonne one = new Abonne("theo");
        Assert.assertEquals(1, one.getID());
    }

    @Test
    public void testGetRIBValide () throws IncorrectNameException {
        Abonne a = new Abonne("theo", "66574857675843219098");
        Assert.assertEquals("66574857675843219098", a.getRib());
    }

    @Test
    public void testMiseAJourRibValide () throws IncorrectNameException {
        Abonne a = new Abonne("theo", "66574857675843219098");
        a.miseAJourRIB("09812345678909876547");
        Assert.assertEquals("09812345678909876547", a.getRib());

        Abonne b = new Abonne("Jacque");
        a.miseAJourRIB("09812345678909876547");
        Assert.assertEquals("09812345678909876547", a.getRib());
    }

    /** Test MiseAJourRib("string") **/
    @Test
    public void testMiseAJourRibInvalideTooShort () throws IncorrectNameException {
        Abonne a = new Abonne("theo", "66574857675843219098");
        a.miseAJourRIB("9898");
        Assert.assertEquals("66574857675843219098", a.getRib());
    }

    @Test
    public void testMiseAJourRibInvalideTooLong () throws IncorrectNameException {
        Abonne a = new Abonne("theo", "66574857675843219098");
        a.miseAJourRIB("989988897989676867896769769676769797696768");
        Assert.assertEquals("66574857675843219098", a.getRib());
    }

    @Test
    public void testMiseAJourRibInvalideChar () throws IncorrectNameException {
        Abonne a = new Abonne("theo", "66574857675843219098");
        a.miseAJourRIB("6HG;dzdGK]&");
        Assert.assertEquals("66574857675843219098", a.getRib());
    }

    /** Test bloquer() */
    @Test
    public void testBloquerValide () throws IncorrectNameException {
        Abonne a = new Abonne("theo", "83456767676767676767");
        Assert.assertEquals(false, a.getIsBloque());
        a.bloquer();
        Assert.assertEquals(true, a.getIsBloque());
    }

    /** Test debloquer() */
    @Test
    public void testDebloquerValide () throws IncorrectNameException {
        Abonne a = new Abonne("theo", "83456767676767676767");
        Assert.assertEquals(false, a.getIsBloque());
        a.bloquer();
        Assert.assertEquals(true, a.getIsBloque());
        a.debloquer();
        Assert.assertEquals(false, a.getIsBloque());
    }

    /**Test estBloque() */
    @Test
    public void testEstBloqueValide () throws IncorrectNameException {
        Abonne a = new Abonne("theo", "67654567654323232323");
        Assert.assertEquals(false, a.estBloque());

        a.bloquer();
        Assert.assertEquals(true, a.estBloque());
    }

    /**Test equals(Object) */
    @Test
    public void testEqualsNull() throws IncorrectNameException{
        Abonne a = new Abonne("theo");
        Assert.assertEquals(false, a.equals(null));
    }

    @Test
    public void testEqualsSame() throws IncorrectNameException{
        Abonne a = new Abonne("theo");
        Assert.assertEquals(true, a.equals(a));
    }

    @Test
    public void testEqualsDifferent() throws IncorrectNameException{
        Abonne a = new Abonne("theo");
        Abonne b = new Abonne("jacques", "76767676767676767676");
        Assert.assertEquals(false, a.equals(b));
    }

    /** Test hashCode() */
    @Test
    public void testHashCodeNotEquals () throws IncorrectNameException {
        Abonne a = new Abonne ("theo");
        Abonne b = new Abonne ("jacques");
        Assert.assertNotEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void testHashCodeNotEquals2 () throws IncorrectNameException {
        Abonne a = new Abonne ("theo");
        Abonne b = new Abonne ("theo", "87878765456767890987");
        Assert.assertNotEquals(a.hashCode(), b.hashCode());
    }









    }

