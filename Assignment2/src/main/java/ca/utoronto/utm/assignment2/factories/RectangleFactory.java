package ca.utoronto.utm.assignment2.factories;

import ca.utoronto.utm.assignment2.drawings.*;

public class RectangleFactory implements DrawingFactory {
    Point topLeft;
    double width;
    double height;

    public RectangleFactory(Point topLeft, double width, double height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    @Override
    public Drawing createDrawing() {return new Rectangle(topLeft, width, height);}
}
