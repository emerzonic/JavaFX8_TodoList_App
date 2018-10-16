package src.emerzonic;

import src.datamodel.TodoData;
import src.datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class Controller {
    private List<TodoItem> todoItems;
    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea itemDetailTextArea;
    @FXML
    private Label deadlineLabel;
    @FXML
    private BorderPane mainBorderPane;

    public void initialize(){
//        TodoItem item1 = new TodoItem("Walk the dog","Walk the dog around the lake",
//                LocalDate.of(2018, Month.JANUARY, 20));
//        TodoItem item2= new TodoItem("Do laundry","yes do the laundry",
//                LocalDate.of(2018, Month.JANUARY, 21));
//        TodoItem item3 = new TodoItem("Make dinner","Make my favorite dish for dinner",
//                LocalDate.of(2018, Month.JANUARY, 22));
//        TodoItem item4 = new TodoItem("Do some coding","Continue working on the my JavaFx project",
//                LocalDate.of(2018, Month.JANUARY, 23));
//        TodoItem item5 = new TodoItem("Read to my boy","Do a 30 minutes reading session with my boy",
//                LocalDate.of(2018, Month.JANUARY, 24));
//        TodoItem item6 = new TodoItem("Watch a movie","Watch my favorite movie",
//                LocalDate.of(2018, Month.JANUARY, 25));
//        TodoItem item7 = new TodoItem("Another todo with very long detail","Another standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
//                LocalDate.of(2018, Month.JANUARY, 27));
//
//
//        todoItems = new ArrayList<TodoItem>();
//        todoItems.add(item1);
//        todoItems.add(item2);
//        todoItems.add(item3);
//        todoItems.add(item4);
//        todoItems.add(item5);
//        todoItems.add(item6);
//        todoItems.add(item7);
//
//        TodoData.getInstance().setTodoItems(todoItems);


        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observable, TodoItem oldValue, TodoItem newValue) {
                if (newValue !=null){
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailTextArea.setText(item.getDetails());
//                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    deadlineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });

        todoListView.setItems(TodoData.getInstance().getTodoItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();

        

    }

    public void showNewItemDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Todo Item");
        dialog.setHeaderText("Use this window to create new todo item.");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/src/emerzonic/todoItemDialog.fxml"));
        try {
//          Parent root = FXMLLoader.load(getClass().getResource("/src/emerzonic/todoItemDialog.fxml"));
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent()&&result.get()==ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            TodoItem newItem = controller.processResult();
            todoListView.getSelectionModel().select(newItem);
        }
    }


    @FXML
    public void handleClickListView(){
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        itemDetailTextArea.setText(item.getDetails());
        deadlineLabel.setText(df.format(item.getDeadline()));
//        //System.out.println("Selected item is "+item);
//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("]\n\n\n\n");
//        sb.append("Due: ");
//        sb.append(item.getDeadline().toString());
//        itemDetailTextArea.setText(sb.toString());


    }


}
