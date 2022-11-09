package fr.ufc.l3info.oprog;

import java.util.*;

public class Station {
    private String nom;
    private double latitude, longitude;
    private int capacite;
    private IRegistre registre;
    private IVelo[] station;
    private List<Abonne> list_abonne_emprunt;

    public Station(String nom, double latitude, double longitude, int capacite) {
        if (capacite > 0) this.capacite = capacite;
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.station = new IVelo[this.capacite];
        this.list_abonne_emprunt = new LinkedList<Abonne>();
    }

    public void setRegistre(IRegistre registre) {
        if (registre == null) return;
        this.registre = registre;
    }

    public String getNom() {
        return this.nom;
    }

    public int capacite() {
        return this.capacite;
    }

    public int nbBornesLibres() {
        int c = 0;
        for (int i = 0; i < this.capacite; i++) {
            if (this.station[i] != null) c++;
        }
        return this.capacite - c++;
    }

    public IVelo veloALaBorne(int b) {
        if (b < 1 || b > this.capacite)
            return null;
        if (this.station[b - 1] == null)
            return null;
        return this.station[b - 1];
    }

    public IVelo emprunterVelo(Abonne a, int b) {
        if (a == null) return null;
        if (b < 1 || b > this.capacite) return null;
        if (this.station[b - 1] == null) return null;
        if (this.list_abonne_emprunt.contains(a)) return null;
        if (a.estBloque()) return null;
        if (this.registre == null) return null;

        if (this.station[b - 1].decrocher() != 0) return null;
        if (this.registre.emprunter(a, this.station[b - 1], this.maintenant()) != 0) return null;

        this.list_abonne_emprunt.add(a);
        IVelo v = this.station[b - 1];
        this.station[b - 1] = null;
        return v;
    }

    public int arrimerVelo(IVelo v, int b) {
        if (v == null || b < 1 || b > this.capacite) return -1;
        if (this.registre == null || this.station[b - 1] != null) return -2;
        if (v.arrimer() != 0) return -3;

        this.station[b - 1] = v;
        if (this.registre.retourner(v, this.maintenant()) != 0) return -4;
        return 0;
    }

    public void equilibrer(Set<IVelo> velos) {
        Set<IVelo> anciens = new HashSet<IVelo>();

        //enlèvement des vélos abîmés
        for(int n = 0; n < this.capacite; n++) {
            if(this.station[n] != null && this.station[n].estAbime()) {
                anciens.add(this.station[n]);
                this.station[n] = null;
            }
        }

        //ajout d'un velo aux bornes vides et changement des vélos à réviser
        int lim = this.capacite / 2 + this.capacite % 2;
        for(int i = 0; i < this.capacite; i++) {
            if(velos.size() != 0) {
                if (station[i] == null) {
                    this.station[i] = prendre_velo(velos);
                }

                if (this.station[i] != null && station[i].prochaineRevision() < 0) {
                    int d = lim - (this.capacite - this.nbBornesLibres()); // bornes vides à remplir jusqu'à avoir la moitié
                    if(velos.size() > d) { //les vélos à réviser ne sont pas la priorité
                        anciens.add(this.station[i]);
                        this.station[i] = prendre_velo(velos);
                    }
                }
            }else{
                break;
            }
        }

        //ajustement du nombre de vélo selon la limite
        int i = this.capacite - 1;
        while(this.capacite - this.nbBornesLibres() > lim && i > -1) {
            if(station[i] != null) {
                anciens.add(this.station[i]);
                this.station[i] = null;
            }
            i--;
        }

        for(IVelo v : anciens) {
            velos.add(v);
        }
    }



    //distances courtes
    public double distance(Station s) {
        if (s == null) return 0;
        double l = (double) (this.latitude + s.latitude) / 2;
        double x = (s.longitude - this.longitude) * Math.cos(l);
        double y = s.latitude - this.longitude;
        double z = Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
        return 1.852 * z;
    }


    public long maintenant() {
        return System.currentTimeMillis();
    }


    private IVelo prendre_velo (Set<IVelo> velos) {
        IVelo ve = null;
        for(IVelo v : velos) {
            if(v != null)
            ve = v;
        }
        velos.remove(ve);
        return ve;
    }





}