import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {
    private final int size;
    private JImageDisplay image;
    private final FractalGenerator fg;
    private final Rectangle2D.Double area;

    public FractalExplorer(int size) {
        image = new JImageDisplay(size, size);
        this.size = size;
        fg = new Mandelbrot();
        area = new Rectangle2D.Double();
        fg.getInitialRange(area);
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("FractalExplorer");
        JButton button = new JButton("Reset Display");
        button.addActionListener(e -> {
            fg.getInitialRange(area);
            drawFractal();
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fg.recenterAndZoomRange(area, FractalGenerator.getCoord(area.x, area.x + area.width, size, e.getX()),
                        FractalGenerator.getCoord(area.y, area.y + area.height, size, e.getY()), 0.05);
                drawFractal();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.getContentPane().add(image, BorderLayout.CENTER);
        frame.getContentPane().add(button, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal() {
        image.clearImage();
        long t1 = 0;
        long t2 = 0;
        for (int i = 1; i < image.getWidth(); i++)
            for (int j = 1; j < image.getHeight(); j++)
                image.drawPixel(i, j, fg.numIterations(FractalGenerator.getCoord(area.x, area.x + area.width, size, i),
                        FractalGenerator.getCoord(area.y, area.y + area.height, size, j)));
        image.repaint();
    }

    public static void main(String[] args) {
        FractalExplorer fe = new FractalExplorer(800);
        fe.createAndShowGUI();
        fe.drawFractal();
    }
}
