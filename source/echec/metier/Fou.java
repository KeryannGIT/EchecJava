package echec.metier;

public class Fou extends Piece
{
    public Fou(char couleur, int x, int y )
    {
        super(couleur, x, y);
    }

    public boolean mouvementValide( Plateau plateau, int nouvLig, int nouvCol)
    {
        if (Math.abs(nouvLig - this.getLig()) != Math.abs(nouvCol - this.getCol())) return false;

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
