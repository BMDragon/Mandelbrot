package mandelbrot.model.rules;

import mandelbrot.model.Complex;
import mandelbrot.model.Grid;

public class Julia extends Grid {
    private static final double THRESHOLD = 3;

    public Julia(int widthInPixels, int baseIter) {
        super(widthInPixels, baseIter);
    }

    @Override
    public int iterate(Complex fixed, Complex varied, int baseIter) {
        for (int n = 1; n <= baseIter; n++) {
            fixed = add(multiply(varied, varied), fixed);
            if (fixed.getMagnitude() > THRESHOLD) {
                return n;
            }
        }
        return 0;
    }
}
