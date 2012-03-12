package com;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class World {

	private Random generator;
	private Integer randGreen;
	private int[][] tiles;
	private GameContainer container;
	
	public World(GameContainer gc) throws SlickException {
		
		container = gc;
		
		randGreen = new Integer(0);
		generator = new Random();
		tiles = new int[26][21]; //add on one to each for extra bit that is just barely shown
		
		for (int x = 0; x < (gc.getWidth()/32); x++)
    	{
    		for (int y = 0; y < (gc.getHeight()/32); y++)
    		{
    			randGreen = generator.nextInt(25);
    			tiles[x][y] = randGreen;
    		}
    	}
		
	}
	
	public int getTile(int x, int y)
	{
		return tiles[x][y];
	}
	
	public void renderWorld(Graphics g)
	{
		Color color;
    	
    	for (int x = 0; x < container.getWidth(); x=x+33)
    	{
    		for (int y = 0; y < container.getHeight(); y=y+33)
    		{
    			color = new Color(0,80 + getTile(x%32,y%32), 0); 
    	    	g.setColor(color);
    			g.fillRect(x,y,33,33);
    		}
    	}
	}
	
}
