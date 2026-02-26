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
