package echec.metier;

public class Pion extends Piece
{
    public Pion(char couleur, int x, int y)
    {
        super(couleur, x, y);
    }

    public boolean mouvementValide(Plateau plateau, int nouvLig, int nouvCol)
    {
        int direction = (couleur == 'B') ? 1 : -1;   
        int ligActuelle = this.getX();
        int colActuelle = this.getY();

        int caseAvant   = ligActuelle + direction;
        int caseDeux    = ligActuelle + 2 * direction;

        
        if (nouvLig == caseAvant && nouvCol == colActuelle)
        {
            if (plateau.estVide(caseAvant, colActuelle))
                return true;
            else
                return false;
        }


        if (this.nbMouv == 0 &&
            nouvLig == caseDeux && nouvCol == colActuelle)
        {
            if (plateau.estVide(caseAvant, colActuelle) &&
                plateau.estVide(caseDeux, colActuelle))
            {
                return true;
            }
            return false;
        }

        
        if (nouvLig == caseAvant && Math.abs(nouvCol - colActuelle) == 1)
        {
            Piece p = plateau.getPiece(nouvLig, nouvCol);
            if (p != null && p.getCouleur() != this.couleur)
            {
                return true; 
            }
            return false;
        }

        
        return false;
    }

}
