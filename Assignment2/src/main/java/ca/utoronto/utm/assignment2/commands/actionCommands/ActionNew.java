package ca.utoronto.utm.assignment2.commands.actionCommands;

import ca.utoronto.utm.assignment2.drawings.Drawing;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ActionNew implements ActionCommand {
    private final Stage stage;
    public ActionNew(Stage stage) {
        this.stage = stage;
    }
    @Override
    public void execute(ActionEvent event, PaintModel model, PaintPanel panel) {
        ArrayList<Drawing> curr_shapes = (ArrayList<Drawing>) model.getShapes().clone();
        panel.savedDrawings.add(curr_shapes);
        panel.setMode("Clear");
        panel.invokeHandle(event);
    }

    @Override
    public String getCommandType() {
        return "ActionCommand";
    }
}
