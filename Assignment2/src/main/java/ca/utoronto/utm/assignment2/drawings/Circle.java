package ca.utoronto.utm.assignment2.drawings;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Oval{
    public Circle(Point point, double radius) {
        super(point, radius, radius);
    }
    public void setRadius(double radius) {
        super.setWidth(radius);
        super.setHeight(radius);
    }
    public void draw(GraphicsContext gc, Color color) {
        super.draw(gc);
    }
}
