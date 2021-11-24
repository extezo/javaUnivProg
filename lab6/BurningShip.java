import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator{
    public static final int MAX_ITERATIONS = 2000;

    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -2.5;
        range.width = 4;
        range.height = 4;
    }

    @Override
    public int numIterations(double x, double y) {
        int i = 0;
        double x0 = 0,y0 = 0;
        double tx = 0;
        while (x0 * x0 + y0 * y0 < 4) {
            if (i > MAX_ITERATIONS)
                return -1;
            tx = x0;
            x0 = x0 * x0 - y0 * y0 + x;
            y0 = 2 * tx * y0 + y;
            x0 = (x0 >= 0) ? x0 : -x0;
            y0 = (y0 >= 0) ? y0 : -y0;
            i++;

        }
        return i;
    }
}
