package ca.utoronto.utm.assignment2.commands.drawCommands;

import ca.utoronto.utm.assignment2.drawings.Point;
import ca.utoronto.utm.assignment2.drawings.PolyLine;
import ca.utoronto.utm.assignment2.factories.PolyLineFactory;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawPolyLine implements DrawCommand {
    private PolyLineFactory polyLineFactory;
    private PolyLine polyLine;
    private boolean pflag = true;
    public DrawPolyLine() {
        this.polyLineFactory = new PolyLineFactory();
        this.polyLine = null;
    }
    public void execute(MouseEvent mouseEvent, PaintModel model, Color color, boolean outlined, boolean fill, double strokeWidth) {
        if (this.pflag) {
            this.polyLine = null;
            this.pflag = false;
        }
        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)
                &&mouseEvent.getButton().equals(MouseButton.SECONDARY)&& this.polyLine != null) {
            this.polyLine = model.finishPolyline();
            this.polyLine = null;
        }
        else if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED) && this.polyLine == null
                &&mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            this.polyLine = (PolyLine) polyLineFactory.createDrawing();
            model.startPolyline(this.polyLine);
            Point mousePoint = new Point(mouseEvent.getX(), mouseEvent.getY());
            model.addPolyPoint(mousePoint);
            this.polyLine = model.getPolyLine();
            model.removePrevious(polyLine);
            this.polyLine.setColor(color);
            this.polyLine.setStrokeWidth(strokeWidth);
            model.addPolyline(this.polyLine);
        } else if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)
                &&mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            this.polyLine = model.finishPolyline();
            Point mousePoint = new Point(mouseEvent.getX(), mouseEvent.getY());
            model.addPolyPoint(mousePoint);
            this.polyLine = model.getPolyLine();
            model.removePrevious(polyLine);
            model.addPolyline(this.polyLine);
        }
    }
    public void setPFlag(boolean pFlag) {
        this.pflag = pFlag;
    }
    @Override
    public String getCommandType() {
        return "DrawCommand";
    }
}
