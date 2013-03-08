package com.entities;

import com.Point;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.Queue;

public class Skeleton extends Entity {

	public Skeleton(Double startx, Double starty) throws SlickException
	{
		
		super(startx, starty);
		
		Queue<Point> moveQueue;

		sheet = new SpriteSheet("./sprites/skeletonshield.png",8,8);
		attackSheet = new SpriteSheet("./sprites/skeletonshield.png",8,8);
		
		rightWalkingAnim = new Animation();
		leftWalkingAnim = new Animation();
		attackLeftAnim = new Animation();
		attackRightAnim = new Animation();
		upWalkingAnim = new Animation();
		downWalkingAnim = new Animation();
		dieAnim = new Animation();
		dieAnim.setLooping(false);
		
		for (int frame=0; frame<2; frame++)
			rightWalkingAnim.addFrame((sheet.getSprite(frame,0).getFlippedCopy(false, false)),300);
    	for (int frame=0; frame<2; frame++) 
    		leftWalkingAnim.addFrame((sheet.getSprite(frame,0).getFlippedCopy(true, false)),300);
    	for (int frame=0; frame<3; frame++)
    		downWalkingAnim.addFrame((sheet.getSprite(frame,1).getFlippedCopy(false, false)),300);
    	for (int frame=0; frame<3; frame++) 
    		upWalkingAnim.addFrame((sheet.getSprite(frame,2).getFlippedCopy(false, false)),300);
    	for (int frame=1; frame>=0; frame--)
    		dieAnim.addFrame((sheet.getSprite(frame,4).getFlippedCopy(false, false)),400);
    	
    	attackLeftAnim.addFrame((attackSheet.getSprite(2,0).getFlippedCopy(true, false)),300);
    	attackRightAnim.addFrame((attackSheet.getSprite(1,0).getFlippedCopy(false, false)),300);
		
    	facingRight = true;
	}
	
	public void draw()
	{

		if(facingLeft)
			nowAnim = leftWalkingAnim;
		if(facingRight)
			nowAnim = rightWalkingAnim;
		if(facingDown)
			nowAnim = downWalkingAnim;
		if(facingUp)
			nowAnim = upWalkingAnim;
		if(attackLeft)
			nowAnim = attackLeftAnim;
		if(attackRight)
			nowAnim = attackRightAnim;
		if(dead)
			nowAnim = dieAnim;
		
		if(moving == false)
			nowAnim.stop();
		if(moving == true)
			nowAnim.start();
		
		if(attackLeft || attackRight)
			nowAnim.draw(x.intValue(),y.intValue(),WIDTH*2,HEIGHT);
		else
			nowAnim.draw(x.intValue(),y.intValue(),WIDTH,HEIGHT);
	}
	
}
