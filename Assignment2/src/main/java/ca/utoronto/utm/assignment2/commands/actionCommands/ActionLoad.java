package ca.utoronto.utm.assignment2.commands.actionCommands;

import ca.utoronto.utm.assignment2.drawings.Drawing;
import ca.utoronto.utm.assignment2.paint.PaintModel;
import ca.utoronto.utm.assignment2.paint.PaintPanel;
import javafx.event.ActionEvent;

import java.io.*;
import java.util.ArrayList;

public class ActionLoad implements ActionCommand {

    public ActionLoad() {
        ;
    }

    @Override
    public void execute(ActionEvent event,PaintModel model, PaintPanel panel) {
        panel.openLoadWindow();
        File file = panel.getSelectedFile();
        model.resetUndoList();
        if (file != null) {
            ArrayList<Drawing> shapes = loadShapes(file);
            model.setShapes(shapes);
        }

    }

    private ArrayList<Drawing> loadShapes(File file){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
            return (ArrayList<Drawing>) in.readObject();
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public String getCommandType() {
        return "ActionCommand";
    }
}
