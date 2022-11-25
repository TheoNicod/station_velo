package fr.ufc.l3info.oprog;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

public class ClosestStationIteratorTest {
    Set<Station> stationSet;
    Ville ville;

    @Before
    public void before() {
       this.stationSet = new HashSet<>();
    }

    @Test
    public void TestClosestStationIteratorStationNull(){
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,null);
        Assert.assertEquals(null,clsStItr);

    }


    /**
     * Test hasNext
     */
    @Test
    public void TestHasNextEmtySet(){
        Station stationPrincipale = new Station("Gare Viotte", 25.0,25.0, 5);
        ClosestStationIterator clsStItr = new ClosestStationIterator(null,stationPrincipale);
        Assert.assertFalse(clsStItr.hasNext());
    }
    @Test
    public void TestHasNextNullStart(){
        Station stationPrincipale = new Station("Gare Viotte", 25.0,25.0, 5);
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,null);
        Assert.assertFalse(clsStItr.hasNext());
    }
    @Test
    public void TestHasNext(){
        Station stationPrincipale = new Station("Gare Viotte", 25.0,25.0, 5);
        stationSet.add(stationPrincipale);
        stationSet.add(new Station("Chamars", 25.4,25.0,5));
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,stationPrincipale);
        Assert.assertFalse(clsStItr.hasNext());
    }
    @Test
    public void TestHasNextAfterNext(){
        Station stationPrincipale = new Station("Gare Viotte", 25.0,25.0, 5);
        stationSet.add(stationPrincipale);
        stationSet.add(new Station("Chamars", 25.4,25.0,5));
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,stationPrincipale);
        clsStItr.next();
        Assert.assertTrue(clsStItr.hasNext());
    }
    @Test
    public void TestHasNextAfterTooMuchNext(){
        Station stationPrincipale = new Station("Gare Viotte", 25.0,25.0, 5);
        stationSet.add(stationPrincipale);
        stationSet.add(new Station("Chamars", 25.4,25.0,5));
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,stationPrincipale);
        clsStItr.next();
        clsStItr.next();
        Assert.assertFalse(clsStItr.hasNext());
    }
    @Test
    public void TestHasNextAfterNotFromFirst(){
        Station station1 = new Station("Gare Viotte", 25.0,25.0, 5);
        Station station2 = new Station("Chamars", 20,25.0, 5);
        Station station3 = new Station("station", 30,55.0, 5);
        Station station4 = new Station("station2", 40,55.0, 5);
        stationSet.add(station1);
        stationSet.add(station2);
        stationSet.add(station3);
        stationSet.add(station4);
        stationSet.add(new Station("Chamars", 25.4,25.0,5));
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,station2);
        clsStItr.next();
        clsStItr.next();
        Assert.assertTrue(clsStItr.hasNext());
    }
    @Test
    public void TestHasNextAfterNotFromFirst2(){
        Station station1 = new Station("Gare Viotte", 25.0,25.0, 5);
        Station station2 = new Station("Chamars", 25,25.0, 5);
        Station station3 = new Station("station", 30,55.0, 5);
        Station station4 = new Station("station2", 40,55.0, 5);
        stationSet.add(station1);
        stationSet.add(station2);
        stationSet.add(station3);
        stationSet.add(station4);
        stationSet.add(new Station("Chamars", 25.4,25.0,5));
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,station4);
        Assert.assertFalse(clsStItr.hasNext());
    }


    /**
     * Test next
     */
    @Test
    public void TestNextSameEmptySet(){
        Station station1 = new Station("Gare Viotte", 0,0, 5);
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,station1);
        Assert.assertEquals(null, clsStItr.next());
    }
    @Test
    public void TestNext1(){
        Station station1 = new Station("Gare Viotte", 0,0, 5);
        Station station2 = new Station("Chamars", 100,100, 5);
        Station station3 = new Station("station", 200,200, 5);
        Station station4 = new Station("station2", 300,300, 5);
        stationSet.add(station1);
        stationSet.add(station2);
        stationSet.add(station3);
        stationSet.add(station4);
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,station1);
        clsStItr.next();
        Assert.assertEquals(station2, clsStItr.next());
    }
    @Test
    public void TestNextNoNext(){
        Station station1 = new Station("Gare Viotte", 0,0, 5);
        stationSet.add(station1);
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,station1);
        clsStItr.next();
        clsStItr.next();
        Assert.assertEquals(null, clsStItr.next());
    }
    @Test
    public void TestNext(){
        Station station1 = new Station("Gare Viotte", 0,0, 5);
        Station station2 = new Station("Chamars", 100,100, 5);
        Station station3 = new Station("station", 200,200, 5);
        stationSet.add(station1);
        stationSet.add(station3);
        stationSet.add(station2);
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,station1);
        Assert.assertEquals(station1, clsStItr.next());
        Assert.assertEquals(station2, clsStItr.next());
        Assert.assertEquals(station3, clsStItr.next());
    }
    @Test
    public void TestNextSameDistantce(){
        Station station1 = new Station("Gare Viotte", 0,0, 5);
        Station station2 = new Station("Chamars", 100,100, 5);
        Station station3 = new Station("station", 100,100, 5);
        stationSet.add(station1);
        stationSet.add(station3);
        stationSet.add(station2);
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,station1);
        Assert.assertEquals(station1, clsStItr.next());
        Assert.assertEquals(station3, clsStItr.next());
        Assert.assertEquals(station2, clsStItr.next());
    }
}
