/*
Luc SIMONAUD
Philippe-Henri PAUL
Aloïs OVIGNEUR
Projet Salle de Sport
AA
 */
package classicfit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author lucsi
 */
public class ClassicFit {

    public static void main(String[] args) throws UserNotFoundException, IOException, FileNotFoundException, DejaInscritException {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Administrateur a = new Administrateur("admin@epfedu.fr", "Admin123456*", "Inshape", "Tibo");
        Salle s = new Salle("classicfit", a);
        s.charger();

        System.out.println("Bienvenue a classicfit !");
        System.out.println("Etes-vous nouveau chez nous ? (oui/non)");
        String etat = sc.nextLine();
        Boolean decision = true;
        Boolean client = false;
        Client cl = null;
        switch (etat.toLowerCase()) {
            case "oui" -> {
                boolean valide = false;
                while (valide == false) {
                    try {
                        cl = s.Creer_compte();
                        s.sauvegarder();
                        valide = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Veuillez reessayer.");
                    }
                }
                System.out.println("bienvenue cher client !");
                client = true;
                break;
            }

            case "non" -> {
                System.out.println("Veuillez vous connecter");
                boolean b = false;
                while (b == false) {
                    System.out.println("adresse mail :");
                    String mail = sc.nextLine();
                    System.out.println("mot de passe :");
                    String mdp = sc.nextLine();
                    try {
                        switch (s.seConnecter(mail, mdp)) {
                            case "administrateur" -> {
                                System.out.println("bienvenue Tibo !");
                                while (decision == true) {
                                    System.out.println("Menu");
                                    System.out.println("""
                                                       1-Gestion des clients
                                                       2-Gestion des cours
                                                       3-Gestion des activites
                                                       4-Se deconnecter""");
                                    String choix = sc.nextLine();
                                    switch (choix) {
                                        case "1" -> {
                                            Boolean retour = false;
                                            while (retour == false) {
                                                System.out.println("Selctionner une action :");
                                                System.out.println("""
                                                       1-Consulter la liste des clients
                                                       2-Rechercher des clients
                                                       3-Reactiver/Desactiver un abonnement
                                                       4-Retour""");
                                                String action = sc.nextLine();
                                                switch (action) {
                                                    case "1" -> {
                                                        System.out.println("Liste des clients :");
                                                        s.Consulter_listeClient();
                                                        break;
                                                    }
                                                    case "2" -> {
                                                        System.out.println("Par quel moyen veux-tu filtrer ta recherche ? (ID,nom,email,telephone)");
                                                        String filtre = sc.nextLine();
                                                        boolean c = false;
                                                        while (c == false) {
                                                            try {
                                                                if (filtre.equalsIgnoreCase("ID")) {
                                                                    System.out.println("Pour quel ID ?");
                                                                    int ID = sc.nextInt();
                                                                    sc.nextLine();
                                                                    s.Rechercher_client_ID(ID).affichage_infosAdmin();
                                                                }
                                                                if (filtre.equalsIgnoreCase("nom")) {
                                                                    System.out.println("Pour quel nom ?");
                                                                    String nom = sc.nextLine();
                                                                    for (Client CL : s.Rechercher_client_nom(nom)) {
                                                                        CL.affichage_infosAdmin();
                                                                    }
                                                                }
                                                                if (filtre.equalsIgnoreCase("email")) {
                                                                    System.out.println("Pour quel email ?");
                                                                    String email = sc.nextLine();
                                                                    s.Rechercher_client_email(email).affichage_infosAdmin();
                                                                }
                                                                if (filtre.equalsIgnoreCase("telephone")) {
                                                                    System.out.println("Pour quel numero de telephone ?");
                                                                    String tel = sc.nextLine();
                                                                    s.Rechercher_client_telephone(tel).affichage_infosAdmin();
                                                                }
                                                                c = true;
                                                            } catch (IllegalArgumentException f) {
                                                                System.out.println(f.getMessage());
                                                                System.out.println("Veuillez reessayer");
                                                            } catch (UserNotFoundException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Veuillez reessayer");
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case "3" -> {
                                                        boolean d = false;
                                                        while (d == false) {
                                                            System.out.println("Entrer l'ID du client concerne :");
                                                            int ID = sc.nextInt();
                                                            sc.nextLine();
                                                            try {
                                                                System.out.println("Etat actuel de l'abonnement de " + s.Rechercher_client_ID(ID).getnom_client() + ":" + "\n" + s.Rechercher_client_ID(ID).getEtat_abonnement() + "\n");
                                                                System.out.println("Voulez_vous toujours modifier l'etat de cet abonnement ?");
                                                                choix = sc.nextLine().toLowerCase();
                                                                if (choix.equalsIgnoreCase("oui")) {
                                                                    s.Modifier_etatAbonnement(ID);
                                                                    s.sauvegarder();
                                                                }
                                                                d = true;
                                                            } catch (UserNotFoundException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Veuillez reessayer");
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case "4" -> {
                                                        retour = true;
                                                        break;
                                                    }
                                                }
                                            }
                                            break;
                                        }
                                        case "2" -> {
                                            Boolean retour = false;
                                            while (retour == false) {
                                                System.out.println("Choisissez une action :");
                                                System.out.println("""
                                                               1-Consulter la liste des cours
                                                               2-Ajouter un cours
                                                               3-Supprimer un cours
                                                               4-Rechercher un cours
                                                               5-Modifier les informations d'un cours
                                                               6-Consulter la liste des cours populaires
                                                               7-Consulter la liste des cours impopulaires
                                                               8-Retour""");
                                                choix = sc.nextLine();
                                                switch (choix) {
                                                    case "1" -> {
                                                        System.out.println("Liste des cours :");
                                                        for (Cours co : s.Consulter_listeCours()) {
                                                            System.out.println(co.affichage_listeAdmin());
                                                        }
                                                        break;
                                                    }
                                                    case "2" -> {
                                                        Boolean v = false;
                                                        while (v == false) {
                                                            try {
                                                                s.Ajouter_cours();
                                                                s.sauvegarder();
                                                                v = true;
                                                            } catch (IllegalArgumentException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Veuillez reesayer");
                                                            } catch (InputMismatchException e) {
                                                                System.out.println("Format invalide");
                                                                System.out.println("Veuillez reesayer");
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case "3" -> {
                                                        Boolean v = false;
                                                        while (v == false) {
                                                            try {
                                                                System.out.println("Entrer l'ID du cours a supprimer :");
                                                                int ID = sc.nextInt();
                                                                sc.nextLine();
                                                                Cours co = s.Rechercher_cours_ID(ID);
                                                                System.out.println(s.Supprimer_cours(co));
                                                                s.sauvegarder();
                                                                v = true;
                                                            } catch (UserNotFoundException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Veuillez reesayer");
                                                            } catch (IllegalArgumentException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Veuillez reesayer");
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case "4" -> {
                                                        System.out.println("Par quel moyen veux-tu filtrer ta recherche ? (ID,nom,date)");
                                                        String filtre = sc.nextLine().toLowerCase();
                                                        boolean c = false;
                                                        while (c == false) {
                                                            try {
                                                                if (filtre.equalsIgnoreCase("id")) {
                                                                    System.out.println("Pour quel ID ?");
                                                                    int ID = sc.nextInt();
                                                                    sc.nextLine();
                                                                    System.out.println(s.Rechercher_cours_ID(ID).affichage_listeAdmin());
                                                                }
                                                                if (filtre.equalsIgnoreCase("nom")) {
                                                                    System.out.println("Pour quel nom ?");
                                                                    String nom = sc.nextLine();
                                                                    for (Cours co : s.Rechercher_cours_nom(nom)) {
                                                                        System.out.println(co.affichage_listeAdmin());
                                                                    }
                                                                }
                                                                if (filtre.equalsIgnoreCase("date")) {
                                                                    System.out.println("Pour quel date (dd-MM-yyyy)?");
                                                                    String date = sc.nextLine();
                                                                    LocalDate Date = LocalDate.parse(date, format);
                                                                    for (Cours CO : s.Rechercher_cours_date(Date)) {
                                                                        System.out.println(CO.affichage_listeAdmin());
                                                                    }
                                                                }
                                                                c = true;
                                                            } catch (IllegalArgumentException f) {
                                                                System.out.println(f.getMessage());
                                                                System.out.println("Veuillez reessayer");
                                                            } catch (UserNotFoundException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Veuillez reessayer");
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case "5" -> {
                                                        Boolean v = false;
                                                        while (v == false) {
                                                            try {
                                                                System.out.println("Entrer l'ID du cours a modifier :");
                                                                int ID = sc.nextInt();
                                                                sc.nextLine();
                                                                Cours co = s.Rechercher_cours_ID(ID);
                                                                s.Modifier_infos_cours(co);
                                                                s.sauvegarder();
                                                                v = true;
                                                            } catch (IllegalArgumentException f) {
                                                                System.out.println(f.getMessage());
                                                                System.out.println("Veuillez reessayer");
                                                            } catch (UserNotFoundException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Veuillez reessayer");
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case "6" -> {
                                                        Boolean v = false;
                                                        while (v == false) {
                                                            try {
                                                                String c = s.Consulter_coursPopulaire();
                                                                if (c.equalsIgnoreCase("pleine")) {
                                                                    System.out.println("Voulez-vous ajouter un cours populaire ?");
                                                                    String ajout = sc.nextLine();
                                                                    if (ajout.toLowerCase().equalsIgnoreCase("oui")) {
                                                                        s.Ajouter_cours();
                                                                        s.sauvegarder();
                                                                    }
                                                                }
                                                                v = true;
                                                            } catch (IllegalArgumentException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Veuillez reessayer");
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case "7" -> {
                                                        Boolean v = false;
                                                        while (v == false) {
                                                            try {
                                                                String c = s.Consulter_coursImpopulaire();
                                                                if (c.equalsIgnoreCase("pleine")) {
                                                                    System.out.println("Voulez-vous supprimer un cours impopulaire ?");
                                                                    String sup = sc.nextLine();
                                                                    if (sup.toLowerCase().equalsIgnoreCase("oui")) {
                                                                        System.out.println("Entrer l'ID du cours à supprimer :");
                                                                        int ID = sc.nextInt();
                                                                        sc.nextLine();
                                                                        Cours co = s.Rechercher_cours_ID(ID);
                                                                        System.out.println(s.Supprimer_cours(co));
                                                                        s.sauvegarder();
                                                                    }
                                                                }
                                                                v = true;
                                                            } catch (IllegalArgumentException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Veuillez reessayer");
                                                            } catch (UserNotFoundException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Veuillez reessayer");
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case "8" -> {
                                                        retour = true;
                                                        break;
                                                    }
                                                }
                                            }
                                            break;
                                        }
                                        case "3" -> {
                                            Boolean retour = false;
                                            while (retour == false) {
                                                System.out.println("Selectionner une action :");
                                                System.out.println("""
                                                                   1-Consulter la liste des activites
                                                                   2-Ajouter une activite
                                                                   3-Supprimer une activite
                                                                   4-Rechercher une activite
                                                                   5-Modifier une activite
                                                                   6-Retour""");
                                                choix = sc.nextLine();
                                                switch (choix) {
                                                    case "1" -> {
                                                        s.Consulter_listeActivite();
                                                        break;
                                                    }
                                                    case "2" -> {
                                                        Boolean v = false;
                                                        while (v == false) {
                                                            try {
                                                                s.Ajouter_activite();
                                                                s.sauvegarder();
                                                                v = true;
                                                            } catch (IllegalArgumentException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Veuillez reessayer");
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case "3" -> {
                                                        Boolean v = false;
                                                        while (v == false) {
                                                            try {
                                                                System.out.println("Entrer l'ID de l'activite a supprimer :");
                                                                int ID = sc.nextInt();
                                                                sc.nextLine();
                                                                Activite act = s.Rechercher_activite_ID(ID);
                                                                System.out.println(s.Supprimer_activite(act));
                                                                s.sauvegarder();
                                                                v = true;
                                                            } catch (UserNotFoundException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Veuillez reesayer");
                                                            } catch (IllegalArgumentException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Veuillez reesayer");
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case "4" -> {
                                                        System.out.println("Par quel moyen veux-tu filtrer ta recherche ? (ID,nom)");
                                                        String filtre = sc.nextLine().toLowerCase();
                                                        boolean c = false;
                                                        while (c == false) {
                                                            try {
                                                                if (filtre.equalsIgnoreCase("id")) {
                                                                    System.out.println("Pour quel ID ?");
                                                                    int ID = sc.nextInt();
                                                                    sc.nextLine();
                                                                    System.out.println(s.Rechercher_activite_ID(ID).affichage_liste());
                                                                }
                                                                if (filtre.equalsIgnoreCase("nom")) {
                                                                    System.out.println("Pour quel nom ?");
                                                                    String nom = sc.nextLine();
                                                                    for (Activite act : s.Rechercher_nom_activite(nom)) {
                                                                        System.out.println(act.affichage_liste());
                                                                    }
                                                                }
                                                                c = true;
                                                            } catch (IllegalArgumentException f) {
                                                                System.out.println(f.getMessage());
                                                                System.out.println("Veuillez reessayer");
                                                            } catch (UserNotFoundException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Veuillez reessayer");
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case "5" -> {
                                                        Boolean v = false;
                                                        while (v == false) {
                                                            try {
                                                                System.out.println("Entrer l'ID de l'activite a modifier :");
                                                                int ID = sc.nextInt();
                                                                sc.nextLine();
                                                                Activite act = s.Rechercher_activite_ID(ID);
                                                                s.Modifier_infos_activite(act);
                                                                s.sauvegarder();
                                                                v = true;
                                                            } catch (IllegalArgumentException f) {
                                                                System.out.println(f.getMessage());
                                                                System.out.println("Veuillez reessayer");
                                                            } catch (UserNotFoundException e) {
                                                                System.out.println(e.getMessage());
                                                                System.out.println("Veuillez reessayer");
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case "6" -> {
                                                        retour = true;
                                                        break;
                                                    }
                                                }
                                            }
                                            break;
                                        }
                                        case "4" -> {
                                            System.out.println("Vous avez ete deconnecte avec succes.");
                                            decision = false;
                                            break;
                                        }
                                    }
                                }
                                break;
                            }

                            case "client autorise" -> {
                                System.out.println("bienvenue cher client !");
                                cl = s.Rechercher_client_email(mail);
                                client = true;
                                break;
                            }

                            case "client interdit" -> {
                                System.out.println("""
                                                   Vous n'etes pas autorise a vous connecter.
                                                   Pour plus d'informations veuillez contacter l'administrateur de la salle.""");
                                break;
                            }
                        }
                        b = true;
                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Veuillez reessayer");
                    } catch (IllegalArgumentException f) {
                        System.out.println(f.getMessage());
                        System.out.println("Veuillez reessayer");
                    }
                }
                break;
            }
            default -> {
                System.out.println("Reponse invalide");
            }
        }

        if (client == true) {
            while (decision == true) {
                System.out.println("Menu :");
                System.out.println("""
                               1-Espace Compte
                               2-Espace Cours
                               3-Espace Activite
                               4-Se deconnecter""");
                String choix = sc.nextLine();
                switch (choix) {
                    case "1" -> {
                        Boolean retour = false;
                        while (retour == false) {
                            System.out.println("Selectionner une action :");
                            System.out.println("""
                                       1-Modifier mon addresse email
                                       2-Modifier mon mot de passe
                                       3-Consulter mes informations
                                       4-Modifier mes informations
                                       5-Retour""");
                            choix = sc.nextLine();
                            switch (choix) {
                                case "1" -> {
                                    Boolean v = false;
                                    while (v == false) {
                                        try {
                                            s.Modifier_addresseMail(cl);
                                            s.sauvegarder();
                                            v = true;
                                        } catch (IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                            System.out.println("Veuillez reesayer");
                                        }
                                    }
                                    break;
                                }
                                case "2" -> {
                                    Boolean v = false;
                                    while (v == false) {
                                        try {
                                            System.out.println("Entrer votre mot de passe actuel :");
                                            String mdp = sc.nextLine();
                                            s.Modifier_mdp(mdp);
                                            s.sauvegarder();
                                            v = true;
                                        } catch (IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                            System.out.println("Veuillez reesayer");
                                        }
                                    }
                                    break;
                                }
                                case "3" -> {
                                    s.Consulter_infos(cl);
                                    break;
                                }
                                case "4" -> {
                                    Boolean v = false;
                                    while (v == false) {
                                        try {
                                            s.Modifier_infos_client(cl);
                                            s.sauvegarder();
                                            v = true;
                                        } catch (IllegalArgumentException e) {
                                            System.out.println(e.getMessage());
                                            System.out.println("Veuillez reesayer");
                                        }
                                    }
                                    break;
                                }
                                case "5" -> {
                                    retour = true;
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "2" -> {
                        Boolean retour = false;
                        while (retour == false) {
                            System.out.println("Selectionner une action :");
                            System.out.println("""
                                       1-S'inscrire a un cours
                                       2-Se desincrire d'un cours
                                       3-Consulter vos prochains cours
                                       4-Consulter vos cours passes
                                       5-Retour""");
                            choix = sc.nextLine();
                            switch (choix) {
                                case "1" -> {
                                    Boolean v = false;
                                    while (v == false) {
                                        try {
                                            System.out.println("Liste des prochains cours :");
                                            for (Cours co : s.Consulter_listeCours()) {
                                                if (co.getDate_cours().isAfter(LocalDate.now())) {
                                                    System.out.println(co.affichage_listeAdmin());
                                                }
                                            }
                                            System.out.println("Entrer l'ID du cours choisi :");
                                            int ID = sc.nextInt();
                                            sc.nextLine();
                                            Cours CO = s.Rechercher_cours_ID(ID);
                                            s.Inscription_client(cl, CO);
                                            s.sauvegarder();
                                            v = true;
                                        } catch (UserNotFoundException e) {
                                            System.out.println(e.getMessage());
                                            System.out.println("Veuillez reessayer");
                                        } catch (IllegalArgumentException f) {
                                            System.out.println(f.getMessage());
                                            System.out.println("Veuillez reessayer");
                                        }
                                    }
                                    break;
                                }
                                case "2" -> {
                                    Boolean v = false;
                                    while (v == false) {
                                        try {
                                            s.Consulter_listeCours_futur(cl);
                                            System.out.println("Entrer l'ID du cours choisi :");
                                            int ID = sc.nextInt();
                                            sc.nextLine();
                                            Cours co = s.Rechercher_cours_ID(ID);
                                            s.Desincription_client(cl, co);
                                            s.sauvegarder();
                                            v = true;
                                        } catch (UserNotFoundException e) {
                                            System.out.println(e.getMessage());
                                            System.out.println("Veuillez reessayer");
                                        } catch (IllegalArgumentException f) {
                                            System.out.println(f.getMessage());
                                            System.out.println("Veuillez reessayer");
                                        }
                                    }
                                    break;
                                }
                                case "3" -> {
                                    s.Consulter_listeCours_futur(cl);
                                    break;
                                }
                                case "4" -> {
                                    s.Consulter_listeCours_passe(cl);
                                    break;
                                }
                                case "5" -> {
                                    retour = true;
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "3" -> {
                        Boolean retour = false;
                        while (retour == false) {
                            System.out.println("Selectionner une action :");
                            System.out.println("""
                                       1-Consulter la liste des activites
                                       2-Retour""");
                            choix = sc.nextLine();
                            switch (choix) {
                                case "1" -> {
                                    s.Consulter_listeActivite();
                                }
                                case "2" -> {
                                    retour = true;
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "4" -> {
                        System.out.println("Vous avez ete deconnecte avec succes.");
                        decision = false;
                        break;
                    }
                }
            }
        }
        s.actualiser();
        s.sauvegarder();
    }

}
