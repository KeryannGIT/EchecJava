package echec.metier;

public class Roi extends Piece
{
    public Roi(char couleur, int x, int y)
    {
        super(couleur, x, y);
    }

    public boolean mouvementValide( Plateau plateau, int nouvLig, int nouvCol)
    {
        int dLig = Math.abs(nouvLig - this.getX());
        int dCol = Math.abs(nouvCol - this.getY());

        if (dLig > 1 || dCol > 1) 
            return false;

        return true;
    }
}
