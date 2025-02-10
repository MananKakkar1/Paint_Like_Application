package ca.utoronto.utm.assignment2.commands.drawCommands;

import ca.utoronto.utm.assignment2.drawings.Point;
import ca.utoronto.utm.assignment2.drawings.Triangle;
import ca.utoronto.utm.assignment2.factories.TriangleFactory;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawTriangle implements DrawCommand {
    private TriangleFactory triangleFactory;
    private Triangle triangle;
    private Point topLeft;
    public DrawTriangle() {
        this.triangleFactory = new TriangleFactory(new Point(0, 0),0, 0);
        this.triangle = (Triangle) this.triangleFactory.createDrawing();
    }
    public void execute(MouseEvent mouseEvent, PaintModel model, Color color, boolean outlined, boolean fill, double strokeWidth){
        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();
        if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
            topLeft = new Point(mouseEvent.getX(), mouseEvent.getY());
            this.triangleFactory = new TriangleFactory(topLeft, 0.0, 0.0);
            this.triangle = (Triangle) this.triangleFactory.createDrawing();
        } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
            Point currPoint = new Point(mouseEvent.getX(), mouseEvent.getY());

            if(this.topLeft.getX() > currPoint.getX() && this.topLeft.getY() > currPoint.getY()){
                this.triangle.setInverted(true);
                this.triangle.setTopLeft(currPoint);
                this.triangle.setHeight(this.topLeft.getY() - currPoint.getY());
                this.triangle.setWidth(this.topLeft.getX() - currPoint.getX());
            }else if(this.topLeft.getX() > currPoint.getX() && this.topLeft.getY() < currPoint.getY()){
                this.triangle.setInverted(false);
                this.triangle.setTopLeft(new Point(currPoint.getX(), this.topLeft.getY()));
                this.triangle.setHeight(currPoint.getY()-this.topLeft.getY());
                this.triangle.setWidth(this.topLeft.getX()-currPoint.getX());
            }else if(this.topLeft.getX() < currPoint.getX() && this.topLeft.getY() > currPoint.getY()){
                this.triangle.setInverted(true);
                this.triangle.setTopLeft(new Point(this.topLeft.getX(), currPoint.getY()));
                this.triangle.setHeight(this.topLeft.getY() - currPoint.getY());
                this.triangle.setWidth(currPoint.getX()-this.topLeft.getX());
            }else{
                this.triangle.setInverted(false);
                this.triangle.setTopLeft(this.topLeft);
                this.triangle.setWidth(currPoint.getX() - this.topLeft.getX());
                this.triangle.setHeight(currPoint.getY() - this.topLeft.getY());
            }
            model.removePrevious(triangle);
            this.triangle.setColor(color);
            this.triangle.setStrokeWidth(strokeWidth);
            this.triangle.toggleFill(fill);
            model.addTriangle(this.triangle);
        } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED) && this.triangle != null) {
            this.triangle = null;
        }
    }
    @Override
    public String getCommandType() {
        return "DrawCommand";
    }
}
