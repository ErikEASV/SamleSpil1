package com.example.samlespil1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    private Vector position;
    private Vector velocity;
    private Image image;
    private Rectangle boundary;

    public Sprite()
    {
        position = new Vector(0, 0);
        velocity = new Vector(0, 0);
        boundary = new Rectangle(0,0,0,0);
    }

    public void setPosition(double x, double y)
    {
        position.set(x,y);
    }

    public void setImage(String filename)
    {
        image = new Image(getClass().getResource(filename).toString());
        boundary.setWidth(image.getWidth());
        boundary.setHeight(image.getHeight());
    }

    public Rectangle getBoundary()
    {
        boundary.setX(position.getX());
        boundary.setY(position.getY());

        return boundary;
    }

    public boolean overlaps(Sprite other)
    {
        return this.getBoundary().overlaps(other.getBoundary());
    }

    public void render(GraphicsContext context)
    {
        context.drawImage(image, position.getX(), position.getY());
    }

    public Vector getPosition() { return position; }

    public Vector getVelocity() { return velocity; }
}
