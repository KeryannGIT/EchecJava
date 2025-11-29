package echec.metier;
public class Cavalier extends Piece
{
    public Cavalier(char couleur, int x, int y)
    {
        super(couleur, x, y);
    }

    public boolean mouvementValide( Plateau plateau, int nouvLig, int nouvCol)
    {
        int dLig = Math.abs(nouvLig - this.getLig());
        int dCol = Math.abs(nouvCol - this.getCol());

        if (dLig * dCol != 2) return false;
        return true;
    }
}
