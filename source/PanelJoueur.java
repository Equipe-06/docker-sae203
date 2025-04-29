import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

public class PanelJoueur extends JPanel implements ActionListener {

	private Client     cli;

	private JPanel     panelNom;
	private JPanel     panelRobot;
	private JPanel     panelValider;

	private JLabel     lblInfo;
	private JTextField txtNom;
	private JButton    btnValider;

	private ButtonGroup btnGroup;

	List<JRadioButton>  listRobot;

	public PanelJoueur(Client cli, String data) {
		this.cli = cli;
		this.listRobot = new ArrayList<>();

		this.setLayout(new BorderLayout());

		// Création des composants
		this.panelNom     = new JPanel();

		this.panelRobot   = new JPanel();
		this.btnGroup     = new ButtonGroup();
		this.panelRobot.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.panelValider = new JPanel();

		// PanelNom
		this.lblInfo = new JLabel("Veuillez entrez votre pseudo : ");
		this.txtNom  = new JTextField(10);

		this.panelNom.add(this.lblInfo);
		this.panelNom.add(this.txtNom);

		// PanelRobot
		for (String nom : this.extractRobotNames(data)) {
			JRadioButton rbTemp = new JRadioButton(nom);
			this.listRobot.add(rbTemp);

			this.btnGroup  .add(rbTemp);
			this.panelRobot.add(rbTemp);
		}

		// PanelValider
		this.btnValider = new JButton("Valider");
		this.panelValider.add(this.btnValider);

		this.add(this.panelNom    , BorderLayout.NORTH);
		this.add(this.panelRobot  , BorderLayout.CENTER);
		this.add(this.panelValider, BorderLayout.SOUTH);

		this.btnValider.addActionListener(this);
	}

	public List<String> extractRobotNames(String data) {
		return List.of(data.split("&"));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnValider) {

			this.cli.sendResponse(this.txtNom.getText());
			for (JRadioButton rb : this.listRobot) {
				if (rb.isSelected()) {
					this.cli.sendResponse(rb.getText());
					break;
				}
			}

		}
	}
}
