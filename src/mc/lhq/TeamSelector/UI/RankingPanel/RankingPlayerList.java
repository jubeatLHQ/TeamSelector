package mc.lhq.TeamSelector.UI.RankingPanel;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class RankingPlayerList extends JList{
	private static final long serialVersionUID = 1L;
	
	private PlayerRankingPanel root;
	
	public RankingPlayerList(DefaultListModel listModel,PlayerRankingPanel root) {
		this.setModel(listModel);
		this.root = root;
	}

	public PlayerRankingPanel getRoot(){
		return root;
	}

}
