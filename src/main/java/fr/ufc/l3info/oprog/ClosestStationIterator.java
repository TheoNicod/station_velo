package fr.ufc.l3info.oprog;


import java.util.*;


public class ClosestStationIterator implements Iterator<Station>{
    Map<Station,Boolean> setStation;
    Station depart;
    Station courrante;

    public ClosestStationIterator(Set<Station> set, Station s) {
        setStation = new HashMap<>();
        courrante = s;
        if(set != null){
            for(Station st : set) {
                setStation.put(st,false);
            }
        }else{
            setStation = null;
        }
        if(s == null) {
            for (Station st: set){
                courrante = st;
                break;
            }
        }
        depart = null;
    }
    @Override
    public boolean hasNext() {
        if(setStation == null) return false;
        if(next() != null) return true;
        return false;
    }

    @Override
    public Station next() {
        if(setStation == null) return null;
        double distanceMin = 100000000;
        Station plusProcheStation = null;
        Set<Map.Entry<Station,Boolean>> paires = setStation.entrySet();
        Iterator <Map.Entry<Station,Boolean>> iter = paires.iterator();
        while(iter.hasNext()){
            Map.Entry<Station,Boolean> paire = iter.next();
            Station s = paire.getKey();
            double currentDistance = s.distance(courrante);
            if(s == courrante && depart == null){
                depart = s;
                return s;
            }

            if(currentDistance < distanceMin && paire.getValue() != true && s!=depart) {
                distanceMin = currentDistance;
                plusProcheStation = s;
                setStation.put(s, true);
            }
        }
        depart = plusProcheStation;
        return plusProcheStation;
    }

    @Override
    public void remove() {}


}
