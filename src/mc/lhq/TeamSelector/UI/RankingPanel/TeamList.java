package mc.lhq.TeamSelector.UI.RankingPanel;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class TeamList extends JList{
	private static final long serialVersionUID = 1L;
	
	private TeamRankingPanel panel;

	public TeamList(DefaultListModel listModel, TeamRankingPanel teamRankingPanel) {
		this.setModel(listModel);
		this.panel = teamRankingPanel;
	}
	
	public TeamRankingPanel getPanel(){
		return panel;
	}

}
