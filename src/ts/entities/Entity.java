package ts.entities;

import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Animation;
import ts.Point;

import java.util.PriorityQueue;

public class Entity {

    Double x;
    Double y;

    Boolean facingLeft = false;
    Boolean facingDown = false;
    Boolean facingUp = false;
    Boolean facingRight = false;

    Boolean attackRight = false;
    Boolean attackLeft = false;
    Boolean moving = false;
    Boolean dead = false;

    SpriteSheet sheet;
    SpriteSheet attackSheet;

    Animation nowAnim;
    Animation rightWalkingAnim;
    Animation leftWalkingAnim;
    Animation downWalkingAnim;
    Animation upWalkingAnim;
    Animation attackRightAnim;
    Animation attackLeftAnim;
    Animation dieAnim;

    Integer HEIGHT = 32;
    Integer WIDTH = 32;

    String Name = "";
    boolean selectable = true;
    boolean isPlayerSide = true;

    public PriorityQueue<Point> movequeue = new PriorityQueue<Point>();

    public Entity(Double startx, Double starty) {

        x = startx;
        y = starty;

    }

    public boolean isMoving()
    {
        return moving;
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

    public Point getPoint() {
        return new Point(x,y);
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

    public boolean collides(Double otherx, Double othery) {
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

    public boolean collides(int otherx, int othery) {
        boolean inx = false;
        boolean iny = false;

        if (otherx >= x) {
            if (otherx <= x + 32) {
                inx = true;
            }
        }
        if (othery > y) {
            if (othery <= y + 32) {
                iny = true;
            }

        }

        if (inx && iny)
            return true;
        else
            return false;
    }

    public boolean collides(Point pt) {
        boolean inx = false;
        boolean iny = false;

        double otherx = pt.getX();
        double othery = pt.getY();

        if (otherx >= x) {
            if (otherx <= x + 32) {
                inx = true;
            }
        }
        if (othery >= y) {
            if (othery <= y + 32) {
                iny = true;
            }

        }

        if (inx && iny)
            return true;
        else
            return false;
    }

    public String getName()
    {
        return Name;
    }

    public void move()
    {

        //if(movequeue.)

    }

    public void setFacingLeft()
    {
        facingLeft = true;
        facingRight = false;
    }

    public void setFacingRight()
    {
        facingLeft = false;
        facingRight = true;
    }

    public boolean isPlayerSide()
    {
        return isPlayerSide;
    }

}