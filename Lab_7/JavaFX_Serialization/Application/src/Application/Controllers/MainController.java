package Application.Controllers;

import Application.Tasks.Task;
import Application.toSerialize;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static Application.Methods.Methode.AboutShowAlert;
import static Application.Methods.Methode.CreateAddTaskWindow;
import static Application.Methods.Methode.CreateEditTaskWindow;


public class MainController implements Initializable, Serializable {

    public static ObservableList<Task> toDoList=FXCollections.observableArrayList();
    public static ObservableList<Task> inProgressList=FXCollections.observableArrayList();
    public static ObservableList<Task> doneList=FXCollections.observableArrayList();



    toSerialize toS=new toSerialize();


    @FXML
    public MenuItem menuItem_Close;
    public MenuItem menuItemAbout;
    public Button buttonAddNewTask;
    public MenuItem menuItemImport;
    public MenuItem menuItemExport;
    public GridPane gridpane;

    public ListView<Task> listVievToDo=new ListView<>(toDoList);
    public ListView<Task> listVievInProgress=new ListView<>(inProgressList);
    public ListView<Task> listVievDone=new ListView<>(doneList);
    public MenuItem contextMenuToDoDelete;
    public MenuItem contextMenuInProgressDelete;
    public MenuItem contextMenuDoneDelete;
    public MenuItem contextMenuToDoEdit;
    public MenuItem contextMenuInProgressEdit;
    public MenuItem contextMenuDoneEdit;
    public MenuItem menuItemSave;
    public MenuItem menuItemLoad;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listVievToDo.setItems(toDoList);
        listVievInProgress.setItems(inProgressList);
        listVievDone.setItems(doneList);
        menuItem_Close.setOnAction(event -> {
            System.exit(0);
        });
        menuItemAbout.setOnAction(event -> AboutShowAlert());

