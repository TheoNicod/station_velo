package fr.ufc.l3info.oprog.parser;

import fr.ufc.l3info.oprog.Station;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *  Quelques tests pour le package parser.
 */
public class ParserTest_RC {

    /** Chemin vers les fichiers de test */
    final String path = "./target/classes/";

    /** Instance singleton du parser de stations */
    final StationParser parser = StationParser.getInstance();

    @Test
    public void testTokenizer() throws StationParserException, IOException {
        List<Token> tokens = StationFileTokenizer.tokenize(new File(path + "data/stationsOK.txt"));
        assertEquals(30, tokens.size());
        String[] expected = { "station", "\"21 - Avenue Fontaine Argent, Boulevard Diderot\"", "{",
                "latitude", ":", "47.2477615", ";", "longitude", ":", "5.9835995", ";",
                "capacite", ":", "12", "}", "station", "\"Avenue du Maréchal Foch\"", "{",
                "capacite", ":", "10", ";", "longitude", ":", "6.022671", ";", "latitude", ":", "47.246511", "}" };
        for (int i=0; i < expected.length; i++) {
            assertEquals(expected[i], tokens.get(i).getValeur());
        }
        assertEquals(1, tokens.get(0).getLigne());
        assertEquals(1, tokens.get(0).getColonne());
        assertEquals(10, tokens.get(tokens.size()-2).getLigne());
        assertEquals(16, tokens.get(tokens.size()-2).getColonne());
    }


    @Test
    public void testParserOK() throws StationParserException, IOException {
        ASTNode n = parser.parse(new File(path + "data/stationsOK.txt"));
        assertTrue(n instanceof ASTListeStations);
        assertEquals(2, n.getNumChildren());
        for (ASTNode n1 : n) {
            assertTrue(n1 instanceof ASTStation);
            assertEquals(4, n1.getNumChildren());
            // premier petit fils -> ASTChaine
            assertTrue(n1.getChild(0) instanceof ASTChaine);
            // 2e, 3e et 4e fils -> ASTDeclaration avec 2 enfants
            for (int i = 1; i < 4; i++) {
                assertTrue(n1.getChild(i) instanceof ASTDeclaration);
                assertEquals(2, n1.getChild(i).getNumChildren());
                assertTrue(n1.getChild(i).getChild(0) instanceof ASTIdentificateur);
                assertTrue(n1.getChild(i).getChild(1) instanceof ASTNombre);
            }
        }
    }


    @Test
    public void testStationBuilder() throws IOException, StationParserException {
        ASTNode n = parser.parse(new File("./target/classes/data/stationsOK.txt"));
        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
        assertEquals(2, builder.getStations().size());
        int nb = 0;
        for (Station s : builder.getStations()) {
            if (s.getNom().equals("21 - Avenue Fontaine Argent, Boulevard Diderot")) {
                assertEquals(12, s.capacite());
                nb = nb | 1;
            }
            else if (s.getNom().equals("Avenue du Maréchal Foch")) {
                assertEquals(10, s.capacite());
                nb = nb | 2;
            }
        }
        assertEquals(3, nb);
    }
    @Test(expected = StationParserException.class )
    public void testErreurStationEmptyList() throws IOException, StationParserException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsEmptyList.txt"));
        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
        assertEquals(2, builder.getStations().size());
    }
    @Test
    public void testErreurStationEmpty() throws IOException, StationParserException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsEmpty.txt"));
        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
    }
    @Test
    public void testErreurStationEmptyName() throws IOException, StationParserException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsEmptyName.txt"));
        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
    }

    @Test(expected = StationParserException.class )
    public void testErreurStationNameWithoutQuotesEnd() throws IOException, StationParserException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsErrorNameQuotes.txt"));
        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
        assertEquals(2, builder.getStations().size());
    }
    @Test(expected = StationParserException.class )
    public void testErreurStationNameWithoutQuotesBegin() throws IOException, StationParserException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsErrorNameQuotes2.txt"));
        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
        assertEquals(2, builder.getStations().size());
    }
    @Test(expected = StationParserException.class )
    public void testErreurStationPtVirguleEnd() throws IOException, StationParserException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsErreurPtVirguleEnd.txt"));
        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
        assertEquals(2, builder.getStations().size());
    }
    @Test(expected = StationParserException.class )
    public void testErreurStationAccoladeBegin() throws IOException, StationParserException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsErreurMissingAcolladeBgin.txt"));
        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
        assertEquals(2, builder.getStations().size());
    }
    @Test(expected = StationParserException.class )
    public void testErreurStationAccoladeEnd() throws IOException, StationParserException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsErreurMissingAcolladeEnd.txt"));
        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
        assertEquals(2, builder.getStations().size());
    }

    @Test(expected = StationParserException.class )
    public void testErreurLatitude1() throws IOException, StationParserException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsErreurLatitude.txt"));
        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
        assertEquals(2, builder.getStations().size());
    }
    @Test(expected = StationParserException.class )
    public void testErreurLatitudePtVirgule() throws IOException, StationParserException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsErreurLatitudePtVirgule.txt"));
        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
        assertEquals(2, builder.getStations().size());
    }
    @Test(expected = StationParserException.class )
    public void testErreurLatitudeValeur() throws IOException, StationParserException{
        ASTNode n = parser.parse(new File("./target/classes/data/stationsErreurLatitudeValeur.txt"));
        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
        assertEquals(2, builder.getStations().size());
    }
    @Test(expected = StationParserException.class )
    public void testErreurLongitudeValue() throws IOException, StationParserException {
        ASTNode n = parser.parse(new File("./target/classes/data/stationsErreurLongitudeValue.txt"));
        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
        assertEquals(2, builder.getStations().size());
    }
    @Test(expected = StationParserException.class )
    public void testErreurCapaciteValue() throws IOException, StationParserException {
        ASTNode n = parser.parse(new File("./target/classes/data/stationsErreurCapaciteValue.txt"));
        ASTStationBuilder builder = new ASTStationBuilder();
        n.accept(builder);
        assertEquals(2, builder.getStations().size());
    }




}
