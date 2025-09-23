package code.ihm;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;

public class FrameFin extends JFrame implements ActionListener
{
    private JButton btnOk;

    public FrameFin( char vainqueur )
    {
        this.setLocation(1920/2-150, 1080/2-50);
        this.setSize(500, 100);
        this.setLayout( new BorderLayout() );

        JPanel panel1;
        JPanel panel2;

        panel1 = new JPanel();
        panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT) );

        this.btnOk = new JButton("Ok");

        String gagnant="";
        
        if ( vainqueur == 'N' )
            gagnant = "Noir";
        else
            gagnant = "Blanc";

        panel1.add ( new JLabel("Les vainqueurs sont les " + gagnant) );

        panel2.add(this.btnOk);

        this.btnOk.addActionListener(this);

        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public void actionPerformed( ActionEvent e)
    {
        if ( e.getSource() == this.btnOk )
        {
            System.exit(0);
        }
    }
}
