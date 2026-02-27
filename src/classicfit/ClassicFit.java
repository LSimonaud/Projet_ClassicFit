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
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author lucsi
 */
public class ClassicFit {

    public static void main(String[] args) throws UserNotFoundException, IOException, FileNotFoundException, DejaInscritException {
        Scanner sc = new Scanner(System.in);
        Administrateur a = new Administrateur("admin@epfedu.fr", "Admin123456*", "Inshape", "Tibo");
        Salle s = new Salle("classicfit", a);
        s.charger();
        System.out.println("Bienvenue a classicfit !");
        System.out.println("Etes-vous nouveau chez nous ?");
        String decision = "oui";
        String etat = sc.nextLine();
        switch (etat) {
            case "Oui", "oui" -> {
                boolean valide = false;
                while (valide == false) {
                    try {
                        s.Creer_compte();
                        valide = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Veuillez reessayer.");
                    }
                }
                System.out.println("Que desirez-vous faire ?");
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
                                    System.out.println("Que desires-tu faire ?" + "\n" + "1-Consulter les comptes des clients" + "\n" + "2-Rechercher des clients");
                                    System.out.println("3-Reactiver/Desactiver un abonnement" + "\n" + "4-Gerer les cours");
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
                                                    String choix = sc.nextLine();
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
                                    System.out.println("Veux-tu effectuer d'autres action ?");
                                    decision = sc.nextLine();

                                }
                            }
                            case "client" -> {
                                System.out.println("bienvenue cher client !");
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
        s.sauvegarder();
    }

}
