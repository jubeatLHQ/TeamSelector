package mc.lhq.TeamSelector.UI.StatusPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import mc.lhq.TeamSelector.PlayerData;
import mc.lhq.TeamSelector.UI.SelectorPanel;
import mc.lhq.TeamSelector.UI.TeamPanel.PlayerListCellRenderer;

public class StatusPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private SelectorPanel root;
	
	private JPanel left;
	private JPanel right;
	
	private Icon healthIconMax;
	private Icon healthIconHalf;
	private Icon healthIconNone;
	private Icon staminaIconMax;
	private Icon staminaIconHalf;
	private Icon staminaIconNone;
	
	private JLabel icon;
	private JLabel playerName;
	private JLabel teamName;
	private List<JLabel> healths = new ArrayList<JLabel>();
	private List<JLabel> staminas = new ArrayList<JLabel>();
	private JLabel kill;
	private JLabel death;
	private JLabel kd;
	
	private String name;
	
	public SelectorPanel getRoot(){
		return root;
	}
	public String getPlayerName(){
		return name;
	}
	public StatusPanel(SelectorPanel root){
		this.root = root;
		loadIcons();
		createPanel();
		setPlayer("");
	}
	
	private void loadIcons() {
        BufferedImage healthMaxBI = null;
        BufferedImage healthHalfBI = null;
        BufferedImage healthNoneBI = null;
        BufferedImage staminaMaxBI = null;
        BufferedImage staminaHalfBI = null;
        BufferedImage staminaNoneBI = null;
        try{
        	healthMaxBI = ImageIO.read(new File("plugins/TeamSelector/UI/health_max.png"));
        	healthHalfBI = ImageIO.read(new File("plugins/TeamSelector/UI/health_half.png"));
        	healthNoneBI = ImageIO.read(new File("plugins/TeamSelector/UI/health_none.png"));
        	staminaMaxBI = ImageIO.read(new File("plugins/TeamSelector/UI/stamina_max.png"));
        	staminaHalfBI = ImageIO.read(new File("plugins/TeamSelector/UI/stamina_half.png"));
        	staminaNoneBI = ImageIO.read(new File("plugins/TeamSelector/UI/stamina_none.png"));
        	
        	healthIconMax = new ImageIcon(healthMaxBI);
        	healthIconHalf = new ImageIcon(healthHalfBI);
        	healthIconNone = new ImageIcon(healthNoneBI);
        	staminaIconMax = new ImageIcon(staminaMaxBI);
        	staminaIconHalf = new ImageIcon(staminaHalfBI);
        	staminaIconNone = new ImageIcon(staminaNoneBI);
        }catch(IOException e){
        	healthIconMax = new ImageIcon();
        	healthIconHalf = new ImageIcon();
        	healthIconNone = new ImageIcon();
        	staminaIconMax = new ImageIcon();
        	staminaIconHalf = new ImageIcon();
        	staminaIconNone = new ImageIcon();
        }
	}

	private void createPanel() {
		this.setLayout(new GridLayout(1,2));
		
		left = new JPanel(new FlowLayout());
		
		JPanel leftLeft = new JPanel();
		leftLeft.setPreferredSize(new Dimension(110,110));
		leftLeft.setBorder(new BevelBorder(BevelBorder.LOWERED));
		icon = new JLabel("");
		leftLeft.add(icon,BorderLayout.CENTER);
		
		JPanel leftRight = new JPanel(new GridLayout(4,1));
		
		JPanel panel1 = new JPanel(new GridLayout(1,3));
		playerName = new JLabel("");
		JLabel split1 = new JLabel("   team:");
		teamName = new JLabel("");
		panel1.add(playerName);
		panel1.add(split1);
		panel1.add(teamName);
		
		JPanel panel2 = new JPanel(new FlowLayout());
		JPanel panel20 = new JPanel();
		panel20.setPreferredSize(new Dimension(50,17));
		panel20.add(new JLabel("health:"),BorderLayout.NORTH);
		JPanel panel21 = new JPanel(new GridLayout(1,10));
		int u = 0;
		while(u!=10){
			JLabel label = new JLabel();
			label.setIcon(healthIconNone);
			panel21.add(label);
			healths.add(label);
			u++;
		}
		panel2.add(panel20);
		panel2.add(panel21);
		
		JPanel panel3 = new JPanel(new FlowLayout());
		JPanel panel30 = new JPanel();
		panel30.setPreferredSize(new Dimension(50,17));
		panel30.add(new JLabel("stamina:"),BorderLayout.NORTH);
		JPanel panel31 = new JPanel(new GridLayout(1,10));
		int a = 0;
		while(a!=10){
			JLabel label = new JLabel();
			label.setIcon(staminaIconNone);
			panel31.add(label,0);
			staminas.add(label);
			a++;
		}
		panel3.add(panel30);
		panel3.add(panel31);
		
		JPanel panel4 = new JPanel(new GridLayout(2,3));
		panel4.add(new JLabel("kill"));
		panel4.add(new JLabel("death"));
		panel4.add(new JLabel("k/d"));
		kill = new JLabel("");
		death = new JLabel("");
		kd = new JLabel("");
		panel4.add(kill);
		panel4.add(death);
		panel4.add(kd);
		
		leftRight.add(panel1);
		leftRight.add(panel2);
		leftRight.add(panel3);
		leftRight.add(panel4);
		
		left.add(leftLeft);
		left.add(leftRight);
		
		right = new JPanel();
		right.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.add(left);
		this.add(right);
	}

	public void setPlayer(String name){
		Player p = null;
		if(name!=null){
			p = Bukkit.getPlayer(name);
		}
		if(p!=null){
			PlayerData pd = PlayerData.getPlayerData(p);
			setIcon(PlayerListCellRenderer.bigIcons.get(name));
			setPlayerName(name,pd);
			setHealth(p.getHealth());
			setStamina(p.getFoodLevel());
			setData(pd);
		}else{
			setIcon(new ImageIcon());
			setPlayerName("",null);
			setHealth(0);
			setStamina(0);
			setData(null);
		}
		this.name = name;
	}
	
	public void reloadPlayer(){
		setPlayer(name);
	}
	
	private void setIcon(Icon playerIcon){
		icon.setIcon(playerIcon);
	}
	private void setPlayerName(String name,PlayerData pd){
		playerName.setText(name);
		if(pd!=null){
			if(pd.getTeam()!=null){
				teamName.setText(pd.getTeam().getName());
			}else{
				teamName.setText("");
			}
		}else{
			teamName.setText("");
		}
	}
	private void setHealth(int i){
		int u = 0;
		while(u!=healths.size()){
			JLabel label = healths.get(u);
			if(i>=2){
				label.setIcon(healthIconMax);
				i = i-2;
			}else if(i==1){
				label.setIcon(healthIconHalf);
				i = i-1;
			}else{
				label.setIcon(healthIconNone);
			}
			u++;
		}
	}
	private void setStamina(int i){
		int u = 0;
		while(u!=staminas.size()){
			JLabel label = staminas.get(u);
			if(i>=2){
				label.setIcon(staminaIconMax);
				i = i-2;
			}else if(i==1){
				label.setIcon(staminaIconHalf);
				i = i-1;
			}else{
				label.setIcon(staminaIconNone);
			}
			u++;
		}
	}
	private void setData(PlayerData pd){
		if(pd!=null){
			kill.setText(String.valueOf(pd.getKillPoint()));
			death.setText(String.valueOf(pd.getDeathPoint()));
			kd.setText(String.valueOf(pd.getKd())+"%");
		}else{
			kill.setText("");
			death.setText("");
			kd.setText("");
		}
	}

}