        menuItemSave.setOnAction(event -> {
//            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("obiektM1.bin"))){
//                ArrayList<Task> cast=new ArrayList<>(toDoList);
//                outputStream.writeObject(cast);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("obiektM2.json"))){
//                ArrayList<Task> cast=new ArrayList<>(inProgressList);
//                outputStream.writeObject(cast);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("obiektM3.csv"))){
//                ArrayList<Task> cast=new ArrayList<>(doneList);
//                outputStream.writeObject(cast);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            toS.cast1=new ArrayList<>(toDoList);
            toS.cast2=new ArrayList<>(inProgressList);
            toS.cast3=new ArrayList<>(doneList);

            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("obiektM.bin"))){
                outputStream.writeObject(toS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        menuItemLoad.setOnAction(event -> {
//            try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("obiektM1.bin"))){
//                ArrayList<Task> cast=new ArrayList<>(toDoList);
//                cast= (ArrayList<Task>) inputStream.readObject();
//                toDoList= FXCollections.observableArrayList(cast);
//                listVievToDo.setItems(toDoList);
//            } catch (FileNotFoundException e){
//                System.err.println("Nie znaleziono pliku "+e);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("obiektM2.json"))){
//                ArrayList<Task> cast=new ArrayList<>(inProgressList);
//                cast= (ArrayList<Task>) inputStream.readObject();
//                inProgressList= FXCollections.observableArrayList(cast);
//                listVievInProgress.setItems(inProgressList);
//            } catch (FileNotFoundException e){
//                System.err.println("Nie znaleziono pliku "+e);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("obiektM3.csv"))){
//                ArrayList<Task> cast=new ArrayList<>(doneList);
//                cast= (ArrayList<Task>) inputStream.readObject();
//                doneList= FXCollections.observableArrayList(cast);
//                listVievDone.setItems(doneList);
//            } catch (FileNotFoundException e){
//                System.err.println("Nie znaleziono pliku "+e);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
            try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("obiektM.bin"))){
                toS = (toSerialize) inputStream.readObject();
            } catch (FileNotFoundException e){
                System.err.println("Nie znaleziono pliku "+e);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            toDoList= FXCollections.observableArrayList(toS.cast1);
            listVievToDo.setItems(toDoList);

            inProgressList= FXCollections.observableArrayList(toS.cast2);
            listVievInProgress.setItems(inProgressList);

            doneList= FXCollections.observableArrayList(toS.cast3);
            listVievDone.setItems(doneList);
        });

//----------------------------------------------------------IMPORT EXPORT-----------------------------------------------
        menuItemImport.setOnAction(event -> {
                FileChooser fileChooser = new FileChooser();
                Stage FCstage = (Stage) gridpane.getScene().getWindow();
                File workingDirectory = new File(System.getProperty("user.dir"));
                fileChooser.setInitialDirectory(workingDirectory);
                fileChooser.setTitle("Choose file to import data");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("JSON", "*.json"),
                        new FileChooser.ExtensionFilter("CSV", "*.csv")
                );
                File file = fileChooser.showOpenDialog(FCstage);

                if (file != null) {
                    //System.out.println(file.getAbsolutePath());

                    try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()))){
                        toS = (toSerialize) inputStream.readObject();
                    } catch (FileNotFoundException e){
                        System.err.println("Nie znaleziono pliku "+e);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    toDoList= FXCollections.observableArrayList(toS.cast1);
                    listVievToDo.setItems(toDoList);

                    inProgressList= FXCollections.observableArrayList(toS.cast2);
                    listVievInProgress.setItems(inProgressList);

                    doneList= FXCollections.observableArrayList(toS.cast3);
                    listVievDone.setItems(doneList);
                }
        });

        menuItemExport.setOnAction(event -> {
                    FileChooser fileChooser = new FileChooser();
                    Stage FCstage = (Stage) gridpane.getScene().getWindow();
                    File workingDirectory = new File(System.getProperty("user.dir"));
                    fileChooser.setInitialDirectory(workingDirectory);
                    fileChooser.setTitle("Choose file to import data");
                    fileChooser.getExtensionFilters().addAll(
                            new FileChooser.ExtensionFilter("JSON", "*.json"),
                            new FileChooser.ExtensionFilter("CSV", "*.csv")
                    );
                    File file = fileChooser.showSaveDialog(FCstage);

                    if (file != null) {
                        toS.cast1=new ArrayList<>(toDoList);
                        toS.cast2=new ArrayList<>(inProgressList);
                        toS.cast3=new ArrayList<>(doneList);

                        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()))){
                            outputStream.writeObject(toS);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {}
        });
//----------------------------------------------------------ADD TASK----------------------------------------------------
        buttonAddNewTask.setOnMouseClicked(event -> {
            try {
                CreateAddTaskWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
//------------------------------------------------------------------------------ PRZENOSZENIE!-------------------------
        listVievToDo.setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.RIGHT){
                if(!listVievToDo.getItems().isEmpty()){
                    listVievInProgress.getItems().add(listVievToDo.getItems().get(listVievToDo.getFocusModel().getFocusedIndex()));
                    listVievToDo.getItems().remove(listVievToDo.getItems().get(listVievToDo.getFocusModel().getFocusedIndex()));
                }
            }
        });

        listVievInProgress.setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.RIGHT){
                if(!listVievInProgress.getItems().isEmpty()){
                    listVievDone.getItems().add(listVievInProgress.getItems().get(listVievInProgress.getFocusModel().getFocusedIndex()));
                    listVievInProgress.getItems().remove(listVievInProgress.getItems().get(listVievInProgress.getFocusModel().getFocusedIndex()));
                }
            }
            else if (event.getCode()==KeyCode.LEFT){
                if(!listVievInProgress.getItems().isEmpty()){
                    listVievToDo.getItems().add(listVievInProgress.getItems().get(listVievInProgress.getFocusModel().getFocusedIndex()));
                    listVievInProgress.getItems().remove(listVievInProgress.getItems().get(listVievInProgress.getFocusModel().getFocusedIndex()));
                }
            }
        });

        listVievDone.setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.LEFT){
                if(!listVievDone.getItems().isEmpty()){
                    listVievInProgress.getItems().add(listVievDone.getItems().get(listVievDone.getFocusModel().getFocusedIndex()));
                    listVievDone.getItems().remove(listVievDone.getItems().get(listVievDone.getFocusModel().getFocusedIndex()));
                }
            }
        });

        listVievToDo.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override public ListCell<Task> call(ListView<Task> list) {
                return new ColorRectCell();
            }
        });
        listVievInProgress.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override public ListCell<Task> call(ListView<Task> list) {
                return new ColorRectCell();
            }
        });
        listVievDone.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override public ListCell<Task> call(ListView<Task> list) {
                return new ColorRectCell();
            }
        });
