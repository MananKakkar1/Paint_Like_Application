package ca.utoronto.utm.assignment2.drawings;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class Triangle implements Shape, Serializable {
    private String outline;
    protected Point topLeft;
    protected double width;
    protected double height;
    private String color;
    private boolean isFilled;
    private boolean inverted;
    private double strokeWidth;
    public Triangle(Point topLeft, double height, double width) {
        this.topLeft = topLeft;
        this.height = height;
        this.width = width;
        this.color = "#000000";
        this.outline = "#000000";
        this.isFilled = true;
        this.inverted = false;
    }
    public double getHeight() {return height;}
    @Override
    public Point getTopLeft() {return topLeft;}
    @Override
    public void setWidth(double width) {this.width = width;}
    @Override
    public double getWidth() {return width;}
    @Override
    public void setHeight(double height) {this.height = height;}
    @Override
    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }
    @Override
    public void toggleFill(boolean fill) {
        this.isFilled = fill;
    }
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }
    @Override
    public void draw(GraphicsContext gc) {
        double[] x_points = {this.topLeft.x, this.topLeft.x+this.width/2, this.topLeft.x+this.width};
        double[] y_points = new double[3];
        if (this.inverted) {
            y_points[0] = this.topLeft.y;
            y_points[1] = this.topLeft.y + this.height;
            y_points[2] = this.topLeft.y;
        }else {
            y_points[0] = this.topLeft.y + this.height;
            y_points[1] = this.topLeft.y;
            y_points[2] = this.topLeft.y + this.height;
        }

        if (!this.isFilled){
            gc.setStroke(Color.web(this.color));
            gc.setLineWidth(this.strokeWidth);
            gc.strokePolygon(x_points, y_points, 3);
        }
        if(this.isFilled) {
            gc.setFill(Color.web(this.color));
            gc.fillPolygon(x_points, y_points, 3);
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
