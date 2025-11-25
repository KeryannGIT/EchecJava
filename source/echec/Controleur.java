package echec;

import echec.metier.Plateau;

import echec.metier.Piece;

import echec.ihm.FramePlateau;

public class Controleur
{
    private FramePlateau frame;

    private Plateau plateau;

    public Controleur()
    {
        this.plateau = new Plateau();

        this.frame = new FramePlateau(this);

    }


    public Piece getPiece(int lig, int col)
    {
        return this.plateau.getPiece(lig, col);
    }

    public void setPiece(int lig, int col, Piece piece)
    {
        this.plateau.setPiece(lig, col, piece);
    }

    public boolean deplacerPiece( int lig, int col, int nouvLig, int nouvCol)
    {
        return this.plateau.deplacerPiece(lig, col, nouvLig, nouvCol);
    }

    public boolean partieFinie()
    {
        return this.plateau.partieFinie();
    }

    public char getVainqueur()
    {
        return this.plateau.getVainqueur();
    }

    public char getJoueurActuel()
    {
        return this.plateau.getJoueurActuel();
    }

    public static void main(String[] a)
    {
        new Controleur();
    }

}