package ca.utoronto.utm.assignment2.drawings;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface Drawing {
    void draw(GraphicsContext gc);
    void setColor(Color color);
    Color getColor();
    void setStrokeWidth(double width);
    double getStrokeWidth();
}
