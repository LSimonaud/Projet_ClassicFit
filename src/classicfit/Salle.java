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
public class Salle {
    
    private String nom; //Nom de la salle de sport
    private Administrateur admin; //Administrateur de la salle
    private TreeSet<Cours> listeCours; //liste des cours passés et futurs
    private TreeSet<Client> listeClient; //liste de tous les clients de la salle
    
    private static final String FICHIER_SAUVEGARDE = "Fichier_de_sauvegarde.txt"; //Fichier de sauvegarde des données
    
    public Salle(String nom,Administrateur admin,LocalDateTime duree){
        this.nom = nom;
        this.admin = admin;
        
        //Initialisation des listes
        this.listeCours = new TreeSet<>();
        this.listeClient = new TreeSet<>();
    }
    
    @Override
    public String toString(){
        return nom;
    }
    
    public void Connection(){
        
    }
    
    public void Modifier_mdp(){
        
    }
    
    public void Creer_compte(){
        
    }
  
}
