package mc.lhq.TeamSelector.UI.StatusPanel;

import javax.swing.JButton;

public class StatusButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	private StatusPanel root;
	
	public StatusButton(StatusPanel root,String name){
		this.root = root;
		this.setText(name);
	}
	
	public StatusPanel getRoot(){
		return root;
	}

}
