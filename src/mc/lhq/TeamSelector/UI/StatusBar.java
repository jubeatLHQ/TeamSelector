package mc.lhq.TeamSelector.UI;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class StatusBar extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JLabel memory;

	public StatusBar(){
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		memory = new JLabel();
		this.add(memory);
		reloadMemory();
	}
	
	public void reloadMemory(){
		long total = Runtime.getRuntime().totalMemory();
		long free = Runtime.getRuntime().freeMemory();
		long used = (total-free)/1048576;
		memory.setText(String.valueOf(used)+"MB使用中");
	}

}
