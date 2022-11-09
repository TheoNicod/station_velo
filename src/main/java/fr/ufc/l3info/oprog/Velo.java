package fr.ufc.l3info.oprog;

public class Velo implements IVelo {
    private char type_cadre;
    private double kilometrage, revision;
    private boolean accroche, abime;


    public Velo (char t) {
        this.type_cadre = t;
        this.kilometrage = 0;
        this.revision = 500;
        this.accroche = false;
        this.abime = false;
    }
    public Velo () {
        this.type_cadre = 'm';
        this.kilometrage = 0;
        this.revision = 500;
        this.accroche = false;
        this.abime = false;
    }

    public double kilometrage() {
        return this.kilometrage;
    }

    public double prochaineRevision() {
        return this.revision;
    }

    public void parcourir(double km) {
        if(km <= 0)
            return;
        if(!accroche) {
            this.kilometrage += km;
            this.revision -= km;
        }
    }

    public double tarif() {
        return 2.00;
    }

    public int decrocher() {
        if(!accroche)
            return -1;
        this.accroche = false;
        return 0;
    }

    public int arrimer() {
        if(accroche)
            return -1;
        accroche = true;
        return 0;
    }

    public void abimer() {
        this.abime = true;
    }

    public boolean estAbime() {
        return this.abime;
    }

    public int reviser() {
        if(accroche)
            return -1;
        this.revision = 500;
        this.abime = false;
        return 0;
    }

    public int reparer() {
        if(accroche)
            return -1;
        if(!abime)
            return -2;
        this.abime = false;
        return 0;
    }

    public String toString() {
        String str = "Vélo cadre ";
        if(this.type_cadre == 'F' || this.type_cadre == 'f') {
            str += "femme";
        }else {
            if (this.type_cadre == 'H' || this.type_cadre == 'h') {
                str += "homme";
            } else {
                str += "mixte";
            }
        }
        //arrondi
        double valeur = this.kilometrage;
        int scale = (int) Math.pow(10, 1);
        double res = (double) Math.round(valeur * scale) / scale;

        str += " - "+res;
        str += " km";
        if(this.prochaineRevision() <= 0) {
            str += " (révision nécessaire)";
        }
        return str;
    }

    
}
