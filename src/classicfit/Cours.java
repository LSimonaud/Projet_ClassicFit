/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classicfit;

import java.time.LocalDateTime;
import java.util.TreeSet;

/**
 *
 * @author lucsi
 */
public class Cours {
    
    private String nom; //nom du cours
    private int nbre_place; //nombre de places du cours
    private final String type_cours; //type de cours (indviduel/collectif)
    private LocalDateTime duree; //durée d'un cours
    
    private TreeSet<Client> liste_inscrits; //liste des personnes inscrites au cours
            
    public Cours(String nom,int nbre_place,String type_cours,LocalDateTime duree){
        this.nom = nom;
        this.nbre_place = nbre_place;
        this.type_cours = type_cours;
        this.duree = duree;
        
        //Initialisation des listes
        liste_inscrits = new TreeSet<>();
    }
    
    @Override
    public String toString(){
        return nom + "," + type_cours;
    }
    
    public void verification_date(){
        
    }
    
}
