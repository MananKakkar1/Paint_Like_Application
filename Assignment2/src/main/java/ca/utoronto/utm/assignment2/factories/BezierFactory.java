package ca.utoronto.utm.assignment2.factories;

import ca.utoronto.utm.assignment2.drawings.Bezier;
import ca.utoronto.utm.assignment2.drawings.Drawing;

public class BezierFactory implements DrawingFactory{
    @Override
    public Drawing createDrawing() {
        return new Bezier();
    }
}
