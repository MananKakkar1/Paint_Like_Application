package ca.utoronto.utm.assignment2.drawings;

import java.util.ArrayList;

public interface Lines extends Drawing{
    void addPoint(Point point);
    ArrayList<Point> getPoints();
}
