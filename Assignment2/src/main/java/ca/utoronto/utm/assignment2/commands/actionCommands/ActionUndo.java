package ca.utoronto.utm.assignment2.commands.actionCommands;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.event.ActionEvent;

public class ActionUndo implements ActionCommand{
    public ActionUndo() {
        ;
    }
    @Override
    public void execute(ActionEvent event, PaintModel model, PaintPanel panel) {
        model.undo();
    }
    @Override
    public String getCommandType() {
        return "ActionCommand";
    }
}
