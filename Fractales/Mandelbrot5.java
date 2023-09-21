/******************************************************************************
 *  Compilation:  javac Mandelbrot.java
 *  Execution:    java Mandelbrot xc yc size
 *  Dependencies: StdDraw.java
 *
 *  Plots the size-by-size region of the Mandelbrot set, centered on (xc, yc)
 *
 *  % java Mandelbrot -0.5 0 2
 *
 ******************************************************************************/
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Mandelbrot5 extends JFrame {

    private final int MAX_ITER = 570;
    private final double ZOOM = 200;
    private BufferedImage image;
    private double zx, zy, cx, cy, tmp;

    public Mandelbrot5() {
        super("Custom Mandelbrot Set");
        setBounds(100, 100, 800, 600); // Cambiar el tamaño de la ventana
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        cx = - 0.6880;
        cy = 0.29150;
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                zx = (x - getWidth() / 2.0) / ZOOM; // Cambiar el centro y el zoom
                zy = (y - getHeight() / 2.0) / ZOOM;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cx; // Ajustar la fórmula
                    zy = 2 * zx * zy + cy;
                    zx = tmp;
                    iter--;
                }

                image.setRGB(x, y, iter | (iter << 8));
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    public static void main(String[] args) {
        new Mandelbrot5().setVisible(true);
    }
}