//----------------------------------------------------------------USUWANIE PRZEDMIOTOW PRAWYM GUZIKIEM MYSZY------------
        listVievToDo.setOnMouseClicked(event -> {
            if(listVievToDo.getItems().isEmpty()){ contextMenuToDoDelete.setVisible(false);
            contextMenuToDoEdit.setVisible(false); }
            else { contextMenuToDoDelete.setVisible(true);
                contextMenuToDoEdit.setVisible(true);}
        });
        listVievInProgress.setOnMouseClicked(event -> {
            if(listVievInProgress.getItems().isEmpty()){ contextMenuInProgressDelete.setVisible(false);
                contextMenuInProgressEdit.setVisible(false);}
            else { contextMenuInProgressDelete.setVisible(true);
                contextMenuInProgressEdit.setVisible(true);}
        });
        listVievDone.setOnMouseClicked(event -> {
            if(listVievDone.getItems().isEmpty()){ contextMenuDoneDelete.setVisible(false);
                contextMenuDoneEdit.setVisible(false);}
            else { contextMenuDoneDelete.setVisible(true);
                contextMenuDoneEdit.setVisible(true);}
        });

        contextMenuToDoDelete.setOnAction(event -> {
            if(!listVievToDo.getItems().isEmpty()){
                listVievToDo.getItems().remove(listVievToDo.getItems().get(listVievToDo.getFocusModel().getFocusedIndex()));
            }
        });
        contextMenuInProgressDelete.setOnAction(event -> {
            if(!listVievInProgress.getItems().isEmpty()){
                listVievInProgress.getItems().remove(listVievInProgress.getItems().get(listVievInProgress.getFocusModel().getFocusedIndex()));
            }
        });
        contextMenuDoneDelete.setOnAction(event -> {
            if(!listVievDone.getItems().isEmpty()){
                listVievDone.getItems().remove(listVievDone.getItems().get(listVievDone.getFocusModel().getFocusedIndex()));
            }
        });
//--------------------------------------------------------EDYCJA PRZEDMIOTOW PRAWYM GUZIKIEM MYSZY-----------------------
        contextMenuToDoEdit.setOnAction(event -> {
            try {
                CreateEditTaskWindow(toDoList,listVievToDo.getFocusModel().getFocusedIndex());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        contextMenuInProgressEdit.setOnAction(event -> {
            try {
                CreateEditTaskWindow(inProgressList,listVievInProgress.getFocusModel().getFocusedIndex());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        contextMenuDoneEdit.setOnAction(event -> {
            try {
                CreateEditTaskWindow(doneList,listVievDone.getFocusModel().getFocusedIndex());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }//-------------------------------------KONIEC INIT-----------------------------------------------------------------
//------------------------------------------------------------TOOTLIP + KOLORKI-----------------------------------------
    static class ColorRectCell extends ListCell<Task> {
        private Circle getCircle(){
            Circle rect = new Circle(5);
            rect.setFill(Color.web(getItem().getPriorityColor()));
            return rect;
        }


        @Override
        public void updateItem(Task item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
                setTooltip(null);
            }
            else{
                setText(getItem().getTitle());
                setGraphic(getCircle());
                Tooltip tooltip = new Tooltip();
                tooltip.setText(getItem().getDescription());
                setTooltip(tooltip);
            }
        }
    }
}