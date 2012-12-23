package mc.lhq.TeamSelector.UI.TeamPanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
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
import javax.swing.ListCellRenderer;

public class PlayerListCellRenderer implements ListCellRenderer {
	
	public static HashMap<String,Icon> icons = new HashMap<String,Icon>();
	public static HashMap<String,Icon> bigIcons = new HashMap<String,Icon>();
	
	public PlayerListCellRenderer(){
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
        BufferedImage skin = null;
        BufferedImage face = null;
        try{
        	if(icons.get(name)==null){
        		skin = ImageIO.read(new URL("http://s3.amazonaws.com/MinecraftSkins/" + name + ".png"));
        		face = skin.getSubimage(8, 8, 8, 8);
        		face = resize(face, 23, 23, true);
                if(skin!=null&face!=null){
        			icons.put(name, new ImageIcon(face));
        			bigIcons.put(name, new ImageIcon(resize(face,50,50,true)));
                }else{
        			icons.put(name, new ImageIcon());
        			bigIcons.put(name, new ImageIcon());
                }
        	}
        }catch(IOException e){
        	try {
        		skin = ImageIO.read(new File("plugins/TeamSelector/char.png"));
        		face = skin.getSubimage(8, 8, 8, 8);
        		face = resize(face, 23, 23, true);
                if(skin!=null&face!=null){
        			icons.put(name, new ImageIcon(face));
        			bigIcons.put(name, new ImageIcon(resize(face,50,50,true)));
                }else{
        			icons.put(name, new ImageIcon());
        			bigIcons.put(name, new ImageIcon());
                }
			} catch (IOException e1) {}
        }
        Icon icon = icons.get(name);
        label.setIcon(icon);
        return label;
	}
	
    public BufferedImage resize(Image originalImage,
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
