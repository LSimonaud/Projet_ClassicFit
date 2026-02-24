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
        Administrateur a = new Administrateur("admin@epfedu.fr","admin1234","Inshape","Tibo");
        Salle s = new Salle("classicfit",a);
        s.charger();
        System.out.println("Bienvenue a classicfit !");
        System.out.println("Etes-vous nouveau chez nous ?");
        String etat = sc.nextLine();       
        switch(etat){
            case "Oui","oui":
                s.Creer_compte();
                break;
                
            case "non", "Non":
                try{
                System.out.println("adresse mail :");
                String mail = sc.nextLine();
                System.out.println("mot de passe :");
                String mdp = sc.nextLine();
                s.seConnecter(mail,mdp);
                }catch(UserNotFoundException e){
                    e.getMessage();
                }
                
        }
        s.sauvegarder();
    }

}
