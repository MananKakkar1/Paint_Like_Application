package ca.utoronto.utm.assignment2.paint;

import ca.utoronto.utm.assignment2.drawings.Point;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class View implements EventHandler<ActionEvent> {

        private PaintModel paintModel;
        private PaintPanel paintPanel;
        private ShapeChooserPanel shapeChooserPanel;
        private UndoRedoPanel undoRedoPanel;
        private Stage stage;
        private ColorPicker colorPicker;
        private Stage subStage;

        private javafx.scene.shape.Rectangle previewLine;
        public View(PaintModel model, Stage stage) {
                this.stage = stage;
                this.paintModel = model;
                this.paintPanel = new PaintPanel(this.paintModel, this);
                this.shapeChooserPanel = new ShapeChooserPanel(this);
                this.undoRedoPanel = new UndoRedoPanel(this);

                BorderPane root = new BorderPane();
                VBox buttonContainer = new VBox();
                HBox hbox = new HBox();
                Separator separator1 = new Separator(Orientation.VERTICAL);
                separator1.setMaxHeight(80);
                separator1.setValignment(VPos.CENTER);
                separator1.setPrefWidth(20);

                Separator separator2 = new Separator(Orientation.VERTICAL);
                separator2.setMaxHeight(80);
                separator2.setValignment(VPos.CENTER);
                separator2.setPrefWidth(20);

                Separator separator3 = new Separator(Orientation.HORIZONTAL);
                separator3.setMaxWidth(480);
                separator3.setValignment(VPos.CENTER);

                Separator separator4 = new Separator(Orientation.VERTICAL);
                separator4.setMaxHeight(80);
                separator4.setValignment(VPos.CENTER);

                Separator separator5 = new Separator(Orientation.VERTICAL);
                separator5.setMaxHeight(80);
                separator5.setValignment(VPos.CENTER);

                HBox empty = new HBox();
                Label emptySpace = new Label(" ");
                empty.getChildren().add(emptySpace);

                HBox empty2 = new HBox();
                Label emptySpace2 = new Label(" ");
                empty2.getChildren().add(emptySpace2);

                ColorPicker colorPicker = new ColorPicker(Color.BLACK);
                this.colorPicker = colorPicker;
                colorPicker.setOnAction(this);
                colorPicker.setStyle("");
                VBox shapes = new VBox();
                Label shape = new Label("Shapes");
                shape.setStyle("-fx-alignment: center; -fx-pref-width: 75;-fx-font-size: 10px;");
                shapes.getChildren().addAll(this.shapeChooserPanel, empty2, shape);

                HBox empty1 = new HBox();
                Label emptySpace1 = new Label(" ");
                empty1.getChildren().add(emptySpace1);

                StackPane stackColorPicker = new StackPane(colorPicker);
                VBox colors = new VBox();
                Label color = new Label("Colors");
                color.setStyle("-fx-alignment: center; -fx-pref-width: 125;-fx-font-size: 10px;");
                colors.getChildren().addAll(empty1, stackColorPicker, empty, color);

                VBox tools = new VBox();
                Label tool = new Label("Tools");
                MenuButton menu = new MenuButton("Erasers");
                menu.setMaxHeight(20);
                menu.setStyle("-fx-font-size: 10px;");
                Circle circle1 = new Circle(5, Color.WHITE);
                circle1.setStroke(Color.BLACK);
                MenuItem menuItem = new MenuItem("Eraser S", circle1);
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                Circle circle2 = new Circle(7, Color.WHITE);
                circle2.setStroke(Color.BLACK);
                menuItem = new MenuItem("Eraser M", circle2);
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                Circle circle3 = new Circle(10, Color.WHITE);
                circle3.setStroke(Color.BLACK);
                menuItem = new MenuItem("Eraser L", circle3);
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);
                menu.setAlignment(Pos.CENTER);

                Button mode = new Button("Dark Mode");
                mode.setMinWidth(65);
                mode.setMaxWidth(40);
                mode.setMaxHeight(20);
                mode.setMinHeight(20);
                mode.setStyle("-fx-font-size: 10px;");
                mode.setOnAction(this);

                HBox modeBox = new HBox();
                modeBox.getChildren().addAll(menu, mode);
                tool.setStyle("-fx-alignment: center; -fx-pref-width: 125;-fx-font-size: 10px;");
                tools.getChildren().addAll(this.undoRedoPanel, menu, tool);

                Line dottedLine = new Line();
                dottedLine.setStartX(0);
                dottedLine.setEndX(500);
                dottedLine.setStroke(Color.WHITE);
                dottedLine.setStrokeWidth(1);
                dottedLine.getStrokeDashArray().addAll(2.0, 4.0);

                Line verticalLine = new Line();
                verticalLine.setStartY(0);
                verticalLine.setEndY(100);
                verticalLine.setStroke(Color.WHITE);
                verticalLine.setStrokeWidth(1);
                verticalLine.getStrokeDashArray().addAll(2.0, 4.0);

                VBox strokeSizeBox = new VBox();
                Label sliderLabel = new Label("Stroke Width");
                sliderLabel.setStyle("-fx-font-size: 10px;");
                Slider lineWidthSlider = new Slider(2, 100, 1);
                this.previewLine = new Rectangle();
                this.previewLine.setFill(this.paintPanel.getCurrentColor());
                this.previewLine.setHeight(5);
                this.previewLine.setWidth(100);
                lineWidthSlider.setPrefWidth(100);
                lineWidthSlider.setMaxWidth(100);
                lineWidthSlider.setPrefHeight(5);
                lineWidthSlider.setValue(2);
                lineWidthSlider.setMin(2);
                lineWidthSlider.setBlockIncrement(1);
                lineWidthSlider.setMaxWidth(100);
                lineWidthSlider.setMax(45);
                this.paintPanel.setCurrentStrokeWidth(lineWidthSlider.getValue());
                strokeSizeBox.getChildren().addAll(sliderLabel, lineWidthSlider, previewLine);
                lineWidthSlider.setOnMouseDragged(e -> this.sliderActionEvent(lineWidthSlider.getValue()));
                lineWidthSlider.setOnMousePressed(e -> this.sliderActionEvent(lineWidthSlider.getValue()));

                hbox.getChildren().addAll(shapes, separator1, colors, separator2, tools, separator4, strokeSizeBox, separator5);
                buttonContainer.getChildren().addAll(createMenuBar(), hbox, separator3);
                root.setTop(buttonContainer);
                root.setCenter(this.paintPanel);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Paint");
                stage.show();
        }

        public void sliderActionEvent(double newval) {
                paintPanel.setCurrentStrokeWidth(newval);
                this.previewLine.setHeight(newval);
        }

        public void darkMode(ActionEvent event) {
                MenuItem source = (MenuItem) event.getSource();
                String sourceText = source.getText();
                Scene scene = this.stage.getScene();
                if (sourceText.equals("Dark Mode")) {
                        scene.getStylesheets().clear();
                        scene.getStylesheets().add(getClass().
                                getResource("/ca/utoronto/utm/assignment2/darkMode.css").toExternalForm());
                        Shape[] shapes = this.shapeChooserPanel.getShapes();
                        for (Shape shape : shapes) {
                                shape.setStroke(Color.WHITE);
                        }
                        source.setText("Light Mode");
                        this.previewLine.setFill(Color.WHITE);
                        this.paintPanel.setOutlineColor(Color.WHITE);
                        this.paintPanel.setOutline();
                        this.paintPanel.setCurrentColor(Color.WHITE);
                        this.changeShapeButtonColor(Color.WHITE);
                        ca.utoronto.utm.assignment2.drawings.Circle circle =
                                new ca.utoronto.utm.assignment2.drawings.Circle(new Point(0, 0),0);
                        this.paintModel.addCircle(circle);
                        this.paintModel.removePrevious(circle);
                        this.colorPicker.setValue(Color.WHITE);
                }
                else {
                        scene.getStylesheets().clear();
                        source.setText("Dark Mode");
                        Shape[] shapes = this.shapeChooserPanel.getShapes();
                        for (Shape shape : shapes) {
                                shape.setStroke(Color.BLACK);
                        }
                        this.previewLine.setFill(Color.BLACK);
                        this.paintPanel.setOutlineColor(Color.BLACK);
                        this.paintPanel.setOutline();
                        this.paintPanel.setCurrentColor(Color.BLACK);
                        this.changeShapeButtonColor(Color.BLACK);
                        ca.utoronto.utm.assignment2.drawings.Circle circle =
                                new ca.utoronto.utm.assignment2.drawings.Circle(new Point(0, 0),0);
                        this.paintModel.addCircle(circle);
                        this.paintModel.removePrevious(circle);
                        this.colorPicker.setValue(Color.BLACK);
                }
        }
        public Color getOutlineColor() {
                return this.paintPanel.getOutlineColor();
        }
        public void setMode(String mode){
                this.paintPanel.setMode(mode);
        }
        private MenuBar createMenuBar() {

                MenuBar menuBar = new MenuBar();
                Menu menu;
                MenuItem menuItem;

                // A menu for File

                menu = new Menu("File");

                menuItem = new MenuItem("New");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Open");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Save");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menu.getItems().add(new SeparatorMenuItem());

                menuItem = new MenuItem("Exit");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuBar.getMenus().add(menu);

                // Another menu for Edit

                menu = new Menu("Edit");

                menuItem = new MenuItem("Cut");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Copy");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Paste");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menu.getItems().add(new SeparatorMenuItem());
                menuItem = new MenuItem("Undo");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Redo");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Clear");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menu.getItems().add(new SeparatorMenuItem());

                menuItem = new MenuItem("Dark Mode");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuBar.getMenus().add(menu);

                return menuBar;
        }
        @Override
        public void handle(ActionEvent event) {
                String command = "";
                double sliderVal = 0;
                if (event.getSource().getClass() == ColorPicker.class) {
                        command = ((ColorPicker) event.getSource()).getValue().toString();
                }
                else if (event.getSource().getClass() == MenuItem.class) {
                        command = ((MenuItem) event.getSource()).getText();
                } else if(event.getSource().getClass() == Slider.class) {
                        Slider slider = (Slider) event.getSource();
                        sliderVal = slider.getValue();
                        command = "Slider";
                }
                if (command.equals("Exit")) {
                        Platform.exit();
                }
                else if (command.equals("Undo")) {
                        this.setMode("Undo");
                        this.invokeHandle(event);
                }
                else if (command.equals("Redo")) {
                        this.setMode("Redo");
                        this.invokeHandle(event);
                }
                else if (command.equals("Clear")) {
                        this.setMode("Clear");
                        this.paintPanel.invokeHandle(event);
                }
                else if (command.equals("New")) {
                        this.setMode("New");
                        this.createSaveChangesStage();
                }
                else if (command.equals("Open")) {
                        this.setMode("Open");
                        this.paintPanel.invokeHandle(event);
                }
                else if (command.equals("Save")) {
                        this.setMode("Save");
                        this.paintPanel.invokeHandle(event);
                }
                else if (command.equals("Dark Mode") || command.equals("Light Mode")) {
                        this.darkMode(event);
                }
                else if (command.equals("Eraser S")) {
                        this.setMode("Eraser S");
                }
                else if (command.equals("Eraser M")) {
                        this.setMode("Eraser M");
                }
                else if (command.equals("Eraser L")) {
                        this.setMode("Eraser L");
                }
                else if (command.equals("Slider")) {
                        this.setMode("Slider");
                        paintPanel.setCurrentStrokeWidth(sliderVal);
                        this.previewLine.setStrokeWidth(sliderVal);
                        this.paintPanel.invokeHandle(event);
                }
                else {
                        this.setMode("Color");
                        this.invokeHandle(event);
                        this.changeShapeButtonColor(this.paintPanel.getCurrentColor());
                        this.previewLine.setFill(this.paintPanel.getCurrentColor());
                        Stage stage = this.getStage();
                        Platform.runLater(() -> {
                                stage.toFront();
                                stage.requestFocus();
                        });
                }
        }
        public void changeShapeButtonColor(Color color) {
                Shape[] shapes = this.shapeChooserPanel.getShapes();
                for (Shape shape : shapes) {
                        if (undoRedoPanel.isFilled() && !(shape.getClass().getName().equals("javafx.scene.shape.Path")
                                || shape.getClass().getName().equals("javafx.scene.shape.Polyline") ||
                                shape.getClass().getName().equals("javafx.scene.shape.SquiggleLine"))) {
                                shape.setFill(color);
                        } else{
                                shape.setFill(Color.TRANSPARENT);
                        }
                        shape.setStroke(color);
                }
        }

        public Stage getStage() {
                return this.stage;
        }

        public void invokeHandle(ActionEvent event) {
                this.paintPanel.invokeHandle(event);

        }
        public Color getCurrentColor() {
                return this.paintPanel.getCurrentColor();
        }

        public void createSaveChangesStage(){
                this.subStage = new Stage();
                Label label = new Label("Would you like to save your changes?");
                Button yesButton = new Button("Save");
                Button noButton = new Button("Don't Save");

                yesButton.setOnAction(e -> this.paintPanel.handleSaveStage(e, this.subStage));
                noButton.setOnAction(e -> this.paintPanel.handleSaveStage(e, this.subStage));
                BorderPane borderPane = new BorderPane();
                borderPane.setTop(label);
                HBox hbox = new HBox();
                hbox.getChildren().addAll(yesButton, noButton);
                borderPane.setCenter(hbox);
                Scene scene = new Scene(borderPane);
                this.subStage.setScene(scene);
                if (this.paintPanel.getOutlineColor().equals(Color.WHITE)) {
                    this.subStage.getScene().getStylesheets().add(getClass().
                            getResource("/ca/utoronto/utm/assignment2/darkMode.css").toExternalForm());
                }
                else {
                    this.subStage.getScene().getStylesheets().clear();
                }
                this.subStage.show();
                this.paintPanel.setMode(this.paintPanel.getDrawMode());
        }

        public void showSaveWindow() {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Drawing");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Drawing File", "*.drawing"));
                Stage stage = (Stage) this.paintPanel.getScene().getWindow();
                File file = fileChooser.showSaveDialog(stage);
                this.paintPanel.saveFile(file);
        }
        public void showLoadWindow(){
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Drawing");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Drawing", "*.drawing"));
                Stage stage = (Stage) this.paintPanel.getScene().getWindow();
                File file = fileChooser.showOpenDialog(stage);
                this.paintPanel.loadFile(file);
        }

}