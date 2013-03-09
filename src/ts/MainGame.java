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

    FrontLine fl = new FrontLine();

    public World world;

    public EntityCollection collection = new EntityCollection();
    ArrayList<Entity> entityClicks = new ArrayList<Entity>();

    boolean moveleft = true;

    public MainGame() {
        super("Squires");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

        collection.add(new Mage(200d, 100d));
        collection.add(new Knight(400d, 400d));
        collection.add(new Archer(300d, 300d));
        collection.add(new Warrior(500d, 300d));
        collection.add(new Skeleton(200d, 400d));
        collection.add(new Demon(300d, 400d));
        collection.add(new Necro(400d, 300d));

        world = new World(gc);
    }

    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        // collision logic will need to be more intelligent, e.g. only really check at end of movement
        // so, it'll need to be somewhere else. I'll keep the checks here for now.
        boolean collided = false;
        for (Map.Entry<Entity, Point> move : moves.entrySet()) {
            collided = false;
            for (Entity entity : collection.container)
            {
                if(move.getKey().collides(entity.getX(), entity.getY()))
                    collided = true;
            }
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
                    g.drawRoundRect((float) (entity.getX() - 2), (float) (entity.getY() - 4), 36, 36, 4);
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

        app.setDisplayMode(800, 600, false);
        app.setVSync(true);
        app.start();
    }
}