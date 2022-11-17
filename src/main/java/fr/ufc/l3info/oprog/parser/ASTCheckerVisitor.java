package fr.ufc.l3info.oprog.parser;

import fr.ufc.l3info.oprog.Station;

import java.util.*;


/**
 * Visiteur réalisant des vérifications sur l'AST du fichier de stations.
 */
public class ASTCheckerVisitor implements ASTNodeVisitor {


    Map<String,ERROR_KIND> errors;


    public ASTCheckerVisitor() {
        errors = new TreeMap<String, ERROR_KIND>();
    }

    public Map<String, ERROR_KIND> getErrors() {
        return errors;
    }

    @Override
    public Object visit(ASTNode n) {
        return null;
    }

    @Override
    public Object visit(ASTListeStations n) {
        if(n.getNumChildren() == 0){
            errors.put(n.getLCPrefix()+" Liste de station vide", ERROR_KIND.EMPTY_LIST);
            System.out.println(n.getLCPrefix()+" Liste de station vide");

        }else{
            List<String> listStation = new ArrayList<String>();
            for(ASTNode child : n){
                child.accept(this);
                if(child.children.get(0).value.equals("\"\"")){
                    errors.put("message 1", ERROR_KIND.EMPTY_STATION_NAME);
                }else if(listStation.contains(child.children.get(0).value)){
                    errors.put("message 2", ERROR_KIND.DUPLICATE_STATION_NAME);
                    return null;
                }else{
                    listStation.add(child.children.get(0).value);
                }
            }
        }
        return null;
    }

    @Override
    public Object visit(ASTStation n) {
        Map<String,Double> declarationList = new HashMap<String, Double>();
        for(ASTNode child: n){
            if(child.value == "[Declaration]"){
                Object [] declaration = (Object[]) child.accept(this);
                if(!declarationList.containsKey(declaration[0])){
                    if(declaration[0].equals("capacite") && (Double) declaration[1]< 0){
                        errors.put("message 3", ERROR_KIND.WRONG_NUMBER_VALUE);
                    }
                    declarationList.put((String)declaration[0], (Double) declaration[1]);
                }else{
                    errors.put("messsage 4", ERROR_KIND.DUPLICATE_DECLARATION);
                }
            }
            if(!declarationList.containsKey("latitude") || !declarationList.containsKey("longitude") || !declarationList.containsKey("capacite") ){
                errors.put("message 4", ERROR_KIND.MISSING_DECLARATION);
            }
        }
        return null;
    }

    @Override
    public Object visit(ASTDeclaration n) {
        System.out.println("visitASTDeclaration");
        String key = (String) n.getChild(0).accept(this);
        Double value = (Double) n.getChild(1).accept(this);
        return new Object[] { key, value };
    }
    @Override
    public Object visit(ASTChaine n) {
        System.out.println("visitASTChiane");
        return n.toString().substring(1, n.toString().length()-1);
    }
    @Override
    public Object visit(ASTIdentificateur n) {
        System.out.println("visitASTID");
        return n.toString();
    }

    @Override
    public Object visit(ASTNombre n){
        System.out.println("visitASTNombre");
        return n.getNumberValue();
    }
}

enum ERROR_KIND {
    EMPTY_LIST,
    EMPTY_STATION_NAME,
    DUPLICATE_STATION_NAME,
    MISSING_DECLARATION,
    DUPLICATE_DECLARATION,
    WRONG_NUMBER_VALUE
}