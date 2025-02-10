package ca.utoronto.utm.assignment2.factories;

import ca.utoronto.utm.assignment2.drawings.*;

public class CircleFactory extends OvalFactory {

    public CircleFactory(Point point, double radius) {
        super(point, radius, radius);
    }

    @Override
    public Drawing createDrawing() {
        return new Circle(this.topLeft, this.width);
    }
}
