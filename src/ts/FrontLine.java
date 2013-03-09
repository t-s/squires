package ts;

import org.newdawn.slick.GameContainer;

public class FrontLine {

    float x1, y1;
    float x2, y2;

    public FrontLine(GameContainer gc) {
        x1 = gc.getWidth()/2;
        y1 = 0;

        x2 = gc.getWidth()/2;
        y2 = gc.getHeight();
    }

    public FrontLine(double x1a, double y1a, double x2a, double y2a) {
        x1 = (float) x1a;
        y1 = (float) y1a;

        x2 = (float) x2a;
        y2 = (float) y2a;
    }

    public boolean isClicked(int x, int y) {
        return false;
    }

    public boolean upperHalfClicked(int x, int y) {
        return false;
    }

    public boolean lowerHalfClicked(int x, int y) {
        return false;
    }

}
