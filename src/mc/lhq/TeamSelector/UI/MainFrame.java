package mc.lhq.TeamSelector.UI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame extends JFrame{
	
	public SelectorPanel selectorPanel;
	
	private static final long serialVersionUID = 1L;
	public JTabbedPane tab;
	public StatusBar statusBar;


	public MainFrame(String name){
		setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle(name);
		this.setSize(990, 615);
		this.setResizable(false);
		this.setVisible(true);
		tab = getAndAddTabBar(this);
		statusBar = getAndAddStatusBar(this);
	}

	private void setLookAndFeel(String feel) {
		try {
			UIManager.setLookAndFeel(feel);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public StatusBar getAndAddStatusBar(MainFrame mainFrame) {
		StatusBar bar = new StatusBar();
		mainFrame.getContentPane().add(bar, BorderLayout.PAGE_END);
		return bar;
	}
	public StatusBar getStatusBar(){
		return statusBar;
	}
	public void reloadMemory(){
		statusBar.reloadMemory();
	}

	public JTabbedPane getAndAddTabBar(MainFrame mainFrame) {
		JTabbedPane tabbedPane = new JTabbedPane();
		mainFrame.getContentPane().add(tabbedPane);
		return tabbedPane;
	}
	
	public void addPanelToTab(String name,JPanel panel){
		tab.add(name,panel);
		if(panel instanceof SelectorPanel){
		 selectorPanel = (SelectorPanel) panel;
		}
	}
	
	public SelectorPanel getSelectorPanel(){
		return selectorPanel;
	}


}
