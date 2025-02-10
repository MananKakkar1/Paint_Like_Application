package ca.utoronto.utm.assignment2.commands.actionCommands;

import ca.utoronto.utm.assignment2.drawings.Drawing;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ActionClear implements ActionCommand {
    private Stage stage;

    public ActionClear(Stage stage) {
        this.stage = stage;
    }
    @Override
    public void execute(ActionEvent event,PaintModel model, PaintPanel panel) {
        ArrayList<Drawing> empty = new ArrayList<>();
        model.setShapes(empty);
        model.resetUndoList();
        GraphicsContext gc = panel.getGraphicsContext2D();
        gc.clearRect(0, 0, panel.getWidth(), panel.getHeight());
        gc.setLineWidth(5);
        gc.strokeRect(0, 0, panel.getWidth(), panel.getHeight());
        stage.close();
    }
    @Override
    public String getCommandType() {
        return "ActionCommand";
    }
}
