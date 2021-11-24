import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

public class FractalExplorer {
    private final int size;
    private JImageDisplay image;
    private FractalGenerator fg;
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

        JButton reset = new JButton("Reset Display");
        reset.addActionListener(e -> {
            fg.getInitialRange(area);
            drawFractal();
        });

        JButton save = new JButton("Save Fractal");
        save.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
            fc.setAcceptAllFileFilterUsed(false);
            if (fc.showSaveDialog(frame) != JFileChooser.APPROVE_OPTION)
                return;
            File saveTo = fc.getSelectedFile();
            try {
                if (!saveTo.exists())
                    saveTo.createNewFile();
                ImageIO.write(image.bufferedImage, "PNG", saveTo);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(fc, ex.getMessage(), "Cannot save image", JOptionPane.ERROR_MESSAGE);
            }
        });

        JComboBox<String> chooseFractal = new JComboBox<String>();
        chooseFractal.addItem("Mandelbrot");
        chooseFractal.addItem("Tricorn");
        chooseFractal.addItem("Burning Ship");
        chooseFractal.addActionListener(e -> {
            switch ((String) chooseFractal.getSelectedItem()) {
                case "Mandelbrot":
                    fg = new Mandelbrot();
                    break;
                case "Tricorn":
                    fg = new Tricorn();
                    break;
                case "Burning Ship":
                    fg = new BurningShip();
                    break;
            }
            fg.getInitialRange(area);
            drawFractal();
        });

        JPanel upper = new JPanel();
        upper.add(new Label("Choose fractal"));
        upper.add(chooseFractal);

        JPanel lower = new JPanel();
        lower.add(reset);
        lower.add(save);

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fg.recenterAndZoomRange(area, FractalGenerator.getCoord(area.x, area.x + area.width, size, e.getX()),
                        FractalGenerator.getCoord(area.y, area.y + area.height, size, e.getY()), 0.5);
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
        frame.getContentPane().add(upper, BorderLayout.NORTH);
        frame.getContentPane().add(image, BorderLayout.CENTER);
        frame.getContentPane().add(lower, BorderLayout.SOUTH);
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
        FractalExplorer fe = new FractalExplorer(600);
        fe.createAndShowGUI();
        fe.drawFractal();
    }
}
