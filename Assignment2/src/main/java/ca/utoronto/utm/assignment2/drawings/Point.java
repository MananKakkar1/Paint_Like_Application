package ca.utoronto.utm.assignment2.drawings;

import java.io.Serializable;

public class Point implements Serializable {
        double x, y; // Available to our package
        public Point(double x, double y){
                this.x=x; this.y=y;
        }
        public double getX(){
                return x;
        }
        public double getY(){
                return y;
        }
}
