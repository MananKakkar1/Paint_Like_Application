package ca.utoronto.utm.assignment2.factories;

import ca.utoronto.utm.assignment2.drawings.Drawing;
import ca.utoronto.utm.assignment2.drawings.Eraser;

public class EraserFactory implements DrawingFactory {
    public Drawing createDrawing() {
        return new Eraser();
    }
}
