package ca.utoronto.utm.assignment2.commands.drawCommands;

import ca.utoronto.utm.assignment2.drawings.Point;
import ca.utoronto.utm.assignment2.drawings.SquiggleLine;
import ca.utoronto.utm.assignment2.factories.SquiggleLineFactory;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawSquiggle implements DrawCommand {
    private SquiggleLineFactory squiggleLineFactory;
    private SquiggleLine squiggle;
    public DrawSquiggle() {
        this.squiggleLineFactory = new SquiggleLineFactory();
        this.squiggle = (SquiggleLine) this.squiggleLineFactory.createDrawing();
    }
    public void execute(MouseEvent mouseEvent, PaintModel model, Color color, boolean outlined, boolean fill, double strokeWidth) {
        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();
        if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)){
            this.squiggle = (SquiggleLine) squiggleLineFactory.createDrawing();
            model.startSquiggle(this.squiggle);
            this.squiggle.setColor(color);
            this.squiggle.setStrokeWidth(strokeWidth);
        } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
            model.addPointSquiggle(new Point(mouseEvent.getX(), mouseEvent.getY()));
            this.squiggle = model.getSquiggleLine();
            this.squiggle.setColor(color);
            this.squiggle.setStrokeWidth(strokeWidth);
            model.removePrevious(squiggle);
            model.addSquiggle(this.squiggle);
        } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
            model.addPointSquiggle(new Point(mouseEvent.getX(), mouseEvent.getY()));
            this.squiggle = model.getSquiggleLine();
            model.removePrevious(this.squiggle);
            model.addSquiggle(this.squiggle);
            this.squiggle = model.finishSquiggle();
        }
    }
    @Override
    public String getCommandType() {
        return "DrawCommand";
    }
}

