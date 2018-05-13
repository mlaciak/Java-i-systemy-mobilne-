package Application.Controllers;

import Application.Methods.Methode;
import Application.Tasks.Priority;
import Application.Tasks.Task;
import javafx.application.Application;
import javafx.beans.value.ObservableListValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import Application.Controllers.MainController.*;
import javafx.scene.input.KeyCode;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static Application.Methods.Methode.addNewTaskStage;

public class AddTaskController implements Initializable {


    @FXML
    public MenuItem menuItemExit;
    public TextField textFieldAddTaskTitle;
    public Button buttonAddNewTask2;
    public TextField textFieldAddNewTaskDescription;
    public ComboBox<Priority> comboBoxAddNewTaskPriority;
    public DatePicker datePickerAddNewTaskDate;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxAddNewTaskPriority.getItems().addAll(Priority.ASAP,Priority.NOW,Priority.MAYBENEVER);
        menuItemExit.setOnAction(event -> addNewTaskStage.close());

        textFieldAddNewTaskDescription.setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.ENTER){
                AddTaskClick();
            }
        });

        buttonAddNewTask2.setOnMouseClicked(event -> {
            AddTaskClick();
        });
    }
    public void AddTaskClick(){
        String title=textFieldAddTaskTitle.getText();
        String description=textFieldAddNewTaskDescription.getText();
        Priority priority=comboBoxAddNewTaskPriority.getValue();
        LocalDate ld=datePickerAddNewTaskDate.getValue();
        MainController.toDoList.add(new Task(title,description,priority,ld));
        addNewTaskStage.close();
    }
}