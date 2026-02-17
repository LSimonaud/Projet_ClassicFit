/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classicfit;

import java.time.LocalDate;
import java.util.TreeSet;

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

    private String numero_cl; //numero attribué au client

    private TreeSet<Cours> listeCours_passes;
    private TreeSet<Cours> listeCours_futurs;

    public Client(String email, String mdp, String nom, String prenom, LocalDate date_naissance,
            String numero_tel, String addresse, String type_ab, String etat_ab,String numero_cl) {
        super(email, mdp);
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.numero_tel = numero_tel;
        this.addresse = addresse;

        this.numero_cl = numero_cl;

        this.type_ab = type_ab;
        this.etat_ab = etat_ab;

        //Initialisation des listes
        listeCours_passes = new TreeSet<>();
        listeCours_futurs = new TreeSet<>();
    }

    @Override
    public String toString() {
        return super.toString()+ ";" + nom + ";" + prenom + ";" + date_naissance + ";" 
                + numero_tel + ";" + addresse + ";" + type_ab + ";" 
                + etat_ab + ";" + numero_cl + ";" + listeCours_passes + ";" 
                + listeCours_futurs;
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
