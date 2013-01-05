package mc.lhq.TeamSelector.UI.RankingPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import mc.lhq.TeamSelector.Team;

public class RankingTeamListCellRenderer implements ListCellRenderer {
	
	public RankingTeamListCellRenderer(){
	
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		String name = value.toString();
		Team t = Team.getTeam(name);
		
		JPanel panel = new JPanel(new GridLayout(1,2));
		if(isSelected){
			panel.setBackground(new Color(163, 225, 255));
		}else{
			panel.setBackground(Color.LIGHT_GRAY);
		}
		
        JLabel label = new JLabel(name);
        JLabel label2 = new JLabel(String.valueOf(t.getTeamKills()));
        label2.setForeground(Color.GREEN);
        JLabel label3 = new JLabel(String.valueOf(t.getTeamDeaths()));
        label3.setForeground(Color.RED);
        JLabel label4 = new JLabel(String.valueOf(t.getKd())+"%");
        label4.setForeground(Color.YELLOW);
        label.setOpaque(true);
        label2.setOpaque(true);
        label3.setOpaque(true);
        label4.setOpaque(true);
        if(isSelected) {
        	label.setBackground(new Color(163, 225, 255));
        	label2.setBackground(new Color(163, 225, 255));
        	label3.setBackground(new Color(163, 225, 255));
        	label4.setBackground(new Color(163, 225, 255));
        }else{
        	label.setBackground(Color.LIGHT_GRAY);
        	label2.setBackground(Color.LIGHT_GRAY);
        	label3.setBackground(Color.LIGHT_GRAY);
        	label4.setBackground(Color.LIGHT_GRAY);
        }
        
        JPanel panel2 = new JPanel(new GridLayout(1,3));
        panel2.add(label2);
        panel2.add(label3);
        panel2.add(label4);
        
        panel.add(label);
        panel.add(panel2);
        
        return panel;
	}

}
