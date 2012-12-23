package mc.lhq.TeamSelector.UI.TeamPanel;

import javax.swing.JButton;


public class NameButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	private TeamPanel root;
	
	public NameButton(String name,TeamPanel panel){
		this.root = panel;
		this.setText(name);
	}
	public TeamPanel getPanel(){
		return root;
	}

}
