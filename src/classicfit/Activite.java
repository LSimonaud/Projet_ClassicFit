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
    
    private LinkedList<Cours> listeCours_activite; //liste des cours associés à une activite

    public Activite(String nom) {
        this.nom = nom;
        
        listeCours_activite = new LinkedList<>();
    }

    @Override
    public String toString() {
        return nom;
    }

}
