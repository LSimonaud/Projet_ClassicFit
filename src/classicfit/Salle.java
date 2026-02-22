/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classicfit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author lucsi
 */
public class Salle {

    private final String nom; //Nom de la salle de sport
    private final Administrateur admin; //Administrateur de la salle
    private LinkedList<Cours> listeCours; //liste des cours passés et futurs
    private LinkedList<Client> listeClient; //liste de tous les clients de la salle

    private static final String FICHIER_CLIENTS = "Fichier_clients.txt"; //Fichier de sauvegarde des clients
    private static final String FICHIER_COURS = "Fichier_cours.txt"; //Fichier de sauvegarde des cours

    Scanner sc = new Scanner(System.in);
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Salle(String nom, Administrateur admin) {
        this.nom = nom;
        this.admin = admin;

        //Initialisation des listes
        this.listeCours = new LinkedList<>();
        this.listeClient = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "Nom de la salle :" + nom;
    }

    public Utilisateur seConnecter(String email, String mdp) throws UserNotFoundException {
        if (email == null || email.trim().isEmpty() || !email.matches("[@]")) {
            throw new IllegalArgumentException("Addresse mail invalide");
        }
        if (mdp == null || mdp.trim().isEmpty()) {
            throw new IllegalArgumentException("Mot de passe invalide");
        }

        if (admin.getemail().equalsIgnoreCase(email) && admin.getmdp().equalsIgnoreCase(mdp)) {
            return admin;
        } else {
            for (Client cl : listeClient) {
                if (cl.getemail().equalsIgnoreCase(email) && cl.getmdp().equalsIgnoreCase(mdp)) {
                    return cl;
                }
            }
        }
        throw new UserNotFoundException("Email ou mot de passe incorrect");
    }

    public String mdp_oublie(String email) {
        if (email == null || email.trim().isEmpty() || !email.matches("[@]")) {
            return "Addresse mail invalide";
        }

        if (admin.getemail().equalsIgnoreCase(email)) {
            return admin.getmdp();
        } else {
            for (Client cl : listeClient) {
                if (cl.getemail().equalsIgnoreCase(email)) {
                    return cl.getmdp();
                }
            }
        }
        return "Email incorrect";
    }

    public void Modifier_mdp() {
        System.out.println("Entrez le nouveau mot de passe");
        String nouv_mdp = sc.nextLine();
        String nouv_mdp1 = "a";
        String email = sc.nextLine();
        while (!nouv_mdp.equals(nouv_mdp1)) {
            System.out.println("Verifiaction de mot de passe : Entrez de nouveau le mot de passe");
            nouv_mdp1 = sc.nextLine();
        }
        for (Utilisateur name : listeClient) {
            if (name.getemail() == email) {
                nouv_mdp1 = name.getmdp();
            }
        }
    }

