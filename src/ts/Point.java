package ts;

public class Point {

    double x;
    double y;

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public Point(double newx, double newy) {
        x = newx;
        y = newy;
    }

    public Point(int newx, int newy) {
        x = (double) newx;
        y = (double) newy;
    }
}
