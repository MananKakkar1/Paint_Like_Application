package ca.utoronto.utm.assignment2.commands.drawCommands;

import ca.utoronto.utm.assignment2.drawings.Bezier;
import ca.utoronto.utm.assignment2.drawings.Point;
import ca.utoronto.utm.assignment2.factories.BezierFactory;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawBezier implements DrawCommand{
    private BezierFactory bezierFactory;
    private int clickCount;
    private Color color;
    private Bezier bezier;
    private double strokeWidth;

    public DrawBezier(){
        this.bezierFactory = new BezierFactory();
    }
    public void setColor(Color color){
        this.color = color;
    }
    public void setStrokeWidth(double width){this.strokeWidth=width;}
    public void setClickCount(int clickCount){
        this.clickCount = clickCount;
    }

    public void execute(MouseEvent mouseEvent, PaintModel model, Color color, boolean fill, boolean outline, double strokeWidth) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        Point mousePoint = new Point(mouseEvent.getX(), mouseEvent.getY());
        if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
            if (clickCount == 0) {
                this.bezier = (Bezier) this.bezierFactory.createDrawing();
                this.bezier.setColor(color);
                this.bezier.setStart(mousePoint);
                this.bezier.setControl1(mousePoint);
                this.bezier.setControl2(mousePoint);
                this.bezier.setStrokeWidth(strokeWidth);
            }
            else if (clickCount == 1) {
                this.bezier.setControl1(mousePoint);

                model.removePrevious(this.bezier);
                model.addBezier(this.bezier);
            }
            else if (clickCount == 2) {
                this.bezier.setControl2(mousePoint);
                model.removePrevious(this.bezier);
                model.addBezier(this.bezier);
            }
        }
        else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            if (clickCount == 0) {
                this.bezier.setEnd(mousePoint);
                model.removePrevious(this.bezier);
                model.addBezier(this.bezier);
            }
            else if (clickCount == 1) {
                this.bezier.setControl1(mousePoint);
                model.removePrevious(this.bezier);
                model.addBezier(this.bezier);
            }
            else if (clickCount == 2) {
                this.bezier.setControl2(mousePoint);
                model.removePrevious(this.bezier);
                model.addBezier(this.bezier);
            }
        }
        else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
            if (clickCount == 0) {
                this.bezier.setEnd(mousePoint);
                model.removePrevious(this.bezier);
                model.addBezier(this.bezier);
                clickCount++;
            }
            else if (clickCount == 1) {
                this.bezier.setControl1(mousePoint);
                model.removePrevious(this.bezier);
                model.addBezier(this.bezier);
                clickCount++;
            }
            else if (clickCount == 2) {
                this.bezier.setControl2(mousePoint);
                model.removePrevious(this.bezier);
                model.addBezier(this.bezier);
                clickCount++;
            }
            if (clickCount == 3) {
                clickCount = 0;
            }
        }
    }

    @Override
    public String getCommandType() {
        return "DrawCommand";
    }
}
