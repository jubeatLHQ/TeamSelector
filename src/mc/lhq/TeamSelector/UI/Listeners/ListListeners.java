package mc.lhq.TeamSelector.UI.Listeners;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mc.lhq.TeamSelector.Team;
import mc.lhq.TeamSelector.TeamSelector;
import mc.lhq.TeamSelector.UI.RankingPanel.TeamList;
import mc.lhq.TeamSelector.UI.TeamPanel.PlayerList;

public class ListListeners implements ListSelectionListener {
	
	public static PlayerList lastUse = null;
	public static String lastSelected = null;
	
	public ListListeners(){
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		if(event.getSource() instanceof PlayerList){
			if(!event.getValueIsAdjusting()){
				return;
			}
			PlayerList jl = (PlayerList) event.getSource();
			if(lastSelected!=null){
				lastUse.clearSelection();
				lastSelected = null;
			}
			lastUse = jl;
			lastSelected = (String) jl.getSelectedValue();
			TeamSelector.mainWindow.getSelectorPanel().getTeamRankingPanel().setData(lastUse.getPanel().getTeam());
		}else if(event.getSource() instanceof TeamList){
			if(!event.getValueIsAdjusting()){
				return;
			}
			TeamList tl = (TeamList) event.getSource();
			String name = (String) tl.getSelectedValue();
			Team t = Team.getTeam(name);
			tl.getPanel().setData(t);
		}
	}

}
