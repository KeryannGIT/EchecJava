package echec.metier;

public class Plateau 
{
    private final int TAILLE = 8;

    private char vainqueur;
    
    private Piece[][] plateau;
    
    public Plateau()
    {
        this.plateau = new Piece[this.TAILLE][this.TAILLE];
        this.initialiserPlateau();
    }

    public void initialiserPlateau()
    {        
        int ligBlanc=1, ligNoir=this.TAILLE-2;
        
        for ( int ind=0; ind < this.TAILLE; ind++)
        {
            this.plateau[1][ind] = new Piece("Pion", 'B');
            this.plateau[6][ind] = new Piece("Pion", 'N');
        }

        int col=0;
        ligBlanc=0;
        ligNoir=TAILLE-1;

        
        /* Placement en dure des pièces sur le plateau */

        this.plateau[ligBlanc][col] = new Piece("Tour", 'B');
        this.plateau[ligNoir ][col++] = new Piece("Tour", 'N');

        this.plateau[ligBlanc][col] = new Piece("Cavalier", 'B');
        this.plateau[ligNoir ][col++] = new Piece("Cavalier", 'N');

        this.plateau[ligBlanc][col] = new Piece("Fou", 'B');
        this.plateau[ligNoir ][col++] = new Piece("Fou", 'N');

        this.plateau[ligBlanc][col] = new Piece("Reine", 'B');
        this.plateau[ligNoir ][col++] = new Piece("Reine", 'N');

        this.plateau[ligBlanc][col] = new Piece("Roi", 'B');
        this.plateau[ligNoir ][col++] = new Piece("Roi", 'N');

        this.plateau[ligBlanc][col] = new Piece("Fou", 'B');
        this.plateau[ligNoir ][col++] = new Piece("Fou", 'N');

        this.plateau[ligBlanc][col] = new Piece("Cavalier", 'B');
        this.plateau[ligNoir ][col++] = new Piece("Cavalier", 'N');

        this.plateau[ligBlanc][col] = new Piece("Tour", 'B');
        this.plateau[ligNoir ][col++] = new Piece("Tour", 'N');

    }

    public boolean deplacerPiece(int lig, int col, int nouvLig, int nouvCol)
    {
        if (nouvLig < 0 || nouvLig >= this.TAILLE ||
            nouvCol < 0 || nouvCol >= this.TAILLE)
            return false;

        var piece = this.plateau[lig][col];
        if (piece == null) return false;

        if (this.plateau[nouvLig][nouvCol] != null &&
            this.plateau[nouvLig][nouvCol].getCouleur() == piece.getCouleur())
            return false;

        return switch (piece.getNom()) 
        {
            case "Pion"      -> this.deplacerPion(lig, col, nouvLig, nouvCol);
            case "Tour"      -> this.deplacerTour(lig, col, nouvLig, nouvCol);
            case "Cavalier"  -> this.deplacerCavalier(lig, col, nouvLig, nouvCol);
            case "Fou"       -> this.deplacerFou(lig, col, nouvLig, nouvCol);
            case "Roi"       -> this.deplacerRoi(lig, col, nouvLig, nouvCol);
            case "Reine"     -> this.deplacerReine(lig, col, nouvLig, nouvCol);

            default -> false;
        };
    }

    private boolean deplacerPion(int lig, int col, int nouvLig, int nouvCol)
    {
        var pion = this.plateau[lig][col];
        char couleur = pion.getCouleur();

        
        int direction = (couleur == 'B') ? 1 : -1; // En fonction de la couleur l'orientation est différente
        int caseAvant = lig + direction;
        int caseDeuxAvant = lig + 2 * direction;

        // Avance simple
        if (nouvLig == caseAvant && nouvCol == col &&
            this.plateau[caseAvant][col] == null)
        {
            this.plateau[lig][col].addDeplacement();
            this.plateau[nouvLig][nouvCol] = pion;
            this.plateau[lig][col] = null;
            return true;
        }

        // Avance double
        if (nouvLig == caseDeuxAvant && nouvCol == col &&
            this.plateau[caseAvant][col] == null &&
            this.plateau[caseDeuxAvant][col] == null && 
            this.plateau[lig][col].getNbDeplacement() < 1 )
        {
            this.plateau[lig][col].addDeplacement();
            this.plateau[nouvLig][nouvCol] = pion;
            this.plateau[lig][col] = null;
            return true;
        }

        // Capture diagonale
        if (nouvLig == caseAvant && Math.abs(nouvCol - col) == 1)
        {
            var cible = this.plateau[nouvLig][nouvCol];
            if (cible != null && cible.getCouleur() != couleur)
            {
                this.plateau[lig][col].addDeplacement();
                this.plateau[nouvLig][nouvCol] = pion;
                this.plateau[lig][col] = null;
                return true;
            }
        }

        return false;
    }

