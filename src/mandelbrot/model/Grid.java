package mandelbrot.model;

import java.util.ArrayList;

public abstract class Grid {
    private static final double INITIAL_WIDTH = 4.0;

    private ArrayList<ArrayList<Integer>> screen;
    private Complex center;
    private Complex fixed;
    private double width;
    private int pixelsAcross;

    public Grid(int widthInPixels, int maxIter) {
        center = new Complex(0, 0);
        fixed = new Complex(0, 0);
        this.width = INITIAL_WIDTH;
        pixelsAcross = widthInPixels;
        updateGrid(maxIter);
    }

    public void updateGrid(int maxIter) {
        double xPos, yPos;
        for (int i = 0; i < pixelsAcross; i++) {
            ArrayList<Integer> column = new ArrayList<>();
            xPos = -width / 2 + center.getReal() + i * width / pixelsAcross;
            for (int j = 0; j < pixelsAcross; j++) {
                yPos = -width / 2 + center.getImaginary() + j * width / pixelsAcross;
                column.add(iterate(fixed, new Complex(xPos, yPos), maxIter));
            }
            screen.add(column);
        }
    }

    public Complex multiply(Complex a, Complex b){
        double real = a.getReal()*b.getReal() - a.getImaginary()*b.getImaginary();
        double im = a.getReal()*b.getImaginary() + a.getImaginary()*b.getReal();
        return new Complex(real, im);
    }

    public Complex add(Complex a, Complex b){
        return new Complex(a.getReal()+b.getReal(), a.getImaginary()+b.getImaginary());
    }

    public void setFixed(Complex fixed){
        this.fixed = fixed;
    }

    public abstract int iterate(Complex fixed, Complex varied, int maxIter);
}
