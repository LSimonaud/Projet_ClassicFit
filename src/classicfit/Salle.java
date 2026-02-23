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

    public Utilisateur seConnecter(String email, String mdp) throws UserNotFoundException {
        if (email == null || email.trim().isEmpty() || !email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Adresse mail invalide");
        }
        if (mdp == null || mdp.trim().isEmpty()) {
            throw new IllegalArgumentException("Mot de passe invalide");
        }

        if (admin.getemail().equals(email) && admin.getmdp().equals(mdp)) {
            return admin;
        } else {
            for (Client cl : listeClient) {
                if (cl.getemail().equals(email) && cl.getmdp().equals(mdp)) {
                    System.out.println("bienvenue cher client !");
                    return cl;
                }
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

        System.out.println("Entrer une adresse mail :");
        String adresse_mail = sc.nextLine();
        if (adresse_mail == null || adresse_mail.trim().isEmpty() || !adresse_mail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Adresse mail invalide");
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

        int ID_cl = listeClient.size() + 1;

        Client client = new Client(adresse_mail, mdp, nom_cl, prenom_cl, date_naissance,
                numero_tel, adresse_cl, type_ab, etat_ab, ID_cl);
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
                if (new_date == null || new_date.trim().isEmpty() || !new_date.matches("^\\d{2}-\\d{2}-\\d{4}$")){
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
    
    public void Desactiver_abonnement(String nom) throws UserNotFoundException{
        Client cl = this.Rechercher_client_nom(nom);
        System.out.println(cl.modifier_etat_abonnement());
    }
    
    public void Reactiver_abonnement(String nom) throws UserNotFoundException{
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
        if (nbre_place<=0){
            throw new IllegalArgumentException("Nombre de place invalide");
        }
        System.out.println("Selectionner le type de cours : 1-Individuel 2-Collectif");
        int choix = sc.nextInt();
        sc.nextLine();
        if (choix!=1 || choix!=2){
            throw new IllegalArgumentException("Reponse invalide");
        }
        String type_co = " ";
        if (choix == 1){
            type_co = "individuel";
        }else{
            type_co = "collectif";
        }
        System.out.println("Entrer la date du cours (dd-MM-yyyy):");
        String date = sc.nextLine();
        if (date == null || date.trim().isEmpty() || !date.matches("^\\d{2}-\\d{2}-\\d{4}$")){
            throw new IllegalArgumentException("Format de date invalide (dd-MM-yyyy attendu)");
        }
        LocalDate date_co = LocalDate.parse(date, format);
        System.out.println("Entrer la duree du cours :");
        int duree_co = sc.nextInt();
        sc.nextLine();
        if (duree_co<=0){
            throw new IllegalArgumentException("Duree invalide");
        }
        
        int ID_co = listeCours.size() + 1;
        
        Cours cours = new Cours(nom_co,nbre_place,type_co,date_co,duree_co,ID_co);
        listeCours.add(cours);
    }
    
    public void Consulter_listeCours(){
        for (Cours co : listeCours){
            System.out.println(co.affichage_liste());
        }
    }
    
    public void sauvegarder() throws IOException {
        String sep = System.lineSeparator();

        FileWriter fichCl = new FileWriter(FICHIER_CLIENTS);
        for (Client cl : listeClient) {
            fichCl.write(cl.toString());
            fichCl.write(sep);
        }        

        FileWriter fichCo = new FileWriter(FICHIER_COURS);
        for (Cours co : listeCours) {
            fichCo.write(co.toString());
            fichCo.write(sep);
        }
        
        fichCl.close();
        fichCo.close();
    }

    public void charger() throws FileNotFoundException, IOException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        boolean fichierTrouve = false;
        while(fichierTrouve == false){
            try{
        FileReader fichCl = new FileReader(FICHIER_CLIENTS);
        BufferedReader br = new BufferedReader(fichCl);
        String ligne = br.readLine();       
  
          
            String[] tab = ligne.trim().split("\\s*:\\s*"); //supprime espaces inutiles
                   
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
            br.close();
            fichierTrouve = true;
            }catch(FileNotFoundException ex){
                System.out.println("fichier introuvable");
                break;
            }
        }
        

      /*  FileReader fichCo = new FileReader(FICHIER_COURS);
        BufferedReader br2 = new BufferedReader(fichCo);
        String ligne = br2.readLine();
        while (ligne != null) {
            String[] tab = ligne.split(";");
        }
        fichCo.close(); */
    }
}
