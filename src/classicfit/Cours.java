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
public class Cours {

    private String nom_co; //nom du cours
    private int nbre_place; //nombre de places du cours
    private String type_co; //type de cours (indviduel/collectif)
    private LocalDate date_co; //date d'un cours
    private int duree_co; //durée d'un cours en minute

    private final int ID_co; //identifiant attribué au cours

    private LinkedList<Client> liste_inscrits; //liste des personnes inscrites au cours

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Cours(int ID_co, String nom_co, int nbre_place, String type_co, LocalDate date_co, int duree_co) {
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
        return ID_co + "," + nom_co + "," + nbre_place + "," + type_co
                + "," + date_co.format(format) + "," + duree_co;
    }

    public int getID_cours() {
        return this.ID_co;
    }

    public String getNom_cours() {
        return this.nom_co;
    }
    
    public int getNbrePlace_cours(){
        return this.nbre_place;
    }

    public LocalDate getDate_cours() {
        return this.date_co;
    }
    
    public LinkedList<Client> getListeInscrit_cours(){
        return this.liste_inscrits;
    }

    public String modifier_nom(String nom) {
        this.nom_co = nom;
        return "Nom : " + nom_co;
    }

    public String modifier_nbrePlace(int nbre) {
        this.nbre_place = nbre;
        return "Nombre de place : " + String.valueOf(nbre_place);
    }

    public String modifier_typeCours(String type) {
        this.type_co = type;
        return "Type de cours : " + type_co;
    }

    public String modifier_date(LocalDate date) {
        this.date_co = date;
        return "Date : " + date_co.format(format);
    }

    public String modifier_duree(int duree) {
        this.duree_co = duree;
        return "Duree : " + String.valueOf(duree_co);
    }

    public String affichage_listeAdmin() {
        return "ID : "+String.valueOf(ID_co) + " | " + nom_co + " | " + type_co + " | " + String.valueOf(nbre_place) + " places | "
                + date_co.format(format) + " | " + String.valueOf(duree_co) + " minutes | " + String.valueOf(liste_inscrits.size())
                + " sur " + String.valueOf(nbre_place) + " inscits";
    }
    
    public String affichage_listeClient() {
        return "ID : "+String.valueOf(ID_co) + " | " + nom_co + " | " + type_co + " | " + String.valueOf(nbre_place) + " places | "
                + date_co.format(format) + " | " + String.valueOf(duree_co) + " minutes ";
    }

    public void ajouter_inscription(Client cl) throws DejaInscritException {
        if (this.liste_inscrits.contains(cl)) {
            throw new DejaInscritException("Vous etes deja inscrit a ce cours");
        }
        this.liste_inscrits.add(cl);
    }

    public void retirer_inscription(Client cl) {
        this.liste_inscrits.remove(cl);
    }

    public boolean verification_date() {
        return this.date_co.isBefore(LocalDate.now());
    }

}
