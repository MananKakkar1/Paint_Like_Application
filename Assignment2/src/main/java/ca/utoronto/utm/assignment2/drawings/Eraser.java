package ca.utoronto.utm.assignment2.drawings;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;

public class Eraser implements Lines, Serializable {
    private ArrayList<Point> points;
    private long size;
    private String color;

    public Eraser() {this.points = new ArrayList<>();
    this.size = 0;
    this.color = "#f4f4f4";}

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.web(this.color));
        if (!(this.size == 0)) {
            gc.setLineWidth(this.size);
        }
        if(this.points.size() == 1){
            //singleton point
            Point p = points.getFirst();
            gc.setFill(Color.web(this.color));
            gc.fillOval(p.getX(), p.getY(), this.size, this.size);
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
        return;
    }

    @Override
    public double getStrokeWidth() {
        return 0;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public void addPoint(Point p) {
        points.add(p);
    }

    @Override
    public ArrayList<Point> getPoints() {
        return points;
    }

}
