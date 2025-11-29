package echec.metier;

public abstract class Piece 
{
    protected char couleur;
    protected int nbMouv;

    private int lig;
    private int col;

    public Piece( char couleur, int lig, int col )
    {
        this.couleur = couleur;
        this.nbMouv  = 0;
        this.lig = lig;
        this.col = col;
    }

    public abstract boolean mouvementValide( Plateau plateau, int nouvLig, int nouvCol ); 

    public char getCouleur() { return this.couleur; }
    public int  getNbMouv () { return this.nbMouv ; }
    public int  getLig      () { return this.lig;       }
    public int  getCol      () { return this.col;       }

    public void setLig( int lig ) { this.lig = lig; }
    public void setCol( int col ) { this.col = col; }

    public void addDeplacement() { this.nbMouv++; }

}
