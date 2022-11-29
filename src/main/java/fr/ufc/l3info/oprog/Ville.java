package fr.ufc.l3info.oprog;


import fr.ufc.l3info.oprog.parser.*;

import javax.swing.text.html.parser.Parser;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

public class Ville implements Iterable<Station>{

    Exploitant exploitant;
    Station stationPrincipal;
    Set<Station> stationSet;
    final StationParser parser = StationParser.getInstance();
    Set<Abonne> listeAbo;
    JRegistre reg;

    public Ville(){
        this.listeAbo = new HashSet<>();
        exploitant = new Exploitant();
        stationSet = new HashSet<Station>();
        this.reg = new JRegistre();
    }
    public void initialiser(File f) throws IOException {
        stationSet.clear();
        ASTNode n = null;
        try{
             n = parser.parse(f);
        }catch (StationParserException e){
            System.out.println(e.getMessage());
        }
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        ASTStationBuilder builder  =  new ASTStationBuilder();
        n.accept(builder);
        if(visitor.getErrors().size() == 0 ){
            for(Station s : builder.getStations()){
                stationSet.add(s);
                s.setRegistre(this.reg);
            }
        }
        Iterator<Station> iter = iterator();
        stationPrincipal = iter.next();
    }
    public void setStationPrincipale(String st){
        for(Station s: stationSet) {
            if (s.getNom().equals(st)) {
                stationPrincipal = s;
            }
        }
    }
    public Station getStation(String nom){
        for(Station s: stationSet){
            if(s.getNom().equals(nom)){
                return s;
            }
        }
        return  null;
    }
    public Station getStationPlusProche(double lat, double lon){
        Station temp = new Station("temp",lat,lon,0);
        double shortestDistance = stationPrincipal.distance(temp);
        Station closest = null;
        for(Station s: stationSet){
            double currentDistance = s.distance(temp);
            if(currentDistance < shortestDistance){
                shortestDistance = currentDistance;
                closest = s;
            }
        }
        return closest;
    }
    public Abonne creerAbonne(String nom, String RIB){
        Abonne abonne = null;
        try {
            abonne = new Abonne(nom, RIB);
        }catch (IncorrectNameException e){
            System.out.println(e.getMessage());
        }
        this.listeAbo.add(abonne);
        return abonne;
    }

    public Iterator<Station> iterator() {
        return new ClosestStationIterator(stationSet, stationPrincipal);
    }


    public Map<Abonne, Double> facturation(int mois, int annee){
        int moisE = mois+1;
        int anneeE = annee;
        if(mois == 12){
            moisE = 1;
            anneeE += 1;
        }
        Map<Abonne,Double> facture = new HashMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String myDate = annee+"/"+mois+"/01 00:00:00";
        String myDateE = anneeE+"/"+moisE+"/01 00:00:00";
        long dateB;
        long dateE;

        try{
            Date date = sdf.parse(myDate);
            dateB = date.getTime();

            date = sdf.parse(myDateE);
            dateE = date.getTime();


            Iterator it = iterator();
            for(Station s: stationSet){  // stationSet ?? sur les test ça marche mieux que this
                /* GET ABONNE */
                for(Abonne a : this.listeAbo){
                    double  facturation = 0;
                    facturation += this.reg.facturation(a,dateB,dateE);
                    if(!facture.containsKey(a)){
                        facture.put(a,facturation);
                    }
                }
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        return facture;
    }
}
