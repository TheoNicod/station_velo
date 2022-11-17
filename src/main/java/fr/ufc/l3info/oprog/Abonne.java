/**
 * CHUAT ROMAIN
 */


package fr.ufc.l3info.oprog;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

/**
 * Classe représentant un abonné au service VéloCité.
 */
public class Abonne {
    String nom;
    int id;
    String rib;
    boolean bloque = false;
    static int cpt = 0;

    /**
     * Créé un abonné dont le nom est passé en paramètre, sans informations bancaires.
     *  Si le nom de l'abonné n'est pas correct (vide ou ne contenant pas des lettres éventuellementséparées par des espaces ou des traits d'union), le constructeur déclenchera l'exception IncorrectNameException.
     *  On notera que le nom peut contenir des espaces inutiles en début et en fin, mais ceux-ci seront retirés pour enregistrer cette donnée.
     * @param nom le nom du nouvel abonné.
     * @throws IncorrectNameException si le nom de l'abonné n'est pas correct.
     */
    public Abonne(String nom) throws IncorrectNameException {
        this.id = cpt;
        cpt ++;
        if(nom.length() == 0) throw new IncorrectNameException();
        nom = nom.replaceAll("[ ]+", " ");
        if(nom.charAt(0) == ' '){
            nom = nom.substring(1);
        }
        if(nom.charAt(nom.length()-1) == ' '){
            nom = nom.substring(0,nom.length()-1 );
        }
        for(int i =0; i< nom.length(); i++){
            if(!isLetter(nom.charAt(i)) && nom.charAt(i) != '-' && nom.charAt(i) != ' '){
                throw new IncorrectNameException();
            }
            if( nom.charAt(i) == '-') {
                if(i == 0 || i == nom.length()-1 || nom.charAt(i-1) == '-' ){
                    throw  new IncorrectNameException();
                }
            }
        }
        this.nom = nom;
    }

    /**
     * Créé un abonné dont le nom est passé en paramètre, avec les informations bancaires spécifiées dans le second paramètre.
     *  Le comportement attendu est le même que celui du constructeur précédent. Le RIB n'est enregistré que si celui-ci est valide.
     * @param nom le nom du nouvel abonné.
     * @param rib le RIB
     * @throws IncorrectNameException si le nom de l'abonné n'est pas correct.
     */
    public Abonne(String nom, String rib) throws IncorrectNameException {
        this(nom);
        boolean error = false;
        if(rib.length() != 26) {
            error = true;
        }else{
            int i = 0;
            while(!error && i< rib.length()){
                if(i != 5 && i != 11 && i != 23){
                    if(!isDigit(rib.charAt(i))){
                        error = true;
                    }
                }
                i++;
            }
            if(!error){
                String cleString = ""+rib.charAt(24)+rib.charAt(25);
                int cle = Integer.parseInt(cleString);

                String codeBanqueString = rib.substring(0,5);
                int codeBanque = Integer.parseInt(codeBanqueString);

                String codeGuichetString = rib.substring(6, 11);
                int codeGuichet = Integer.parseInt(codeGuichetString);

                String numCompteString = rib.substring(12, 23);
                Long numCompte = Long.parseLong(numCompteString);

                Long correctCle = 97 - ( ( (89*codeBanque)+(15*codeGuichet)+(3*numCompte) ) %97 );
                if(cle != correctCle){
                    error = true;
                }
            }
        }
        if(!error) {
            this.rib = rib;

        }else{
            this.bloquer();
        }
    }

    /**
     * Renvoie l'identifiant de l'abonné, généré autoamtiquement à sa création.
     * @return l'identifiant de l'abonné.
     */
    public int getID() {
        return this.id;
    }

    /**
     * Renvoie le nom de l'abonné.
     * @return le nom de l'abonné, sans les éventuels espace en début et en fin de chaîne.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Met à jour l'ancien RIB pour un nouveau. Si le nouveau RIB n'est pas valide, l'abonné conserve ses anciennes coordonnées bancaires.
     * @param rib nouveau RIB pour la mise à jour.
     */
    public void miseAJourRIB(String rib) {
        boolean error = false;
        if(rib.length() != 26) {
            error = true;
        }else{
            int i = 0;
            while(!error && i< rib.length()){
                if(i != 5 && i != 11 && i != 23){
                    if(!isDigit(rib.charAt(i))){
                        error = true;
                    }
                }
                i++;
            }
            if(!error){
                String cleString = ""+rib.charAt(24)+rib.charAt(25);
                int cle = Integer.parseInt(cleString);

                String codeBanqueString = rib.substring(0,5);
                int codeBanque = Integer.parseInt(codeBanqueString);

                String codeGuichetString = rib.substring(6, 11);
                int codeGuichet = Integer.parseInt(codeGuichetString);

                String numCompteString = rib.substring(12, 23);
                Long numCompte = Long.parseLong(numCompteString);

                Long correctCle = 97 - ( ( (89*codeBanque)+(15*codeGuichet)+(3*numCompte) ) %97 );
                if(cle != correctCle){
                    error = true;
                }
            }
        }
        if(!error && this.estBloque()) {
            this.rib = rib;
            this.debloquer();

        }else{
            if(!error){
                this.rib = rib;
            }
        }
    }

    /**
     * Permet de bloquer volontairement un abonné.
     */
    public void bloquer() {
        this.bloque = true;
    }

    /**
     * Permet de débloquer un abonné.
     */
    public void debloquer() {
        this.bloque = false;
    }

    /**
     * Vérifie si un abonné est bloqué. Celui-ci peut être bloqué volontairement ou parce que ses coordonnées bancaires sont invalides.
     * @return true si l'abonné est considéré comme bloqué, false sinon.
     */
    public boolean estBloque() {
        return this.bloque;
    }

    /**
     * permet de tester si deux abonnés sont identiques. Pour cela, on vérifiera si leur identifiant est le même.
     * @param a l'abonné avec lequel est comparé l'instance courante.
     * @return true si les deux objets ont le même ID, false sinon.
     */
    public boolean equals(Object a) {
        if(this.getClass() != a.getClass() || a == null){
            return  false;
        }
        return this.getID() == ((Abonne) a).getID();
    }

    /**
     * Utilisée en interne par Java pour obtenir un hash de l'objet. Cette méthode est utilisée pour les structures de collection de type HashSet ou HashMap.
     * @return le hash de l'instance courante.
     */
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + this.id;
        hash = hash * 31 + this.nom.hashCode();
        return hash;
    }
}

class IncorrectNameException extends Exception {
    public IncorrectNameException() {
        super("Le nom fourni n'est pas correct.");
    }
}
