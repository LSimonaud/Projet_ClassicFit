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
public class Salle {
    
    private String nom; //Nom de la salle de sport
    LinkedList<String> Administrateurs;
    LinkedList<String> cours;
    LinkedList<String> client;
    private LocalDate duree;
    private String prenom,mdp; //Pour se connecter
    
    public Salle(String nom){
        this.nom = nom;
        this.duree = duree;
        this.prenom = prenom;
        this.mdp = mdp;
    }
    
    public void Affichage(){
        return;
    }
    
    
    
}
