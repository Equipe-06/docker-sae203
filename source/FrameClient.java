import javax.swing.*;
import java.awt.*;

public class FrameClient extends JFrame
{
    private PanelJoueur    panelJoueur;
	
	private Client         cli;

	public FrameClient ( Client cli )
    {	 
		this.setLayout(new FlowLayout());
		this.setTitle   ( "Battle Mania" );
		this.pack();

		this.cli = cli;

		this.setVisible(true);

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

	public void openPanelJoueur(String data) 
	{
		this.panelJoueur = new PanelJoueur(this.cli, data);
		this.add(this.panelJoueur);
	}

	public void update()
	{
		this.revalidate();
        this.repaint   ();
	}


}
