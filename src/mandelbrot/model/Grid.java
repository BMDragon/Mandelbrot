package mandelbrot.model;

import java.util.ArrayList;

public abstract class Grid {
    private static final double INITIAL_WIDTH = 3.2;

    private ArrayList<ArrayList<Integer>> screen;
    private Complex center;
    private Complex fixed;
    private double width;
    private int pixelsAcross;

    public Grid(int widthInPixels, int baseIter) {
        center = new Complex(0, 0);
        fixed = new Complex(0, 0);
        setWidth(INITIAL_WIDTH);
        pixelsAcross = widthInPixels;
        initGrid(baseIter);
    }

    public void initGrid(int baseIter) {
        double xPos, yPos;
        screen = new ArrayList<>();
        for (int i = 0; i < pixelsAcross; i++) {
            ArrayList<Integer> column = new ArrayList<>();
            xPos = -width / 2 + center.getReal() + i * width / pixelsAcross;
            for (int j = 0; j < pixelsAcross; j++) {
                yPos = -width / 2 + center.getImaginary() + j * width / pixelsAcross;
                column.add(iterate(fixed, new Complex(xPos, yPos), (int) (baseIter / Math.sqrt(width))));
            }
            screen.add(column);
        }
    }

    public void panUpUpdate(int baseIter){

    }

    public Complex multiply(Complex a, Complex b) {
        double real = a.getReal() * b.getReal() - a.getImaginary() * b.getImaginary();
        double im = a.getReal() * b.getImaginary() + a.getImaginary() * b.getReal();
        return new Complex(real, im);
    }

    public Complex add(Complex a, Complex b) {
        return new Complex(a.getReal() + b.getReal(), a.getImaginary() + b.getImaginary());
    }

    public void setCenter(double real, double im) {
        center.setReal(real);
        center.setImaginary(im);
    }

    public void setFixed(Complex fixed) {
        this.fixed = fixed;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public int getPixelValue(int xIndex, int yIndex) {
        return screen.get(xIndex).get(yIndex);
    }

    public double getCenterReal() {
        return center.getReal();
    }

    public double getCenterImaginary() {
        return center.getImaginary();
    }

    public abstract int iterate(Complex fixed, Complex varied, int baseIter);
}
