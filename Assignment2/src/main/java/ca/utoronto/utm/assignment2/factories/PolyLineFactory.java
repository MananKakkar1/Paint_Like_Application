package ca.utoronto.utm.assignment2.factories;

import ca.utoronto.utm.assignment2.drawings.Drawing;
import ca.utoronto.utm.assignment2.drawings.PolyLine;

public class PolyLineFactory implements DrawingFactory{
    @Override
    public Drawing createDrawing() {
        return new PolyLine();
    }
}
