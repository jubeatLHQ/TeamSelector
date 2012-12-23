package mc.lhq.TeamSelector.UI.Listeners;

import java.util.HashMap;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mc.lhq.TeamSelector.UI.TeamPanel.PVPCheckBox;
import mc.lhq.TeamSelector.UI.TeamPanel.TeamPanel;

public class PVPListeners implements ChangeListener {
	
	
	HashMap<PVPCheckBox,Boolean> booleanCheck = new HashMap<PVPCheckBox,Boolean>();

	@Override
	public void stateChanged(ChangeEvent event) {
		if(event.getSource() instanceof PVPCheckBox){
			PVPCheckBox button = (PVPCheckBox) event.getSource();
			if(booleanCheck.get(button)==null){
				booleanCheck.put(button, Boolean.valueOf(button.isSelected()));
			}
			if(booleanCheck.get(button).booleanValue()==button.isSelected()){
				return;
			}
			TeamPanel tp = button.getPanel();
			if(tp.getTeam()==null){
				return;
			}
			if(button.isSelected()){
				tp.getTeam().onPVP();
			}else{
				tp.getTeam().offPVP();
			}
			booleanCheck.put(button, Boolean.valueOf(button.isSelected()));
		}
	}

}