    public void Creer_compte() throws IllegalArgumentException {

        System.out.println("Entrer une addresse mail :");
        String addresse_mail = sc.nextLine();
        if (addresse_mail == null || addresse_mail.trim().isEmpty() || !addresse_mail.matches(".*[@].*")) {
            throw new IllegalArgumentException("Addresse mail invalide");
        }
        System.out.println("Definir un mot de passe (12 caracteres minimum, 1 majuscule, 1 minuscule, 1 chiffre, 1 caractere speciale):");
        String mdp = sc.nextLine();
        if (mdp == null || mdp.trim().isEmpty() || mdp.length() < 12 || !mdp.matches(".*[^a-zA-Z0-9].*") || !mdp.matches(".*[a-z].*") || !mdp.matches(".*[A-Z].*") || !mdp.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Mot de passe invalide");
        }

        System.out.println("Veuillez entrer vos informations personnelles");
        System.out.println("Nom :");
        String nom_cl = sc.nextLine();
        if (nom_cl == null || nom_cl.trim().isEmpty() || !nom_cl.matches("[a-zA-Z]")) {
            throw new IllegalArgumentException("Nom invalide");
        }
        System.out.println("Prenom :");
        String prenom_cl = sc.nextLine();
        if (prenom_cl == null || prenom_cl.trim().isEmpty() || !prenom_cl.matches("[a-zA-Z]")) {
            throw new IllegalArgumentException("Prenom invalide");
        }
        System.out.println("Date de naissance (dd-MM-yyyy) :");
        String date = sc.nextLine();
        if (date == null || date.trim().isEmpty() || !date.matches("[0-9]") || !date.matches(".*[-].*")) {
            throw new IllegalArgumentException("Date de naissance invalide");
        }
        LocalDate date_naissance = LocalDate.parse(date, format);

        System.out.println("Numero de telephone :");
        String numero_tel = sc.nextLine();
        if (numero_tel == null || numero_tel.trim().isEmpty() || !numero_tel.matches("[0-9]")) {
            throw new IllegalArgumentException("Numero de telephone invalide");
        }
        System.out.println("Addresse :");
        String addresse_cl = sc.nextLine();
        if (addresse_cl == null || addresse_cl.trim().isEmpty() || !addresse_cl.matches("[a-zA-Z0-9]")) {
            throw new IllegalArgumentException("Addresse invalide");
        }

        System.out.println("Veuillez choisir un type d'abonnement : 1-trimestriel 2-semestriel 3-annuel");
        int rep = sc.nextInt();
        sc.nextLine();
        if (rep != 1 || rep != 2 || rep != 3) {
            throw new IllegalArgumentException("Reponse invalide");
        }
        String type_ab = " ";
        switch (rep) {
            case 1 -> {
                type_ab = "trimestriel";
                break;
            }
            case 2 -> {
                type_ab = "semestriel";
                break;
            }
            case 3 -> {
                type_ab = "annuel";
                break;
            }
        }
        String etat_ab = "actif";

        int numero_cl = listeClient.size() + 1;

        Client client = new Client(addresse_mail, mdp, nom_cl, prenom_cl, date_naissance,
                numero_tel, addresse_cl, type_ab, etat_ab, numero_cl);
        listeClient.add(client);
    }

    public void Consulter_infos(Client cl) {
        cl.affichage_infos();
    }

    public void Modifier_infos(Client cl) throws IllegalArgumentException {
        System.out.println("Que souhaitez-vous modifier : 1-Nom 2-Prenom 3-Date de naissance 4-Numero de telephone 5-Addresse 6-Type d'abonnement");
        int choix = sc.nextInt();
        sc.nextLine();
        switch (choix) {
            case 1 -> {
                System.out.println("Entrez un nouveau Nom :");
                String new_nom = sc.nextLine();
                if (new_nom == null || new_nom.trim().isEmpty() || !new_nom.matches("[a-zA-Z]")) {
                    throw new IllegalArgumentException("Nom invalide");
                }
                System.out.println(cl.modifier_nom(new_nom));
                break;
            }
            case 2 -> {
                System.out.println("Entrez un nouveau Prenom :");
                String new_prenom = sc.nextLine();
                if (new_prenom == null || new_prenom.trim().isEmpty() || !new_prenom.matches("[a-zA-Z]")) {
                    throw new IllegalArgumentException("Prenom invalide");
                }
                System.out.println(cl.modifier_prenom(new_prenom));
                break;
            }
            case 3 -> {
                System.out.println("Entrez une nouvelle Date de naissance (dd-MM-yyyy):");
                String new_date = sc.nextLine();
                if (new_date == null || new_date.trim().isEmpty() || !new_date.matches("[0-9]") || !new_date.matches(".*[-].*")) {
                    throw new IllegalArgumentException("Date de naissance invalide");
                }
                LocalDate new_date_naissance = LocalDate.parse(new_date, format);
                System.out.println(cl.modifier_date_naissance(new_date_naissance));
                break;
            }
            case 4 -> {
                System.out.println("Entrez un nouveau Numero de telephone :");
                String new_num_tel = sc.nextLine();
                if (new_num_tel == null || new_num_tel.trim().isEmpty() || !new_num_tel.matches("[0-9]")) {
                    throw new IllegalArgumentException("Numero de telephone invalide");
                }
                System.out.println(cl.modifier_numero_telephone(new_num_tel));
                break;
            }
            case 5 -> {
                System.out.println("Entrez une nouvelle Addresse :");
                String new_addresse = sc.nextLine();
                if (new_addresse == null || new_addresse.trim().isEmpty() || !new_addresse.matches("[a-zA-Z0-9]")) {
                    throw new IllegalArgumentException("Addresse invalide");
                }
                System.out.println(cl.modifier_addresse(new_addresse));
                break;
            }
            case 6 -> {
                System.out.println("Choisissez un nouveau Type d'abonnement : 1-trimestriel 2-semestriel 3-annuel");
                int i = sc.nextInt();
                sc.nextLine();
                if (i != 1 || i != 2 || i != 3) {
                    throw new IllegalArgumentException("Reponse invalide");
                }
                switch (i) {
                    case 1 -> {
                        String new_type_ab = "trimestriel";
                        cl.modifier_abonnement(new_type_ab);
                        break;
                    }
                    case 2 -> {
                        String new_type_ab = "semestriel";
                        cl.modifier_abonnement(new_type_ab);
                        break;
                    }
                    case 3 -> {
                        String new_type_ab = "annuel";
                        cl.modifier_abonnement(new_type_ab);
                        break;
                    }
                }
                break;
            }
        }
    }

