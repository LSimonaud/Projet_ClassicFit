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
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    private LinkedList<Cours> listeCours; //liste des cours passés et futurs de la salle
    private LinkedList<Client> listeClient; //liste de tous les clients de la salle
    private LinkedList<Activite> listeActivite; //liste des activites de la salle

    public static final String FICHIER_CLIENTS = "Fichier_clients.txt"; //Fichier de sauvegarde des clients
    public static final String FICHIER_COURS = "Fichier_cours.txt"; //Fichier de sauvegarde des cours
    public static final String FICHIER_ACTIVITES = "Fichier_activites.txt"; //Fichier de sauvegarde des activites

    Scanner sc = new Scanner(System.in);
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Salle(String nom, Administrateur admin) {
        this.nom = nom;
        this.admin = admin;

        //Initialisation des listes
        this.listeCours = new LinkedList<>();
        this.listeClient = new LinkedList<>();
        this.listeActivite = new LinkedList<>();
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
            if (cl.getemail().equals(email) && cl.getmdp().equals(mdp) && cl.getEtat_abonnement().equalsIgnoreCase("actif")) {
                return "client autorise";
            }
            if (cl.getemail().equals(email) && cl.getmdp().equals(mdp) && cl.getEtat_abonnement().equalsIgnoreCase("inactif")) {
                return "client interdit";
            }
        }
        throw new UserNotFoundException("Email ou mot de passe incorrect");

    }

    public void Modifier_addresseMail(Client cl) {
        System.out.println("Entrer une nouvelle addresse mail :");
        String new_email = sc.nextLine();
        if (new_email == null || new_email.trim().isEmpty() || !new_email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Adresse mail invalide");
        }
        cl.modifier_addresseMail(new_email);
    }

    public void Modifier_mdp(String mdp) throws IllegalArgumentException {
        System.out.println("Entrez un nouveau mot de passe (12 caracteres minimum, 1 majuscule, 1 minuscule, 1 chiffre, 1 caractere speciale):");
        String new_mdp = sc.nextLine();
        if (new_mdp == null || new_mdp.trim().isEmpty() || !new_mdp.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&,.#:;\\-_/])[A-Za-z\\d@$!%*?&,.#:;\\-_/]{12,}$")) {
            throw new IllegalArgumentException("Mot de passe invalide");
        }
        String new_mdpConfirm = " ";
        while (!new_mdpConfirm.equals(new_mdp)) {
            System.out.println("Confirmer le nouveau mot de passe :");
            new_mdpConfirm = sc.nextLine();
        }
        try {
            Client cl = this.Rechercher_client_mdp(mdp);
            cl.modifier_mdp(new_mdp);
        } catch (UserNotFoundException e) {
            e.getMessage();
            System.out.println("Veuillez reesayer");
        }
    }

    public Client Creer_compte() throws IllegalArgumentException {
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
        LocalDate date_naissance = verifierDateNaissance(date, format);

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
        return client;

    }

    public static LocalDate verifierDateNaissance(String dateStr, DateTimeFormatter format) {
        //Vérification date correcte
        LocalDate date;
        try {
            date = LocalDate.parse(dateStr, format);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La date entree n'existe pas.");
        }

        // Vérifications logiques
        LocalDate aujourdHui = LocalDate.now();
        if (date.isAfter(aujourdHui)) {
            throw new IllegalArgumentException("La date ne peut pas etre dans le futur.");
        }

        if (date.isBefore(LocalDate.of(1900, 1, 1))) {
            throw new IllegalArgumentException("La date est trop ancienne.");
        }

        if (Period.between(date, aujourdHui).getYears() > 120) {
            throw new IllegalArgumentException("L'age depasse 120 ans.");
        }
        return date;
    }

    public static LocalDate verifierDateCours(String dateStr, DateTimeFormatter format) {
        //Vérification date correcte
        LocalDate date;
        try {
            date = LocalDate.parse(dateStr, format);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("La date entree n'existe pas.");
        }

        // Vérifications logiques
        LocalDate aujourdHui = LocalDate.now();
        if (date.isBefore(aujourdHui)) {
            throw new IllegalArgumentException("La date ne peut pas etre dans le passe.");
        }
        return date;
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
                LocalDate new_date_naissance = verifierDateNaissance(new_date, format);
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
                        System.out.println(cl.modifier_abonnement(new_type_ab));
                        break;
                    }
                    case 2 -> {
                        String new_type_ab = "semestriel";
                        System.out.println(cl.modifier_abonnement(new_type_ab));
                        break;
                    }
                    case 3 -> {
                        String new_type_ab = "annuel";
                        System.out.println(cl.modifier_abonnement(new_type_ab));
                        break;
                    }
                }
                break;
            }
        }
    }

    public void Inscription_client(Client cl, Cours co) {
        if (co.getListeInscrit_cours().size() == co.getNbrePlace_cours()) {
            System.out.println("Ce cours est complet");
        } else {
            try {
                co.ajouter_inscription(cl);
                cl.ajouterCours_listeFutur(co);
                System.out.println("Votre inscription au cours " + co.getNom_cours() + " a bien ete enregistre.");
            } catch (DejaInscritException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void Desincription_client(Client cl, Cours co) {
        if (cl.getlisteFutur_client().contains(co)) {
            System.out.println("Etes-vous certain de vouloir vous desinscrire ?");
            String rep = sc.nextLine();
            if (rep.toLowerCase().equalsIgnoreCase("oui")) {
                co.retirer_inscription(cl);
                cl.retirerCours_listeFutur(co);
                System.out.println("Vous avez bien ete desinscrit du cours " + co.getNom_cours());
            } else {
                System.out.println("Desinscription annule");
            }
        } else {
            System.out.println("Vous n'etes pas inscrit a ce cours");
        }
    }

    public void Consulter_listeCours_futur(Client cl) {
        if (cl.getlisteFutur_client().isEmpty()) {
            System.out.println("Vous n'avez pas de prochains cours");
        } else {
            System.out.println("Liste de vos prochains cours :");
            cl.affichage_listeFutur();
        }
    }

    public void Consulter_listeCours_passe(Client cl) {
        if (cl.getlistePasse_client().isEmpty()) {
            System.out.println("Vous n'avez participe a aucun cours");
        } else {
            System.out.println("Liste de vos cours precedents :");
            cl.affichage_listePasse();
        }
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

    public LinkedList<Client> Rechercher_client_nom(String nom) throws UserNotFoundException {
        if (nom == null || nom.trim().isEmpty() || !nom.matches("^[A-Za-zÀ-ÖØ-öø-ÿ]+([ '-][A-Za-zÀ-ÖØ-öø-ÿ]+)*$")) {
            throw new IllegalArgumentException("Nom invalide");
        }
        LinkedList<Client> liste = new LinkedList<>();
        for (Client cl : listeClient) {
            if (cl.getnom_client().equalsIgnoreCase(nom)) {
                liste.add(cl);
            }
        }
        if (liste == null) {
            throw new UserNotFoundException("Nom de client " + nom + " introuvable.");
        }
        return liste;
    }

    public Client Rechercher_client_mdp(String mdp) throws UserNotFoundException {
        if (mdp == null || mdp.trim().isEmpty() || !mdp.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&,.#:;\\-_/])[A-Za-z\\d@$!%*?&,.#:;\\-_/]{12,}$")) {
            throw new IllegalArgumentException("Mot de passe invalide");
        }
        for (Client cl : listeClient) {
            if (cl.getmdp().equalsIgnoreCase(mdp)) {
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

    public void Modifier_etatAbonnement(int ID) throws UserNotFoundException {
        Client cl = this.Rechercher_client_ID(ID);
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
        LocalDate date_co = verifierDateCours(date, format);
        System.out.println("Entrer la duree du cours en minutes:");
        int duree_co = sc.nextInt();
        sc.nextLine();
        if (duree_co <= 0) {
            throw new IllegalArgumentException("Duree invalide");
        }

        int ID_co = listeCours.size() + 1;

        Cours cours = new Cours(ID_co, nom_co, nbre_place, type_co, date_co, duree_co);
        listeCours.add(cours);
    }

    public String Supprimer_cours(Cours co) {
        if (co.getListeInscrit_cours().isEmpty()) {
            System.out.println("Etes-vous certain de vouloir supprimer le cours " + co.getNom_cours() + " ? (oui/non)");
            String rep = sc.nextLine();
            if (rep.toLowerCase().equalsIgnoreCase("oui")) {
                listeCours.remove(co);
                return "Le cours " + co.getNom_cours() + " a bien ete supprime";
            } else {
                return "Operation annulee";
            }
        } else {
            return "Vous ne pouvez pas supprimer ce cours";
        }
    }

    public LinkedList<Cours> Consulter_listeCours() {
        return this.listeCours;
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

    public LinkedList<Cours> Rechercher_cours_nom(String nom) throws UserNotFoundException {
        if (nom == null || nom.trim().isEmpty() || !nom.matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9'’()\\- ]{3,50}$")) {
            throw new IllegalArgumentException("Nom invalide");
        }
        LinkedList<Cours> liste = new LinkedList<>();
        for (Cours co : listeCours) {
            if (co.getNom_cours().equalsIgnoreCase(nom)) {
                liste.add(co);
            }
        }
        if (liste == null) {
            throw new UserNotFoundException("Nom de cours " + nom + " introuvable.");
        }
        return liste;
    }

    public LinkedList<Cours> Rechercher_cours_date(LocalDate date) throws UserNotFoundException {
        String date_recherche = date.format(format);
        if (date_recherche == null || date_recherche.trim().isEmpty() || !date_recherche.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
            throw new IllegalArgumentException("Format de date invalide (dd-MM-yyyy attendu)");
        }
        LinkedList<Cours> liste = new LinkedList<>();
        for (Cours co : listeCours) {
            if (co.getDate_cours().equals(date)) {
                liste.add(co);
            }
        }
        if (liste == null) {
            throw new UserNotFoundException("Aucun cours à la date " + nom + " prévu.");
        }
        return liste;
    }

    public void Modifier_infos_cours(Cours co) throws IllegalArgumentException {
        if (co.getListeInscrit_cours().isEmpty()) {
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
                    LocalDate new_date_co = verifierDateCours(new_date, format);
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
        } else {
            System.out.println("Vous ne pouvez pas modifier ce cours");
        }
    }

    public String Consulter_coursPopulaire() {
        LinkedList<Cours> listeCoursPopulaire = new LinkedList<>();
        for (Cours co : listeCours) {
            if ((co.getListeInscrit_cours().size() / co.getNbrePlace_cours()) >= 0.8) {
                listeCoursPopulaire.add(co);
            }
        }
        if (listeCoursPopulaire.isEmpty()) {
            System.out.println("Il n'y a aucun cours populaire");
            return "vide";
        } else {
            for (Cours co : listeCoursPopulaire) {
                System.out.println(co.affichage_listeAdmin());
            }
            return "pleine";
        }
    }

    public String Consulter_coursImpopulaire() {
        LinkedList<Cours> listeCoursImpopulaire = new LinkedList<>();
        for (Cours co : listeCours) {
            if ((co.getListeInscrit_cours().size() / co.getNbrePlace_cours()) <= 0.2) {
                listeCoursImpopulaire.add(co);
            }
        }
        if (listeCoursImpopulaire.isEmpty()) {
            System.out.println("Il n'y a aucun cours impopulaire");
            return "vide";
        } else {
            for (Cours co : listeCoursImpopulaire) {
                System.out.println(co.affichage_listeAdmin());
            }
            return "pleine";
        }
    }

    public void Ajouter_activite() throws IllegalArgumentException {
        System.out.println("Entrer le nom de l'activite :");
        String nom = sc.nextLine();
        if (nom == null || nom.trim().isEmpty() || !nom.matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9]+([ '-][A-Za-zÀ-ÖØ-öø-ÿ0-9]+)*$")) {
            throw new IllegalArgumentException("Nom invalide");
        }
        System.out.println("Entrer la description de l'activite :");
        String description = sc.nextLine();
        if (description == null || description.trim().isEmpty() || !description.matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9 ,.!?;:'\"()\\-]+$")) {
            throw new IllegalArgumentException("Description invalide");
        }

        int ID = listeActivite.size() + 1;

        Activite a = new Activite(ID, nom, description);
        listeActivite.add(a);
    }

    public String Supprimer_activite(Activite a) {
        System.out.println("Etes-vous certain de vouloir supprimer l'activite " + a.getNom_activite() + " ? (oui/non)");
        String rep = sc.nextLine();
        if (rep.toLowerCase().equalsIgnoreCase("oui")) {
            listeActivite.remove(a);
            return "L'activite " + a.getNom_activite() + " a bien ete supprime";
        } else {
            return "Operation annulee";
        }
    }

    public Activite Rechercher_activite_ID(int ID) throws UserNotFoundException {
        if (ID <= 0) {
            throw new IllegalArgumentException("ID invalide");
        }
        for (Activite a : listeActivite) {
            if (a.getID_activite() == ID) {
                return a;
            }
        }
        throw new UserNotFoundException("Activite avec ID " + ID + " introuvable.");
    }

    public LinkedList<Activite> Rechercher_nom_activite(String nom) throws UserNotFoundException {
        if (nom == null || nom.trim().isEmpty() || !nom.matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9]+([ '-][A-Za-zÀ-ÖØ-öø-ÿ0-9]+)*$")) {
            throw new IllegalArgumentException("Nom invalide");
        }
        LinkedList<Activite> liste = new LinkedList<>();
        for (Activite a : listeActivite) {
            if (a.getNom_activite().equalsIgnoreCase(nom)) {
                liste.add(a);
            }
        }
        if (liste == null) {
            throw new UserNotFoundException("Nom de l'activite " + nom + " introuvable.");
        }
        return liste;
    }

    public void Modifier_infos_activite(Activite a) throws IllegalArgumentException {
        System.out.println("Que souhaitez-vous modifier : 1-Nom 2-Description");
        int choix = sc.nextInt();
        sc.nextLine();
        if (choix != 1 && choix != 2) {
            throw new IllegalArgumentException("Reponse invalide");
        }
        switch (choix) {
            case 1 -> {
                System.out.println("Entrer un nouveau Nom :");
                String new_nom = sc.nextLine();
                if (new_nom == null || new_nom.trim().isEmpty() || !new_nom.matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9]+([ '-][A-Za-zÀ-ÖØ-öø-ÿ0-9]+)*$")) {
                    throw new IllegalArgumentException("Nom invalide");
                }
                System.out.println(a.modifier_nom(new_nom));
                break;
            }
            case 2 -> {
                System.out.println("Entrer une nouvelle Description :");
                String new_description = sc.nextLine();
                if (new_description == null || new_description.trim().isEmpty() || !new_description.matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9 ,.!?;:'\"()\\-]+$")) {
                    throw new IllegalArgumentException("Description invalide");
                }
                System.out.println(a.modifier_description(new_description));
                break;
            }
        }
    }

    public void Consulter_listeActivite() {
        System.out.println("Liste des activites :");
        for (Activite a : listeActivite) {
            System.out.println(a.affichage_liste());
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

        FileWriter fichA = new FileWriter(FICHIER_ACTIVITES);
        for (Activite a : listeActivite) {
            fichA.write(a.toString());
            fichA.write(sep);
        }
        fichCl.close();
    }

    public void charger() throws FileNotFoundException, IOException, DejaInscritException {
        listeClient.clear();
        listeCours.clear();
        listeActivite.clear();

        boolean fichierTrouve1 = false;
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
                            LocalDate date_co = LocalDate.parse(tab3[4], format);
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
                            LocalDate date_co = LocalDate.parse(tab5[4], format);
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
                System.out.println("Creation du fichier des clients");
                break;
            }
        }

        boolean fichierTrouve2 = false;
        while (fichierTrouve2 == false) {
            try {
                FileReader fichCo = new FileReader(FICHIER_COURS);
                BufferedReader br2 = new BufferedReader(fichCo);
                String ligne;
                while ((ligne = br2.readLine()) != null) {
                    if (ligne == null) {
                        break;
                    }
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
                System.out.println("Creation du fichier des cours");
                break;
            }
        }
        for (Cours co : listeCours) {
            for (Client cl : listeClient) {
                if (cl.getlistePasse_client().contains(co) || cl.getlisteFutur_client().contains(co)) {
                    try {
                        co.ajouter_inscription(cl);
                    } catch (DejaInscritException e) {
                        e.getMessage();
                    }
                }
            }
        }

        boolean fichierTrouve3 = false;
        while (fichierTrouve3 == false) {
            try {
                FileReader fichA = new FileReader(FICHIER_ACTIVITES);
                BufferedReader br3 = new BufferedReader(fichA);
                String ligne;
                while ((ligne = br3.readLine()) != null) {
                    if (ligne == null) {
                        break;
                    }
                    String[] tab = ligne.split(";"); //supprime espaces inutiles

                    int ID_a = Integer.parseInt(tab[0]);
                    String nom_a = tab[1];
                    String description_a = tab[2];

                    Activite a = new Activite(ID_a, nom_a, description_a);
                    listeActivite.add(a);
                }
                br3.close();
                fichierTrouve3 = true;
            } catch (FileNotFoundException ex) {
                System.out.println("Creation du fichier des activites");
                break;
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
