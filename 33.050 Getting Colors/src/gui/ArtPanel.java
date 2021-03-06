package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JPanel;

import controller.Rule;

public class ArtPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private Rule rule;
	private Random random = new Random();
	
	private static final int ON_COLOR = 0x00FF00;
	private static final int OFF_COLOR = 0x0000FF;

	public ArtPanel(Rule rule) {
		this.rule = rule;
	}

	@Override
	protected void paintComponent(Graphics g) {

		int width = getWidth();
		int height = getHeight();

		if (image == null || image.getWidth() != width || image.getHeight() != height) {
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		}

		boolean on = true;
		
		for (int x = 0; x < width; x++) {
			
			image.setRGB(x, 0, on ? ON_COLOR: OFF_COLOR);
			
			if(random.nextInt(20) == 0) {
				on = !on;
			}
		}
		
		//Set<Integer> set = new HashSet<>();
		
		for(int y = 1; y < 2; y++) {
			for(int x = 0; x < width; x++) {
				
				int xLeft = x == 0 ? width - 1: x - 1;
				int xRight = x == width - 1 ? 0: x + 1;
				
				int leftColor = image.getRGB(xLeft, y - 1) & 0xFFFFFF;
				int centerColor = image.getRGB(x, y - 1) & 0xFFFFFF;
				int rightColor = image.getRGB(xRight, y - 1) & 0xFFFFFF;
				
				if(rightColor == ON_COLOR) {
					System.out.print("!");
				}
				else if(rightColor == OFF_COLOR) {
					System.out.print("x");
				}
				else {
					System.out.print("?");
				}
			}
		}
		
		//System.out.println(set);

		g.drawImage(image, 0, 0, null);
	}

}
