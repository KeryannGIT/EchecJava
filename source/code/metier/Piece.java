package code.metier;

public class Piece 
{
    private char couleur;
    private String nom;
    private int nbDeplacement;

    public Piece(String nom, char couleur)
    {
        this.nom = nom;
        this.couleur = couleur;
        this.nbDeplacement = 0;
    }

    public char   getCouleur() { return this.couleur; }
    public String getNom    () { return this.nom;     }
    public void setNbDeplacement() { this.nbDeplacement++; }
    public int getNbDeplacement() { return this.nbDeplacement; }

}
