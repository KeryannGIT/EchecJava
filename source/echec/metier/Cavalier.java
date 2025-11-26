package echec.metier;
public class Cavalier extends Piece
{
    public Cavalier(char couleur, int x, int y)
    {
        super(couleur, x, y);
    }

    public boolean mouvementValide( Plateau plateau, int nouvLig, int nouvCol)
    {
        int dLig = Math.abs(nouvLig - this.getX());
        int dCol = Math.abs(nouvCol - this.getY());

        if (dLig * dCol != 2) return false;
        return true;
    }
}
