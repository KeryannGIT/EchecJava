package echec.metier;

public class Reine extends Piece
{
    public Reine(char couleur, int x, int y)
    {
        super(couleur, x, y);
    }

    public boolean mouvementValide(Plateau plateau, int nouvLig, int nouvCol)
    {

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
