/*

 */
package classicfit;

import java.util.LinkedList;

/**
 *
 * @author lucsi
 */
public class Activite {

    private String nom; //nom de l'activité
    private String description; //description de l'activite
    
    private final int ID; //ID de l'activite

    private LinkedList<Cours> listeCours_activite; //liste des cours associés à une activite

    public Activite(int ID,String nom,String description) {
        this.nom = nom;
        this.description = description;
        
        this.ID = ID;

        listeCours_activite = new LinkedList<>();
    }

    @Override
    public String toString() {
        return String.valueOf(ID)+";"+nom+";"+description;
    }
    
    public String affichage_liste(){
        return String.valueOf(ID)+" "+nom+" Description : "+description;
    }

}
