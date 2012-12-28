package mc.lhq.TeamSelector.UI;

import javax.swing.JButton;

public class JDialogButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	private Dialog dialog;

	public JDialogButton(Dialog dialog,String string) {
		this.setText(string);
		this.dialog = dialog;
	}
	
	public Dialog getDialog(){
		return dialog;
	}

}
