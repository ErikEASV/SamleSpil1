package com.example.samlespil1;

public class Vector
{
    private double x, y;

    public Vector(double vx, double vy)
    {
        set(vx, vy);
    }

    public void set(double xx, double yy)
    {
        x = xx; y = yy;
    }

    public void add(double dx, double dy)
    {
        x += dx; y += dy;
    }

    public void add(Vector other)
    {
        this.add(other.x, other.y);
    }

    public double getX() { return x; }
    public double getY() { return y; }
}
