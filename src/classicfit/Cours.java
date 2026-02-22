/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classicfit;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 *
 * @author lucsi
 */
public class Cours {

    private String nom_co; //nom du cours
    private int nbre_place; //nombre de places du cours
    private final String type_co; //type de cours (indviduel/collectif)
    private LocalDate date_co; //date d'un cours
    private int duree_co; //durée d'un cours en minute
    
    private int ID_co; //identifiant attribué au cours

    private LinkedList<Client> liste_inscrits; //liste des personnes inscrites au cours

    public Cours(String nom_co, int nbre_place, String type_co, LocalDate date_co, int duree_co, int ID_co) {
        this.nom_co = nom_co;
        this.nbre_place = nbre_place;
        this.type_co = type_co;
        this.date_co = date_co;
        this.duree_co = duree_co;

        this.ID_co = ID_co;
        
        //Initialisation des listes
        liste_inscrits = new LinkedList<>();
    }

    @Override
    public String toString() {
        return nom_co + ";" + nbre_place + ";" + type_co + ";" + date_co + ";" + duree_co;
    }
    
    public String affichage_liste(){
        return nom_co+" "+type_co+" "+nbre_place+" places"+date_co+" "+duree_co+" minutes";
    }

    public void verification_date() {

    }

}
