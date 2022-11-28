package fr.ufc.l3info.oprog;




import java.util.*;

public class ClosestStationIterator implements Iterator<Station>{
    private Set<Station> setStation, setStationTemp;
    private Station depart, courrante;
    private boolean first_next;
    private Deque<Station> buffer;



    public ClosestStationIterator(Set<Station> set, Station s) {
        if(set != null) this.setStation = new HashSet<>(set);
        if(set != null) this.setStationTemp = new HashSet<>(set);
        this.first_next = true;
        this.buffer = new ArrayDeque<>();
        if(s == null && set != null) {
            for(Station st : set) {
                this.courrante = this.depart = st;
                break;
            }
        }else
            this.courrante = this.depart = s;
    }
    @Override
    public boolean hasNext() {
        Station s = this.getNext();
        if(s == null) return false;
        else this.buffer.add(s);
        return true;
    }

    @Override
    public Station next() {
        /*
        if(this.first_next) {
            this.first_next = false;
            this.setStationTemp.remove(this.depart);
            return this.depart;
        }

         */
        if(this.buffer.size() != 0) {
            return this.buffer.pollFirst();
        }
        return this.getNext();
    }

    @Override
    public void remove() {
    }

    private Station getNext() {
        if(this.setStation == null) return null;
        Station closest_station = null;
        double dist_curr = 0.00;
        double closest_dist = 100000000000.00;
        for(Station s : this.setStationTemp) {
            dist_curr = this.courrante.distance(s);
            if(dist_curr < closest_dist) {
                closest_dist = dist_curr;
                closest_station = s;
            }
        }
        if(closest_station != null)  this.setStationTemp.remove(closest_station);
        this.courrante = closest_station;
        return closest_station;
    }


}
