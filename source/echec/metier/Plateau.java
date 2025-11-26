package echec.metier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Plateau implements Iterable<Piece>
{
    private final int TAILLE = 8;

    private char vainqueur;
    private char joueurActuel;
    
    private List<Piece> lstPiece;
    
    public Plateau()
    {
        this.lstPiece = new ArrayList<Piece>();
        this.initJoueurDepart();
        this.initialiserPlateau();
    }

    /*-------------------------------------------*/
    /* Classe permettant d'initialiser la partie */
    /*-------------------------------------------*/

    public void initJoueurDepart()
    {
        int i = (int)(Math.random() * 2);
        if ( i== 0 ) this.joueurActuel = 'B';
        else this.joueurActuel = 'N';
    }

    public void initialiserPlateau()
    {        
        int ligBlanc=1, ligNoir=this.TAILLE-2;
        
        for ( int ind=0; ind < this.TAILLE; ind++)
        {
            this.lstPiece.add( new Pion('B', 1, ind) );
            this.lstPiece.add( new Pion('N', 6, ind) );
        }

        ligBlanc=0;
        ligNoir=TAILLE-1;

        this.initPlacement(ligBlanc, 'B');
        this.initPlacement(ligNoir, 'N');

    }

    public void initPlacement( int ligne, char couleur )
    {
        /* Placement des pièces sur le plateau */

        this.lstPiece.add( new Tour    ( couleur, ligne, 0 ) );
        this.lstPiece.add( new Cavalier( couleur, ligne, 1 ) );
        this.lstPiece.add( new Fou     ( couleur, ligne, 2 ) );
        this.lstPiece.add( new Reine   ( couleur, ligne, 3 ) );
        this.lstPiece.add( new Roi     ( couleur, ligne, 4 ) );
        this.lstPiece.add( new Fou     ( couleur, ligne, 5 ) );
        this.lstPiece.add( new Cavalier( couleur, ligne, 6 ) );
        this.lstPiece.add( new Tour    ( couleur, ligne, 7 ) );
    }

    /*-------------------------------------------------*/
    /* Méthodes permettant le déroulement de la partie */
    /*-------------------------------------------------*/

    public boolean deplacerPiece(int lig, int col, int nouvLig, int nouvCol)
    {
        if ( this.getPiece(lig, col).getCouleur() != this.joueurActuel ) return false;

        if (nouvLig < 0 || nouvLig >= this.TAILLE ||
            nouvCol < 0 || nouvCol >= this.TAILLE)
            return false;

        Piece piece = this.getPiece(lig, col);
        if (piece == null) return false;

        if (this.getPiece(nouvLig, nouvCol) != null &&
            this.getPiece(nouvLig, nouvCol).getCouleur() == piece.getCouleur())
            return false;

        if ( ! piece.mouvementValide( this, nouvLig, nouvCol ) )
            return false;
        
        if ( this.getPiece(nouvLig, nouvCol) != null )
        {
            this.lstPiece.remove(this.getPiece(nouvLig, nouvCol));
        }
        this.deplacement( lig, col, nouvLig, nouvCol );
        return true;
    }

    private void deplacement( int lig, int col, int nouvLig, int nouvCol )
    {
        Piece piece = this.getPiece(lig, col);

        this.getPiece(lig, col).addDeplacement();
        piece.setX(nouvLig);
        piece.setY(nouvCol);
        this.joueurActuel = (this.joueurActuel == 'N') ? 'B' : 'N';
    }

    public boolean estVide( int x, int y )
    {
        boolean bRet = true;
        for ( Piece piece : this.lstPiece )
        {
            if ( piece.getX() == x && piece.getY() == y )
                bRet = false;
        }

        return bRet;
    }

    public boolean partieFinie()
    {
        int nbRoi = 0;
        Piece roi=null;

        for ( int lig=0; lig < 8; lig++ )
        {
            for ( int col=0; col < 8; col++ )
            {
                if ( this.getPiece(lig, col ) != null && this.getPiece( lig, col ) instanceof Roi )
                {
                    nbRoi += 1;
                    roi = this.getPiece(lig, col);
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


    /*-------------------------*/
    /* Iterator pour la classe */
    /*-------------------------*/
    
    public Iterator<Piece> iterator()
    {
        return new IterPlateau();
    }
    
    /*-----------------------------------*/
    /*             Setters               */
    /*-----------------------------------*/
    public void setPiece( Piece piece )
    {
        this.lstPiece.add( piece );
    }

    /*-----------------------------------*/
    /*             Getters               */
    /*-----------------------------------*/

    public Piece getPiece(int lig, int col)
    {
        for ( Piece p : this.lstPiece )
        {
            if ( p.getX() == lig && p.getY() == col ) 
                return p;
        }

        return null;
    }

    public char getVainqueur()
    {
        return this.vainqueur;
    }

    public char getJoueurActuel()
    {
        return this.joueurActuel;
    }

    /*----------------------------------------------------------*/
    /* Classe interne afin de rendre la classe Plateau Iterable */
    /*----------------------------------------------------------*/
    private class IterPlateau implements Iterator<Piece>
    {
        private int cpt=0;

        public boolean hasNext()
        {
            return this.cpt < Plateau.this.lstPiece.size();
        }

        public Piece next()
        {
            return Plateau.this.lstPiece.get( this.cpt++ );
        }
    } 
}

