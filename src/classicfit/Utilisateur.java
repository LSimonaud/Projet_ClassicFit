/*
Luc SIMONAUD
Philippe-Henri PAUL
Aloïs OVIGNEUR
Projet Salle de Sport
AA
 */
package classicfit;

/**
 *
 * @author lucsi
 */
public class Utilisateur {

    private String email; //mail d'un utilisateur
    private String mdp; //mot de passe d'un utilisateur

    public Utilisateur(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return email + ";" + mdp;
    }

    public String getmdp() {
        return this.mdp;
    }

    public String getemail() {
        return this.email;
    }

    public void modifier_addresseMail(String email) {
        this.email = email;
        System.out.println("Nouvelle addresse mail enregistree");
    }

    public void modifier_mdp(String mdp) {
        this.mdp = mdp;
        System.out.println("Nouveau mot de passe enregistre");
    }
}
