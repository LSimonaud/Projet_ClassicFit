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
        System.out.println("Etes-vous nouveau chez nous ?");
        String decision = "oui";
        Boolean client = false;
        Client cl = null;
        String etat = sc.nextLine();
        switch (etat) {
            case "Oui", "oui" -> {
                boolean valide = false;
                while (valide == false) {
                    try {
                        cl = s.Creer_compte();
                        valide = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Veuillez reessayer.");
                    }
                }
                System.out.println("bienvenue cher client !");
                client = true;
            }

            case "non", "Non" -> {
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
                                while (decision.equalsIgnoreCase("oui") || decision.equalsIgnoreCase("Oui")) {
                                    System.out.println("Que desires-tu faire ?");
                                    System.out.println("""
                                                       1-Gestion des clients
                                                       2-Gestion des cours""");
                                    String choix = sc.nextLine();
                                    switch (choix) {
                                        case "1" -> {
                                            System.out.println("Selctionne une action :");
                                            System.out.println("""
                                                       1-Consulter les comptes des clients
                                                       2-Rechercher des clients
                                                       3-Reactiver/Desactiver un abonnement""");
                                            String action = sc.nextLine();

                                            switch (action) {
                                                case "1" -> {
                                                    s.Consulter_listeClient();
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
                                                                System.out.println(s.Rechercher_client_ID(ID));
                                                            }
                                                            if (filtre.equalsIgnoreCase("nom")) {
                                                                System.out.println("Pour quel nom ?");
                                                                String nom = sc.nextLine();
                                                                System.out.println(s.Rechercher_client_nom(nom));
                                                            }
                                                            if (filtre.equalsIgnoreCase("email")) {
                                                                System.out.println("Pour quel email ?");
                                                                String email = sc.nextLine();
                                                                if (s.Rechercher_client_telephone(email) == null) {

                                                                }
                                                            }
                                                            if (filtre.equalsIgnoreCase("telephone")) {
                                                                System.out.println("Pour quel numero de telephone ?");
                                                                String tel = sc.nextLine();
                                                                if (s.Rechercher_client_telephone(tel) == null) {

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
                                                }
                                                case "3" -> {
                                                    boolean d = false;
                                                    while (d == false) {
                                                        System.out.println("Entrer le nom du client concerne :");
                                                        String nom = sc.nextLine();
                                                        try {
                                                            System.out.println("Etat actuel de l'abonnement de M." + nom + ":" + "\n" + s.Rechercher_client_nom(nom).getEtat_abonnement() + "\n");
                                                            System.out.println("Voulez_vous toujours modifier l'etat de cet abonnement ?");
                                                            choix = sc.nextLine();
                                                            if (choix.equalsIgnoreCase("Oui") || choix.equalsIgnoreCase("oui")) {
                                                                s.Reactiver_abonnement(nom);
                                                            }

                                                            d = true;
                                                        } catch (UserNotFoundException e) {
                                                            System.out.println(e.getMessage());
                                                            System.out.println("Veuillez reessayer");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        case "2" -> {
                                            System.out.println("Choisissez une action :");
                                            System.out.println("""
                                                               1-Consulter la liste des cours
                                                               2-Ajouter un cours
                                                               3-Supprimer un cours
                                                               4-Rechercher un cours
                                                               5-Modifier les informations d'un cours""");
                                            choix = sc.nextLine();
                                            switch (choix) {
                                                case "1" -> {
                                                    s.Consulter_listeCours();
                                                }
                                                case "2" -> {
                                                    Boolean v = false;
                                                    while (v == false) {
                                                        try {
                                                            s.Ajouter_cours();
                                                            v = true;
                                                        } catch (IllegalArgumentException e) {
                                                            System.out.println(e.getMessage());
                                                            System.out.println("Veuillez reesayer");
                                                        }
                                                    }
                                                }
                                                case "3" -> {
                                                    System.out.println("Entrer le nom du cours a supprimer :");
                                                    String nom = sc.nextLine();
                                                    Boolean v = false;
                                                    while (v == false) {
                                                        try {
                                                            Cours co = s.Rechercher_cours_nom(nom);
                                                            s.Supprimer_cours(co);
                                                        } catch (UserNotFoundException e) {
                                                            System.out.println(e.getMessage());
                                                            System.out.println("Veuillez reesayer");
                                                        }
                                                    }
                                                }
                                                case "4" -> {
                                                    System.out.println("Par quel moyen veux-tu filtrer ta recherche ? (ID,nom,date)");
                                                    String filtre = sc.nextLine();
                                                    boolean c = false;
                                                    while (c == false) {
                                                        try {
                                                            if (filtre.equalsIgnoreCase("ID")) {
                                                                System.out.println("Pour quel ID ?");
                                                                int ID = sc.nextInt();
                                                                sc.nextLine();
                                                                System.out.println(s.Rechercher_cours_ID(ID));
                                                            }
                                                            if (filtre.equalsIgnoreCase("nom")) {
                                                                System.out.println("Pour quel nom ?");
                                                                String nom = sc.nextLine();
                                                                System.out.println(s.Rechercher_cours_nom(nom));
                                                            }
                                                            if (filtre.equalsIgnoreCase("date")) {
                                                                System.out.println("Pour quel date (dd-MM-yyyy)?");
                                                                String date = sc.nextLine();
                                                                LocalDate Date = LocalDate.parse(date, format);
                                                                System.out.println(s.Rechercher_cours_date(Date));
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
                                                }
                                                case "5" -> {
                                                    System.out.println("Entrer le nom du cours a modifier :");
                                                    String nom = sc.nextLine();
                                                    Boolean v = false;
                                                    while (v == false) {
                                                        try {
                                                            Cours co = s.Rechercher_cours_nom(nom);
                                                            s.Modifier_infos_cours(co);
                                                        } catch (IllegalArgumentException f) {
                                                            System.out.println(f.getMessage());
                                                            System.out.println("Veuillez reessayer");
                                                        } catch (UserNotFoundException e) {
                                                            System.out.println(e.getMessage());
                                                            System.out.println("Veuillez reessayer");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    System.out.println("Veux-tu effectuer d'autres action ?");
                                    decision = sc.nextLine();
                                }
                            }
                            case "client" -> {
                                System.out.println("bienvenue cher client !");
                                cl = s.Rechercher_client_email(mail);
                                client = true;
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
            }
        }

        if (client == true) {
            while (decision.equalsIgnoreCase("oui") || decision.equalsIgnoreCase("Oui")) {
                System.out.println("Que desirez-vous faire ?");
                System.out.println("""
                               1-Espace Compte
                               2-Espace Cours""");
                String choix = sc.nextLine();
                switch (choix) {
                    case "1" -> {
                        System.out.println("Selectionner une action :");
                        System.out.println("""
                                       1-Modifier mon addresse email
                                       2-Modifier mon mot de passe
                                       3-Consulter mes informations
                                       4-Modifier mes informations""");
                        choix = sc.nextLine();
                        switch (choix) {
                            case "1" -> {
                                Boolean v = false;
                                while (v == false) {
                                    try {
                                        s.Modifier_addresseMail(cl);
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                        System.out.println("Veuillez reesayer");
                                    }
                                }
                            }
                            case "2" -> {
                                Boolean v = false;
                                while (v == false) {
                                    try {
                                        System.out.println("Entrer votre mot de passe actuel :");
                                        String mdp = sc.nextLine();
                                        s.Modifier_mdp(mdp);
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                        System.out.println("Veuillez reesayer");
                                    }
                                }
                            }
                            case "3" -> {
                                s.Consulter_infos(cl);
                            }
                            case "4" -> {
                                Boolean v = false;
                                while (v == false) {
                                    try {
                                        s.Modifier_infos_client(cl);
                                    } catch (IllegalArgumentException e) {
                                        System.out.println(e.getMessage());
                                        System.out.println("Veuillez reesayer");
                                    }
                                }
                            }
                        }
                    }
                    case "2" -> {
                        System.out.println("Selectionner une action :");
                        System.out.println("""
                                       1-S'inscrire a un cours
                                       2-Se desincrire d'un cours
                                       3-Consulter vos prochains cours
                                       4-Consulter vos cours passes""");
                        choix = sc.nextLine();
                        switch (choix) {
                            case "1" -> {

                            }
                            case "2" -> {

                            }
                            case "3" -> {
                                s.Consulter_listeCours_futur(cl);
                            }
                            case "4" -> {
                                s.Consulter_listeCours_passe(cl);
                            }
                        }
                    }
                }
                System.out.println("Veux-tu effectuer d'autres action ?");
                decision = sc.nextLine();
            }
        }
        s.actualiser();
        s.sauvegarder();
    }

}
