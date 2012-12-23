package mc.lhq.TeamSelector.UI.RankingPanel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class TeamListCellRenderer implements ListCellRenderer {
	
	public TeamListCellRenderer(){
	
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		String name = value.toString();
        JLabel label = new JLabel(name);
        label.setOpaque(true);
        if(isSelected) {
        	label.setForeground(Color.BLACK);
        	label.setBackground(new Color(163, 225, 255));
        }else{
        	label.setBackground(Color.LIGHT_GRAY);
        }
        return label;
	}

}
