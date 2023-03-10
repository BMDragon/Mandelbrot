package mandelbrot.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class CanvasView extends Canvas {
    public static final int WIDTH = 500;

    private int baseIter;

    public CanvasView(int baseIter) {
        super(WIDTH, WIDTH);
        this.baseIter = baseIter;
    }

    public void updatePixel(int xIndex, int yIndex, int numIter) {
        if (numIter == 0) {
            getGraphicsContext2D().getPixelWriter().setColor(xIndex, yIndex, Color.BLACK);
        } else {
            getGraphicsContext2D().getPixelWriter().setColor(xIndex, yIndex,
                    Color.rgb(10, 20, 40 + (numIter % baseIter) * 215 / baseIter));
        }
    }
}
