/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classicfit;

/**
 *
 * @author lucsi
 */
public class Administrateur extends Utilisateur {

    private String nom; //nom de l'administrateur

    public Administrateur(String email, String mdp, String nom) {
        super(email, mdp);
        this.nom = nom;
    }

    @Override
    public String toString() {
        return super.toString() + " ; " + nom;
    }

    public void lister_client() {

    }

    /*
    public Client rechercher_client(){
        
    }*/
    public void desactiver_abonnement() {

    }

    public void reactiver_abonnement() {

    }

    public void lister_cours() {

    }

}
