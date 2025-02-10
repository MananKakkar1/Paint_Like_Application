package ca.utoronto.utm.assignment2.commands.drawCommands;

import ca.utoronto.utm.assignment2.paint.PaintModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public interface DrawCommand {
    void execute(MouseEvent event, PaintModel model, Color color, boolean outlined, boolean fill, double strokeWidth);
    String getCommandType();
}
