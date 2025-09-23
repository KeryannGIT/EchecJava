package code.ihm;

import javax.swing.JFrame;

import java.awt.event.*;

import code.Controleur;

public class FramePlateau extends JFrame
{

    //private Controleur ctrl;
    private PanelPlateau panelPlateau;
    
    public FramePlateau(Controleur ctrl)
    {
        this.setSize(500, 500);
        this.setLocation(500,200);

        this.panelPlateau = new PanelPlateau(ctrl);

        this.add(this.panelPlateau);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
