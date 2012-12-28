package mc.lhq.TeamSelector.UI.TeamPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import mc.lhq.TeamSelector.PlayerData;
import mc.lhq.TeamSelector.Team;
import mc.lhq.TeamSelector.UI.SelectorPanel;
import mc.lhq.TeamSelector.UI.Listeners.ButtonListeners;
import mc.lhq.TeamSelector.UI.Listeners.ListListeners;
import mc.lhq.TeamSelector.UI.Listeners.PVPListeners;

public class TeamPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private SelectorPanel root;
	
	private String name;
	private Team team;
	
	private NameButton defaultButton;
	private JTextField defaultName;
	private JPanel up;
	
	private PlayerList playerList;
	private DefaultListModel listModel;
	
	NameButton nameButton;
	PVPCheckBox pvpButton;
	
	private boolean isNull;
	private boolean isTeam;
	
	public SelectorPanel getRoot(){
		return root;
	}
	public String getName(){
		return name;
	}
	public Team getTeam(){
		return team;
	}
	public JPanel getUp(){
		return up;
	}
	public JTextField getDefaultName(){
		return defaultName;
	}
	public boolean isTeam(){
		return isTeam;
	}
	public PlayerList getPlayerList(){
		return playerList;
	}
	
	public TeamPanel(SelectorPanel root,boolean isNullTeam){
		this.root = root;
		this.isNull = isNullTeam;
		isTeam = isNullTeam;
		createPanel();
	}

	private void createPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		up = new JPanel();
		up.setLayout(new FlowLayout());
		this.add(up);
		JPanel down = new JPanel();
		this.add(down);
		
		listModel = new DefaultListModel();
		playerList = new PlayerList(listModel,this);
		playerList.setCellRenderer(new PlayerListCellRenderer());
		playerList.setSelectionBackground(Color.black);
		playerList.addListSelectionListener(new ListListeners());
		JScrollPane listBar = new JScrollPane(playerList);
		listBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listBar.setPreferredSize(new Dimension(215, 200));
		
		down.add(listBar);
		
		if(isNull){
			name = "none";
			putButtons(up,"none",null);
		}else{
			putDefault(up);
		}
		
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.setVisible(true);
	}
	
	public void putDefault(JPanel up) {
		ButtonListeners listener = new ButtonListeners();
		defaultButton = new NameButton("作成",this);
		defaultButton.addActionListener(listener);
		defaultName = new JTextField(8);
		up.add(defaultName);
		up.add(defaultButton);
		this.name = "";
		this.team = null;
		isTeam = false;
		up.invalidate();
		up.validate();
	}
	public void removeDefault(JPanel up){
		up.remove(defaultButton);
		up.remove(defaultName);
	}
	public void putButtons(JPanel up,String name,Team team){
		ButtonListeners listener = new ButtonListeners();
		nameButton = new NameButton(name,this);
		nameButton.addActionListener(listener);
		pvpButton = new PVPCheckBox(this,"PVP");
		pvpButton.addChangeListener(new PVPListeners());
		up.add(nameButton);
		up.add(pvpButton);
		if(isNull){
			pvpButton.setEnabled(false);
		}
		this.name = name;
		this.team = team;
		isTeam = true;
		up.invalidate();
		up.validate();
	}
	public void removeButtons(JPanel up){
		up.remove(nameButton);
		up.remove(pvpButton);
	}

	public GridBagConstraints getGBConstraints(int x, int y, int w, int h){
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        return gbc;
	}
    public DefaultListModel getListModel() {
        return listModel;
    }
    
    public void reloadRanking(){
    	if(team!=null){
    		playerList.clearSelection();
    		listModel = new DefaultListModel();
    		List<String> datas = PlayerData.getRankingFromPlayers(team.getPlist());
    		int u = 0;
    		while(u!=datas.size()){
    			listModel.addElement(datas.get(u));
    			u++;
    		}
    		playerList.setModel(listModel);
    		playerList.repaint();
    	}
    }

}
