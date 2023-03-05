package mandelbrot.model.rules;

import mandelbrot.model.Complex;
import mandelbrot.model.Grid;

public class Mandelbrot extends Grid {
    private static final double THRESHOLD = 2;

    public Mandelbrot(int widthInPixels, int maxIter) {
        super(widthInPixels, maxIter);
    }

    @Override
    public int iterate(Complex fixed, Complex varied, int maxIter) {
        for (int n = 1; n <= maxIter; n++) {
            fixed = add(multiply(fixed, fixed), varied);
            if (fixed.getMagnitude() > THRESHOLD) {
                return n;
            }
        }
        return 0;
    }
}
