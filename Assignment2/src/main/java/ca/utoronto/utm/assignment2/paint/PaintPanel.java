package ca.utoronto.utm.assignment2.paint;
import ca.utoronto.utm.assignment2.commands.drawCommands.DrawBezier;
import ca.utoronto.utm.assignment2.commands.invokers.ActionInvoker;
import ca.utoronto.utm.assignment2.commands.invokers.DrawInvoker;
import ca.utoronto.utm.assignment2.drawings.*;
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PaintPanel extends Canvas implements EventHandler<Event>, Observer {
    private String mode="Circle";
    private PaintModel model;
    public Color currentColor;
    private String drawMode;
    private Color outlineColor = Color.BLACK;
    private final DrawInvoker drawInvoker;
    private final ActionInvoker actionInvoker;
    public boolean outlineFlag;
    public boolean fillFlag;
    public int clickCount=0;
    public boolean pFlag = false;
    private DrawBezier bezCom;
    private javafx.scene.shape.Rectangle outline = new javafx.scene.shape.Rectangle();
    public ArrayList<ArrayList<Drawing>> savedDrawings = new ArrayList<>();
    private double currentStrokeWidth;
    private boolean newSave = false;
    private final View view;
    private File selectedFile;
    public PaintPanel(PaintModel model, View view) {
        super(300,300);
        this.model=model;
        this.view = view;
        this.model.addObserver(this);
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
        this.drawInvoker = new DrawInvoker(this.model, this);
        this.actionInvoker = new ActionInvoker(this.model, this);
        this.currentColor = Color.BLACK;
        this.fillFlag = true;
        this.drawMode = "Circle";

        this.sceneProperty().addListener((_, _, newScene) -> {
            if (newScene != null) {
                newScene.widthProperty().addListener((_, _, newVal) ->{
                    setWidth(newVal.doubleValue());
                    update(model, null);
                });
                newScene.heightProperty().addListener((_, _, newVal) -> {
                    setHeight(newVal.doubleValue() - 100);
                    update(model, null);
                });
                GraphicsContext gc = this.getGraphicsContext2D();
                gc.setStroke(this.outlineColor);
                gc.strokeRect(0, 0, this.getWidth(), this.getHeight());
            }
        });
    }

    public void setNewSave(boolean newSave) {
        this.newSave = newSave;
    }

    public boolean getNewSave() {
        return this.newSave;
    }

    public void setDrawMode(String mode) {
        this.drawMode = mode;
    }

    public String getDrawMode() {
        return drawMode;
    }
    public String getMode() {
        return mode;
    }


    public DrawBezier getDrawInvoker() {
        return this.bezCom;
    }

    public void setDrawBezier(DrawBezier drawBezier) {
        this.bezCom = drawBezier;
    }

    public Color getOutlineColor() {
        return this.outlineColor;
    }

    public void setOutlineColor(Color color) {
        this.outlineColor = color;
    }
    public void setCurrentStrokeWidth(double d) {this.currentStrokeWidth = d;}
    /**
     *  Controller aspect of this
     */
    public void setMode(String mode){
        this.mode=mode;
    }
    public void invokeHandle(ActionEvent event) {
        this.handle(event);
    }

    @Override
    public void handle(Event event) {
        if (event.getEventType().equals(ActionEvent.ACTION)) {
            this.actionInvoker.runInvoker(this.mode, (ActionEvent) event);

        }else{
            this.drawInvoker.runInvoker(this.mode, (MouseEvent) event, this.currentColor);
            this.drawMode = this.mode;
        }
    }

    public void handleSaveStage(Event event, Stage substage){
        Button source = (Button) event.getSource();
        this.mode = source.getText();
        if(this.mode.equals("Save")) this.mode = "Save";
        this.actionInvoker.runInvoker(mode, (ActionEvent) event);
        if (this.newSave) {
            this.mode = "Clear";
        }
        else {
            this.mode = this.drawMode;
        }
        this.actionInvoker.runInvoker(mode, (ActionEvent) event);
        substage.close();
    }

    public Color getCurrentColor(){
        return this.currentColor;
    }

    public double getStrokeWidth() {return this.currentStrokeWidth;}

    public void setStrokeWidth(double strokeWidth) {this.currentStrokeWidth = strokeWidth;}

    public void setOutline() {
        GraphicsContext g2d = this.getGraphicsContext2D();
        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setStroke(this.outlineColor);
        g2d.setLineWidth(5);
        g2d.strokeRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void setCurrentColor (Color color) {
        this.currentColor = color;
    }

    @Override
    public void update(Observable o, Object arg) {
                this.model = (PaintModel) o;
                GraphicsContext g2d = this.getGraphicsContext2D();
                g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
                g2d.setStroke(this.outlineColor);
                g2d.setLineWidth(5);
                g2d.strokeRect(0, 0, this.getWidth(), this.getHeight());
                ArrayList<Drawing> shapes = this.model.getShapes();
                for (Drawing drawing : shapes) {
                    if (drawing instanceof Eraser) {
                        if (this.outlineColor == Color.WHITE) {
                            drawing.setColor(Color.web("#2b2b2b"));
                        }
                        else if (this.outlineColor == Color.BLACK) {
                            drawing.setColor(Color.web("#f4f4f4"));
                        }
                    }
                }
                for (Drawing d : shapes){
                    if (d.getClass() == SquiggleLine.class) {
                        d.draw(g2d);
                    }
                    else if (d.getClass() == Eraser.class) {
                        d.draw(g2d);
                    }
                    else {
                        d.draw(g2d);
                    }

                }
        g2d.setStroke(this.outlineColor);
        g2d.setLineWidth(5);
        g2d.strokeRect(0, 0, this.getWidth(), this.getHeight());
    }
    public void openSaveWindow() {
        this.view.showSaveWindow();
    }

    public void saveFile(File file){
        if (file != null) {
            this.newSave = true;
        } else {
            this.newSave = false;
        }
        this.selectedFile = file;
    }

    public File getSelectedFile() {
        return this.selectedFile;
    }

    public void loadFile(File file){
        if (file != null) {
            this.selectedFile = file;
        }else{
            this.selectedFile = null;
        }
    }

    public void openLoadWindow(){
        this.view.showLoadWindow();
    }
}
