package ca.utoronto.utm.assignment2.factories;

import ca.utoronto.utm.assignment2.drawings.*;

public class OvalFactory implements DrawingFactory{
    double height;
    double width;
    Point topLeft;

    public OvalFactory(Point point, double height, double width) {
        this.height = height;
        this.width = width;
        this.topLeft = point;
    }

    @Override
    public Drawing createDrawing() {
        return new Oval(this.topLeft, this.height, this.width);
    }
}
