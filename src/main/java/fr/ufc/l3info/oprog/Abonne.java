package fr.ufc.l3info.oprog;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Classe représentant un abonné au service VéloCité.
 */
public class Abonne {
    private String nom, rib;
    //private static int count = 0;
    private int id;
    private boolean isBloque;
    /**
     * Créé un abonné dont le nom est passé en paramètre, sans informations bancaires. 
     *  Si le nom de l'abonné n'est pas correct (vide ou ne contenant pas des lettres éventuellement séparées par des espaces ou des traits d'union), le constructeur déclenchera l'exception IncorrectNameException.
     *  On notera que le nom peut contenir des espaces inutiles en début et en fin, mais ceux-ci seront retirés pour enregistrer cette donnée.
     * @param nom le nom du nouvel abonné.  
     * @throws IncorrectNameException si le nom de l'abonné n'est pas correct.
     */
    public Abonne(String nom) throws IncorrectNameException {
        if(nom == null || nom.length() == 0 || !nom.matches("[[a-zA-Z]+[  |-]?]*")) {
            throw new IncorrectNameException();
        }
        this.nom = nom;
        this.rib = "";
        this.id = 1;
        this.isBloque = true;
    }

    /**
     * Créé un abonné dont le nom est passé en paramètre, avec les informations bancaires spécifiées dans le second paramètre.
     *  Le comportement attendu est le même que celui du constructeur précédent. Le RIB n'est enregistré que si celui-ci est valide.
     * @param nom le nom du nouvel abonné.
     * @param rib le RIB
     * @throws IncorrectNameException si le nom de l'abonné n'est pas correct.
     */
    public Abonne(String nom, String rib) throws IncorrectNameException {
        if(nom == null || nom.length() == 0 || !nom.matches("[[a-zA-Z]+[  |-]?]*")) {
            throw new IncorrectNameException();
        }
        this.nom = nom;
        //pas de taille mini/max pour le rib, je décide donc de mettre 20 carac
        if(rib.matches("^[0-9]{20}")) {
            this.rib = rib;
        }else{
            this.rib = "";
        }
        this.id = 2;
        this.isBloque = false;
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
        return this.nom.trim();
    }

    /**
     * (Ajouté)
     * @return Renvoie le RIB l'abonné.
     */
    public String getRib () {
        return this.rib;
    }

    public boolean getIsBloque() {
        return this.isBloque;
    }

    /**
     * Met à jour l'ancien RIB pour un nouveau. Si le nouveau RIB n'est pas valide, l'abonné conserve ses anciennes coordonnées bancaires.
     * @param rib nouveau RIB pour la mise à jour. 
     */
    public void miseAJourRIB(String rib) {
        if(rib.matches("^[0-9]{20}")) {
            this.rib = rib;
        }
    }

    /**
     * Permet de bloquer volontairement un abonné.
     */
    public void bloquer() {
        this.isBloque = true;
    }

    /**
     * Permet de débloquer un abonné. 
     */
    public void debloquer() {
        this.isBloque = false;
    }

    /**
     * Vérifie si un abonné est bloqué. Celui-ci peut être bloqué volontairement ou parce que ses coordonnées bancaires sont invalides.
     * @return true si l'abonné est considéré comme bloqué, false sinon.
     */
    public boolean estBloque() {
        return this.isBloque;
    }

    /**
     * permet de tester si deux abonnés sont identiques. Pour cela, on vérifiera si leur identifiant est le même.
     * @param a l'abonné avec lequel est comparé l'instance courante. 
     * @return true si les deux objets ont le même ID, false sinon. 
     */
    public boolean equals(Object a) {
        if(a == null)
            return false;
        if(this == a)
            return true;
        if(this.getClass() != a.getClass())
            return false;
        Abonne abo = (Abonne) a;
        return this.id == abo.getID();
    }

    /**
     * Utilisée en interne par Java pour obtenir un hash de l'objet. Cette méthode est utilisée pour les structures de collection de type HashSet ou HashMap.
     * @return le hash de l'instance courante. 
     */
    public int hashCode() { // revoir
        int hash = 7;
        hash = 31 * hash + (int) id;
        hash = 31 * hash + (nom == null ? 0 : nom.hashCode());
        hash = 31 * hash + (rib == null ? 0 : rib.hashCode());
        return hash;
    }
}

class IncorrectNameException extends Exception {
    public IncorrectNameException() {
        super("Le nom fourni n'est pas correct.");
    }
}


