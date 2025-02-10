package ca.utoronto.utm.assignment2.drawings;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class Bezier implements Drawing, Serializable {
    private Point start;
    private Point end;
    private Point control1;
    private Point control2;
    private double strokeWidth;
    private String color;

    public void setStart(Point start) {
        this.start = start;
    }
    public void setEnd(Point end) {
        this.end = end;
    }
    public void setControl1(Point control1) {
        this.control1 = control1;
    }
    public void setControl2(Point control2) {
        this.control2 = control2;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.web(color));
        gc.beginPath();
        gc.setLineWidth(this.strokeWidth);
        gc.moveTo(start.getX(), start.getY());
        gc.bezierCurveTo(
                control1.getX(), control1.getY(),
                control2.getX(), control2.getY(),
                end.getX(), end.getY()
        );
        gc.stroke();
    }

    @Override
    public void setColor(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);
        int alpha = (int) (color.getOpacity() * 255);

        this.color = String.format("#%02X%02X%02X%02X", red, green, blue, alpha);
    }

    @Override
    public Color getColor() {
        return Color.web(this.color);
    }

    @Override
    public void setStrokeWidth(double width){this.strokeWidth =width;}

    @Override
    public double getStrokeWidth(){return this.strokeWidth;}
}
