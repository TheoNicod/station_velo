package fr.ufc.l3info.oprog.parser;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;


public class ASTCheckerVisitorTest_RC {

    /** Chemin vers les fichiers de test */
    final String path = "./target/classes/";

    /** Instance singleton du parser de stations */
    final StationParser parser = StationParser.getInstance();
    @Test
    public void TestNoErrors() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsOK.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertEquals(0,visitor.getErrors().size());
    }
    @Test
    public void TestEmptyFile() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsEmpty.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_LIST));
        assertEquals(1,visitor.getErrors().size());
    }
    @Test
    public void TestDoubleStation() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsNomDouble.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_STATION_NAME));
        assertEquals(1,visitor.getErrors().size());
    }
    @Test
    public void TestEmptyStationName() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsEmptyName.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME));
        assertEquals(1,visitor.getErrors().size());
    }
    @Test
    public void TestDuplicateLatitude() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsDuplicateLatitude.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_DECLARATION));
        assertEquals(1,visitor.getErrors().size());
    }
    @Test
    public void TestMissingLatitude() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsErreurLatitudeMissing.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.MISSING_DECLARATION));
        assertEquals(1,visitor.getErrors().size());
    }
    @Test
    public void TestWrongNumberValue() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsErreurValeur.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.WRONG_NUMBER_VALUE));
        assertEquals(1,visitor.getErrors().size());
    }
    @Test
    public void TestMultipleErreur() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsMultipleErreur.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME));
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_STATION_NAME));
        assertEquals(2,visitor.getErrors().size());
    }
    @Test
    public void TestMultipleErreur1() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsMultipleErreur1.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME));
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_STATION_NAME));
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.MISSING_DECLARATION));
        assertEquals(3,visitor.getErrors().size());
    }
    @Test
    public void TestMultipleErreur2() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsMultipleErreur2.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME));
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_STATION_NAME));
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.MISSING_DECLARATION));
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_DECLARATION));
        assertEquals(4,visitor.getErrors().size());
    }
    @Test
    public void TestMultipleErreur3() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsMultipleErreur3.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME));
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_STATION_NAME));
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.MISSING_DECLARATION));
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_DECLARATION));
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.WRONG_NUMBER_VALUE));
        assertEquals(5,visitor.getErrors().size());
    }
    @Test
    public void TestMultipleErreurSame() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsMultipleErreurSame.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_LIST));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_STATION_NAME));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.MISSING_DECLARATION));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_DECLARATION));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.WRONG_NUMBER_VALUE));
        assertEquals(2,visitor.getErrors().size());
    }
    @Test
    public void TestMultipleErreurSame1() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsMultipleErreurSame1.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_STATION_NAME));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_LIST));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.MISSING_DECLARATION));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_DECLARATION));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.WRONG_NUMBER_VALUE));
        assertEquals(1,visitor.getErrors().size());
    }
    @Test
    public void TestMultipleErreurSame2() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsMultipleErreurSame2.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.MISSING_DECLARATION));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_LIST));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_STATION_NAME));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_DECLARATION));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.WRONG_NUMBER_VALUE));
        assertEquals(2,visitor.getErrors().size());
    }
    @Test
    public void TestMultipleErreurSame3() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsMultipleErreurSame3.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_DECLARATION));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_LIST));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_STATION_NAME));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.MISSING_DECLARATION));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.WRONG_NUMBER_VALUE));
        assertEquals(1,visitor.getErrors().size());
    }
    @Test
    public void TestMultipleErreurSame4() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsMultipleErreurSame4.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.WRONG_NUMBER_VALUE));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_LIST));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_STATION_NAME));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.MISSING_DECLARATION));
        assertFalse(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_DECLARATION));
        assertEquals(2,visitor.getErrors().size());
    }






}
