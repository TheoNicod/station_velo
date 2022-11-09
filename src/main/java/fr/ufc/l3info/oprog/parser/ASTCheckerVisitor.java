package fr.ufc.l3info.oprog;

import java.util.*;


////  /!\ pour les tests des messages d'erreurs, ne pas les vérifier en accèdant avec des indices(pasque les ordre de dadeau ne seront surement pas les mêmes)
// pas d'exception à lever dans le CheckerVisitor

public class ASTCheckerVisitor {

    public ASTCheckerVisitor() {

    }

    //code du visiteur pour ListStation (c'est le plus facile) (même structure pour tous les visit (en gros))
    public Object visit(ASTListeStations n) {
        if(n.getNumChild() == 0) {
            error.put(n.getPrefix() + "message erreur", ERROR_KIND.EMPTY_LIST);
        }else{
            for(ASTNode child : n) {
                child.accept(this);
            }
        }
        return null;
    }


}