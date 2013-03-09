package ts;

import java.util.ArrayList;
import java.util.Random;

import ts.entities.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class World {

    public ArrayList<Entity> entities = new ArrayList<Entity>();

    private Random generator;
    private Integer randGreen;
    private int[][] tiles;
    private GameContainer container;
    // 26 and 21 are rough approximations of game screen (800 x 600) divided by 32
    // it looks nice!
    private int WORLD_WIDTH = 26;
    private int WORLD_HEIGHT = 21;
    // tile is square, so TILE_SIZE is same for height and width
    private int TILE_SIZE = 32;


    public World(GameContainer gc) throws SlickException {

        container = gc;

        int randGreen;
        generator = new Random();

        tiles = new int[WORLD_WIDTH][WORLD_HEIGHT];

        for (int x = 0; x < (gc.getWidth() / TILE_SIZE); x++) {
            for (int y = 0; y < (gc.getHeight() / TILE_SIZE); y++) {
                // give me a number between 0 and 25
                // this'll help determine how much green is tile
                randGreen = generator.nextInt(25);
                tiles[x][y] = randGreen;
            }
        }

    }

    public int getTile(int x, int y) {
        return tiles[x][y];
    }

    public void renderWorld(Graphics g) {
        Color color;

        for (int x = 0; x < container.getWidth(); x = x + TILE_SIZE + 1) {
            for (int y = 0; y < container.getHeight(); y = y + TILE_SIZE + 1) {
                // 80 is offset to make sure green value is high enough to, well, look green
                color = new Color(0, 80 + getTile(x % TILE_SIZE, y % TILE_SIZE), 0);
                g.setColor(color);
                g.fillRect(x, y, TILE_SIZE + 1, TILE_SIZE + 1);
            }
        }
    }

}
