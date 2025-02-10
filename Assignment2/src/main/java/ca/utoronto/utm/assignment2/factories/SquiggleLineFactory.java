package ca.utoronto.utm.assignment2.factories;

import ca.utoronto.utm.assignment2.drawings.*;

public class SquiggleLineFactory implements DrawingFactory {
    public Drawing createDrawing() {
        return new SquiggleLine();
    }
}
