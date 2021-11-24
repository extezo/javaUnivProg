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
    private int rowsRemaining;
    private JFrame frame;
    private JPanel upper, lower;
    private JButton reset, save;
    private JComboBox<FractalGenerator> chooseFractal;
    public FractalExplorer(int size) {
        image = new JImageDisplay(size, size);
        this.size = size;
        fg = new Mandelbrot();
        area = new Rectangle2D.Double();
        fg.getInitialRange(area);
    }

    public void createAndShowGUI() {
        frame = new JFrame("FractalExplorer");

        reset = new JButton("Reset Display");
        reset.addActionListener(e -> {
            fg.getInitialRange(area);
            drawFractal();
        });

        save = new JButton("Save Fractal");
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

        chooseFractal = new JComboBox<>();
        chooseFractal.addItem(new Mandelbrot());
        chooseFractal.addItem(new Tricorn());
        chooseFractal.addItem(new BurningShip());
        chooseFractal.addActionListener(e -> {
            switch (chooseFractal.getSelectedItem().toString()) {
                case "Mandelbrot" -> fg = new Mandelbrot();
                case "Tricorn" -> fg = new Tricorn();
                case "BurningShip" -> fg = new BurningShip();
            }
            fg.getInitialRange(area);
            drawFractal();
        });

        upper = new JPanel();
        upper.add(new Label("Choose fractal"));
        upper.add(chooseFractal);

        lower = new JPanel();
        lower.add(reset);
        lower.add(save);

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (rowsRemaining != 0)
                    return;
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
        enableUI(false);
        rowsRemaining = size-1;
        for (int y = 1; y < image.getWidth(); y++)
            new FractalWorker(y).execute();
    }

    void enableUI(boolean val) {
        save.setEnabled(val);
        reset.setEnabled(val);
        chooseFractal.setEnabled(val);

    }

    private class FractalWorker extends SwingWorker<Object,Object> {
        private int y;
        private int[] rgb;
        public FractalWorker(int y) {
            this.y = y;
        }

        @Override
        protected Object doInBackground() {
            rgb = new int[size];
            int c;
            for (int x = 1; x < size; x++) {
                c = (int)(255*Math.cos(3.14159*Math.log(fg.numIterations(
                        FractalGenerator.getCoord(area.x, area.x + area.width, size, x),
                        FractalGenerator.getCoord(area.y, area.y + area.height, size, y)))/Math.log(2000)));
                rgb[x] = ((c & 0xFF) << 16) |
                        ((c / 3 & 0xFF) << 8) |
                        ((c / 3 & 0xFF));
            }
            return null;
        }

        @Override
        protected void done() {
            image.bufferedImage.setRGB(0, y, size,1,rgb,0,0);
            image.repaint(0, y, size, 1);
            rowsRemaining--;
            if (rowsRemaining == 0)
                enableUI(true);
        }
    }

    public static void main(String[] args) {
        FractalExplorer fe = new FractalExplorer(600);
        fe.createAndShowGUI();
        fe.drawFractal();
    }
}
