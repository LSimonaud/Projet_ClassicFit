/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classicfit;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author lucsi
 */
public class Client extends Utilisateur {

    private String nom_cl; //nom du client
    private String prenom_cl; //prenom du client
    private LocalDate date_naissance; //date de naissance du client
    private String numero_tel; //numero de telephone du client
    private String addresse_cl; //addresse du client

    private String type_ab; //type d'abonnement d'un client (trimestriel,semstriel,annuel)
    private String etat_ab; //état de l'abonnement (actif/inactif)

    private int ID_cl; //identifiant attribué au client

    private LinkedList<Cours> listeCours_passes;
    private LinkedList<Cours> listeCours_futurs;

    Scanner sc = new Scanner(System.in);

    public Client(String email, String mdp, String nom, String prenom, LocalDate date_naissance,
            String numero_tel, String addresse, String type_ab, String etat_ab, int ID_cl) {
        super(email, mdp);
        this.nom_cl = nom;
        this.prenom_cl = prenom;
        this.date_naissance = date_naissance;
        this.numero_tel = numero_tel;
        this.addresse_cl = addresse;

        this.ID_cl = ID_cl;

        this.type_ab = type_ab;
        this.etat_ab = etat_ab;

        //Initialisation des listes
        listeCours_passes = new LinkedList<>();
        listeCours_futurs = new LinkedList<>();
    }

    @Override
    public String toString() {
        return super.toString() + ";" + nom_cl + ";" + prenom_cl + ";" + date_naissance + ";"
                + numero_tel + ";" + addresse_cl + ";" + type_ab + ";"
                + etat_ab + ";" + String.valueOf(ID_cl) + ";" + listeCours_passes + ";"
                + listeCours_futurs;
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
        return "Date de naissance : "+date_naissance;
    }
    
    public String modifier_numero_telephone(String numtel){
        this.numero_tel = numtel;
        return "Numero de telephone : "+numero_tel;
    }
    
    public String modifier_addresse(String addresse){
        this.addresse_cl = addresse;
        return "Addresse : "+addresse_cl;
    }
    
    public String modifier_abonnement(String abonnement){
        this.type_ab = abonnement;
        return "Type d'abonnement : "+type_ab;
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
        System.out.println("Date de naissance : "+ date_naissance);
        System.out.println("Numero de telephone : "+ numero_tel);
        System.out.println("Addresse : "+ addresse_cl);
        System.out.println("Type d'abonnement : "+ type_ab);
    }
}
