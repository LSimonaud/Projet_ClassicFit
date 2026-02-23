/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classicfit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

/**
 *
 * @author lucsi
 */
public class Client extends Utilisateur {

    private String nom_cl; //nom du client
    private String prenom_cl; //prenom du client
    private LocalDate date_naissance; //date de naissance du client
    private String numero_tel; //numero de telephone du client
    private String adresse_cl; //addresse du client

    private String type_ab; //type d'abonnement d'un client (trimestriel,semstriel,annuel)
    private String etat_ab; //état de l'abonnement (actif/inactif)

    private final int ID_cl; //identifiant attribué au client

    private LinkedList<Cours> listeCours_passes;
    private LinkedList<Cours> listeCours_futurs;
    
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Client(int ID_cl, String email, String mdp, String nom, String prenom, LocalDate date_naissance,
            String numero_tel, String adresse, String type_ab, String etat_ab, 
            LinkedList<Cours> listeCours_passes, LinkedList<Cours> listeCours_futurs) {
        super(email, mdp);
        this.nom_cl = nom;
        this.prenom_cl = prenom;
        this.date_naissance = date_naissance;
        this.numero_tel = numero_tel;
        this.adresse_cl = adresse;
        this.ID_cl = ID_cl;
        this.type_ab = type_ab;
        this.etat_ab = etat_ab;
        //Initialisation des listes
        this.listeCours_passes = listeCours_passes;
        this.listeCours_futurs = listeCours_futurs;
    }

    @Override
    public String toString() {
        return String.valueOf(ID_cl) + ";" + super.toString() + ";" + nom_cl + ";" + prenom_cl + ";" + date_naissance.format(format) + ";"
                + numero_tel + ";" + adresse_cl + ";" + type_ab + ";"
                + etat_ab + ";" + this.Affichage_listeCours_client(listeCours_passes) + ";"
                + this.Affichage_listeCours_client(listeCours_futurs);
    }
    
    public String Affichage_listeCours_client(LinkedList<Cours> listeCours){
        String liste = "";
        for (Cours co : listeCours){
            liste = liste+co.affichage_listeCours_client()+"|";
        }
        return liste;
    }
    
    public String modifier_nom(String nom){
        this.nom_cl = nom;
        return "Nom : "+nom_cl;
    }
    
    public String modifier_prenom(String prenom){
        this.prenom_cl = prenom;
        return "Prenom : "+prenom_cl;
    }
    
    public String modifier_date_naissance(LocalDate date){
        this.date_naissance = date;
        return "Date de naissance : "+date_naissance.format(format);
    }
    
    public String modifier_numero_telephone(String numtel){
        this.numero_tel = numtel;
        return "Numero de telephone : "+numero_tel;
    }
    
    public String modifier_adresse(String adresse){
        this.adresse_cl = adresse;
        return "Addresse : "+adresse_cl;
    }
    
    public String modifier_abonnement(String abonnement){
        this.type_ab = abonnement;
        return "Type d'abonnement : "+type_ab;
    }
    
    public String modifier_etat_abonnement(){
        if (etat_ab.equalsIgnoreCase("actif")){
            etat_ab = "inactif";
            return "Etat abonnement : "+etat_ab;
        }else{
            etat_ab = "actif";
            return "Etat abonnement : "+etat_ab;
        }
    }
    
    public int getID_client(){
        return this.ID_cl;
    }
    
    public String getnom_client(){
        return this.nom_cl;
    }
    
    public String affichage_liste(){
        return nom_cl + " " + prenom_cl + " ID : " + String.valueOf(ID_cl);
    }
    
    public void affichage_infos(){
        System.out.println("Nom : "+nom_cl);
        System.out.println("Prenom : "+prenom_cl);
        System.out.println("Date de naissance : "+ date_naissance.format(format));
        System.out.println("Numero de telephone : "+ numero_tel);
        System.out.println("Adresse : "+ adresse_cl);
        System.out.println("Type d'abonnement : "+ type_ab);
    }
}
