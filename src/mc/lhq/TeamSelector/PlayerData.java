package mc.lhq.TeamSelector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.SwingUtilities;

import mc.lhq.TeamSelector.UI.SelectorPanel;

import org.bukkit.entity.Player;

public class PlayerData {
	
	public static List<PlayerData> playerDatas = new ArrayList<PlayerData>();
	
	private Player player;
	private int killPoint;
	private int deathPoint;
	private Team team;
	
	public PlayerData(Player player){
		this.player = player;
		this.killPoint = 0;
		this.deathPoint = 0;
		this.team = null;
	}
	
	public Player getPlayer(){
		return player;
	}
	public int getKillPoint(){
		return killPoint;
	}
	public int getDeathPoint(){
		return deathPoint;
	}
	public Team getTeam(){
		return team;
	}
	public int getKd() {
		if(killPoint!=0){
			double killd = (double)killPoint;
			double deathd = (double)deathPoint;
			double killdeat = 0.0;
			killdeat = (killd/(killd+deathd))*100.0;
			return (int)killdeat;
		}else{
			return 0;
		}
	}
	
	public void setKillPoint(int value){
		killPoint = value;
	}
	public void setDeathPoint(int value){
		deathPoint = value;
	}
	public void setTeam(Team team){
		this.team = team;
	}
	public void resetPoints(){
		setKillPoint(0);
		setDeathPoint(0);
	}
	public static void addPlayer(final Player p) {
		PlayerData pd = new PlayerData(p);
		PlayerData.playerDatas.add(pd);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	SelectorPanel.addPlayer(TeamSelector.nullTeamPanel, p.getName());
            }
        });
	}
	
	public static PlayerData getPlayerData(Player p){
		int u = 0;
		while(u!=playerDatas.size()){
			PlayerData pd = playerDatas.get(u);
			if(pd.getPlayer().equals(p)){
				return pd;
			}
			u++;
		}
		return null;
	}
	public static PlayerData getPlayerData(String name){
		int u = 0;
		while(u!=playerDatas.size()){
			PlayerData pd = playerDatas.get(u);
			if(pd.getPlayer().getName().equals(name)){
				return pd;
			}
			u++;
		}
		return null;
	}
	
	public static List<String> getRanking(){
		List<String> list = new ArrayList<String>();
		int u = 0;
		while(u!=playerDatas.size()){
			PlayerData pd = playerDatas.get(u);
			if(pd.getTeam()!=null){
				list.add(String.valueOf(100-pd.getKd())+","+pd.getPlayer().getName());
			}
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
	public static List<String> getRankingFromPlayers(List<Player> pl){
		List<String> list = new ArrayList<String>();
		int u = 0;
		while(u!=pl.size()){
			PlayerData pd = PlayerData.getPlayerData(pl.get(u));
			list.add(String.valueOf(100-pd.getKd())+","+pd.getPlayer().getName());
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
	public static void reload(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				TeamSelector.mainWindow.getSelectorPanel().getStatusPanel().reloadPlayer();
			}
		});
	}
}
