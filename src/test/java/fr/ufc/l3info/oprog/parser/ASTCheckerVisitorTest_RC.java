package fr.ufc.l3info.oprog.parser;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ASTCheckerVisitorTest_RC {

    /** Chemin vers les fichiers de test */
    final String path = "./target/classes/";

    /** Instance singleton du parser de stations */
    final StationParser parser = StationParser.getInstance();

    @Test
    public void TestEmptyFile() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsEmpty.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_LIST));
        //assertTrue(1==2);
    }

    //ajout d'un test ok


    @Test (expected = StationParserException.class)
    public void TestEmptyList() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsEmptyList.txt"));
    }
    @Test
    public void TestDoubleStation() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsNomDouble.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_STATION_NAME));
    }
    @Test
    public void TestEmptyStationName() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsEmptyName.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.EMPTY_STATION_NAME));
    }
    @Test
    public void TestDuplicateLatitude() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsDuplicateLatitude.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.DUPLICATE_DECLARATION));
    }
    @Test
    public void TestMissingLatitude() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsErreurLatitudeMissing.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.MISSING_DECLARATION));
    }
    @Test
    public void TestWrongNumberValue() throws StationParserException, IOException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsErreurValeur.txt"));
        ASTCheckerVisitor visitor = new ASTCheckerVisitor();
        n.accept(visitor);
        assertTrue(visitor.getErrors().containsValue(ERROR_KIND.WRONG_NUMBER_VALUE));
    }


    ///tester multiple erreurs
}
