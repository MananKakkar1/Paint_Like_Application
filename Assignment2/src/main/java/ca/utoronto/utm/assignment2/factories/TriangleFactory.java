package ca.utoronto.utm.assignment2.factories;


import ca.utoronto.utm.assignment2.drawings.*;

public class TriangleFactory implements DrawingFactory {
    private Point topLeft;
    private double height;
    private double width;

    public TriangleFactory(Point topLeft, double height, double width) {
        this.topLeft = topLeft;
        this.height = height;
        this.width = width;
    }

    @Override
    public Drawing createDrawing() {
        return new Triangle(topLeft, height, width);
    }
}
