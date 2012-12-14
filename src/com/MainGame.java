package com;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Color;

import java.util.ArrayList;
import java.util.*;

public class MainGame extends BasicGame {

	Map<Guy,Point> moves = new HashMap<Guy,Point>();
	boolean guySelected = false;
	FrontLine fl = new FrontLine();
	Mage bob;
	Knight ted;
	Archer mike;
	Warrior walt;
	Skeleton sam;
	Demon dale;
	Necro ned;
	
	World world;
	
	ArrayList<Guy> guys = new ArrayList<Guy>();
	ArrayList<Guy> guyClicks = new ArrayList<Guy>();
	
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
    	walt = new Warrior(500d,300d);
    	sam = new Skeleton(200d,400d);
    	dale = new Demon(300d,400d);
    	ned = new Necro(400d,300d);
    	
    	guys.add(bob);
    	guys.add(ted);
    	guys.add(mike);
    	guys.add(walt);
    	guys.add(sam);
    	guys.add(dale);
    	guys.add(ned);
    	
    	world = new World(gc);
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
    	
    	for(Map.Entry<Guy, Point> move: moves.entrySet())
    	{
    		move.getKey().moveTo(move.getValue().x, move.getValue().y);
    	}

    }
    
    @Override
    public void mouseReleased(int button, int x, int y)
    {
    	boolean guyClicked = false;
    	int numGuysClicked = 0;
    	
    	if(button == Input.MOUSE_LEFT_BUTTON)
    	{
    		System.out.println(x + " " + y);
    		
    		for (Guy bro: guys)
    		{
    			if (bro.collides(x, y))
    			{  
    				if(numGuysClicked < 1)
    					guyClicks.add(bro);
    				numGuysClicked++;
    				guyClicked = true;
    			}
    			
    			
    					
    		}
    		
    		if(guyClicked == false)
    		{
    			guySelected = false;
    			
    			for (Guy broski : guyClicks)
    			{	
    				Point tempPoint = new Point(x,y);
    				moves.put(broski, tempPoint);
    			}
    			
    			guyClicks.clear();
    		}
    		else 
    		{
    			guySelected = true;
    		}
    	}
    }

	public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
    	world.renderWorld(g);
    	
    	g.setColor(Color.white);
    	g.setLineWidth(4);
    	g.drawLine(fl.x1, fl.y1, fl.x2, fl.y2);
    	
    	for (Guy bro: guys)
    	{
    		bro.draw();
    	}
    	
    	if((guyClicks != null) && (guyClicks.size() > 0))
    	{	
    		g.setColor(Color.white);
    		g.setLineWidth(3);
    		if(guySelected)
    		{
    			for (Guy broski: guyClicks)
    			{
    				g.drawRoundRect((float)(broski.x-2), (float)(broski.y-4), 36, 36, 4);
    			}
    		}
    		else
    		{
    			g.clear();
    		}
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