package ca.utoronto.utm.assignment2.commands.invokers;

import ca.utoronto.utm.assignment2.commands.actionCommands.*;
import ca.utoronto.utm.assignment2.commands.drawCommands.DrawBezier;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ActionInvoker {
    private final ArrayList<ActionCommand> actionCommands;
    private final PaintModel model;
    private final PaintPanel panel;
    private boolean actionInvoked = false;
    public ActionInvoker(PaintModel model, PaintPanel panel) {
        this.actionCommands = new ArrayList<ActionCommand>();
        this.actionCommands.add(new ActionNew(new Stage()));
        this.actionCommands.add(new ActionSave());
        this.actionCommands.add(new ActionUndo());
        this.actionCommands.add(new ActionRedo());
        this.actionCommands.add(new ActionClear(new Stage()));
        this.actionCommands.add(new ActionColor());
        this.actionCommands.add(new ActionFill());
        this.actionCommands.add(new ActionLoad());
        this.model = model;
        this.panel = panel;
    }

    public void runInvoker(String mode, ActionEvent actionEvent) {
        DrawBezier bezCom = panel.getDrawInvoker();
        switch (mode){
            case "New":
                actionCommands.getFirst().execute(actionEvent, this.model, this.panel);
                panel.pFlag=true;
                if (bezCom != null) {
                    bezCom.setClickCount(panel.clickCount);
                }
                actionInvoked = true;
                break;
            case "Save":
                actionCommands.get(1).execute(actionEvent, this.model, this.panel);
                panel.pFlag=true;
                if (bezCom != null) {
                    bezCom.setClickCount(panel.clickCount);
                }
                actionInvoked = true;
                break;
            case "Open":
                actionCommands.get(7).execute(actionEvent, this.model, this.panel);
                panel.pFlag=true;
                if (bezCom != null) {
                    bezCom.setClickCount(panel.clickCount);
                }
                actionInvoked = true;
                break;
            case "Undo":
                actionCommands.get(2).execute(actionEvent, this.model, this.panel);
                panel.pFlag=true;
                if (bezCom != null) {
                    bezCom.setClickCount(panel.clickCount);
                }
                actionInvoked = true;
                break;
            case "Redo":
                actionCommands.get(3).execute(actionEvent, this.model, this.panel);
                panel.pFlag=true;
                if (bezCom != null) {
                    bezCom.setClickCount(panel.clickCount);
                }
                actionInvoked = true;
                break;
            case "Don't Save":
            case "Clear":
                actionCommands.get(4).execute(actionEvent, this.model, this.panel);
                panel.pFlag=true;
                if (bezCom != null) {
                    bezCom.setClickCount(panel.clickCount);
                }
                actionInvoked = true;
                break;
            case "Color":
                actionCommands.get(5).execute(actionEvent, this.model, this.panel);
                panel.pFlag=true;
                if (bezCom != null) {
                    bezCom.setClickCount(panel.clickCount);
                }
                actionInvoked = true;
                break;
            case "Fill":
                actionCommands.get(6).execute(actionEvent, this.model, this.panel);
                panel.pFlag=true;
                actionInvoked = true;
                break;
            case "Eraser S":
            case "Eraser M":
            case "Eraser L":
            default:
                this.panel.setMode(mode);
                this.panel.setDrawMode(mode);
                if (bezCom != null) {
                    bezCom.setClickCount(panel.clickCount);
                }
                panel.pFlag=true;
                break;
        }
        if(actionInvoked){
            this.panel.setMode(panel.getDrawMode());
        }
    }


}
