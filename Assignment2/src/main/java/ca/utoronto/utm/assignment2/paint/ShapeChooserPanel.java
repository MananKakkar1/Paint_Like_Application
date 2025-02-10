package ca.utoronto.utm.assignment2.paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Circle;
import java.util.ArrayList;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

        private View view;
        private Button currentButton;
        private ArrayList<Button> buttons = new ArrayList<>();
        private Shape[] Shapes;
        public ShapeChooserPanel(View view) {

                this.view = view;

                String[] buttonLabels = {"Circle", "Rectangle", "Square", "Squiggle",
                        "Polyline", "Triangle", "Oval", "Bezier Curve"};
                this.Shapes = new Shape[buttonLabels.length];
                Shapes[1] = new javafx.scene.shape.Rectangle(10, 15);
                Shapes[0] = new Circle(7.5);
                Shapes[2] = new javafx.scene.shape.Rectangle(10, 10);
                Shapes[3] = new javafx.scene.shape.Polyline(0, 0, 2, 3, 5, 5, 7, 9, 10, 10);
                Shapes[4] = new javafx.scene.shape.Polyline(0,10,5,5,10,10,10,0);
                Shapes[5] = new javafx.scene.shape.Polygon(7.5, 0, 0, 15, 15, 15);
                Shapes[6] = new javafx.scene.shape.Ellipse(7.5, 5);
                Path bezierPath = new Path();
                bezierPath.getElements().add(new MoveTo(0, 10));
                bezierPath.getElements().add(new CubicCurveTo(5, -5, 10, 10, 15, 15));
                Shapes[7] = bezierPath;

                int col = 0;
                for (String label : buttonLabels) {
                        Button button = new Button();
                        button.setMinWidth(100);
                        Tooltip tooltip = new Tooltip(label);
                        button.setTooltip(tooltip);
                        button.setGraphic(Shapes[col]);
                        button.setMinHeight(10);
                        button.setMinWidth(10);
                        button.setMaxHeight(20);
                        button.setMaxWidth(20);
                        if (col%2==0) {
                                this.add(button, col, 0);
                        }
                        else {
                                this.add(button, col-1, 1);
                        }
                        col++;
                        button.setOnAction(this);
                        buttons.add(button);
                }
                for (javafx.scene.shape.Shape shape : Shapes) {
                        if ( !(shape.getClass().getName().equals("javafx.scene.shape.Path") ||
                                shape.getClass().getName().equals("javafx.scene.shape.Polyline") ||
                                shape.getClass().getName().equals("javafx.scene.shape.SquiggleLine"))) {
                                shape.setFill(Color.BLACK);
                        }
                        if (this.view.getOutlineColor().equals(Color.WHITE)) {
                                shape.setStroke(Color.WHITE);
                        }
                        else {
                                shape.setStroke(Color.BLACK);
                        }
                        shape.setStrokeWidth(2);
                }
        }
        public Shape[] getShapes() {
                return this.Shapes;
        }

        @Override
        public void handle(ActionEvent event) {
                if (currentButton != null) {
                        currentButton.setStyle("");
                }
                Button selectedButton = (Button) event.getSource();
                selectedButton.setStyle("-fx-background-color: #6d9ae3; -fx-text-fill: black;" + "-fx-border-color: transparent;");
                currentButton = selectedButton;
                String command = selectedButton.getTooltip().getText();
                view.setMode(command);
                this.view.invokeHandle(event);
        }
}


