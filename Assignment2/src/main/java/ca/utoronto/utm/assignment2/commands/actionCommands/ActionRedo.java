package ca.utoronto.utm.assignment2.commands.actionCommands;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.event.ActionEvent;

public class ActionRedo implements ActionCommand{
    @Override
    public void execute(ActionEvent event, PaintModel model, PaintPanel panel) {
        model.redo();
    }

    @Override
    public String getCommandType() {
        return "ActionCommand";
    }
}
