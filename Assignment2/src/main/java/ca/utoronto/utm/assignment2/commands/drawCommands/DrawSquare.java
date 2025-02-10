package ca.utoronto.utm.assignment2.commands.drawCommands;

import ca.utoronto.utm.assignment2.drawings.Point;
import ca.utoronto.utm.assignment2.factories.SquareFactory;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.drawings.*;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawSquare implements DrawCommand {
    private Square square;
    private SquareFactory squareFactory;
    private Point topLeft;

    public DrawSquare() {
        this.squareFactory = new SquareFactory(new Point(0, 0), 0);
        this.square = (Square) this.squareFactory.createDrawing();
    }

    public void execute(MouseEvent mouseEvent, PaintModel model, Color color, boolean outlined, boolean fill, double strokeWidth) {
        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();
        if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
            topLeft = new Point(mouseEvent.getX(), mouseEvent.getY());
            this.squareFactory = new SquareFactory(topLeft, 0.0);
            this.square = (Square) this.squareFactory.createDrawing();
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
                this.square.setTopLeft(new Point(topLeftX, topLeftY));
            }else if(this.topLeft.getX() > currPoint.getX() && this.topLeft.getY() < currPoint.getY()){
                this.square.setTopLeft(new Point(currPoint.getX(), this.topLeft.getY()));
                curr_smallest = Math.min(currPoint.getY()-this.topLeft.getY(),
                        this.topLeft.getX()-currPoint.getX());
                if(currPoint.getY() >= topLeft.getY() + curr_smallest){
                    this.square.setTopLeft(new Point(this.topLeft.getX() - curr_smallest, this.topLeft.getY()));
                }
            }else if(this.topLeft.getX() < currPoint.getX() && this.topLeft.getY() > currPoint.getY()){
                this.square.setTopLeft(new Point(this.topLeft.getX(), currPoint.getY()));
                curr_smallest = Math.min(this.topLeft.getY() - currPoint.getY(),
                        currPoint.getX()-this.topLeft.getX());
                if(currPoint.getX() >= topLeft.getX() + curr_smallest){
                    this.square.setTopLeft(new Point(this.topLeft.getX(), this.topLeft.getY() - curr_smallest));
                }
            }else{
                this.square.setTopLeft(this.topLeft);
                curr_smallest = Math.min(currPoint.getX() - this.topLeft.getX(),
                        currPoint.getY() - this.topLeft.getY());
            }
            square.setWidth(curr_smallest);
            square.setHeight(curr_smallest);
            this.square.setColor(color);
            this.square.setStrokeWidth(strokeWidth);
            this.square.toggleFill(fill);
            model.removePrevious(square);
            model.addSquare(this.square);
        } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED) && this.square != null) {
            this.square = null;
        }
    }
    @Override
    public String getCommandType() {
        return "DrawCommand";
    }
}




