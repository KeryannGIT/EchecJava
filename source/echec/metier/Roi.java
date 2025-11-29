package echec.metier;

public class Roi extends Piece
{
    private Piece tourRock;

    public Roi(char couleur, int x, int y)
    {
        super(couleur, x, y);
    }

    public Piece getTourRock() { return this.tourRock; }

    public boolean mouvementValide( Plateau plateau, int nouvLig, int nouvCol)
    {
        int dLig = Math.abs(nouvLig - this.getLig());
        int dCol = Math.abs(nouvCol - this.getCol());

        if ( dLig == 1 || dCol == 1 )
            return true;

        dLig = nouvLig - this.getLig();
        dCol = nouvCol - this.getCol();

        return this.rock(plateau, dCol);
    }

    public boolean rock( Plateau plateau, int dCol )
    {
        if ( this.nbMouv != 0 ) return false; // Le rock peut se faire seulement si le roi n'a pas bougé
        if ( Math.abs( dCol ) != 2 ) return false; // Le rock décale de deux cases sur le côté

        for ( Piece piece : plateau )
        {
            if ( piece.couleur == this.couleur  && 
                this.getLig() == piece.getLig() && 
                piece instanceof Tour           &&
                piece.nbMouv == 0                  )
            {
                if ( dCol > 0 && piece.getCol() == 7 || dCol < 0 && piece.getCol() == 0 )    
                {
                    this.tourRock = piece;
                    return true;
                }
            }
        }

        return false;
    }
}
