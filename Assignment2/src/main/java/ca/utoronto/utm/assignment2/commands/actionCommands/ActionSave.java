package ca.utoronto.utm.assignment2.commands.actionCommands;

import ca.utoronto.utm.assignment2.drawings.Drawing;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

public class ActionSave implements ActionCommand {
    @Override
    public void execute(ActionEvent event,PaintModel model, PaintPanel panel) {
        ArrayList<Drawing> shapes = (ArrayList<Drawing>) model.getShapes().clone();
        panel.openSaveWindow();
        File file = panel.getSelectedFile();
        if (file != null) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(shapes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public String getCommandType() {
        return "ActionCommand";
    }
}
