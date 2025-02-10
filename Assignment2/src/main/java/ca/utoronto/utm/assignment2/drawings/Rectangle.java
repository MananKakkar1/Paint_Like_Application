package ca.utoronto.utm.assignment2.drawings;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class Rectangle implements Shape, Serializable {
    private String outline;
    protected Point topLeft;
    protected double width;
    protected double height;
    private String color;
    private boolean isFilled;
    private double strokeWidth;

    public Rectangle(Point initialPoint, double width, double height) {
        this.topLeft = initialPoint;
        this.width = width;
        this.height = height;
        this.color = "#000000";
        this.outline = "#000000";
        this.isFilled = true;
    }
    @Override
    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }


    @Override
    public void toggleFill(boolean fill) {
        this.isFilled = fill;
    }


    @Override
    public Point getTopLeft() {
        return this.topLeft;
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
    public void setWidth(double width) {
        this.width = width;
    }
    @Override
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (!this.isFilled) {
            gc.setStroke(Color.web(this.color));
            gc.setLineWidth(this.strokeWidth);
            gc.strokeRect(this.topLeft.x, this.topLeft.y, this.width, this.height);
        }
        if (this.isFilled) {
            gc.setFill(Color.web(this.color));
            gc.fillRect(this.topLeft.x,this.topLeft.y,this.width,this.height);
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
}
