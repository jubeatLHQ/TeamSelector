package mc.lhq.TeamSelecter;

import java.util.List;

import org.bukkit.entity.Player;

public class Team {
	
	private String name;
	private List<Player> pl;
	private int teamKills;
	private int teamDeaths;
	private boolean isStart;
	
	public Team(String name,List<Player> pl){
		this.name = name;
		this.pl = pl;
		this.teamKills = 0;
		this.teamDeaths = 0;
		this.isStart = false;
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
	
	public void setTeamKills(int value){
		teamKills = value;
	}
	public void setTeamDeaths(int value){
		teamDeaths = value;
	}
	public void setStart(boolean isStart){
		this.isStart = isStart;
	}

}
