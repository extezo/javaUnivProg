import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;

    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }

    @Override
    public int numIterations(double x, double y) {
        int i = 0;
        double x0 = 0,y0 = 0;
        double tx;
        while (x0 * x0 + y0 * y0 < 4) {
            if (i > MAX_ITERATIONS)
                return -1;
            tx = x0;
            x0 = x0 * x0 - y0 * y0 + x;
            y0 = 2 * tx * y0 + y;
            i++;
        }
        return i;
    }
}
