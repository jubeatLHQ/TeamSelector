package mc.lhq.TeamSelector.UI.TeamPanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import mc.lhq.TeamSelector.PlayerData;

public class PlayerListCellRenderer implements ListCellRenderer {
	
	public static HashMap<String,Icon> icons = new HashMap<String,Icon>();
	public static HashMap<String,Icon> bigIcons = new HashMap<String,Icon>();
	
	public PlayerListCellRenderer(){
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		String name = value.toString();
		Player p = Bukkit.getPlayer(name);
		PlayerData pd = PlayerData.getPlayerData(p);
		
		JPanel panel = new JPanel(new GridLayout(1,2));
		if(isSelected){
			panel.setBackground(new Color(163, 225, 255));
		}else{
			panel.setBackground(Color.LIGHT_GRAY);
		}
		
        JLabel label = new JLabel(name);
        JLabel label2 = new JLabel(String.valueOf(pd.getKillPoint()));
        label2.setForeground(Color.GREEN);
        JLabel label3 = new JLabel(String.valueOf(pd.getDeathPoint()));
        label3.setForeground(Color.RED);
        JLabel label4 = new JLabel(String.valueOf(pd.getKd())+"%");
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
        Icon icon = getSmallFaceIcon(name);
        label.setIcon(icon);
        
        JPanel panel2 = new JPanel(new GridLayout(1,3));
        panel2.add(label2);
        panel2.add(label3);
        panel2.add(label4);
        
        panel.add(label);
        if(pd.getTeam()!=null){
        	panel.add(panel2);
        }
        return panel;
	}
	
    private Icon getSmallFaceIcon(String name) {
        BufferedImage skin = null;
        BufferedImage face = null;
        try{
        	if(icons.get(name)==null){
        		skin = ImageIO.read(new URL("http://s3.amazonaws.com/MinecraftSkins/" + name + ".png"));
        		face = skin.getSubimage(8, 8, 8, 8);
        		face = resize(face, 23, 23, true);
                if(skin!=null&face!=null){
        			icons.put(name, new ImageIcon(face));
        			bigIcons.put(name, new ImageIcon(resize(face,100,100,true)));
                }
        	}
        }catch(IOException e){
        	try {
        		skin = ImageIO.read(new File("plugins/TeamSelector/UI/char.png"));
        		face = skin.getSubimage(8, 8, 8, 8);
        		face = resize(face, 23, 23, true);
                if(skin!=null&face!=null){
        			icons.put(name, new ImageIcon(face));
        			bigIcons.put(name, new ImageIcon(resize(face,100,100,true)));
                }else{
        			icons.put(name, new ImageIcon());
        			bigIcons.put(name, new ImageIcon());
                }
			} catch (IOException e1) {}
        }
        return icons.get(name);
	}

	public static BufferedImage resize(Image originalImage,
            int scaledWidth, int scaledHeight, boolean preserveAlpha) {
    	int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
    	BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
    	Graphics2D g = scaledBI.createGraphics();
    	if(preserveAlpha) {
    		g.setComposite(AlphaComposite.Src);
    	}
    	g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
    	g.dispose();
    	return scaledBI;
    }
}
