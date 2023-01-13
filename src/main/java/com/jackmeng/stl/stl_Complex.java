package com.jackmeng.stl;

import java.util.Objects;

public class stl_Complex
{
    private double real, imaginary;

    public stl_Complex(double real, double imaginary)
    {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double real()
    {
        return real;
    }

    public double imaginary()
    {
        return imaginary;
    }

    public void real(double r)
    {
        this.real = r;
    }

    public void imaginary(double i)
    {
        this.imaginary = i;
    }

    public double re()
    {
        return real();
    }

    public double im()
    {
        return imaginary();
    }

    public stl_Complex plus(stl_Complex stl_complex)
    {
        return new stl_Complex(this.real() + stl_complex.real(), this.imaginary() + stl_complex.imaginary());
    }

    public stl_Complex minus(stl_Complex stl_complex)
    {
        return new stl_Complex(this.real() - stl_complex.real(), this.imaginary() - stl_complex.imaginary());
    }

    public stl_Complex $minus0(stl_Complex stl_complex)
    {
        return plus(new stl_Complex(-stl_complex.real(), -stl_complex.imaginary()));
    }

    public stl_Complex times(stl_Complex stl_complex)
    {
        return new stl_Complex (
                this.real() * stl_complex.real() - this.imaginary() * stl_complex.imaginary(),
                this.real() * this.imaginary() - stl_complex.real() * stl_complex.imaginary()
        );
    }

    public stl_Complex divides(stl_Complex stl_complex)
    {
        return this.times(stl_complex.recp());
    }

    public double abs()
    {
        return Math.hypot(imaginary, real);
    }

    public stl_Complex sin()
    {
        return new stl_Complex(Math.sin(real) * Math.cosh(imaginary), Math.cos(real) * Math.sinh(imaginary));
    }

    public stl_Complex cos()
    {
        return new stl_Complex(Math.cos(real) * Math.cosh(imaginary), -Math.sin(real) * Math.sinh(imaginary));
    }

    public stl_Complex recp()
    {
        return new stl_Complex(real / (real * real + imaginary * imaginary), imaginary / (real * real + imaginary * imaginary));
    }

    public stl_Complex scale(double k)
    {
        return new stl_Complex(k * this.real(), k * this.imaginary());
    }

    @Override public int hashCode()
    {
        return Objects.hash(imaginary, real);
    }
}
