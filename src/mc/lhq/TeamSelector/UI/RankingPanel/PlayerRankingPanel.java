package mc.lhq.TeamSelector.UI.RankingPanel;

import javax.swing.JPanel;

import mc.lhq.TeamSelector.UI.SelectorPanel;

public class PlayerRankingPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private SelectorPanel panel;
	
	public SelectorPanel getPanel(){
		return panel;
	}

	public PlayerRankingPanel(SelectorPanel selectorPanel){
		this.panel = selectorPanel;
		createPanel();
	}

	private void createPanel() {
	}

}
