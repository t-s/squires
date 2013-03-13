package ts;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Color;
import ts.entities.*;
import ts.entities.EntityCollection;

import java.util.ArrayList;
import java.util.*;

public class MainGame extends BasicGame {

    Map<Entity, Point> moves = new HashMap<Entity, Point>();

    boolean entitySelected = false;



    //can't initialize until we have a GameContainer, same with fl
    public World world = null;
    FrontLine fl = null;

    public EntityCollection collection = new EntityCollection();
    ArrayList<Entity> entityClicks = new ArrayList<Entity>();

    public MainGame() {
        super("Squires");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

        collection.add(new Mage(400d-32d, 100d));
        collection.add(new Knight(400d-32d, 200d));
        collection.add(new Archer(400d-32d, 400d));
        collection.add(new Warrior(400d-32d, 500d));
        collection.add(new Necro(400d-32d, 300d));

        collection.add(new Skeleton(600d,100d));
        collection.add(new Skeleton(600d,200d));
        collection.add(new Demon(600d, 300d));
        collection.add(new Skeleton(600d,400d));
        collection.add(new Skeleton(600d,500d));

        for (Entity entity : collection.container)
        {
            if(!entity.isPlayerSide())
                entity.setFacingLeft();
        }

        world = new World(gc);
        fl = new FrontLine(gc);

    }

    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {

        boolean collisionFlag;

        for (Map.Entry<Entity, Point> move : moves.entrySet()) {

            collisionFlag = false;

            if(!move.getKey().isMoving())
            {
                for (Entity entity : collection.container)
                {
                    if(move.getKey().hashCode() == entity.hashCode())
                        continue;

                    if(move.getKey().collides(entity.getPoint()))
                    {
                        collisionFlag = true;

                    }

                }

            }

            if(!collisionFlag)
                move.getKey().moveTo(move.getValue().x, move.getValue().y);

        }

    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        boolean entitiesClicked = false;
        int numEntitiesClicked = 0;

        if (button == Input.MOUSE_LEFT_BUTTON) {
            //System.out.println(x + " " + y);

            for (Entity entity : collection.container) {
                if (entity.collides(x, y)) {
                    if (numEntitiesClicked < 1)
                        entityClicks.add(entity);
                    numEntitiesClicked++;
                    entitiesClicked = true;
                }

            }

            if (entitiesClicked == false) {
                entitySelected = false;

                for (Entity entity : entityClicks) {
                    Point tempPoint = new Point(x, y);
                    moves.put(entity, tempPoint);
                }

                entityClicks.clear();
            } else {
                entitySelected = true;
            }
        }
    }

    public void render(GameContainer gc, Graphics g)
            throws SlickException {
        world.renderWorld(g);

        g.setColor(Color.white);
        g.setLineWidth(4);
        g.drawLine(fl.x1, fl.y1, fl.x2, fl.y2);

        for (Entity entity : collection.container) {
            entity.draw();
        }

        if ((entityClicks != null) && (entityClicks.size() > 0)) {
            g.setColor(Color.white);
            g.setLineWidth(3);

            if (entitySelected) {
                for (Entity entity : entityClicks) {
                    // let's draw a thin white box around the character selected
                    g.drawRoundRect((float) (entity.getX() - 2), (float) (entity.getY() - 2), 36, 36, 4);
                }
            } else {
                g.clear();
            }
        }
    }

    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app =
                new AppGameContainer(new MainGame());

        app.setDisplayMode(1000, 600, false);
        app.setVSync(true);
        app.start();
    }
}