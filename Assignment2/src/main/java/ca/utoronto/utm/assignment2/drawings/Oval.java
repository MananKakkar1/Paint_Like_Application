package ca.utoronto.utm.assignment2.drawings;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class Oval implements Shape, Serializable {
    private String outline;
    private double height;
    private double width;
    private Point topLeft;
    private Point center;
    private String color;
    private boolean isFilled;
    private double strokeWidth;

    public Oval(Point point, double height, double width) {
        this.height = height;
        this.width = width;
        this.topLeft = point;
        this.center = new Point(this.topLeft.x+this.width, this.topLeft.y + this.height);
        this.color = "#000000";
        this.outline = "#000000";
        this.isFilled = true;
    }

    public Point getCentre() {
        return this.center;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (!this.isFilled) {
            gc.setStroke(Color.web(this.color));
            gc.setLineWidth(this.strokeWidth);
            gc.strokeOval(this.topLeft.x, this.topLeft.y, this.width, this.height);
        }
        if (this.isFilled) {
            gc.setFill(Color.web(this.color));
            gc.fillOval(this.topLeft.x,this.topLeft.y,this.width,this.height);
        }
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
    public void setStrokeWidth(double width) {
        this.strokeWidth = width;
    }

    @Override
    public double getStrokeWidth() {
        return this.strokeWidth;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public Point getTopLeft() {
        return this.topLeft;
    }

    @Override
    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
        this.center = new Point(this.topLeft.x+this.width, this.topLeft.y + this.height);
    }

    @Override
    public void toggleFill(boolean fill) {
        this.isFilled = fill;
    }
}
