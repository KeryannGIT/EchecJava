package echec.metier;

public abstract class Piece 
{
    protected char couleur;
    protected int nbMouv;

    private int x;
    private int y;

    public Piece( char couleur, int x, int y )
    {
        this.couleur = couleur;
        this.nbMouv  = 0;
        this.x = x;
        this.y = y;
    }

    public abstract boolean mouvementValide( Plateau plateau, int nouvLig, int nouvCol ); 

    public char getCouleur() { return this.couleur; }
    public int  getNbMouv () { return this.nbMouv ; }
    public int  getX      () { return this.x;       }
    public int  getY      () { return this.y;       }

    public void setX( int x ) { this.x = x; }
    public void setY( int y ) { this.y = y; }

    public void addDeplacement() { this.nbMouv++; }

}
