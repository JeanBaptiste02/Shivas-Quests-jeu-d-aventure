package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.JPanel;
import configuration.GameConfiguration;

import motor.map.Map;
import motor.map.MapBuilder;
import motor.mobile.Joueur;
import motor.mobile.JoueurFictif;
import motor.objects.SuperObject;
import motor.process.MobileElementManager;

/**
 * 
 * @author D.JB E.SRI Z.VIC
 *
 */
@SuppressWarnings("serial")
public class GameDisplay extends JPanel {
	
	private boolean debugGrid = false; // permet de montrer la grille pour débuger facilement
	
	private Map map;
	private MobileElementManager manager;
	private PaintStrategy paintStrategy = new PaintStrategy();
	
	
	public GameDisplay(Map map, MobileElementManager manager) {
		this.map = map;
		this.manager = manager;
	}
	
	
	@Override
	public void paintComponent(Graphics g) { // comme si on avait un crayon pour dessiner
		super.paintComponent(g);
		
		
		Graphics2D g2 = (Graphics2D)g; // permet d'avoir plus de fonctionalité graphique
		
		Joueur player = manager.getPlayer();
		JoueurFictif point = manager.getPoint();
		
		//MAP
		paintStrategy.paint(map, player, point, g2);
		
		//OBJECT
		SuperObject[] object = manager.getObj();
		paintStrategy.paint(object, map, point, g2);
		
		//PLAYER
		paintStrategy.paint(player,g2);
		paintStrategy.paint(point, g2);
		
		if (debugGrid) {
			drawDebugGrid(g);
		}
		
		
	}
	
	private void drawDebugGrid(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		g.setColor(Color.BLACK);

		for (int i = GameConfiguration.BLOCK_SIZE; i <= width; i += GameConfiguration.BLOCK_SIZE) {
			g.drawLine(i, 1, i, height);
		}

		for (int i = GameConfiguration.BLOCK_SIZE; i <= height; i += GameConfiguration.BLOCK_SIZE) {
			g.drawLine(1, i, width, i);
		}
	}
	

	
}
