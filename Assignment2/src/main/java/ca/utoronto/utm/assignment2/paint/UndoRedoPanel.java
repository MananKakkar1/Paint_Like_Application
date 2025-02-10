package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;


public class UndoRedoPanel extends GridPane implements EventHandler<ActionEvent> {
    private View view;
    private Button currentButton;
    private boolean toggleFill = true;
    private ArrayList<Button> undoButtons = new ArrayList<>();
    public UndoRedoPanel(View view) {
        this.view = view;
        String[] buttonLabels = {"Undo", "Redo", "Clear", "Fill"};
        int row = 0;
        for (String label : buttonLabels) {
            Button button = new Button(label);
            if (label.equals("Fill")) {
                button.setStyle("-fx-font-size: 10px;-fx-background-color: #6d9ae3;-fx-border-color: transparent");
            }
            else {
                button.setStyle("-fx-font-size: 10px;");
            }
            button.setMinWidth(40);
            button.setMaxWidth(40);
            button.setMaxHeight(20);
            button.setMinHeight(20);
            if (label.equals("Fill")) {
                button.setMaxWidth(80);
                this.add(button, 0, row);
            }
            else {
                this.add(button, row, 0);
            }
            row++;
            button.setOnAction(this);
            undoButtons.add(button);
        }
    }
    @Override
    public void handle(ActionEvent event) {

        if (currentButton != null) {
            currentButton.setStyle("");
        }
        Button selectedButton = (Button) event.getSource();
        if (toggleFill&&selectedButton != null&&selectedButton.getText().equals("Fill")){
            toggleFill = false;
        }else if (!toggleFill&&selectedButton != null&&selectedButton.getText().equals("Fill")){
            toggleFill = true;
        }
        currentButton = selectedButton;
        String command = ((Button) event.getSource()).getText();
        for (Button button : undoButtons) {
            if (!command.equals(button.getText())) {
                button.setStyle(
                        "-fx-background-color: transparent; " +
                                "-fx-border-color: transparent;"
                );
            }
            if (button.getText().equals("Fill") && toggleFill) {
                button.setStyle("-fx-font-size: 10px;-fx-background-color: #6d9ae3;-fx-border-color: transparent;");
            }
            else {
                button.setStyle("-fx-font-size: 10px;");
            }
        this.view.changeShapeButtonColor(this.view.getCurrentColor());
        }
        view.setMode(command);
        this.view.invokeHandle(event);
    }

    public boolean isFilled(){
        return this.toggleFill;
    }
}
