package echec.ihm;

import javax.swing.JFrame;
import javax.swing.JLabel;

import echec.Controleur;

public class FramePlateau extends JFrame
{
    private Controleur ctrl;

    private PanelPlateau panelPlateau;

    private JLabel lblMessageJoueur;
    
    public FramePlateau(Controleur ctrl)
    {
        this.ctrl = ctrl;

        this.setSize(500, 500);
        this.setLocation(500,200);

        this.panelPlateau = new PanelPlateau(ctrl);
        this.add(this.panelPlateau);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void setLblJoueur()
    {
        this.lblMessageJoueur.setText( "C'est au joueur " + this.ctrl.getJoueurActuel() + " de jouer !" );
    }
}
