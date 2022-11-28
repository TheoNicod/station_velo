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
        Station stationPrincipale = new Station("Gare Viotte", 25.0,25.0, 5);
        Station station1 = new Station("Chamars", 25.4,25.0,5);
        stationSet.add(stationPrincipale);
        stationSet.add(station1);
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,null);
        Assert.assertEquals(stationPrincipale.getNom(),clsStItr.next().getNom());
        Assert.assertEquals(station1.getNom(),clsStItr.next().getNom());
        stationSet.clear();
        clsStItr = null;
    }


    /**
     * Test hasNext
     */
    @Test
    public void TestHasNextEmtySet(){
        Station stationPrincipale = new Station("Gare Viotte", 25.0,25.0, 5);
        ClosestStationIterator clsStItr = new ClosestStationIterator(null,stationPrincipale);
        Assert.assertFalse(clsStItr.hasNext());
        stationSet.clear();

    }
    @Test
    public void TestHasNextNullStart(){
        Station stationPrincipale = new Station("Gare Viotte", 25.0,25.0, 5);
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,null);
        Assert.assertFalse(clsStItr.hasNext());
        stationSet.clear();

    }
    @Test
    public void TestHasNext(){
        Station stationPrincipale = new Station("Gare Viotte", 25.0,25.0, 5);
        stationSet.add(stationPrincipale);
        stationSet.add(new Station("Chamars", 25.4,25.0,5));
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,stationPrincipale);
        Assert.assertTrue(clsStItr.hasNext());
        stationSet.clear();

    }
    @Test
    public void TestHasNextAfterNext(){
        Station stationPrincipale = new Station("Gare Viotte", 25.0,25.0, 5);
        stationSet.add(stationPrincipale);
        stationSet.add(new Station("Chamars", 25.4,25.0,5));
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,stationPrincipale);
        clsStItr.next();
        Assert.assertTrue(clsStItr.hasNext());
        stationSet.clear();

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
        stationSet.clear();

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
        stationSet.clear();

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
        Assert.assertEquals(station4,clsStItr.next()); // Car c'est la première
        Assert.assertEquals(station3,clsStItr.next());
        stationSet.clear();


    }


    /**
     * Test next
     */
    @Test
    public void TestNextSameEmptySet(){
        Station station1 = new Station("Gare Viotte", 0,0, 5);
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,station1);
        Assert.assertEquals(null, clsStItr.next());
        stationSet.clear();

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
        Assert.assertEquals(station3, clsStItr.next());
        stationSet.clear();

    }
    @Test
    public void TestNextNoNext(){
        Station station1 = new Station("Gare Viotte", 0,0, 5);
        stationSet.add(station1);
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,station1);
        clsStItr.next();
        clsStItr.next();
        Assert.assertEquals(null, clsStItr.next());
        stationSet.clear();

    }
    @Test
    public void TestNextEqualDistance(){
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
        stationSet.clear();

    }
    @Test
    public void TestNextDistantce0(){
        Station station1 = new Station("Gare Viotte", 0,0, 5);
        Station station2 = new Station("Chamars", 100,100, 5);
        Station station3 = new Station("station", 100,100, 5);
        stationSet.add(station1);
        stationSet.add(station3);
        stationSet.add(station2);
        ClosestStationIterator clsStItr = new ClosestStationIterator(stationSet,station1);
        Assert.assertEquals(station1, clsStItr.next());
        Assert.assertEquals(station3, clsStItr.next());
        Assert.assertEquals(station2, clsStItr.next());   // impossible de savoir pour le moment
        stationSet.clear();

    }
}
