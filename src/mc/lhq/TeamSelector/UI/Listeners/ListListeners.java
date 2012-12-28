package mc.lhq.TeamSelector.UI.Listeners;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mc.lhq.TeamSelector.Team;
import mc.lhq.TeamSelector.TeamSelector;
import mc.lhq.TeamSelector.UI.RankingPanel.RankingPlayerList;
import mc.lhq.TeamSelector.UI.RankingPanel.TeamList;
import mc.lhq.TeamSelector.UI.TeamPanel.PlayerList;

public class ListListeners implements ListSelectionListener {
	
	public static PlayerList lastUse = null;
	public static String lastSelected = null;
	public static boolean adjusting = false;
	
	public ListListeners(){
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		if(!event.getValueIsAdjusting()){
			return;
		}
		if(event.getSource() instanceof PlayerList){
			if(!adjusting){
				PlayerList jl = (PlayerList) event.getSource();
				if(jl.getSelectedValue()==null){
					return;
				}
				String name = (String) jl.getSelectedValue();
				TeamSelector.mainWindow.selectorPanel.lookupPlayer(name);
			}
		}else if(event.getSource() instanceof TeamList){
			TeamList tl = (TeamList) event.getSource();
			String name = (String) tl.getSelectedValue();
			Team t = Team.getTeam(name);
			tl.getPanel().setData(t);
		}else if(event.getSource() instanceof RankingPlayerList){
			if(!adjusting){
				RankingPlayerList list = (RankingPlayerList) event.getSource();
				if(list.getSelectedValue()==null){
					return;
				}
				TeamSelector.mainWindow.selectorPanel.lookupPlayer((String)list.getSelectedValue());
			}
		}
	}

}
