package fr.ufc.l3info.oprog;

public abstract class Option implements IVelo {
    private IVelo v;
    private double tarifOption;
    private String nom;


    protected Option (IVelo v, double tariff, String nom) {
        this.v = v;
        if(tariff >= 0)
            this.tarifOption = tariff;
        else
            this.tarifOption = 0;
        this.nom = nom;
    }
    public double kilometrage() {
        return v.kilometrage();
    }

    public double prochaineRevision() {
        return v.prochaineRevision();
    }

    public void parcourir(double km) {
        v.parcourir(km);
    }

    public double tarif() {
        return v.tarif() + this.tarifOption;
    }

    public int decrocher() {
        return v.decrocher();
    }

    public int arrimer() {
        return v.arrimer();
    }

    public void abimer() {
        v.abimer();
    }

    public boolean estAbime() {
        return v.estAbime();
    }

    public int reviser() {
        return v.reviser();
    }

    public int reparer() {
        return v.reparer();
    }

    public String toString () {
        String [] msg = v.toString().split("-");
        msg[0] = msg[0].trim();
        String str = msg[0]+", "+this.nom+" -"+msg[1];
        return str.trim();
    }
}








