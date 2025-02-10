package ca.utoronto.utm.assignment2.drawings;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;

public class PolyLine implements Lines, Serializable {
    private ArrayList<Point> points;
    private String color;
    private double strokeWidth;

    public PolyLine() {
        this.points = new ArrayList<>();
        this.color = "#000000";
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.web(this.color));
        gc.setLineWidth(this.strokeWidth);
        if(this.points.size() == 1){
            //singleton point
            Point p = points.getFirst();
            gc.setFill(Color.web(this.color));
            gc.fillOval(p.getX(), p.getY(), this.strokeWidth, this.strokeWidth);
        }else{
            for(int i = 0; i < points.size()-1; i++){
                Point p1 = points.get(i);
                Point p2 = points.get(i + 1);
                gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            }
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
    public void addPoint(Point point) {
        this.points.add(point);
    }

    @Override
    public ArrayList<Point> getPoints() {
        return this.points;
    }

    public void removePoint(Point point) {
        this.points.remove(point);
    }
}