    private boolean deplacerCavalier(int lig, int col, int nouvLig, int nouvCol)
    {
        int dLig = Math.abs(nouvLig - lig);
        int dCol = Math.abs(nouvCol - col);

        if (dLig * dCol == 2) 
        {
            this.plateau[lig][col].addDeplacement();
            this.plateau[nouvLig][nouvCol] = this.plateau[lig][col];
            this.plateau[lig][col] = null;
            return true;
        }

        return false;
    }


    private boolean deplacerFou(int lig, int col, int nouvLig, int nouvCol)
    {
        if (Math.abs(nouvLig - lig) != Math.abs(nouvCol - col)) return false;

        int dLig = Integer.compare(nouvLig, lig);
        int dCol = Integer.compare(nouvCol, col);

        int i = lig + dLig;
        int j = col + dCol;

        while (i != nouvLig || j != nouvCol) 
        {
            if (this.plateau[i][j] != null) return false;
            i += dLig;
            j += dCol;
        }

        this.plateau[lig][col].addDeplacement();
        this.plateau[nouvLig][nouvCol] = this.plateau[lig][col];
        this.plateau[lig][col] = null;
        return true;
    }


    private boolean deplacerRoi(int lig, int col, int nouvLig, int nouvCol)
    {
        int dLig = Math.abs(nouvLig - lig);
        int dCol = Math.abs(nouvCol - col);

        if (dLig <= 1 && dCol <= 1) 
        {
            this.plateau[lig][col].addDeplacement();
            this.plateau[nouvLig][nouvCol] = this.plateau[lig][col];
            this.plateau[lig][col] = null;
            return true;
        }

        return false;
    }

    private boolean deplacerTour(int lig, int col, int nouvLig, int nouvCol)
    {
        if (lig != nouvLig && col != nouvCol) return false;

        int dLig = Integer.compare(nouvLig, lig);
        int dCol = Integer.compare(nouvCol, col);

        int i = lig + dLig;
        int j = col + dCol;

        while (i != nouvLig || j != nouvCol) 
        {
            if (this.plateau[i][j] != null) return false;
            i += dLig;
            j += dCol;
        }

        this.plateau[lig][col].addDeplacement();
        this.plateau[nouvLig][nouvCol] = this.plateau[lig][col];
        this.plateau[lig][col] = null;
        return true;
    }



    private boolean deplacerReine(int lig, int col, int nouvLig, int nouvCol)
    {
        return deplacerTour(lig, col, nouvLig, nouvCol)
            || deplacerFou (lig, col, nouvLig, nouvCol);
    }


    

    public void setPiece(int lig, int col, Piece piece)
    {
        this.plateau[lig][col] = piece;
    }

    public Piece getPiece(int lig, int col)
    {
        return this.plateau[lig][col];
    }

    public boolean partieFinie()
    {
        int nbRoi = 0;
        Piece roi=null;

        for ( int lig=0; lig < 8; lig++ )
        {
            for ( int col=0; col < 8; col++ )
            {
                if ( this.plateau[lig][col]!=null && this.plateau[lig][col].getNom().equals("Roi") )
                {
                    nbRoi += 1;
                    roi = this.plateau[lig][col];
                }
            }
        }
        
        if ( nbRoi == 2 )
        {
            return false;
        }

        this.vainqueur = roi.getCouleur();
        
        return true;

            
    }


    public char getVainqueur()
    {
        return this.vainqueur;
    }
}
