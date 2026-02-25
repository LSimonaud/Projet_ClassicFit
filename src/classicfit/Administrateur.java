/*
 
 */
package classicfit;

/**
 *
 * @author lucsi
 */
public class Administrateur extends Utilisateur {

    private String nom_admin; //nom de l'administrateur
    private String prenom_admin; //prenom de l'administrateur

    public Administrateur(String email, String mdp, String nom, String prenom) {
        super(email, mdp);
        this.nom_admin = nom;
        this.prenom_admin = prenom;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + nom_admin + ";" + prenom_admin;
    }

}
