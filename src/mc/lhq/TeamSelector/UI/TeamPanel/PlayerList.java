package mc.lhq.TeamSelector.UI.TeamPanel;

import javax.swing.DefaultListModel;
import javax.swing.JList;


public class PlayerList extends JList{
	private static final long serialVersionUID = 1L;
	
	private TeamPanel root;

	public PlayerList(DefaultListModel listModel,TeamPanel panel){
		this.setModel(listModel);
		this.root = panel;
	}
	
	public TeamPanel getPanel(){
		return root;
	}

}
