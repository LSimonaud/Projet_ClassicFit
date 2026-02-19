/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classicfit;

import java.time.LocalDate;
import java.util.TreeSet;
import java.util.Scanner;

/**
 *
 * @author lucsi
 */
public class Client extends Utilisateur {

    private String nom; //nom du client
    private String prenom; //prenom du client
    private LocalDate date_naissance; //date de naissance du client
    private String numero_tel; //numero de telephone du client
    private String addresse; //addresse du client

    private String type_ab; //type d'abonnement d'un client (trimestriel,semstriel,annuel)
    private String etat_ab; //état de l'abonnement (actif/inactif)

    private int ID_cl; //identifiant attribué au client

    private TreeSet<Cours> listeCours_passes;
    private TreeSet<Cours> listeCours_futurs;

    Scanner sc = new Scanner(System.in);

    public Client(String email, String mdp, String nom, String prenom, LocalDate date_naissance,
            String numero_tel, String addresse, String type_ab, String etat_ab, int ID_cl) {
        super(email, mdp);
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.numero_tel = numero_tel;
        this.addresse = addresse;

        this.ID_cl = ID_cl;

        this.type_ab = type_ab;
        this.etat_ab = etat_ab;

        //Initialisation des listes
        listeCours_passes = new TreeSet<>();
        listeCours_futurs = new TreeSet<>();
    }

    @Override
    public String toString() {
        return super.toString() + ";" + nom + ";" + prenom + ";" + date_naissance + ";"
                + numero_tel + ";" + addresse + ";" + type_ab + ";"
                + etat_ab + ";" + ID_cl + ";" + listeCours_passes + ";"
                + listeCours_futurs;
    }

    public int getID_client(){
        return this.ID_cl;
    }
    
    public String getnom_client(){
        return this.nom;
    }
    public void inscription_cours() {

    }

    public void desinscription_cours() {

    }

    public void inscription_activite() {

    }

    public void desinscription_activite() {

    }

}
