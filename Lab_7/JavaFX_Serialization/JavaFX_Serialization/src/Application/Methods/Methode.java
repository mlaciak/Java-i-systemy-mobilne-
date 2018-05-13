package Application.Methods;

import Application.Tasks.Task;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

public class Methode implements Serializable {
    public static Stage addNewTaskStage=new Stage();
    public static Stage editTaskStage=new Stage();
    public static ObservableList<Task> TempList=null;
    public static int TempIndex;

    public static void AboutShowAlert(){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Author Information");
        alert.setContentText("Michał Łaciak, IO, 261354");
        alert.showAndWait();
    }

    public static void CreateAddTaskWindow() throws IOException {
        try {
            FXMLLoader loaderAddTask = new FXMLLoader();
            loaderAddTask.setLocation(Methode.class.getClassLoader().getResource("Application/FXMLs/AddTaskGUI.fxml"));
            Parent root1 = loaderAddTask.load();
            addNewTaskStage.setTitle("Add New Task");
            addNewTaskStage.setScene(new Scene(root1,600,400));
            addNewTaskStage.show();
        }catch (IOException e){
            System.err.println("Can not create new scene! "+e);
        }
    }

    public static void CreateEditTaskWindow(ObservableList<Task> list, int position) throws IOException {
        try {
            TempList=list;
            TempIndex=position;
            FXMLLoader loaderEditTask = new FXMLLoader();
            loaderEditTask.setLocation(Methode.class.getClassLoader().getResource("Application/FXMLs/EditTaskGUI.fxml"));
            Parent root1 = loaderEditTask.load();
            editTaskStage.setTitle("Edit Task");
            editTaskStage.setScene(new Scene(root1,600,400));
            editTaskStage.show();
        }catch (IOException e){
            System.err.println("Can not create new scene! "+e);
        }
    }
}