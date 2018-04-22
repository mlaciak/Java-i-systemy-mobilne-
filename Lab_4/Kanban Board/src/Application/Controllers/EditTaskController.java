package Application.Controllers;

import Application.Methods.Methode;
import Application.Tasks.Priority;
import Application.Tasks.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import Application.Controllers.MainController.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static Application.Controllers.MainController.*;
import static Application.Methods.Methode.TempIndex;
import static Application.Methods.Methode.TempList;
import static Application.Methods.Methode.editTaskStage;

public class EditTaskController implements Initializable {
    public TextField textFieldEditTaskTitle;
    public ComboBox comboBoxEditTaskPriority;
    public DatePicker datePickerEditTaskDate;
    public TextField textFieldEditTaskDescription;
    public MenuItem menuItemExit2;
    public Button buttonEditTask2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuItemExit2.setOnAction(event -> Methode.editTaskStage.close());
        comboBoxEditTaskPriority.getItems().addAll(Priority.ASAP,Priority.NOW,Priority.MAYBENEVER);
        if(TempList==toDoList){
            textFieldEditTaskTitle.setText(toDoList.get(TempIndex).getTitle());
            comboBoxEditTaskPriority.getSelectionModel().select(toDoList.get(TempIndex).getPriority());
            textFieldEditTaskDescription.setText(toDoList.get(TempIndex).getDescription());
            datePickerEditTaskDate.setValue(toDoList.get(TempIndex).getLocalDate());
        }
        else if(TempList==inProgressList){
            textFieldEditTaskTitle.setText(inProgressList.get(TempIndex).getTitle());
            comboBoxEditTaskPriority.getSelectionModel().select(inProgressList.get(TempIndex).getPriority());
            textFieldEditTaskDescription.setText(inProgressList.get(TempIndex).getDescription());
            datePickerEditTaskDate.setValue(inProgressList.get(TempIndex).getLocalDate());
        }
        else if(TempList==doneList){
            textFieldEditTaskTitle.setText(doneList.get(TempIndex).getTitle());
            comboBoxEditTaskPriority.getSelectionModel().select(doneList.get(TempIndex).getPriority());
            textFieldEditTaskDescription.setText(doneList.get(TempIndex).getDescription());
            datePickerEditTaskDate.setValue(doneList.get(TempIndex).getLocalDate());
        }

        textFieldEditTaskDescription.setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.ENTER){
                EditTaskClick();
            }
        });

        buttonEditTask2.setOnMouseClicked(event -> {
            EditTaskClick();
        });
    }
    public void EditTaskClick(){
        if(TempList==toDoList){
//            toDoList.get(TempIndex).setTitle(textFieldEditTaskTitle.getText());
//            toDoList.get(TempIndex).setPriority((Priority) comboBoxEditTaskPriority.getValue());
//            toDoList.get(TempIndex).setDescription(textFieldEditTaskDescription.getText());
//            toDoList.get(TempIndex).setLocalDate(datePickerEditTaskDate.getValue());
            String title=textFieldEditTaskTitle.getText();
            String description=textFieldEditTaskDescription.getText();
            Priority priority= (Priority) comboBoxEditTaskPriority.getValue();
            LocalDate ld=datePickerEditTaskDate.getValue();
            MainController.toDoList.remove(TempIndex);
            MainController.toDoList.add(new Task(title,description,priority,ld));
        }
        else if(TempList==inProgressList){
//            inProgressList.get(TempIndex).setTitle(textFieldEditTaskTitle.getText());
//            inProgressList.get(TempIndex).setPriority((Priority) comboBoxEditTaskPriority.getValue());
//            inProgressList.get(TempIndex).setDescription(textFieldEditTaskDescription.getText());
//            inProgressList.get(TempIndex).setLocalDate(datePickerEditTaskDate.getValue());
            String title=textFieldEditTaskTitle.getText();
            String description=textFieldEditTaskDescription.getText();
            Priority priority= (Priority) comboBoxEditTaskPriority.getValue();
            LocalDate ld=datePickerEditTaskDate.getValue();
            MainController.inProgressList.remove(TempIndex);
            MainController.inProgressList.add(new Task(title,description,priority,ld));
        }
        else if(TempList==doneList){
//            doneList.get(TempIndex).setTitle(textFieldEditTaskTitle.getText());
//            doneList.get(TempIndex).setPriority((Priority) comboBoxEditTaskPriority.getValue());
//            doneList.get(TempIndex).setDescription(textFieldEditTaskDescription.getText());
//            doneList.get(TempIndex).setLocalDate(datePickerEditTaskDate.getValue());
            String title=textFieldEditTaskTitle.getText();
            String description=textFieldEditTaskDescription.getText();
            Priority priority= (Priority) comboBoxEditTaskPriority.getValue();
            LocalDate ld=datePickerEditTaskDate.getValue();
            MainController.doneList.remove(TempIndex);
            MainController.doneList.add(new Task(title,description,priority,ld));
        }
        editTaskStage.close();
    }
}
