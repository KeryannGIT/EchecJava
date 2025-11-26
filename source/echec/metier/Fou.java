package echec.metier;

public class Fou extends Piece
{
    public Fou(char couleur, int x, int y )
    {
        super(couleur, x, y);
    }

    public boolean mouvementValide( Plateau plateau, int nouvLig, int nouvCol)
    {
        if (Math.abs(nouvLig - this.getX()) != Math.abs(nouvCol - this.getY())) return false;

        int dLig = Integer.compare(nouvLig, this.getX());
        int dCol = Integer.compare(nouvCol, this.getY());

        int i = this.getX() + dLig;
        int j = this.getY() + dCol;

        while (i != nouvLig || j != nouvCol) 
        {
            for ( Piece piece : plateau )
            {
                if ( piece != this && i==piece.getX() && j==piece.getY() )
                    return false;
            }
            i += dLig;
            j += dCol;
        }

        return true;
    }
}
