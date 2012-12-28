package mc.lhq.TeamSelector.UI.RankingPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import mc.lhq.TeamSelector.Team;
import mc.lhq.TeamSelector.UI.SelectorPanel;
import mc.lhq.TeamSelector.UI.Listeners.ButtonListeners;
import mc.lhq.TeamSelector.UI.Listeners.ListListeners;

public class TeamRankingPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private SelectorPanel root;
	
	private DefaultListModel listModel;
	private TeamList teamList;
	
	private JLabel killData;
	private JLabel deathData;
	private JLabel kdData;
	private TeamButton delete;
	private TeamButton reset;
	
	private Team lastTeam;

	public SelectorPanel getRoot(){
		return root;
	}
	public TeamRankingPanel(SelectorPanel selectorPanel){
		this.root = selectorPanel;
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		createPanel();
	}
	public Team getTeam(){
		return lastTeam;
	}

	private void createPanel() {
		this.setLayout(new GridLayout(1,2));
		
		JPanel left = new JPanel();
		listModel = new DefaultListModel();
		teamList = new TeamList(listModel,this);
		teamList.setCellRenderer(new RankingTeamListCellRenderer());
		teamList.setSelectionBackground(Color.black);
		teamList.addListSelectionListener(new ListListeners());
		JScrollPane listBar = new JScrollPane(teamList);
		listBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listBar.setPreferredSize(new Dimension(200, 120));
		left.add(listBar);
		
		JPanel right = new JPanel(new GridLayout(3,3));
		JLabel killTemp = new JLabel("kill");
		JLabel deathTemp = new JLabel("death");
		JLabel kdTemp = new JLabel("k/d");
		killData = new JLabel();
		deathData = new JLabel();
		kdData = new JLabel();
		
		ButtonListeners listeners = new ButtonListeners();
		delete = new TeamButton(this,"Delete");
		reset = new TeamButton(this,"Reset");
		delete.addActionListener(listeners);
		reset.addActionListener(listeners);
		
		right.add(killTemp);
		right.add(deathTemp);
		right.add(kdTemp);
		right.add(killData);
		right.add(deathData);
		right.add(kdData);
		right.add(delete);
		right.add(reset);

		this.add(left);
		this.add(right);
		
		setData(null);
		reloadRanking();
	}
	public void setData(Team t){
		String kill = "";
		String death = "";
		String kd = "";
		if(t!=null){
			teamList.setSelectedIndex(listModel.indexOf(t.getName()));
			kill = String.valueOf(t.getTeamKills());
			death = String.valueOf(t.getTeamDeaths());
			kd = String.valueOf(t.getKd())+"%";
			delete.setEnabled(true);
			reset.setEnabled(true);
		}else{
			teamList.clearSelection();
			delete.setEnabled(false);
			reset.setEnabled(false);
		}
		killData.setText(kill);
		deathData.setText(death);
		kdData.setText(kd);
		this.lastTeam = t;
	}
	public void reloadData(){
		if(lastTeam!=null){
			setData(lastTeam);
		}
	}
	public void reloadRanking(){
		teamList.clearSelection();
		listModel = new DefaultListModel();
		List<String> datas = Team.getRanking();
		int u = 0;
		while(u!=datas.size()){
			listModel.addElement(datas.get(u));
			u++;
		}
		teamList.setModel(listModel);
		teamList.repaint();
	}
	public void addTeam(Team t){
		listModel.addElement(t.getName());
	}
	
    public DefaultListModel getListModel() {
        return listModel;
    }

}
