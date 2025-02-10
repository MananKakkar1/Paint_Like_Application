package ca.utoronto.utm.assignment2.commands.drawCommands;

import ca.utoronto.utm.assignment2.drawings.Oval;
import ca.utoronto.utm.assignment2.drawings.Point;
import ca.utoronto.utm.assignment2.factories.OvalFactory;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawOval implements DrawCommand {
    private OvalFactory ovalFactory;
    private Oval oval;
    private Point topLeft;
    public DrawOval() {
        this.ovalFactory = new OvalFactory(new Point(0, 0), 0, 0);
        this.oval = (Oval) this.ovalFactory.createDrawing();
    }
    public void execute(MouseEvent mouseEvent, PaintModel model, Color color, boolean outlined, boolean fill, double strokeWidth){
        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();
        if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
            topLeft = new Point(mouseEvent.getX(), mouseEvent.getY());
            this.ovalFactory = new OvalFactory(topLeft, 0.0, 0.0);
            this.oval = (Oval) this.ovalFactory.createDrawing();
        }
        else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
            Point currPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
            if(this.topLeft.getX() > currPoint.getX() && this.topLeft.getY() > currPoint.getY()){
                this.oval.setTopLeft(currPoint);
                this.oval.setHeight(this.topLeft.getY() - currPoint.getY());
                this.oval.setWidth(this.topLeft.getX() - currPoint.getX());
            }else if(this.topLeft.getX() > currPoint.getX() && this.topLeft.getY() < currPoint.getY()){
                this.oval.setTopLeft(new Point(currPoint.getX(), this.topLeft.getY()));
                this.oval.setHeight(currPoint.getY()-this.topLeft.getY());
                this.oval.setWidth(this.topLeft.getX()-currPoint.getX());
            }else if(this.topLeft.getX() < currPoint.getX() && this.topLeft.getY() > currPoint.getY()){
                this.oval.setTopLeft(new Point(this.topLeft.getX(), currPoint.getY()));
                this.oval.setHeight(this.topLeft.getY() - currPoint.getY());
                this.oval.setWidth(currPoint.getX()-this.topLeft.getX());
            }else{
                this.oval.setTopLeft(this.topLeft);
                this.oval.setWidth(currPoint.getX() - this.topLeft.getX());
                this.oval.setHeight(currPoint.getY() - this.topLeft.getY());
            }
            model.removePrevious(oval);
            this.oval.setColor(color);
            this.oval.setStrokeWidth(strokeWidth);
            this.oval.toggleFill(fill);
            model.addOval(this.oval);
        }
        else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED) && this.oval != null) {
            this.oval = null;
        }
    }
    @Override
    public String getCommandType() {
        return "DrawCommand";
    }
}
