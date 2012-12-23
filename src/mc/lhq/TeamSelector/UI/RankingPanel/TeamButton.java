package mc.lhq.TeamSelector.UI.RankingPanel;

import javax.swing.JButton;

public class TeamButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	private TeamRankingPanel panel;
	
	public TeamButton(TeamRankingPanel trp,String name){
		this.panel = trp;
		this.setText(name);
	}
	
	public TeamRankingPanel getRoot(){
		return panel;
	}

}
