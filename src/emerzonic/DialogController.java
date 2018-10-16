package src.emerzonic;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import src.datamodel.TodoData;
import src.datamodel.TodoItem;

import java.time.LocalDate;

public class DialogController {


    @FXML
    private TextField description;
    @FXML
    private TextArea detailsArea;
    @FXML
    private DatePicker deadlinePicker;

    public TodoItem processResult(){
        String shortDescription = description.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadline = deadlinePicker.getValue();

        TodoItem newItem = new TodoItem(shortDescription, details, deadline);
        TodoData.getInstance().addTodoItem(newItem);
        return newItem;
    }
}

