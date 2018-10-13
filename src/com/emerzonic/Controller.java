package com.emerzonic;

import com.emerzonic.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<TodoItem> todoItems;

    @FXML
    private ListView<TodoItem> todoListView;
    @FXML
    private TextArea itemDetailTextArea;

    public void initialize(){
        TodoItem item1 = new TodoItem("Walk the dog","Walk the dog around the lake", LocalDate.of(2018, Month.JANUARY, 20));
        TodoItem item2= new TodoItem("Do laundry","yes do the laundry", LocalDate.of(2018, Month.JANUARY, 21));
        TodoItem item3 = new TodoItem("Make dinner","Make my favorite dish for dinner", LocalDate.of(2018, Month.JANUARY, 22));
        TodoItem item4 = new TodoItem("Do some coding","Continue working on the my JavaFx project", LocalDate.of(2018, Month.JANUARY, 23));
        TodoItem item5 = new TodoItem("Read to my boy","Do a 30 minutes reading session with my boy", LocalDate.of(2018, Month.JANUARY, 24));
        TodoItem item6 = new TodoItem("Watch a movie","Watch my favorite movie", LocalDate.of(2018, Month.JANUARY, 25));


        todoItems = new ArrayList<TodoItem>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);
        todoItems.add(item6);

        todoListView.getItems().setAll(todoItems);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);



    }

    @FXML
    public void handleClickListView(){
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
        //System.out.println("Selected item is "+item);
        StringBuilder sb = new StringBuilder(item.getDetails());
        sb.append("]\n\n\n\n");
        sb.append("Due: ");
        sb.append(item.getDeadline().toString());
        itemDetailTextArea.setText(sb.toString());


    }


}
