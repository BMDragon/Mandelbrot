package mandelbrot.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class CanvasView extends Canvas {
    public static final int WIDTH = 500;

    private int maxIter;

    public CanvasView(int maxIter) {
        super(WIDTH, WIDTH);
        this.maxIter = maxIter;
    }

    public void updatePixel(int xIndex, int yIndex, int numIter) {
        if (numIter == 0) {
            getGraphicsContext2D().getPixelWriter().setColor(xIndex, yIndex, Color.BLACK);
        } else {
            getGraphicsContext2D().getPixelWriter().setColor(xIndex, yIndex,
                    Color.rgb(0, 20, 20 + numIter * 235 / maxIter));
        }
    }
}
