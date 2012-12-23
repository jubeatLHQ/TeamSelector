package mc.lhq.TeamSelector.UI.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingUtilities;

import org.bukkit.entity.Player;

import mc.lhq.TeamSelector.Team;
import mc.lhq.TeamSelector.TeamSelector;
import mc.lhq.TeamSelector.UI.SelectorPanel;
import mc.lhq.TeamSelector.UI.RankingPanel.TeamButton;
import mc.lhq.TeamSelector.UI.RankingPanel.TeamRankingPanel;
import mc.lhq.TeamSelector.UI.TeamPanel.NameButton;
import mc.lhq.TeamSelector.UI.TeamPanel.PlayerList;
import mc.lhq.TeamSelector.UI.TeamPanel.TeamPanel;

public class ButtonListeners implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() instanceof NameButton){
			NameButton nb = (NameButton) event.getSource();
			if(nb.getPanel().isTeam()){
				final TeamPanel tpMove = nb.getPanel();
				PlayerList listRoot = ListListeners.lastUse;
				if(listRoot!=null){
					final TeamPanel tpRoot = listRoot.getPanel();
					final String name = ListListeners.lastSelected;
					listRoot.clearSelection();
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							SelectorPanel.changeTeam(tpRoot, tpMove, name);
						}
					});
					ListListeners.lastSelected = null;
					ListListeners.lastUse = null;
					TeamSelector.mainWindow.getSelectorPanel().getTeamRankingPanel().setData(tpMove.getTeam());
				}
			}else{
				TeamPanel tp = nb.getPanel();
				String name = tp.getDefaultName().getText();
				if(name.equalsIgnoreCase("")){
					return;
				}
				if(!SelectorPanel.isEx(name)){
					Team t = new Team(name);
					tp.getRoot().getTeamRankingPanel().addTeam(t);
					tp.removeDefault(tp.getUp());
					tp.putButtons(tp.getUp(), name,t);
				}
			}
		}else if(event.getSource() instanceof TeamButton){
			TeamButton tb = (TeamButton) event.getSource();
			TeamRankingPanel trp = tb.getRoot();
			String text = tb.getText();
			if(text.equalsIgnoreCase("reset")){
				Team t = trp.getTeam();
				t.setTeamDeaths(0);
				t.setTeamKills(0);
				Team.reloadRanking();
			}else if(text.equalsIgnoreCase("delete")){
				Team t = trp.getTeam();
				TeamPanel tp = Team.getPanel(t);
				final String name = tp.getName();
				TeamPanel changetp = TeamSelector.nullTeamPanel;
				List<Player> pl = t.getPlist();
				while(pl.size()!=0){
					SelectorPanel.changeTeam(tp, changetp, pl.get(0).getName());
				}
				Team.deleteTeam(t);
				TeamSelector.mainWindow.getSelectorPanel().removeTeamPanel(name,tp);
			}
		}
	}

}
