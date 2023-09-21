
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

public class Mandelbrot4 extends JFrame {

    private final int MAX_ITER = 570;
    private final double ZOOM = 250;
    private BufferedImage I;
    private double zx, zy, cX, cY, tmp;

    public Mandelbrot4() {
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        cX = -0.7;
        cY = 0.27015;
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                zx = (x - getWidth() / 2.0) / ZOOM;
                zy = (y - getHeight() / 2.0) / ZOOM;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                I.setRGB(x, y, iter | (iter << 28));
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }

    public static void main(String[] args) {
        new Mandelbrot4().setVisible(true);
    }
}
