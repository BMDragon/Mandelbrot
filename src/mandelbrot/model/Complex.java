package mandelbrot.model;

public class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        setReal(real);
        setImaginary(imaginary);
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public double getMagnitude() {
        return real * real + imaginary * imaginary;
    }
}
