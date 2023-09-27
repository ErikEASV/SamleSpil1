package com.example.samlespil1;

public class Rectangle {
    private double x, y, width, height;

    public Rectangle(double xx, double yy, double w, double h)
    {
        x = xx; y = yy; width = w; height = h;
    }

    public boolean overlaps(Rectangle other)
    {
        boolean noOverlap =
                this.x + this.width < other.x ||
                other.x + other.width < this.x ||
                this.y + this.height < other.y ||
                other.y + other.height < this.y;

        return !noOverlap;
    }

   public void setWidth(double w) {
        width = w;
   }

   public void setHeight(double h) {
        height = h;
   }

   public void setX(double xx) {
        x = xx;
   }

   public void setY(double yy) {
        y = yy;
   }

}
