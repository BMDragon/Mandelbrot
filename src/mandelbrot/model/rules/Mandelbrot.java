package mandelbrot.model.rules;

import mandelbrot.model.Complex;
import mandelbrot.model.Grid;

public class Mandelbrot extends Grid {
    private static final double THRESHOLD = 2;

    public Mandelbrot(int widthInPixels, int baseIter) {
        super(widthInPixels, baseIter);
    }

    @Override
    public int iterate(Complex fixed, Complex varied, int baseIter) {
        for (int n = 1; n <= baseIter; n++) {
            fixed = add(multiply(fixed, fixed), varied);
            if (fixed.getMagnitude() > THRESHOLD) {
                return n;
            }
        }
        return 0;
    }
}
