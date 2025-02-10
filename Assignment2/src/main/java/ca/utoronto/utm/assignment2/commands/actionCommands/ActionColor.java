package ca.utoronto.utm.assignment2.commands.actionCommands;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class ActionColor implements ActionCommand{

    @Override
    public void execute(ActionEvent event, PaintModel model, PaintPanel panel) {
        ColorPicker cp = (ColorPicker) event.getSource();
        String cp2 = cp.getValue().toString();
        panel.currentColor = Color.web(cp2);
    }
    @Override
    public String getCommandType() {
        return "ActionCommand";
    }
}
