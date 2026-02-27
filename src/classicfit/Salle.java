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
    private int clientdapres = 1;
    private LinkedList<Cours> listeCours; //liste des cours passés et futurs
    private LinkedList<Client> listeClient; //liste de tous les clients de la salle

    public static final String FICHIER_CLIENTS = "Fichier_clients.txt"; //Fichier de sauvegarde des clients
    public static final String FICHIER_COURS = "Fichier_cours.txt"; //Fichier de sauvegarde des cours

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

    public String seConnecter(String email, String mdp) throws UserNotFoundException {
        if (email == null || email.trim().isEmpty() || !email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Adresse mail invalide");
        }
        if (mdp == null || mdp.trim().isEmpty() || !mdp.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&,.#:;\\-_/])[A-Za-z\\d@$!%*?&,.#:;\\-_/]{12,}$")) {
            throw new IllegalArgumentException("Mot de passe invalide");
        }

        if (admin.getemail().equals(email) && admin.getmdp().equals(mdp)) {
            return "administrateur";
        }
        for (Client cl : listeClient) {
            if (cl.getemail().equals(email) && cl.getmdp().equals(mdp)) {
                return "client";
            }
        }

        throw new UserNotFoundException("Email ou mot de passe incorrect");

    }

    public String mdp_oublie(String email) {
        if (email == null || email.trim().isEmpty() || !email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "Adresse mail invalide";
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

    public void Modifier_mdp(String email) {
        System.out.println("Entrez le nouveau mot de passe");
        String nouv_mdp = sc.nextLine();
        String nouv_mdp1 = "a";
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
        System.out.println("Entrer une adresse mail :");
        String adresse_mail = sc.nextLine();
        if (adresse_mail == null || adresse_mail.trim().isEmpty() || !adresse_mail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Adresse mail invalide");
        }
        for (Client cl : listeClient) {
            if (cl.getemail().equalsIgnoreCase(adresse_mail)) {
                throw new IllegalArgumentException("Un compte contient déjà cette adresse mail");
            }
        }
        System.out.println("Definir un mot de passe (12 caracteres minimum, 1 majuscule, 1 minuscule, 1 chiffre, 1 caractere speciale):");
        String mdp = sc.nextLine();
        if (mdp == null || mdp.trim().isEmpty() || !mdp.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&,.#:;\\-_/])[A-Za-z\\d@$!%*?&,.#:;\\-_/]{12,}$")) {
            throw new IllegalArgumentException("Mot de passe invalide");
        }

        System.out.println("Veuillez entrer vos informations personnelles");
        System.out.println("Nom :");
        String nom_cl = sc.nextLine();
        if (nom_cl == null || nom_cl.trim().isEmpty() || !nom_cl.matches("^[A-Za-zÀ-ÖØ-öø-ÿ]+([ '-][A-Za-zÀ-ÖØ-öø-ÿ]+)*$")) {
            throw new IllegalArgumentException("Nom invalide");
        }
        System.out.println("Prenom :");
        String prenom_cl = sc.nextLine();
        if (prenom_cl == null || prenom_cl.trim().isEmpty() || !prenom_cl.matches("^[A-Za-zÀ-ÖØ-öø-ÿ]+([-'][A-Za-zÀ-ÖØ-öø-ÿ]+)*$")) {
            throw new IllegalArgumentException("Prenom invalide");
        }
        System.out.println("Date de naissance (dd-MM-yyyy) :");
        String date = sc.nextLine();
        if (date == null || date.trim().isEmpty() || !date.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
            throw new IllegalArgumentException("Format de date invalide (dd-MM-yyyy attendu)");
        }
        LocalDate date_naissance = LocalDate.parse(date, format);

        System.out.println("Numero de telephone :");
        String numero_tel = sc.nextLine();
        if (numero_tel == null || numero_tel.trim().isEmpty() || !numero_tel.matches("^\\d{10}$")) {
            throw new IllegalArgumentException("Numero de telephone invalide");
        }
        System.out.println("Adresse :");
        String adresse_cl = sc.nextLine();
        if (adresse_cl == null || adresse_cl.trim().isEmpty() || !adresse_cl.matches("^[0-9A-Za-zÀ-ÖØ-öø-ÿ'’ .,-]+$")) {
            throw new IllegalArgumentException("Addresse invalide");
        }

        System.out.println("Veuillez selectionner un type d'abonnement : 1-trimestriel 2-semestriel 3-annuel");
        int rep = sc.nextInt();
        sc.nextLine();
        if (rep != 1 && rep != 2 && rep != 3) {
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

        int ID_cl = clientdapres++;

        LinkedList<Cours> listeCours_passe = new LinkedList<>();
        LinkedList<Cours> listeCours_futur = new LinkedList<>();

        Client client = new Client(ID_cl, adresse_mail, mdp, nom_cl, prenom_cl, date_naissance,
                numero_tel, adresse_cl, type_ab, etat_ab, listeCours_passe, listeCours_futur);
        listeClient.add(client);

    }

    public void Consulter_infos(Client cl) {
        cl.affichage_infos();
    }

    public void Modifier_infos_client(Client cl) throws IllegalArgumentException {
        System.out.println("Que souhaitez-vous modifier : 1-Nom 2-Prenom 3-Date de naissance 4-Numero de telephone 5-Addresse 6-Type d'abonnement");
        int choix = sc.nextInt();
        sc.nextLine();
        if (choix != 1 && choix != 2 && choix != 3 && choix != 4 && choix != 5 && choix != 6) {
            throw new IllegalArgumentException("Reponse invalide");
        }
        switch (choix) {
            case 1 -> {
                System.out.println("Entrer un nouveau Nom :");
                String new_nom = sc.nextLine();
                if (new_nom == null || new_nom.trim().isEmpty() || !new_nom.matches("^[A-Za-zÀ-ÖØ-öø-ÿ]+([ '-][A-Za-zÀ-ÖØ-öø-ÿ]+)*$")) {
                    throw new IllegalArgumentException("Nom invalide");
                }
                System.out.println(cl.modifier_nom(new_nom));
                break;
            }
            case 2 -> {
                System.out.println("Entrer un nouveau Prenom :");
                String new_prenom = sc.nextLine();
                if (new_prenom == null || new_prenom.trim().isEmpty() || !new_prenom.matches("^[A-Za-zÀ-ÖØ-öø-ÿ]+([-'][A-Za-zÀ-ÖØ-öø-ÿ]+)*$")) {
                    throw new IllegalArgumentException("Prenom invalide");
                }
                System.out.println(cl.modifier_prenom(new_prenom));
                break;
            }
            case 3 -> {
                System.out.println("Entrer une nouvelle Date de naissance (dd-MM-yyyy):");
                String new_date = sc.nextLine();
                if (new_date == null || new_date.trim().isEmpty() || !new_date.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
                    throw new IllegalArgumentException("Format de date invalide (dd-MM-yyyy attendu)");
                }
                LocalDate new_date_naissance = LocalDate.parse(new_date, format);
                System.out.println(cl.modifier_date_naissance(new_date_naissance));
                break;
            }
            case 4 -> {
                System.out.println("Entrer un nouveau Numero de telephone :");
                String new_num_tel = sc.nextLine();
                if (new_num_tel == null || new_num_tel.trim().isEmpty() || !new_num_tel.matches("^\\d{10}$")) {
                    throw new IllegalArgumentException("Numero de telephone invalide");
                }
                System.out.println(cl.modifier_numero_telephone(new_num_tel));
                break;
            }
            case 5 -> {
                System.out.println("Entrer une nouvelle Adresse :");
                String new_adresse = sc.nextLine();
                if (new_adresse == null || new_adresse.trim().isEmpty() || !new_adresse.matches("^[0-9A-Za-zÀ-ÖØ-öø-ÿ'’ .,-]+$")) {
                    throw new IllegalArgumentException("Addresse invalide");
                }
                System.out.println(cl.modifier_adresse(new_adresse));
                break;
            }
            case 6 -> {
                System.out.println("Selectionner un nouveau Type d'abonnement : 1-trimestriel 2-semestriel 3-annuel");
                int i = sc.nextInt();
                sc.nextLine();
                if (i != 1 && i != 2 && i != 3) {
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

    public void Inscription_client(Client cl, Cours co) throws DejaInscritException {
        try {
            co.ajouter_inscription(cl);
            cl.ajouterCours_listeFutur(co);
            System.out.println("Votre inscription au cours " + co.getNom_cours() + " a bien ete enregistre.");
        } catch (DejaInscritException e) {
            e.getMessage();
        }
    }

    public void Desincription_client(Client cl, Cours co) {
        co.retirer_inscription(cl);
        cl.retirerCours_listeFutur(co);
    }

    public void Consulter_listeCours_futur(Client cl) {
        cl.affichage_listeFutur();
    }

    public void Consulter_listeCours_passe(Client cl) {
        cl.affichage_listePasse();
    }

    public void Consulter_listeClient() {
        for (Client cl : listeClient) {
            System.out.println(cl.affichage_liste());
        }
    }

    public Client Rechercher_client_ID(int ID) throws UserNotFoundException {
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

    public Client Rechercher_client_nom(String nom) throws UserNotFoundException {
        if (nom == null || nom.trim().isEmpty() || !nom.matches("^[A-Za-zÀ-ÖØ-öø-ÿ]+([ '-][A-Za-zÀ-ÖØ-öø-ÿ]+)*$")) {
            throw new IllegalArgumentException("Nom invalide");
        }
        for (Client cl : listeClient) {
            if (cl.getnom_client().equalsIgnoreCase(nom)) {
                return cl;
            }
        }
        throw new UserNotFoundException("Nom de client " + nom + " introuvable.");
    }

    public Client Rechercher_client_email(String email) throws UserNotFoundException {
        if (email == null || email.trim().isEmpty() || !email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Adresse mail invalide");
        }
        for (Client cl : listeClient) {
            if (cl.getemail().equalsIgnoreCase(email)) {
                return cl;
            }
        }
        System.out.println("adresse email " + email + " introuvable");
        return null;
    }

    public Client Rechercher_client_telephone(String telephone) throws UserNotFoundException {
        if (telephone == null || telephone.trim().isEmpty() || !telephone.matches("^\\d{10}$")) {
            throw new IllegalArgumentException("Numero de telephone invalide");
        }
        for (Client cl : listeClient) {
            if (cl.getTel_client().equalsIgnoreCase(telephone)) {
                return cl;
            }
        }
        System.out.println("numero de telephone " + telephone + " introuvable");
        return null;
    }

    public void Desactiver_abonnement(String nom) throws UserNotFoundException {
        Client cl = this.Rechercher_client_nom(nom);

        System.out.println(cl.modifier_etat_abonnement());
    }

    public void Reactiver_abonnement(String nom) throws UserNotFoundException {
        Client cl = this.Rechercher_client_nom(nom);
        System.out.println(cl.modifier_etat_abonnement());
    }

    public void Ajouter_cours() throws IllegalArgumentException {
        System.out.println("Entrer le nom du cours :");
        String nom_co = sc.nextLine();
        if (nom_co == null || nom_co.trim().isEmpty() || !nom_co.matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9'’()\\- ]{3,50}$")) {
            throw new IllegalArgumentException("Nom invalide");
        }
        System.out.println("Entrer le nombre de places :");
        int nbre_place = sc.nextInt();
        sc.nextLine();
        if (nbre_place <= 0) {
            throw new IllegalArgumentException("Nombre de place invalide");
        }
        System.out.println("Selectionner le type de cours : 1-Individuel 2-Collectif");
        int choix = sc.nextInt();
        sc.nextLine();
        if (choix != 1 && choix != 2) {
            throw new IllegalArgumentException("Reponse invalide");
        }
        String type_co = " ";
        if (choix == 1) {
            type_co = "individuel";
        } else {
            type_co = "collectif";
        }
        System.out.println("Entrer la date du cours (dd-MM-yyyy):");
        String date = sc.nextLine();
        if (date == null || date.trim().isEmpty() || !date.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
            throw new IllegalArgumentException("Format de date invalide (dd-MM-yyyy attendu)");
        }
        LocalDate date_co = LocalDate.parse(date, format);
        System.out.println("Entrer la duree du cours :");
        int duree_co = sc.nextInt();
        sc.nextLine();
        if (duree_co <= 0) {
            throw new IllegalArgumentException("Duree invalide");
        }

        int ID_co = listeCours.size() + 1;

        Cours cours = new Cours(ID_co, nom_co, nbre_place, type_co, date_co, duree_co);
        listeCours.add(cours);
    }

    public void Supprimer_cours(Cours co) {
        listeCours.remove(co);
    }

    public void Consulter_listeCours() {
        for (Cours co : listeCours) {
            System.out.println(co.affichage_liste());
        }
    }

    public Cours Rechercher_cours_ID(int ID) throws UserNotFoundException {
        if (ID <= 0) {
            throw new IllegalArgumentException("ID invalide");
        }
        for (Cours co : listeCours) {
            if (co.getID_cours() == ID) {
                return co;
            }
        }
        throw new UserNotFoundException("Cours avec ID " + ID + " introuvable.");
    }

    public Cours Rechercher_cours_nom(String nom) throws UserNotFoundException {
        if (nom == null || nom.trim().isEmpty() || !nom.matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9'’()\\- ]{3,50}$")) {
            throw new IllegalArgumentException("Nom invalide");
        }
        for (Cours co : listeCours) {
            if (co.getNom_cours().equalsIgnoreCase(nom)) {
                return co;
            }
        }
        throw new UserNotFoundException("Nom de cours " + nom + " introuvable.");
    }

    public Cours Rechercher_cours_date(LocalDate date) throws UserNotFoundException {
        String date_recherche = date.format(format);
        if (date_recherche == null || date_recherche.trim().isEmpty() || !date_recherche.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
            throw new IllegalArgumentException("Format de date invalide (dd-MM-yyyy attendu)");
        }
        for (Cours co : listeCours) {
            if (co.getDate_cours().equals(date)) {
                return co;
            }
        }
        throw new UserNotFoundException("Aucun cours à la date " + nom + " prévu.");
    }

    public void Modifier_infos_cours(Cours co) throws IllegalArgumentException {
        System.out.println("Que souhaitez-vous modifier : 1-Nom 2-Nombre de place 3-Type de cours 4-Date 5-Duree");
        int choix = sc.nextInt();
        sc.nextLine();
        if (choix != 1 && choix != 2 && choix != 3 && choix != 4 && choix != 5) {
            throw new IllegalArgumentException("Reponse invalide");
        }
        switch (choix) {
            case 1 -> {
                System.out.println("Entrer un nouveau Nom :");
                String new_nom = sc.nextLine();
                if (new_nom == null || new_nom.trim().isEmpty() || !new_nom.matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9'’()\\- ]{3,50}$")) {
                    throw new IllegalArgumentException("Nom invalide");
                }
                System.out.println(co.modifier_nom(new_nom));
                break;
            }
            case 2 -> {
                System.out.println("Entrer un nouveau Nombre de place :");
                int new_nbre = sc.nextInt();
                sc.nextLine();
                if (new_nbre <= 0) {
                    throw new IllegalArgumentException("Nombre de place invalide");
                }
                System.out.println(co.modifier_nbrePlace(new_nbre));
                break;
            }
            case 3 -> {
                System.out.println("Selectionner un nouveau type de cours : 1-Individuel 2-Collectif");
                int i = sc.nextInt();
                sc.nextLine();
                if (i != 1 && choix != 2) {
                    throw new IllegalArgumentException("Reponse invalide");
                }
                String type_co = "";
                if (i == 1) {
                    type_co = "individuel";
                } else {
                    type_co = "collectif";
                }
                System.out.println(co.modifier_typeCours(type_co));
                break;
            }
            case 4 -> {
                System.out.println("Entrer une nouvelle Date (dd-MM-yyyy):");
                String new_date = sc.nextLine();
                if (new_date == null || new_date.trim().isEmpty() || !new_date.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
                    throw new IllegalArgumentException("Format de date invalide (dd-MM-yyyy attendu)");
                }
                LocalDate new_date_co = LocalDate.parse(new_date, format);
                System.out.println(co.modifier_date(new_date_co));
                break;
            }
            case 5 -> {
                System.out.println("Entrer une nouvelle Duree :");
                int new_duree = sc.nextInt();
                sc.nextLine();
                if (new_duree <= 0) {
                    throw new IllegalArgumentException("Duree invalide");
                }
                System.out.println(co.modifier_duree(new_duree));
                break;
            }
        }
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
        fichCo.close();
    }

    public void charger() throws FileNotFoundException, IOException, DejaInscritException {
        boolean fichierTrouve1 = false;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (fichierTrouve1 == false) {
            try {
                FileReader fichCl = new FileReader(FICHIER_CLIENTS);
                BufferedReader br = new BufferedReader(fichCl);
                String ligne;
                while ((ligne = br.readLine()) != null) {

                    if (ligne == null) {
                        break;
                    }
                    String[] tab = ligne.split(";"); //supprime espaces inutiles

                    int numero_cl = Integer.parseInt(tab[0]);
                    if (numero_cl >= clientdapres) {
                        clientdapres = numero_cl + 1;
                    }
                    String addresse_mail = tab[1];
                    String mdp = tab[2];
                    String nom_cl = tab[3];
                    String prenom_cl = tab[4];
                    LocalDate date_naissance = LocalDate.parse(tab[5], format);
                    String numero_tel = tab[6];
                    String addresse_cl = tab[7];
                    String type_ab = tab[8];
                    String etat_ab = tab[9];

                    LinkedList<Cours> listeCours_passe = new LinkedList<>();

                    if (tab.length > 10 && !tab[10].trim().isEmpty()) {

                        String[] tab2 = tab[10].split("\\|");

                        for (int i = 0; i < tab2.length; i++) {

                            String[] tab3 = tab2[i].split(",");
                            int ID_co = Integer.parseInt(tab3[0]);
                            String nom_co = tab3[1];
                            int nbre_place = Integer.parseInt(tab3[2]);
                            String type_co = tab3[3];
                            LocalDate date_co = LocalDate.parse(tab3[4]);
                            int duree_co = Integer.parseInt(tab3[5]);

                            Cours co = new Cours(ID_co, nom_co, nbre_place, type_co, date_co, duree_co);
                            listeCours_passe.add(co);
                        }
                    }

                    LinkedList<Cours> listeCours_futur = new LinkedList<>();

                    if (tab.length > 11 && !tab[11].trim().isEmpty()) {
                        String[] tab4 = tab[11].split("\\|");
                        for (int i = 0; i < tab4.length; i++) {
                            String[] tab5 = tab4[i].split(",");
                            int ID_co = Integer.parseInt(tab5[0]);
                            String nom_co = tab5[1];
                            int nbre_place = Integer.parseInt(tab5[2]);
                            String type_co = tab5[3];
                            LocalDate date_co = LocalDate.parse(tab5[4]);
                            int duree_co = Integer.parseInt(tab5[5]);

                            Cours co = new Cours(ID_co, nom_co, nbre_place, type_co, date_co, duree_co);
                            listeCours_futur.add(co);
                        }
                    }
                    Client cl = new Client(numero_cl, addresse_mail, mdp, nom_cl, prenom_cl, date_naissance, numero_tel,
                            addresse_cl, type_ab, etat_ab, listeCours_passe, listeCours_futur);
                    listeClient.add(cl);
                }
                br.close();
                fichierTrouve1 = true;
            } catch (FileNotFoundException ex) {
                System.out.println("creation du fichier des clients");
                break;
            }
        }

        boolean fichierTrouve2 = false;
        while (fichierTrouve2 == false) {
            try {
                FileReader fichCo = new FileReader(FICHIER_COURS);
                BufferedReader br2 = new BufferedReader(fichCo);
                String ligne = br2.readLine();
                while (ligne != null) {
                    String[] tab = ligne.trim().split(",");

                    int ID_co = Integer.parseInt(tab[0]);
                    String nom_co = tab[1];
                    int nbre_place = Integer.parseInt(tab[2]);
                    String type_co = tab[3];
                    LocalDate date_co = LocalDate.parse(tab[4], format);
                    int duree_co = Integer.parseInt(tab[5]);

                    Cours co = new Cours(ID_co, nom_co, nbre_place, type_co, date_co, duree_co);
                    listeCours.add(co);
                }
                br2.close();
                fichierTrouve2 = true;
            } catch (FileNotFoundException ex) {
                System.out.println("creation du fichier des cours");
                break;
            }
        }
        for (Cours co : listeCours) {
            for (Client cl : listeClient) {
                if (cl.getlistePasse_client().contains(co) || cl.getlisteFutur_client().contains(co)) {
                    co.ajouter_inscription(cl);
                }
            }
        }
    }

    public void actualiser() {
        for (Client cl : listeClient) {
            for (Cours co : cl.getlisteFutur_client()) {
                if (co.verification_date() == true) {
                    cl.retirerCours_listeFutur(co);
                    cl.ajouterCours_listePasse(co);
                }
            }
        }
    }
}
