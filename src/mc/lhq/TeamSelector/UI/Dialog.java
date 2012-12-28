package mc.lhq.TeamSelector.UI;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import mc.lhq.TeamSelector.UI.Listeners.ButtonListeners;

public class Dialog extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private Runnable task;
	
	public Dialog(){
		createDialog();
	}
	
	private void createDialog() {
		this.setVisible(false);
		this.setTitle("");
		this.setSize(240, 100);
		this.setResizable(false);
		
		JPanel panel = new JPanel(new GridLayout(1,2));

		JDialogButton b1 = new JDialogButton(this,"YES");
		JDialogButton b2 = new JDialogButton(this,"NO");
		b1.setBorder(new BevelBorder(BevelBorder.LOWERED));
		b2.setBorder(new BevelBorder(BevelBorder.LOWERED));
		ButtonListeners listener = new ButtonListeners();
		b1.addActionListener(listener);
		b2.addActionListener(listener);
		
		panel.add(b1);
		panel.add(b2);
		
		this.add(panel);
	}
	public void setTask(Runnable task){
		this.task = task;
	}
	
	public Runnable getTask(){
		return task;
	}

}
