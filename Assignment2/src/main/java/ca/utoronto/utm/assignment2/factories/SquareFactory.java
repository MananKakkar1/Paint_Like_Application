package ca.utoronto.utm.assignment2.factories;

import ca.utoronto.utm.assignment2.drawings.*;

public class SquareFactory extends RectangleFactory {

    public SquareFactory(Point topLeft, double length) {
        super(topLeft, length, length);
    }

    @Override
    public Drawing createDrawing() {
        return new Square(this.topLeft, this.width);
    }
}
