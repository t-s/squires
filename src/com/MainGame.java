package com;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class MainGame extends BasicGame {

	Mage bob;
	Knight ted;
	World world;
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
    	ted = new Knight(200d,400d);
    	world = new World(gc);
    }
 
    @Override
    public void update(GameContainer gc, int delta) 
			throws SlickException     
    {
    	bob.moveTo(760d,560d);
    	
    	if(moveleft)
    		ted.moveTo(0d, 0d);
    	else
    		ted.moveTo(700d, 0d);
    	if(ted.x < 5)
    		moveleft = false;
    	
    		
    }
 
    public void render(GameContainer gc, Graphics g) 
			throws SlickException 
    {
    	world.renderWorld(g);
    	bob.draw();
    	ted.draw();
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