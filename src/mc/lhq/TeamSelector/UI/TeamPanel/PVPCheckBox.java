package mc.lhq.TeamSelector.UI.TeamPanel;

import javax.swing.JCheckBox;


public class PVPCheckBox extends JCheckBox{
	private static final long serialVersionUID = 1L;
	
	private TeamPanel panel;
	
	public PVPCheckBox(TeamPanel tp,String name){
		this.panel = tp;
		this.setText(name);
	}
	
	public TeamPanel getPanel(){
		return panel;
	}

}
