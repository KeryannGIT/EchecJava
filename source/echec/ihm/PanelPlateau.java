package echec.ihm;

import echec.Controleur;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;

public class PanelPlateau extends JPanel
{
    private Controleur ctrl;

    private JLabel[][] tabLabel;

    public PanelPlateau(Controleur ctrl)
    {
        this.setLayout( new GridLayout(8, 8) );
        this.ctrl = ctrl;

        this.creerPlateau();

        this.majIHM();

        for ( int lig=0; lig < 8; lig++ )
            for ( int col=0; col < 8; col++ )
                this.add(this.tabLabel[lig][col]);

        GereSouris gereSouris = new GereSouris();
        for ( int lig=0; lig < 8; lig++ )
            for ( int col=0; col < 8; col++ )
                this.tabLabel[lig][col].addMouseListener(gereSouris);
       
        JOptionPane.showMessageDialog(       null, 
                                            "C'est au joueur " + this.ctrl.getJoueurActuel() + " de commencer !", 
                                            "Premier joueur", 
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
    }

    public void creerPlateau()
    {
        Color blanc = Color.WHITE;
        Color noir  = Color.GRAY;

        this.tabLabel = new JLabel[8][8];
        for ( int lig=0; lig < 8; lig++ )
            for ( int col=0; col < 8; col++ )
                this.tabLabel[lig][col] = new JLabel();
        

        Color couleur;
        for ( int lig=0; lig < 8; lig++ )
        {
            if ( lig%2 == 0 )
                couleur = blanc;
            else
                couleur = noir;
            
            for ( int col=0; col < 8; col++ )
            {
                this.tabLabel[lig][col].setBackground(couleur);
                this.tabLabel[lig][col].setOpaque(true);
                if ( couleur == noir )
                    couleur = blanc;
                else
                    couleur = noir;
            }

        }
    }

    public void majIHM()
    {
        ImageIcon icon;
        Image image;

        for ( int lig=0; lig < 8; lig++ )
        {
            for ( int col=0; col < 8; col++ )
            {
                if ( this.ctrl.getPiece(lig, col) != null )
                {
                    String typePiece = "" + this.ctrl.getPiece(lig, col).getClass();
                    typePiece = typePiece.substring(19);

                    String chemin = "../images/" + typePiece + this.ctrl.getPiece(lig, col).getCouleur() + ".png" ;
                    icon =  new ImageIcon(chemin);
                    image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    
                    this.tabLabel[lig][col].setIcon( new ImageIcon ( image )  );
                    this.tabLabel[lig][col].setHorizontalAlignment(JLabel.CENTER);
                    this.tabLabel[lig][col].setVerticalAlignment(JLabel.CENTER);
                }
            }
        }
            
    }

    private class GereSouris extends MouseAdapter
    {
        private int ligDepart = -1;
        private int colDepart = -1;

        private Color couleurBase;

        public void mouseClicked(MouseEvent e)
        {            
            for ( int lig=0; lig<8; lig++ )
            {
                for ( int col=0; col < 8; col++ )
                {
                    if ( e.getSource() == PanelPlateau.this.tabLabel[lig][col] )
                    {
                        if ( this.ligDepart == -1 && this.colDepart == -1 )
                        {
                            if ( PanelPlateau.this.tabLabel[lig][col].getIcon() != null )
                            {
                                this.ligDepart = lig;
                                this.colDepart = col;
                                this.couleurBase = PanelPlateau.this.tabLabel[lig][col].getBackground();
                                PanelPlateau.this.tabLabel[lig][col].setBackground(Color.YELLOW);
                            }
                        }
                        else
                        {
                            if ( PanelPlateau.this.ctrl.deplacerPiece(this.ligDepart, this.colDepart, lig, col) )
                            {
                                PanelPlateau.this.tabLabel[ligDepart][colDepart].setIcon(null);
                            }

                            PanelPlateau.this.tabLabel[ligDepart][colDepart].setBackground(this.couleurBase);
                            this.ligDepart = -1;
                            this.colDepart = -1;
                            PanelPlateau.this.majIHM();

                            if ( PanelPlateau.this.ctrl.partieFinie() )
                            {
                                new FrameFin( PanelPlateau.this.ctrl.getVainqueur() );
                            }
                        }
                    }
                }
            }

            
        }
    }

}
