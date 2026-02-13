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
    private String numero_telephone; //numero de telephone du client
    private String addresse; //addresse du client

    private String type_abonnement; //type d'abonnement d'un client (trimestriel,semstriel,annuel)
    private String etat_abonnement; //état de l'abonnement (actif/inactif)

    private int numero_client; //numero attribué au client

    private TreeSet<Cours> listeCours_passes;
    private TreeSet<Cours> listeCours_futurs;

    public Client(String email, String mdp, String nom, String prenom, LocalDate date_naissance,
            String numero_telephone, String addresse, String type_abonnement, int numero_client) {
        super(email, mdp);
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.numero_telephone = numero_telephone;
        this.addresse = addresse;

        this.numero_client = numero_client;

        this.type_abonnement = type_abonnement;
        this.etat_abonnement = "actif";

        //Initialisation des listes
        listeCours_passes = new TreeSet<>();
        listeCours_futurs = new TreeSet<>();
    }

    @Override
    public String toString() {
        return "";
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