    public void Consulter_listeCient() {
        for (Client cl : listeClient) {
            cl.affichage_liste();
        }
    }

    public Client Rechercher_client_IDcl(int ID) throws UserNotFoundException {
        if (ID <= 0) {
            throw new IllegalArgumentException("ID invalide");
        }
        for (Client cl : listeClient) {
            if (cl.getID_client() == ID) {
                return cl;
            }
        }
        throw new UserNotFoundException("Utilisateur avec ID " + ID + " introuvable.");
    }

    public Client Rechercher_client_nomCl(String nom) throws UserNotFoundException {
        if (nom == null || nom.trim().isEmpty() || !nom.matches("[a-zA-Z]")) {
            throw new IllegalArgumentException("Nom invalide");
        }
        for (Client cl : listeClient) {
            if (cl.getnom_client().equalsIgnoreCase(nom)) {
                return cl;
            }
        }
        throw new UserNotFoundException("Nom de client " + nom + " introuvable.");
    }
    
    public void Desactiver_abonnement(String nom){
        
    }
    
    public void Reactiver_abonnement(String nom){
        
    }

    public void sauvegarder() throws IOException {
        String sep = System.lineSeparator();

        FileWriter fichCl = new FileWriter(FICHIER_CLIENTS);
        for (Client cl : listeClient) {
            fichCl.write(cl.toString());
            fichCl.write(sep);
        }
        fichCl.close();

        FileWriter fichCo = new FileWriter(FICHIER_COURS);
        for (Cours co : listeCours) {
            fichCo.write(co.toString());
            fichCo.write(sep);
        }
    }

    public void charger() throws FileNotFoundException, IOException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        FileReader fichCl = new FileReader(FICHIER_CLIENTS);
        BufferedReader br = new BufferedReader(fichCl);
        String ligne = br.readLine();
        while (ligne != null) {
            String[] tab = ligne.split(";");
            String addresse_mail = tab[0];
            String mdp = tab[1];
            String nom_cl = tab[2];
            String prenom_cl = tab[3];
            LocalDate date_naissance = LocalDate.parse(tab[4], format);
            String numero_tel = tab[5];
            String addresse_cl = tab[6];
            String type_ab = tab[7];
            String etat_ab = tab[8];
            int numero_cl = Integer.parseInt(tab[9]);

            Client cl = new Client(addresse_mail, mdp, nom_cl, prenom_cl, date_naissance, numero_tel,
                    addresse_cl, type_ab, etat_ab, numero_cl);
            listeClient.add(cl);
            ligne = br.readLine();
        }

        FileReader fichCo = new FileReader(FICHIER_COURS);
        BufferedReader br2 = new BufferedReader(fichCo);
        ligne = br2.readLine();
        while (ligne != null) {
            String[] tab = ligne.split(";");
        }
    }
}
