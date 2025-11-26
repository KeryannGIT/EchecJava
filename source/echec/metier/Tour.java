package echec.metier;

public class Tour extends Piece
{
    public Tour(char couleur, int x, int y )
    {
        super(couleur, x, y);
    }

    public boolean mouvementValide( Plateau plateau, int nouvLig, int nouvCol)
    {
        if (this.getX() != nouvLig && this.getY() != nouvCol) return false;

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
