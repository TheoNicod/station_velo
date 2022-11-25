package fr.ufc.l3info.oprog;


import fr.ufc.l3info.oprog.parser.*;

import javax.swing.text.html.parser.Parser;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
public class Ville implements Iterable<Station>{

    Exploitant exploitant;
    Station stationPrincipal;
    Set<Station> stationSet;
    final StationParser parser = StationParser.getInstance();

    Ville(){
        exploitant = new Exploitant();
        stationSet = new HashSet<Station>();
    }
    void initialiser(File f) throws IOException {
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
            }
        }
        Iterator<Station> iter = iterator();
        stationPrincipal = iter.next();
    }
    void setStationPrincipale(String st){
        for(Station s: stationSet) {
            if (s.getNom() == st) {
                stationPrincipal = s;
            }
        }
    }
    public Station getStation(String nom){
        for(Station s: stationSet){
            if(s.getNom() == nom){
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
        return abonne;
    }

    public Iterator<Station> iterator(){
        return new ClosestStationIterator(stationSet,stationPrincipal);
    }


    Map<Abonne, Double> facturation(int mois, int annee){
        for(Station s : stationSet){
        }
        return  null;
    }
}
