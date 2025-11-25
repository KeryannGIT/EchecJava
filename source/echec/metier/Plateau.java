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
        
        
        if ( nouvLig < 0 || nouvLig > this.TAILLE ||
             nouvCol < 0 || nouvCol > this.TAILLE    ) return false;

        
        if ( this.plateau[nouvLig][nouvCol] != null && this.plateau[nouvLig][nouvCol].getCouleur() == this.plateau[lig][col].getCouleur() ) return false;

        switch ( this.plateau[lig][col].getNom() )
        {
            case "Pion" -> {
                
                int posLigP;

                if ( this.plateau[lig][col].getCouleur() == 'B' )
                {
                    posLigP = lig+1;
                }

                else
                {
                    posLigP = lig-1;
                }
                
                if ( nouvLig == posLigP && nouvCol == col && this.plateau[nouvLig][nouvCol] == null )
                {
                    this.plateau[nouvLig][nouvCol] = this.plateau[lig][col];
                    this.plateau[lig][col] = null;
                }

                else if ( nouvLig == posLigP && 
                        ( nouvCol == col+1 && this.plateau[posLigP][col+1]!=null && this.plateau[posLigP][col+1].getCouleur() != this.plateau[lig][col].getCouleur() || 
                        nouvCol == col-1 && this.plateau[posLigP][col-1]!=null && this.plateau[posLigP][col-1].getCouleur() != this.plateau[lig][col].getCouleur()    ) )
                {
                    this.plateau[nouvLig][nouvCol] = this.plateau[lig][col];
                    this.plateau[lig][col] = null;
                }

                /*else if ( nouvCol == col && this.plateau[lig+1][col] == null )
                {
                    if ( this.plateau[lig][col].getCouleur() == 'B' )
                    {
                        int posLig = 
                    }
                }*/

            }


            case "Tour" -> {
                this.verifMouvTour( lig, col, nouvLig, nouvCol);
                
            }

            case "Cavalier" -> {
                if ( nouvLig == lig + 1 && ( nouvCol == col-2 || nouvCol == col+2 ) ||
                     nouvLig == lig - 1 && ( nouvCol == col-2 || nouvCol == col+2 ) ||
                     nouvLig == lig + 2 && ( nouvCol == col-1 || nouvCol == col+1 ) ||
                     nouvLig == lig - 2 && ( nouvCol == col-1 || nouvCol == col+1 )    )
                {
                    this.plateau[nouvLig][nouvCol] = this.plateau[lig][col];
                    this.plateau[lig][col]   = null;
                }
            }

            case "Fou" -> {
                this.verifMouvFou( lig, col, nouvLig, nouvCol);
            }

            case "Roi" -> {
                if ( nouvLig == lig && ( nouvCol == col+1 || nouvCol == col-1 ) || 
                     nouvCol == col && ( nouvLig == lig+1 || nouvLig == lig-1 ) || 
                     ( nouvLig == lig+1 || nouvCol == col+1 )                  && 
                     ( nouvLig == lig-1 || nouvCol == col+1 )                      )
                {
                    this.plateau[nouvLig][nouvCol] = this.plateau[lig][col];
                    this.plateau[lig][col]   = null;
                }
            }

            case "Reine" -> {
                this.verifMouvTour( lig, col, nouvLig, nouvCol);
                this.verifMouvFou ( lig, col, nouvLig, nouvCol);
                
            }
        }

        if ( this.plateau[lig][col] != null )
            return false;



        return true;
        
    }

    public boolean verifMouvTour(int lig, int col, int nouvLig, int nouvCol)
    {
        if (lig != nouvLig && col != nouvCol) 
        {
            return false;
        }

        int dLig = Integer.compare(nouvLig, lig);
        int dCol = Integer.compare(nouvCol, col);

        int i = lig + dLig;
        int j = col + dCol;

        while (i != nouvLig || j != nouvCol) 
        {
            if (this.plateau[i][j] != null) 
            {
                return false; 
            }
            i += dLig;
            j += dCol;
        }


        if ( this.plateau[nouvLig][nouvCol] != null ) 
        {
            if ( this.plateau[nouvLig][nouvCol].getCouleur() == this.plateau[lig][col].getCouleur() )
            {
                return false; 
            }
        }

        
        this.plateau[nouvLig][nouvCol] = this.plateau[lig][col];
        this.plateau[lig][col] = null;

        return true;
    }

    public boolean verifMouvFou(int lig, int col, int nouvLig, int nouvCol)
    {
        
        if (lig == nouvLig || col == nouvCol) 
        {
            return false;
        }

        int dLig = Integer.compare(nouvLig, lig);
        int dCol = Integer.compare(nouvCol, col);

        int i = lig + dLig;
        int j = col + dCol;

        while ( i != nouvLig && j != nouvCol )
        {
            if ( this.plateau[i][j] != null ) { return false; }

            i += dLig;
            j += dCol;
                
        }
        
        
        
        if ( this.plateau[nouvLig][nouvCol] != null ) 
        {
            if ( this.plateau[nouvLig][nouvCol].getCouleur() == this.plateau[lig][col].getCouleur() )
            {
                return false; 
            }
        }

        
        this.plateau[nouvLig][nouvCol] = this.plateau[lig][col];
        this.plateau[lig][col] = null;

        return true;
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
