package ca.utoronto.utm.assignment2.drawings;


public interface Shape extends Drawing{
    double getWidth();
    double getHeight();
    Point getTopLeft();
    void setWidth(double width);
    void setHeight(double height);
    void setTopLeft(Point topLeft);
    void toggleFill(boolean fill);
}
