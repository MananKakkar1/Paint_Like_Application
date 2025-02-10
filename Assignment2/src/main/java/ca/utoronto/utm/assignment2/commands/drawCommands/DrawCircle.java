package ca.utoronto.utm.assignment2.commands.drawCommands;

import ca.utoronto.utm.assignment2.drawings.Circle;
import ca.utoronto.utm.assignment2.drawings.Point;
import ca.utoronto.utm.assignment2.factories.CircleFactory;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawCircle implements DrawCommand {
    private CircleFactory circleFactory;
    private Circle circle;
    private Point topLeft;

    public DrawCircle() {
        this.circleFactory = new CircleFactory(new Point(0, 0), 0);
        this.circle = (Circle) this.circleFactory.createDrawing();

    }

    public void execute(MouseEvent mouseEvent, PaintModel model, Color color, boolean outlined, boolean fill, double strokeWidth) {
        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();
        if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {

            topLeft = new Point(mouseEvent.getX(), mouseEvent.getY());
            this.circleFactory = new CircleFactory(topLeft, 0.0);
            this.circle = (Circle) this.circleFactory.createDrawing();
        } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
            Point currPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
            double curr_smallest = 0;
            if(this.topLeft.getX() > currPoint.getX() && this.topLeft.getY() > currPoint.getY()){
                double topLeftX = currPoint.getX();
                double topLeftY = currPoint.getY();
                curr_smallest = Math.min(this.topLeft.getY() - currPoint.getY(),
                        this.topLeft.getX() - currPoint.getX());
                if(currPoint.getX() + curr_smallest < this.topLeft.getX()){
                    topLeftX = (this.topLeft.getX() - curr_smallest);
                }
                if(currPoint.getY() + curr_smallest < this.topLeft.getY()){
                    topLeftY = (this.topLeft.getY() - curr_smallest);
                }
                this.circle.setTopLeft(new Point(topLeftX, topLeftY));
            }else if(this.topLeft.getX() > currPoint.getX() && this.topLeft.getY() < currPoint.getY()){
                this.circle.setTopLeft(new Point(currPoint.getX(), this.topLeft.getY()));
                curr_smallest = Math.min(currPoint.getY()-this.topLeft.getY(),
                        this.topLeft.getX()-currPoint.getX());
                if(currPoint.getY() >= topLeft.getY() + curr_smallest){
                    this.circle.setTopLeft(new Point(this.topLeft.getX() - curr_smallest, this.topLeft.getY()));
                }
            }else if(this.topLeft.getX() < currPoint.getX() && this.topLeft.getY() > currPoint.getY()){
                this.circle.setTopLeft(new Point(this.topLeft.getX(), currPoint.getY()));
                curr_smallest = Math.min(this.topLeft.getY() - currPoint.getY(),
                        currPoint.getX()-this.topLeft.getX());
                if(currPoint.getX() >= topLeft.getX() + curr_smallest){
                    this.circle.setTopLeft(new Point(this.topLeft.getX(), this.topLeft.getY() - curr_smallest));
                }
            }else{
                this.circle.setTopLeft(this.topLeft);
                curr_smallest = Math.min(currPoint.getX() - this.topLeft.getX(),
                        currPoint.getY() - this.topLeft.getY());
            }

            this.circle.setRadius(curr_smallest); // More responsive.
            model.removePrevious(circle);
            this.circle.setColor(color);
            this.circle.setStrokeWidth(strokeWidth);
            this.circle.toggleFill(fill);

            model.addCircle(this.circle);
        } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
            if (this.circle != null) {
                this.circle = null;
            }
        }
    }
    @Override
    public String getCommandType() {
        return "DrawCommand";
    }
}
