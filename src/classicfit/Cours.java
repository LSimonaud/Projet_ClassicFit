/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classicfit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

/**
 *
 * @author lucsi
 */
public class Cours {

    private String nom_co; //nom du cours
    private int nbre_place; //nombre de places du cours
    private final String type_co; //type de cours (indviduel/collectif)
    private LocalDate date_co; //date d'un cours
    private LocalDateTime duree_co; //durée d'un cours

    private TreeSet<Client> liste_inscrits; //liste des personnes inscrites au cours

    public Cours(String nom_co, int nbre_place, String type_co, LocalDate date_co, LocalDateTime duree_co) {
        this.nom_co = nom_co;
        this.nbre_place = nbre_place;
        this.type_co = type_co;
        this.date_co = date_co;
        this.duree_co = duree_co;

        //Initialisation des listes
        liste_inscrits = new TreeSet<>();
    }

    @Override
    public String toString() {
        return nom_co + ";" + nbre_place + ";" + type_co + ";" + date_co + ";" + duree_co;
    }

    public void verification_date() {

    }

}
