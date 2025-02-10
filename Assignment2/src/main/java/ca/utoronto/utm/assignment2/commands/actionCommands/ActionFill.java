package ca.utoronto.utm.assignment2.commands.actionCommands;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.event.ActionEvent;

public class ActionFill implements ActionCommand {
    @Override
    public void execute(ActionEvent event, PaintModel model, PaintPanel panel) {
        panel.fillFlag = !panel.fillFlag;
    }

    @Override
    public String getCommandType() {
        return "ActionCommand";
    }
}
