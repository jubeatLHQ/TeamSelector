package mc.lhq.TeamSelector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mc.lhq.TeamSelector.UI.SelectorPanel;
import mc.lhq.TeamSelector.UI.TeamPanel.TeamPanel;

import org.bukkit.entity.Player;

public class Team {
	
	public static List<Team> teamList = new ArrayList<Team>();
	
	private String name;
	private List<Player> pl;
	private int teamKills;
	private int teamDeaths;
	private boolean isStart;
	
	public Team(String name){
		this.name = name;
		this.pl = new ArrayList<Player>();
		this.teamKills = 0;
		this.teamDeaths = 0;
		this.isStart = false;
		teamList.add(this);
	}
	
	public String getName(){
		return name;
	}
	public List<Player> getPlist(){
		return pl;
	}
	public int getTeamKills(){
		return teamKills;
	}
	public int getTeamDeaths(){
		return teamDeaths;
	}
	public boolean isStart(){
		return isStart;
	}
	public int getKd() {
		if(teamKills!=0){
			double killd = (double)teamKills;
			double deathd = (double)teamDeaths;
			double killdeat = 0.0;
			killdeat = (killd/(killd+deathd))*100.0;
			return (int)killdeat;
		}else{
			return 0;
		}
	}
	
	public void setTeamKills(int value){
		teamKills = value;
	}
	public void setTeamDeaths(int value){
		teamDeaths = value;
	}
	public void onPVP(){
		int u = 0;
		while(u!=pl.size()){
			u++;
		}
		this.isStart = true;
	}
	public void offPVP(){
		int u = 0;
		while(u!=pl.size()){
			u++;
		}
		this.isStart = false;
	}
	
	public static Team getTeam(String name){
		int u = 0;
		while(u!=teamList.size()){
			Team t = teamList.get(u);
			if(t.getName().equals(name)){
				return t;
			}
			u++;
		}
		return null;
	}
	
	public static void addTeam(Team t,Player p){
		if(t!=null){
			t.getPlist().add(p);
			PlayerData.getPlayerData(p).setTeam(t);
		}
	}
	public static void removeTeam(Team t,Player p){
		if(t!=null){
			t.getPlist().remove(p);
			PlayerData.getPlayerData(p).setTeam(null);
		}
	}
	public static void changeTeam(Team t,Team changeT,Player p){
		PlayerData pd = PlayerData.getPlayerData(p);
		pd.setDeathPoint(0);
		pd.setKillPoint(0);
		removeTeam(t,p);
		addTeam(changeT,p);
	}
	public static void reloadRanking(){
		TeamSelector.mainWindow.getSelectorPanel().reloadRankings();
	}
	public static TeamPanel getPanel(Team team){
		List<TeamPanel> tpl = SelectorPanel.teamPanelList;
		int u = 0;
		while(u!=tpl.size()){
			Team t = tpl.get(u).getTeam();
			if(t!=null){
				if(t.equals(team)){
					return tpl.get(u);
				}
			}
			u++;
		}
		return null;
	}
	public static void deleteTeam(Team t){
		teamList.remove(t);
	}

	public static List<String> getRanking(){
		List<String> list = new ArrayList<String>();
		int u = 0;
		while(u!=teamList.size()){
			Team pd = teamList.get(u);
			list.add(String.valueOf(100-pd.getKd())+","+pd.getName());
			u++;
		}
		Collections.sort(list);
		List<String> datas = new ArrayList<String>();
		int a = 0;
		while(a!=list.size()){
			String[] strs = list.get(a).split(",");
			datas.add(strs[1]);
			a++;
		}
		return datas;
	}
}
