package fr.ufc.l3info.oprog;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ClosestStationIterator implements Iterator<Station>{
    Set<Station> setStation;
    Station depart;
    Station courrante;

    public ClosestStationIterator(Set<Station> set, Station s) {
        setStation = new HashSet<>();
        if(s == null) {
            for(Station st : set) {
                depart = st;
                break;
            }
        }
        courrante = depart = s;
    }
    @Override
    public boolean hasNext() {
        if(setStation == null) return false;
        return false;
    }

    @Override
    public Station next() {
        return null;
    }

    @Override
    public void remove() {
    }


}
