package ca.utoronto.utm.assignment2.commands.actionCommands;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.event.ActionEvent;

public interface ActionCommand {
    void execute(ActionEvent event, PaintModel model, PaintPanel panel);
    String getCommandType();
}
