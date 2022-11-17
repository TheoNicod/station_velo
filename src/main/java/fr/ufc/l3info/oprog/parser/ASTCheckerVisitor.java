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
        return n;
    }

    @Override
    public Object visit(ASTListeStations n) {
        if(n.getNumChildren() == 0){
            errors.put(n.getLCPrefix()+" Liste de station vide", ERROR_KIND.EMPTY_LIST);
            System.out.println(n.getLCPrefix()+" Liste de station vide");
        }else{
            List<String> listStation = new ArrayList<String>();
            int i = 0;
            for(ASTNode child : n){
                child.accept(this);
                String nomStation  = child.children.get(0).value.replaceAll("\\s", "");
                if(nomStation.equals("\"\"")){
                    errors.put(n.getLCPrefix()+" Nom de station vide "+i, ERROR_KIND.EMPTY_STATION_NAME);
                    System.out.println(n.getLCPrefix()+" Nom de station vide");
                }else if(listStation.contains(child.children.get(0).value)){
                    errors.put(n.getLCPrefix()+" Nom de station en double "+nomStation, ERROR_KIND.DUPLICATE_STATION_NAME);
                    System.out.println(n.getLCPrefix()+" Nom de station en double");
                    return null;
                }else{
                    listStation.add(child.children.get(0).value);
                }
                i++;
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
                        errors.put(n.getLCPrefix()+" Mauvaise valeur nombre", ERROR_KIND.WRONG_NUMBER_VALUE);
                        System.out.println(n.getLCPrefix()+" Mauvaise valeur nombre");
                    }
                    declarationList.put((String)declaration[0], (Double) declaration[1]);
                }else{
                    errors.put(n.getLCPrefix()+" Declaration duplique", ERROR_KIND.DUPLICATE_DECLARATION);
                    System.out.println(n.getLCPrefix()+" Declaration duplique");
                }
            }
        }
        if(!declarationList.containsKey("latitude") ){
            errors.put(n.getLCPrefix()+" Declaration latitude manquante", ERROR_KIND.MISSING_DECLARATION);
            System.out.println(n.getLCPrefix()+" Declaration latitude manquante");
        }
        if(!declarationList.containsKey("longitude") ){
            errors.put(n.getLCPrefix()+" Declaration longitude manquante", ERROR_KIND.MISSING_DECLARATION);
            System.out.println(n.getLCPrefix()+" Declaration longitude manquante");
        }
        if(!declarationList.containsKey("capacite") ){
            errors.put(n.getLCPrefix()+" Declaration capacite manquante", ERROR_KIND.MISSING_DECLARATION);
            System.out.println(n.getLCPrefix()+"Declaration capacite manquante");
        }
        return null;
    }

    @Override
    public Object visit(ASTDeclaration n) {
        String key = (String) n.getChild(0).accept(this);
        Double value = (Double) n.getChild(1).accept(this);
        return new Object[] { key, value };
    }
    @Override
    public Object visit(ASTChaine n) {
        return n.toString().substring(1, n.toString().length()-1);
    }
    @Override
    public Object visit(ASTIdentificateur n) {
        return n.toString();
    }

    @Override
    public Object visit(ASTNombre n){
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