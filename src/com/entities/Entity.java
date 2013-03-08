package com.entities;

import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Animation;

public class Entity {

    protected Boolean facingLeft = false;
    protected Boolean facingDown = false;
    protected Boolean facingUp = false;
    protected Boolean facingRight = false;
    protected Boolean attackRight = false;
    protected Boolean attackLeft = false;
    protected Boolean moving = false;
    protected Boolean dead = false;
    protected SpriteSheet sheet;
    protected SpriteSheet attackSheet;
    protected Animation nowAnim;
    protected Animation rightWalkingAnim;
    protected Animation leftWalkingAnim;
    protected Animation downWalkingAnim;
    protected Animation upWalkingAnim;
    protected Animation attackRightAnim;
    protected Animation attackLeftAnim;
    protected Animation dieAnim;
    protected Double x;
    protected Double y;
    protected Integer HEIGHT = 32;
    protected Integer WIDTH = 32;

    public Entity(Double startx, Double starty) {

        x = startx;
        y = starty;

    }

    public void draw() {
        if (moving == true)
            nowAnim.start();
        if (moving == false)
            nowAnim.stop();

        nowAnim.draw(x.intValue(), y.intValue(), WIDTH, HEIGHT);
    }

    public Double getX() {
        return x;
    }

    public void setX(Double newx) {
        x = newx;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double newy) {
        y = newy;
    }

    public void setLeft(boolean left) {
        facingLeft = left;
    }

    public void setRight(boolean right) {
        facingRight = right;
    }

    public void moveTo(Double newx, Double newy) {

        // TODO make it so that facing flags are set automatically
        // if destination x is to right of current, then face right true
        // if destination y is under current y, face down true
        // if difference between current y and target y is greater than
        // destination x and current x, face down wins

		/*
		moving = true;
		
		Double xMV = (newx - x);
		Double yMV = (newy - y);
		
		Double distance = Math.sqrt((xMV.intValue()^2) + (yMV.intValue()^2));
		
		Double xUnitVector = (xMV/distance);
		Double yUnitVector = (yMV/distance);
		
		if(distance > 0)
		{
			xMV = (xUnitVector) * 0.25;
			yMV = (yUnitVector) * 0.25;
		}
		else
		{
			xMV = 0d;
			yMV = 0d;
			
			moving = false;
		}
		
		x = (x + xMV);
		y = (y + yMV);*/

        float speed = 300;
        float elapsed = 0.01f;

        if (newx < x) {
            facingLeft = true;
            facingRight = false;
            facingUp = false;
            facingDown = false;
        }
        if (newx > x) {
            facingLeft = false;
            facingRight = true;
            facingUp = false;
            facingDown = false;
        }

        // On starting movement
        float distance = (float) Math.sqrt(Math.pow(newx - x, 2) + Math.pow(newy - y, 2));
        float directionX = (float) ((newx - x) / distance);
        float directionY = (float) ((newy - y) / distance);

        moving = true;

        if (distance > 2) {
            // On update
            if (moving == true) {
                x += directionX * speed * elapsed;
                y += directionY * speed * elapsed;
            }
        } else {
            moving = false;
        }

    }

    public boolean collides(int otherx, int othery) {
        boolean inx = false;
        boolean iny = false;

        if (otherx >= x) {
            if (otherx <= x + 32) {
                inx = true;
                //System.out.println("inx true");
            }
        }
        if (othery > y) {
            if (othery <= y + 32) {
                iny = true;
                //System.out.println("iny true");
            }

        }

        if (inx && iny)
            return true;
        else
            return false;
    }

}