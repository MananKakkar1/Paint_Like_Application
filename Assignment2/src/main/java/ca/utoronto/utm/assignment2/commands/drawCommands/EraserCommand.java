package ca.utoronto.utm.assignment2.commands.drawCommands;

import ca.utoronto.utm.assignment2.drawings.Eraser;
import ca.utoronto.utm.assignment2.drawings.Point;
import ca.utoronto.utm.assignment2.factories.EraserFactory;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class EraserCommand implements DrawCommand {
        private EraserFactory eraserFactory;
        private Eraser eraser;
        private int size;

        public EraserCommand(int size) {
            this.size = size;
            this.eraserFactory = new EraserFactory();
            this.eraser = (Eraser) this.eraserFactory.createDrawing();

        }

        @Override
        public void execute(MouseEvent mouseEvent, PaintModel model, Color color, boolean outlined, boolean fill, double strokeWidth) {
            EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();
            if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
                this.eraser = (Eraser) this.eraserFactory.createDrawing();
                model.startEraser(this.eraser);
                this.eraser.setSize(this.size);
            } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
                model.addPointEraser(new ca.utoronto.utm.assignment2.drawings.Point(mouseEvent.getX(), mouseEvent.getY()));
                this.eraser = model.getEraser();
                model.removePrevious(this.eraser);
                model.addEraser(this.eraser);
            } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
                model.addPointEraser(new Point(mouseEvent.getX(), mouseEvent.getY()));
                this.eraser = model.getEraser();
                model.removePrevious(this.eraser);
                model.addEraser(this.eraser);
                this.eraser = model.finishEraser();
            }
        }

        @Override
        public String getCommandType() {
            return "EraseCommand";
        }
    }
