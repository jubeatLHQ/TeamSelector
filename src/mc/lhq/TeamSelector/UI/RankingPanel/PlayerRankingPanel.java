package mc.lhq.TeamSelector.UI.RankingPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

import mc.lhq.TeamSelector.PlayerData;
import mc.lhq.TeamSelector.UI.SelectorPanel;
import mc.lhq.TeamSelector.UI.Listeners.ListListeners;
import mc.lhq.TeamSelector.UI.TeamPanel.PlayerListCellRenderer;

public class PlayerRankingPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private SelectorPanel panel;
	
	private DefaultListModel listModel;
	private RankingPlayerList playerList;
	
	public RankingPlayerList getPlayerList(){
		return playerList;
	}
	public SelectorPanel getPanel(){
		return panel;
	}

	public PlayerRankingPanel(SelectorPanel selectorPanel){
		this.panel = selectorPanel;
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		createPanel();
		reloadRanking();
	}

	private void createPanel() {
		listModel = new DefaultListModel();
		playerList = new RankingPlayerList(listModel,this);
		playerList.setCellRenderer(new PlayerListCellRenderer());
		playerList.setSelectionBackground(Color.black);
		playerList.addListSelectionListener(new ListListeners());
		JScrollPane listBar = new JScrollPane(playerList);
		listBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listBar.setPreferredSize(new Dimension(350, 120));
		
		this.add(listBar);
	}
	
	public void reloadRanking(){
		playerList.clearSelection();
		listModel = new DefaultListModel();
		List<String> datas = PlayerData.getRanking();
		int u = 0;
		while(u!=datas.size()){
			listModel.addElement(datas.get(u));
			u++;
		}
		playerList.setModel(listModel);
		playerList.repaint();
	}

}
