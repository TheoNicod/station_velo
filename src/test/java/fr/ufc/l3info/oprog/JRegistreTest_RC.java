package fr.ufc.l3info.oprog;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class JRegistreTest_RC {


    private JRegistre reg;
    private IVelo velo;
    private Abonne abonne;
    private Station st;
    private long date;


    @Before
    public void before() throws IncorrectNameException{
        reg = new JRegistre();
        velo = new Velo();
        abonne = new Abonne("fred");
        st = new Station("Gare", 47.2,6.02, 10);
        date = st.maintenant();

    }
    /**
     * Test Emprunter
     **/
    @Test
    public void TestEmprunter(){
        Assert.assertEquals(0,reg.emprunter(abonne,velo,date));
    }
    @Test
    public void TestEmprunterAbonneNull(){
        Assert.assertEquals(-1, reg.emprunter(null, velo, date));
    }
    @Test
    public void TestEmprunterVeloNull(){
        Assert.assertEquals(-1, reg.emprunter(abonne,null,date));
    }
    @Test
    public void TestEmprunterEnCoursVelo(){
        Assert.assertEquals(0, reg.emprunter(abonne,velo,date));
        Assert.assertEquals(-2, reg.emprunter(abonne,velo,date));
    }
    @Test
    public void TestEmprunterEnCoursAbonne(){
        IVelo velo1 = new Velo();
        Assert.assertEquals(0, reg.emprunter(abonne,velo,date));
        Assert.assertEquals(0, reg.emprunter(abonne,velo1,date));
    }
    @Test
    public void TestEmprunterEnCoursDiffAbonnees() throws IncorrectNameException{
        Abonne abonne1 = new Abonne("Jean");
        Assert.assertEquals(0,reg.emprunter(abonne,velo,date));
        Assert.assertEquals(-2,reg.emprunter(abonne1,velo,date));
    }
    @Test
    public void TestEmprunterEnCoursTerminer(){
        Assert.assertEquals(0, reg.emprunter(abonne,velo,date));
        Assert.assertEquals(0, reg.retourner(velo, date+500000));
        Assert.assertEquals(-2, reg.emprunter(abonne,velo,date+250000));
    }
    @Test
    public void TestEmprunterMultiple(){
        IVelo velo1 = new Velo();
        Assert.assertEquals(0, reg.emprunter(abonne,velo,date));
        Assert.assertEquals(0, reg.retourner(velo, date+10));
        Assert.assertEquals(0, reg.emprunter(abonne,velo1,date+5));
        Assert.assertEquals(0, reg.retourner(velo1, date+25));
    }
    /**
     * Test Retourner
     **/
    @Test
    public void TestRetourner(){
        Assert.assertEquals(0, reg.emprunter(abonne, velo, date));
        Assert.assertEquals(0, reg.retourner(velo, date+10));
    }
    @Test
    public void TestRetournerVeloNull(){
        Assert.assertEquals(-1, reg.retourner(null, date));
    }
    @Test
    public void TestRetournerNonEmprunter(){
        Assert.assertEquals(-2, reg.retourner(velo, date));
    }
    @Test
    public void TestRetournerDateAnterieur(){
        Assert.assertEquals(0, reg.emprunter(abonne,velo,date+15000000));
        Assert.assertEquals(-3, reg.retourner(velo, date));
    }
    @Test
    public void TestRetournerDateAnterieur2() throws IncorrectNameException{
        Abonne abonne1 = new Abonne("fred");
        IVelo velo2 = new Velo();
        Assert.assertEquals(0, reg.emprunter(abonne, velo2, date+10));
        Assert.assertEquals(0, reg.retourner(velo2, date+20));
        Assert.assertEquals(0, reg.emprunter(abonne1, velo, date+15));
        Assert.assertEquals(0, reg.retourner(velo, date+25));
        Assert.assertEquals(0, reg.emprunter(abonne,velo,date));
        Assert.assertEquals(-3, reg.retourner(velo, date-1000));
    }

    /**
     * Test attribué à l'erreur de codage : impossible de remplir la condition ligne 62
     * Si l'emprunt n'est pas en cours, le retour a déjà été rejeter ligne 57
     */
    @Test
    public void TestRetournerDateChevauchement(){
        Assert.assertEquals(0, reg.emprunter(abonne,velo,date));
        Assert.assertEquals(0, reg.retourner(velo, date+1000));
        Assert.assertEquals(-2, reg.emprunter(abonne,velo,date+500));
        Assert.assertEquals(-2, reg.retourner(velo, date+1500));
    }
    @Test
    public void TestRetournerMultpile(){
        Assert.assertEquals(0, reg.emprunter(abonne, velo, date));
        Assert.assertEquals(0, reg.retourner(velo, date+2));
        Assert.assertEquals(0, reg.emprunter(abonne, velo, date+10));
        Assert.assertEquals(0, reg.retourner(velo, date+15));
        Assert.assertEquals(0, reg.emprunter(abonne, velo, date+25));
        Assert.assertEquals(0, reg.retourner(velo, date+30));
    }
    @Test
    public void TestRetournerMultpileVelo(){
        IVelo velo2 = new Velo();
        Assert.assertEquals(0, reg.emprunter(abonne, velo, date));
        Assert.assertEquals(0, reg.emprunter(abonne, velo2, date+25));
        Assert.assertEquals(0, reg.retourner(velo, date+100));
        Assert.assertEquals(0, reg.retourner(velo2, date+100));
    }
    @Test
    public void TestRetournerMultpile3() throws IncorrectNameException{
        IVelo velo2 = new Velo();
        Abonne abonne1 = new Abonne("Fred");
        Assert.assertEquals(0, reg.emprunter(abonne, velo2, date+10));
        Assert.assertEquals(0, reg.retourner(velo2, date+20));
        Assert.assertEquals(0, reg.emprunter(abonne1, velo, date+15));
        Assert.assertEquals(0, reg.retourner(velo, date+20));
        Assert.assertEquals(0, reg.emprunter(abonne, velo2, date+25));
        Assert.assertEquals(0, reg.emprunter(abonne, velo, date+25));
        Assert.assertEquals(0, reg.retourner(velo, date+30));
        Assert.assertEquals(0, reg.retourner(velo2, date+30));
    }

    /**
     * Test Emprunt
     **/
    @Test
    public void TestNbEmprunt(){
        IVelo velo1 = new  Velo();
        Assert.assertEquals(0, reg.emprunter(abonne,velo,date));
        Assert.assertEquals(0, reg.emprunter(abonne,velo1,date));
        Assert.assertEquals(2, reg.nbEmpruntsEnCours(abonne));
    }
    @Test
    public void TestNbEmpruntNullAbonne() {
        Assert.assertEquals(0, reg.nbEmpruntsEnCours(null));
    }
    @Test
    public void TestNbEmpruntSansEmprunt(){
        Assert.assertEquals(0, reg.nbEmpruntsEnCours(abonne));
    }
    @Test
    public void TestEmpruntMultiple() {
        IVelo velo1 = new Velo();
        IVelo velo2 = new Velo();
        Assert.assertEquals(0, reg.emprunter(abonne,velo,date));
        Assert.assertEquals(0,reg.retourner(velo,date+10));
        Assert.assertEquals(0,reg.emprunter(abonne,velo1,date+5));
        Assert.assertEquals(0,reg.emprunter(abonne,velo2,date+10));
        Assert.assertEquals(2,reg.nbEmpruntsEnCours(abonne));
    }
    @Test
    public void TestEmpruntChevauchement() {
        Assert.assertEquals(0, reg.emprunter(abonne,velo,date));
        Assert.assertEquals(0,reg.retourner(velo,date+10));
        Assert.assertEquals(0,reg.emprunter(abonne,velo,date-10));
        Assert.assertEquals(-3,reg.retourner(velo,date+5));
        Assert.assertEquals(1,reg.nbEmpruntsEnCours(abonne));
    }
    @Test
    public void TestEmpruntChevauchement1() {
        Assert.assertEquals(0, reg.emprunter(abonne,velo,date));
        Assert.assertEquals(0,reg.retourner(velo,date+10));
        Assert.assertEquals(0,reg.emprunter(abonne,velo,date-10));
        Assert.assertEquals(-3,reg.retourner(velo,date+15));
        Assert.assertEquals(1,reg.nbEmpruntsEnCours(abonne));
    }
    /**
     * Test facturation
     */
    @Test
    public void TestFacturation(){
        long fin =  date +500000;
        Assert.assertEquals(0,reg.emprunter(abonne,velo,date));
        Assert.assertEquals(0, reg.retourner(velo,fin));
        Assert.assertEquals((Math.abs(fin-date)/(60*1000)*velo.tarif())/60, reg.facturation(abonne, date,fin), 0);
    }
    @Test
    public void TestFacturationMultipleNotEnded(){
        long fin =  date +500000;
        Assert.assertEquals(0,reg.emprunter(abonne,velo,date));
        Assert.assertEquals(0, reg.retourner(velo,fin));
        Assert.assertEquals(0,reg.emprunter(abonne,velo,fin+15000));
        Assert.assertEquals((Math.abs(fin-date)/(60*1000)*velo.tarif())/60, reg.facturation(abonne, date,fin), 0);
    }
    @Test
    public void TestFacturationMultiple(){
        long fin =  date +500000;
        long debut2 = fin+15;
        long fin2 = fin+30;
        Assert.assertEquals(0,reg.emprunter(abonne,velo,date));
        Assert.assertEquals(0, reg.retourner(velo,fin));
        Assert.assertEquals(0,reg.emprunter(abonne,velo,debut2));
        Assert.assertEquals(0, reg.retourner(velo,fin2));
        double cout1 = (Math.abs(fin-date)/(60*1000)*velo.tarif())/60;
        double cout2 = (Math.abs(fin2-debut2)/(60*1000)*velo.tarif())/60;
        Assert.assertEquals(cout1+cout2, reg.facturation(abonne, date,fin), 0);
    }
    @Test
    public void TestFacturationMultipleWithNotEnd(){
        long fin =  date +500000;
        long debut2 = fin+15;
        long fin2 = fin+30;
        Assert.assertEquals(0,reg.emprunter(abonne,velo,date));
        Assert.assertEquals(0, reg.retourner(velo,fin));
        Assert.assertEquals(0,reg.emprunter(abonne,velo,debut2));
        Assert.assertEquals(0, reg.retourner(velo,fin2));
        Assert.assertEquals(0,reg.emprunter(abonne,velo,debut2+50));
        double cout1 = (Math.abs(fin-date)/(60*1000)*velo.tarif())/60;
        double cout2 = (Math.abs(fin2-debut2)/(60*1000)*velo.tarif())/60;
        Assert.assertEquals(cout1+cout2, reg.facturation(abonne, date,fin), 0);
    }
    @Test
    public void TestFacturationAbonneNull() throws IncorrectNameException{
        Station st = new Station("gare", 47.5,6.02, 10);
        long debut = st.maintenant();
        long fin =  debut +500000;

        Assert.assertEquals(0, reg.facturation(null, debut,fin), 0);
    }



}

