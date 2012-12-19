package mc.lhq.TeamSelecter;

import java.util.ArrayList;
import java.util.List;

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
	
	public void setKillPoint(int value){
		killPoint = value;
	}
	public void setDeathPoint(int value){
		deathPoint = value;
	}
	public void setTeam(Team team){
		this.team = team;
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

}
