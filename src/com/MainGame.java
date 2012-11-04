package com;

//import org.lwjgl.util.Color;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Color;

import java.util.ArrayList;

public class MainGame extends BasicGame {

	Mage bob;
	Knight ted;
	Archer mike;
	World world;
	ArrayList<Guy> guys = new ArrayList<Guy>();
	ArrayList<Point> mouseClicks = new ArrayList<Point>();
	boolean moveleft = true;
	
    public MainGame()
    {
        super("Sprites");
    }
 
    @Override
    public void init(GameContainer gc) 
			throws SlickException 
	{
		bob = new Mage(200d,100d);
    	ted = new Knight(400d,400d);
    	mike = new Archer(300d,300d);
    	
    	guys.add(bob);
    	guys.add(ted);
    	guys.add(mike);
    	
    	world = new World(gc);
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
    	bob.moveTo(ted.x,ted.y);
    	
    	if(moveleft)
    		ted.moveTo(0d, 0d);
    	else
    		ted.moveTo(700d, 0d);
    	if(ted.x < 5)
    		moveleft = false;
    	
    }
    
    @Override
    public void mouseReleased(int button, int x, int y)
    {
    	if(button == Input.MOUSE_LEFT_BUTTON)
    	{
    		System.out.println(x + " " + y);
    		
    		for (Guy bro: guys)
    		{
    			if (bro.collides(x, y))
    			{
    				Point tempPoint = new Point((double)bro.x,(double)bro.y);  
    				mouseClicks.add(tempPoint);
    			}
    					
    		}
    	}
    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
    	world.renderWorld(g);
    	
    	for (Guy bro: guys)
    	{
    		bro.draw();
    	}
    	
    	if((mouseClicks != null) && (mouseClicks.size() > 0))
    	{	
    		g.setColor(Color.white);
    		g.setLineWidth(3);
    		for (Point p: mouseClicks)
    			g.drawRoundRect((float)p.x-2, (float)p.y-4, 36, 36, 4);
    	}
    }
 
    public static void main(String[] args) 
			throws SlickException
    {
         AppGameContainer app = 
			new AppGameContainer(new MainGame());
 
         app.setDisplayMode(800, 600, false);
         app.setVSync(true);
         app.start();
    }
}