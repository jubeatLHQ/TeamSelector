package mc.lhq.TeamSelector.UI.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.SwingUtilities;

import org.bukkit.entity.Player;

import mc.lhq.TeamSelector.PlayerData;
import mc.lhq.TeamSelector.Team;
import mc.lhq.TeamSelector.TeamSelector;
import mc.lhq.TeamSelector.UI.Dialog;
import mc.lhq.TeamSelector.UI.JDialogButton;
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
							TeamSelector.mainWindow.getSelectorPanel().lookupPlayer(name);
						}
					});
					Team.reloadRanking();
				}
			}else{
				final TeamPanel tp = nb.getPanel();
				final String name = tp.getDefaultName().getText();
				if(name.equalsIgnoreCase("")){
					return;
				}
				if(!SelectorPanel.isEx(name)){
					TeamSelector.mainWindow.showDialog("["+name+"]を作成します、よろしいですか？", new Runnable(){
						public void run(){
							Team t = new Team(name);
							tp.getRoot().getTeamRankingPanel().addTeam(t);
							tp.removeDefault(tp.getUp());
							tp.putButtons(tp.getUp(), name,t);
						}
					});
				}
			}
		}else if(event.getSource() instanceof TeamButton){
			TeamButton tb = (TeamButton) event.getSource();
			final TeamRankingPanel trp = tb.getRoot();
			String text = tb.getText();
			if(text.equalsIgnoreCase("reset")){
				TeamSelector.mainWindow.showDialog("ステータスをリセットしてよろしいですか？", new Runnable(){
					public void run(){
						Team t = trp.getTeam();
						t.setTeamDeaths(0);
						t.setTeamKills(0);
						int u = 0;
						while(u!=t.getPlist().size()){
							PlayerData pd = PlayerData.getPlayerData(t.getPlist().get(u));
							pd.resetPoints();
							u++;
						}
						Team.reloadRanking();
					}
				});
			}else if(text.equalsIgnoreCase("delete")){
				TeamSelector.mainWindow.showDialog("チームを削除してよろしいですか？", new Runnable(){
					public void run(){
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
				});
			}
		}else if(event.getSource() instanceof JDialogButton){
			JDialogButton jdb = (JDialogButton) event.getSource();
			Dialog dialog = jdb.getDialog();
			if(jdb.getText().equalsIgnoreCase("yes")){
				SwingUtilities.invokeLater(dialog.getTask());
			}
			dialog.setTask(null);
			dialog.setTitle("");
			dialog.setVisible(false);
		}
	}

}
