package fr.ufc.l3info.oprog;


import org.junit.Assert;
import org.junit.Test;

/**
 * Test unitaire pour les abonnés.
 */
public class JRegistreTest {


    /* public int emprunter(Abonne a, IVelo v, long d) */

    @Test
    public void testEmprunterOk() throws IncorrectNameException {
        JRegistre r = new JRegistre();
        IVelo v = new Velo('t');
        Abonne a = new Abonne("theo", "888888888888888888888888");
        Assert.assertEquals(0, r.emprunter(a, v, System.currentTimeMillis()));
    }

    @Test
    public void testEmprunterMauvaisParametres() throws IncorrectNameException {
        JRegistre r = new JRegistre();
        IVelo v = new Velo('t');
        Assert.assertEquals(-1, r.emprunter(null, v, System.currentTimeMillis()));

        Abonne a = new Abonne("theo", "888888888888888888888888");
        Assert.assertEquals(-1, r.emprunter(a, null, System.currentTimeMillis()));
    }

    @Test
    public void testEmprunterEmpruntEnCours() throws IncorrectNameException {
        JRegistre r = new JRegistre();
        IVelo v = new Velo('t');
        Abonne a = new Abonne("theo", "888888888888888888888888");
        r.emprunter(a, v , 20082022);

        Abonne b = new Abonne("jean", "99999999999999999999999");
        Assert.assertEquals(-2, r.emprunter(b, v, System.currentTimeMillis()));
    }

    @Test
    public void testEmprunterEmpruntTermine() throws IncorrectNameException {
        JRegistre r = new JRegistre();
        IVelo v = new Velo('t');
        Abonne a = new Abonne("theo", "888888888888888888888888");
        r.emprunter(a, v , System.currentTimeMillis());
        r.retourner(v, System.currentTimeMillis());
        Assert.assertEquals(-2, r.emprunter(a, v, System.currentTimeMillis()));

        Abonne b = new Abonne("jean", "99999999999999999999999");
        Assert.assertEquals(-2, r.emprunter(b, v, System.currentTimeMillis()));
    }


    /* public int retourner(IVelo v, long d) */

    @Test
    public void testRetournerOk() throws IncorrectNameException {
        JRegistre r = new JRegistre();
        IVelo v = new Velo('t');
        Abonne a = new Abonne("Theo", "999999999999999999999999999999999999999999999");
        r.emprunter(a, v , System.currentTimeMillis());
        Assert.assertEquals(0, r.retourner(v, System.currentTimeMillis()));
    }

    @Test
    public void testRetournerMauvaisParametres() {
        JRegistre r = new JRegistre();
        Assert.assertEquals(-1, r.retourner(null, System.currentTimeMillis()));
    }

    @Test
    public void testRetournerVeloNonEmprunte() {
        JRegistre r = new JRegistre();
        IVelo v = new Velo('t');
        Assert.assertEquals(-2, r.retourner(v, System.currentTimeMillis()));
    }

    @Test
    public void testRetournerVeloDateIncoherente() throws IncorrectNameException {
        JRegistre r = new JRegistre();
        IVelo v = new Velo('t');
        Abonne a = new Abonne("Theo", "999999999999999999999999999999999999999999999");
        r.emprunter(a, v , System.currentTimeMillis());

        Assert.assertEquals(-3, r.retourner(v, 10));
    }

    @Test
    public void testRetournerVeloSansEmprunt() throws IncorrectNameException {
        JRegistre r = new JRegistre();
        IVelo v = new Velo('t');
        Abonne a = new Abonne("Theo", "999999999999999999999999999999999999999999999");

        Assert.assertEquals(-2, r.retourner(v, 10));
    }

    /* public int nbEmpruntsEnCours(Abonne a) */

    @Test
    public void testNbEmpruntEnCoursMauvaisParametre() {
        JRegistre r = new JRegistre();
        Assert.assertEquals(0, r.nbEmpruntsEnCours(null));
    }

    @Test
    public void testNbEmpruntEnCours() throws IncorrectNameException {
        JRegistre r = new JRegistre();
        Abonne a = new Abonne("theo", "888888888888888888888");
        IVelo v1 = new Velo('t');
        IVelo v2 = new Velo('t');

        r.emprunter(a, v1, System.currentTimeMillis());
        r.emprunter(a, v2, System.currentTimeMillis());

        Assert.assertEquals(2, r.nbEmpruntsEnCours(a));

    }

    @Test
    public void testNbEmpruntEnCoursDefaut() throws IncorrectNameException {
        JRegistre r = new JRegistre();
        Abonne a = new Abonne("theo", "888888888888888888888");

        Assert.assertEquals(0, r.nbEmpruntsEnCours(a));
    }

    @Test
    public void testNbEmpruntEnCoursTermine() throws IncorrectNameException {
        JRegistre r = new JRegistre();
        Abonne a = new Abonne("theo", "888888888888888888888");
        IVelo v1 = new Velo('t');
        IVelo v2 = new Velo('t');

        r.emprunter(a, v1, System.currentTimeMillis());
        r.emprunter(a, v2, System.currentTimeMillis());
        r.retourner(v1, System.currentTimeMillis());

        Assert.assertEquals(1, r.nbEmpruntsEnCours(a));
    }

    /* facturation */
    @Test
    public void testFacturationOk() throws IncorrectNameException {
        JRegistre r = new JRegistre();
        Abonne a = new Abonne("theo", "888888888888888888888");
        IVelo v1 = new Velo('t');

        long debut = System.currentTimeMillis();
        r.emprunter(a, v1, debut);

        long fin = System.currentTimeMillis() + 60*60000 + 35000;
        r.retourner(v1, fin);

        long t = (fin - debut) / 1000 / 60;
        double tarif = (double)t / 60 * v1.tarif();

        Assert.assertEquals(tarif, r.facturation(a, debut, fin), 0.0);
    }

    @Test
    public void testFacturationMauvaisesDates() throws IncorrectNameException {
        JRegistre r = new JRegistre();
        Abonne a = new Abonne("theo", "888888888888888888888");
        IVelo v1 = new Velo('t');

        long debut = System.currentTimeMillis();
        r.emprunter(a, v1, debut);

        long fin = System.currentTimeMillis() + 60*60000 + 35000;
        r.retourner(v1, fin);

        long t = (fin - debut) / 1000 / 60;

        Assert.assertEquals(0, r.facturation(a, fin, debut), 0.0);
    }

    @Test
    public void testFacturationPasDEmprunt() throws IncorrectNameException {
        JRegistre r = new JRegistre();
        Abonne a = new Abonne("theo", "888888888888888888888");

        Assert.assertEquals(0, r.facturation(a, 0, 0), 0.0);
    }




}