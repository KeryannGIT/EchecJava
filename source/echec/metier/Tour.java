package echec.metier;

public class Tour extends Piece
{
    public Tour(char couleur, int x, int y )
    {
        super(couleur, x, y);
    }

    public boolean mouvementValide( Plateau plateau, int nouvLig, int nouvCol)
    {
        if (this.getLig() != nouvLig && this.getCol() != nouvCol) return false;

        int dLig = Integer.compare(nouvLig, this.getLig());
        int dCol = Integer.compare(nouvCol, this.getCol());

        int i = this.getLig() + dLig;
        int j = this.getCol() + dCol;

        while (i != nouvLig || j != nouvCol) 
        {
            for ( Piece piece : plateau )
            {
                if ( piece != this && i==piece.getLig() && j==piece.getCol() )
                    return false;
            }
            i += dLig;
            j += dCol;
        }
        
        

        return true;
    }


}
