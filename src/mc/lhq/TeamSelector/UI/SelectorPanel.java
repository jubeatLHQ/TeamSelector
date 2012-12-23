package mc.lhq.TeamSelector.UI;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;

import org.bukkit.Bukkit;

import mc.lhq.TeamSelector.Team;
import mc.lhq.TeamSelector.TeamSelector;
import mc.lhq.TeamSelector.UI.RankingPanel.PlayerRankingPanel;
import mc.lhq.TeamSelector.UI.RankingPanel.TeamRankingPanel;
import mc.lhq.TeamSelector.UI.TeamPanel.TeamPanel;

public class SelectorPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static List<TeamPanel> teamPanelList = new ArrayList<TeamPanel>();
	private JTabbedPane tab;
	private JPanel teamsPanel;
	
	private JPanel up;
	private JPanel down;
	
	private TeamRankingPanel teamRankingPanel;
	private PlayerRankingPanel playerRankingPanel;

	public TeamRankingPanel getTeamRankingPanel(){
		return teamRankingPanel;
	}
	public SelectorPanel(LayoutManager layout){
		this.setLayout(layout);
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		up = new JPanel();
		up.setBorder(new BevelBorder(BevelBorder.LOWERED));
		tab = getAndAddTabBar(up);
		down = new JPanel();
		down.setLayout(new GridLayout(2,2));
		down.setBorder(new BevelBorder(BevelBorder.LOWERED));
		createDown(down);
		this.add(up);
		this.add(down);
	}
	public void removeTeamPanel(String name,TeamPanel panel){
		panel.removeButtons(panel.getUp());
		panel.putDefault(panel.getUp());
		teamRankingPanel.setData(null);
		teamRankingPanel.getListModel().removeElement(name);
	}
	
	private void createDown(JPanel down2) {
		teamRankingPanel = new TeamRankingPanel(this);
		playerRankingPanel = new PlayerRankingPanel(this);
		down.add(teamRankingPanel);
		down.add(playerRankingPanel);
	}

	private JTabbedPane getAndAddTabBar(JPanel selectorPanel) {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		selectorPanel.add(tabbedPane);
		return tabbedPane;
	}

	public void addTeamPanel(TeamPanel panel){
		int size = teamPanelList.size();
		if(size%4==0){
			int amount = size%4;
			teamsPanel = new JPanel();
			teamsPanel.setLayout(new GridLayout(1,4));
			tab.add("Tab"+String.valueOf(amount+1),teamsPanel);
		}
		teamsPanel.add(panel);
		teamPanelList.add(panel);
		TeamSelector.mainWindow.reloadMemory();
	}
	
	public void addComponent(Component part){
		this.add(part);
	}
	
	public List<TeamPanel> getTeamPanels(){
		return teamPanelList;
	}
	
	public static HashMap<Object,ImageIcon> onlinePlayers = new HashMap<Object,ImageIcon>();
	
	public static void addPlayer(TeamPanel tp,String name){
		tp.getListModel().addElement(name);
		TeamSelector.mainWindow.reloadMemory();
	}
	public static void removePlayer(TeamPanel tp,String name){
		tp.getListModel().removeElement(name);
		TeamSelector.mainWindow.reloadMemory();
	}
	public static void allRemovePlayer(String name){
		int u = 0;
		while(u!=teamPanelList.size()){
			removePlayer(teamPanelList.get(u),name);
			u++;
		}
	}
	public static void changeTeam(TeamPanel tp,TeamPanel changetp,String name){
		Team.changeTeam(tp.getTeam(), changetp.getTeam(), Bukkit.getPlayer(name));
		removePlayer(tp,name);
		addPlayer(changetp,name);
	}
	public static boolean isEx(String name){
		int u = 0;
		while(u!=teamPanelList.size()){
			TeamPanel tp = teamPanelList.get(u);
			if(tp.isTeam()){
				if(tp.getName().equalsIgnoreCase(name)){
					return true;
				}
			}
			u++;
		}
		return false;
	}

}
