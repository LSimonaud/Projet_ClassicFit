/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classicfit;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
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

    private static final String FICHIER_CLIENTS = "Fichier_clients.txt"; //Fichier de sauvegarde des clients
    private static final String FICHIER_COURS = "Fichier_cours.txt"; //Fichier de sauvegarde des cours

    Scanner sc = new Scanner(System.in);

    public Salle(String nom, Administrateur admin) {
        this.nom = nom;
        this.admin = admin;

        //Initialisation des listes
        this.listeCours = new TreeSet<>();
        this.listeClient = new TreeSet<>();
    }

    @Override
    public String toString() {
        return "Nom de la salle :" + nom;
    }

    public void Connection() {
        System.out.println("Entrer votre addresse mail :");
        String addresse_mail = sc.nextLine();
        System.out.println("Entrer votre mot de passe :");
        String mdp = sc.nextLine();
    }

    public void Modifier_mdp() {

    }

    public void Creer_compte() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("Entrer une addresse mail :");
        String addresse_mail = sc.nextLine();
        System.out.println("Definir un mot de passe :");
        String mdp = sc.nextLine();

        System.out.println("Veuillez entrer vos informations personnelles");
        System.out.print("Nom :");
        String nom_client = sc.nextLine();
        System.out.print("Prenom :");
        String prenom_client = sc.nextLine();
        System.out.print("Date de naissance :");
        LocalDate date_naissance = LocalDate.parse(sc.nextLine(), format);
        System.out.print("Numero de telephone :");
        String numero_telephone = sc.nextLine();
        System.out.print("Addresse :");
        String addresse_client = sc.nextLine();

        System.out.print("Veuillez choisir un type d'abonnement : (trimestriel/semestriel/annuel)");
        String type_abonnement = sc.nextLine();

        int numero_client = 1;

        Client client = new Client(addresse_mail, mdp, nom_client, prenom_client, date_naissance,
                numero_telephone, addresse_client, type_abonnement, numero_client);
        listeClient.add(client);

    }

}
