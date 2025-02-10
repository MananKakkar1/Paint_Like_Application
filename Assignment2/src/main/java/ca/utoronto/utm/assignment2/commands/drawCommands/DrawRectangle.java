package ca.utoronto.utm.assignment2.commands.drawCommands;

import ca.utoronto.utm.assignment2.drawings.Point;
import ca.utoronto.utm.assignment2.drawings.Rectangle;
import ca.utoronto.utm.assignment2.factories.RectangleFactory;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class DrawRectangle implements DrawCommand {
    private RectangleFactory rectangleFactory;
    private Rectangle rectangle;
    private Point topLeft;
    public DrawRectangle() {
        this.rectangleFactory = new RectangleFactory(new Point(0, 0),0, 0);
        this.rectangle = (Rectangle) this.rectangleFactory.createDrawing();
    }

    public void execute(MouseEvent mouseEvent, PaintModel model, Color color, boolean outlined, boolean fill, double strokeWidth){
        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();
        if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
            topLeft = new Point(mouseEvent.getX(), mouseEvent.getY());
            this.rectangleFactory = new RectangleFactory(topLeft, 0.0, 0.0);
            this.rectangle = (Rectangle) this.rectangleFactory.createDrawing();
        } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
            Point currPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
            if(this.topLeft.getX() > currPoint.getX() && this.topLeft.getY() > currPoint.getY()){
                this.rectangle.setTopLeft(currPoint);
                this.rectangle.setHeight(this.topLeft.getY() - currPoint.getY());
                this.rectangle.setWidth(this.topLeft.getX() - currPoint.getX());
            }else if(this.topLeft.getX() > currPoint.getX() && this.topLeft.getY() < currPoint.getY()){
                this.rectangle.setTopLeft(new Point(currPoint.getX(), this.topLeft.getY()));
                this.rectangle.setHeight(currPoint.getY()-this.topLeft.getY());
                this.rectangle.setWidth(this.topLeft.getX()-currPoint.getX());
            }else if(this.topLeft.getX() < currPoint.getX() && this.topLeft.getY() > currPoint.getY()){
                this.rectangle.setTopLeft(new Point(this.topLeft.getX(), currPoint.getY()));
                this.rectangle.setHeight(this.topLeft.getY() - currPoint.getY());
                this.rectangle.setWidth(currPoint.getX()-this.topLeft.getX());
            }else{
                this.rectangle.setTopLeft(this.topLeft);
                this.rectangle.setWidth(currPoint.getX() - this.topLeft.getX());
                this.rectangle.setHeight(currPoint.getY() - this.topLeft.getY());
            }
            model.removePrevious(rectangle);
            this.rectangle.setColor(color);
            this.rectangle.setStrokeWidth(strokeWidth);
            this.rectangle.toggleFill(fill);
            model.addRectangle(this.rectangle);
        } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED) && this.rectangle != null) {
            this.rectangle = null;
        }
    }
    @Override
    public String getCommandType() {
        return "DrawCommand";
    }
}
