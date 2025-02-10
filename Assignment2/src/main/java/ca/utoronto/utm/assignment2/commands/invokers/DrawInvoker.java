package ca.utoronto.utm.assignment2.commands.invokers;

import ca.utoronto.utm.assignment2.commands.drawCommands.*;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class DrawInvoker {
    private final ArrayList<DrawCommand> drawCommands;
    private final PaintModel model;
    private PaintPanel panel;
    private DrawBezier bezCom;
    public DrawInvoker(PaintModel model, PaintPanel panel) {
        this.drawCommands = new ArrayList<DrawCommand>();
        this.drawCommands.add(new DrawCircle());
        this.drawCommands.add(new DrawRectangle());
        this.drawCommands.add(new DrawTriangle());
        this.drawCommands.add(new DrawSquare());
        this.drawCommands.add(new DrawOval());
        this.drawCommands.add(new DrawSquiggle());
        this.drawCommands.add(new DrawPolyLine());
        this.drawCommands.add(new EraserCommand(2));
        this.drawCommands.add(new EraserCommand(6));
        this.drawCommands.add(new EraserCommand(10));
        this.drawCommands.add(new DrawBezier());
        this.model = model;
        this.panel = panel;
        this.bezCom = (DrawBezier) this.drawCommands.get(10);
    }

    public void runInvoker(String mode, MouseEvent mouseEvent, Color color){
        this.panel.setDrawBezier(this.bezCom);
        switch(mode) {
            //Circle 0, Rectangle 1, Triangle 2, Square 3, Oval 4, Squiggle 5 PolyLine 6 Small Eraser 7 Medium Eraser 8 Big eraser 9 Bezier 10
            case "Circle":
                DrawCircle cirCom = (DrawCircle) this.drawCommands.getFirst();
                cirCom.execute(mouseEvent, this.model, color, panel.outlineFlag, panel.fillFlag, panel.getStrokeWidth());
                bezCom.setClickCount(panel.clickCount);
                panel.pFlag=true;
                break;
            case "Rectangle":
                DrawRectangle rectCom = (DrawRectangle) this.drawCommands.get(1);
                rectCom.execute(mouseEvent, this.model, color, panel.outlineFlag, panel.fillFlag, panel.getStrokeWidth());
                bezCom.setClickCount(panel.clickCount);
                panel.pFlag=true;
                break;
            case "Square":
                DrawSquare squareCom = (DrawSquare) this.drawCommands.get(3);
                squareCom.execute(mouseEvent, this.model, color, panel.outlineFlag, panel.fillFlag, panel.getStrokeWidth());
                bezCom.setClickCount(panel.clickCount);
                panel.pFlag=true;
                break;
            case "Squiggle":
                DrawSquiggle squCom = (DrawSquiggle) this.drawCommands.get(5);
                squCom.execute(mouseEvent, this.model, color, panel.outlineFlag, panel.fillFlag, panel.getStrokeWidth());
                bezCom.setClickCount(panel.clickCount);
                panel.pFlag=true;
                break;
            case "Eraser S":
                EraserCommand eraSCom = (EraserCommand) this.drawCommands.get(7);
                eraSCom.execute(mouseEvent, this.model, color, panel.outlineFlag, panel.fillFlag, panel.getStrokeWidth());
                bezCom.setClickCount(panel.clickCount);
                panel.pFlag=true;
                break;
            case "Eraser M":
                EraserCommand eraMCom = (EraserCommand) this.drawCommands.get(8);
                eraMCom.execute(mouseEvent, this.model, color, panel.outlineFlag, panel.fillFlag, panel.getStrokeWidth());
                bezCom.setClickCount(panel.clickCount);
                panel.pFlag=true;
                break;
            case "Eraser L":
                EraserCommand eraLCom = (EraserCommand) this.drawCommands.get(9);
                eraLCom.execute(mouseEvent, this.model, color, panel.outlineFlag, panel.fillFlag, panel.getStrokeWidth());
                bezCom.setClickCount(panel.clickCount);
                panel.pFlag=true;
                break;
            case "Polyline":
                DrawPolyLine polyCom = (DrawPolyLine) this.drawCommands.get(6);
                polyCom.setPFlag(panel.pFlag);
                polyCom.execute(mouseEvent, this.model, color, panel.outlineFlag, panel.fillFlag, panel.getStrokeWidth());
                bezCom.setClickCount(panel.clickCount);
                panel.pFlag=false;
                break;
            case "Oval":
                DrawOval ovalCom = (DrawOval) this.drawCommands.get(4);
                ovalCom.execute(mouseEvent, this.model, color, panel.outlineFlag, panel.fillFlag, panel.getStrokeWidth());
                bezCom.setClickCount(panel.clickCount);
                panel.pFlag=true;
                break;
            case "Triangle":
                DrawTriangle triCom = (DrawTriangle) this.drawCommands.get(2);
                triCom.execute(mouseEvent, this.model, color, panel.outlineFlag, panel.fillFlag, panel.getStrokeWidth());
                bezCom.setClickCount(panel.clickCount);
                panel.pFlag=true;
                break;
            case "Bezier Curve":
                bezCom.execute(mouseEvent, this.model, color, false, false, panel.getStrokeWidth());
                panel.pFlag=true;
                break;
            default:bezCom.setClickCount(panel.clickCount);break;
        }
    }
    public DrawBezier getBezCom() {
        return bezCom;
    }


}
