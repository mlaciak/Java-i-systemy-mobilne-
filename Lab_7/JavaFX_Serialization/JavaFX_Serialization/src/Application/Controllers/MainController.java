package Application.Controllers;

import Application.Tasks.Priority;
import Application.Tasks.Task;
import Application.toSerialize;
import com.google.gson.Gson;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static Application.Methods.Methode.AboutShowAlert;
import static Application.Methods.Methode.CreateAddTaskWindow;
import static Application.Methods.Methode.CreateEditTaskWindow;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;


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
        menuItem_Close.setOnAction(event -> System.exit(0));
        menuItemAbout.setOnAction(event -> AboutShowAlert());

        menuItemSave.setOnAction(event -> {
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
//                FileChooser fileChooser = new FileChooser();
//                Stage FCstage = (Stage) gridpane.getScene().getWindow();
//                File workingDirectory = new File(System.getProperty("user.dir"));
//                fileChooser.setInitialDirectory(workingDirectory);
//                fileChooser.setTitle("Choose file to import data");
//                fileChooser.getExtensionFilters().addAll(
//                        new FileChooser.ExtensionFilter("JSON", "*.json"),
//                        new FileChooser.ExtensionFilter("CSV", "*.csv")
//                );
//                File file = fileChooser.showOpenDialog(FCstage);
//
//                if (file != null) {
//                    //System.out.println(file.getAbsolutePath());
//
//                    try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()))){
//                        toS = (toSerialize) inputStream.readObject();
//                    } catch (FileNotFoundException e){
//                        System.err.println("Nie znaleziono pliku "+e);
//                    } catch (IOException | ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    toDoList= FXCollections.observableArrayList(toS.cast1);
//                    listVievToDo.setItems(toDoList);
//
//                    inProgressList= FXCollections.observableArrayList(toS.cast2);
//                    listVievInProgress.setItems(inProgressList);
//
//                    doneList= FXCollections.observableArrayList(toS.cast3);
//                    listVievDone.setItems(doneList);

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
                toS.cast1=new ArrayList<>(toDoList);
                toS.cast2=new ArrayList<>(inProgressList);
                toS.cast3=new ArrayList<>(doneList);
            if (file != null) {
                try(Reader reader = new FileReader(file.getAbsolutePath())){
                    switch (fileChooser.getSelectedExtensionFilter().getDescription()){
                        case "JSON" :
                            loadJSONfile(reader);
                            break;
                        case "CSV" :
                            loadFromCSVfile(file);
                            break;
                    }

                    toDoList= FXCollections.observableArrayList(toS.cast1);
                    listVievToDo.setItems(toDoList);

                    inProgressList= FXCollections.observableArrayList(toS.cast2);
                    listVievInProgress.setItems(inProgressList);

                    doneList= FXCollections.observableArrayList(toS.cast3);
                    listVievDone.setItems(doneList);
                } catch (FileNotFoundException | NullPointerException e){
                    System.err.println("Nie znaleziono pliku "+e);
                } catch (IOException e) { }
            }
                });
        menuItemExport.setOnAction(event -> {
//                    FileChooser fileChooser = new FileChooser();
//                    Stage FCstage = (Stage) gridpane.getScene().getWindow();
//                    File workingDirectory = new File(System.getProperty("user.dir"));
//                    fileChooser.setInitialDirectory(workingDirectory);
//                    fileChooser.setTitle("Choose file to import data");
//                    fileChooser.getExtensionFilters().addAll(
//                            new FileChooser.ExtensionFilter("JSON", "*.json"),
//                            new FileChooser.ExtensionFilter("CSV", "*.csv")
//                    );
//                    File file = fileChooser.showSaveDialog(FCstage);
//
//                    if (file != null) {
//                        toS.cast1=new ArrayList<>(toDoList);
//                        toS.cast2=new ArrayList<>(inProgressList);
//                        toS.cast3=new ArrayList<>(doneList);
//
//                        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file.getAbsolutePath()))){
//                            outputStream.writeObject(toS);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }else {}
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

                    switch (fileChooser.getSelectedExtensionFilter().getDescription()){
                        case "JSON" :
                            generateJSONfile(file);
                            break;
                        case "CSV" :
                            generateCSVfile(file);
                            break;
                        }
                    }
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
//    ---------------------------------------------------------------METODY DO SERIALIZACJI CSV I JSONA-----------------
    public void generateCSVfile(File file){
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("title", "description", "priority", "localDate", "typeOfList"));
        ) {

            for (int i = 0; i < toS.cast1.size(); i++){
                csvPrinter.printRecord(toS.cast1.get(i).getTitle(),
                        toS.cast1.get(i).getDescription().replace("\n"," "),
                        toS.cast1.get(i).getPriority(),
                        toS.cast1.get(i).getLocalDate(),
                        "cast1");
            }
            for (int i = 0; i < toS.cast2.size(); i++){
                csvPrinter.printRecord(toS.cast2.get(i).getTitle(),
                        toS.cast2.get(i).getDescription().replace("\n"," "),
                        toS.cast2.get(i).getPriority(),
                        toS.cast2.get(i).getLocalDate(),
                        "cast2");
            }

            for (int i = 0; i < toS.cast3.size(); i++){
                csvPrinter.printRecord(toS.cast3.get(i).getTitle(),
                        toS.cast3.get(i).getDescription().replace("\n"," "),
                        toS.cast3.get(i).getPriority(),
                        toS.cast3.get(i).getLocalDate(),
                        "cast3");
            }

            csvPrinter.flush();
        } catch (IOException e){ }
    }

    public void loadFromCSVfile(File file){
        try (
                Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            toS.cast1.clear();
            toS.cast2.clear();
            toS.cast3.clear();
            for (CSVRecord csvRecord : csvParser) {
                String title = csvRecord.get("title");
                String description = csvRecord.get("description");
                String priority = csvRecord.get("priority");
                LocalDate localdate = LocalDate.parse(csvRecord.get("localDate"));
                String typeOfList = csvRecord.get("typeOfList");

                Priority priority1;
                if(priority.equals("ASAP")) {priority1=Priority.ASAP;}
                else if(priority.equals("NOW")){priority1=Priority.NOW;}
                else if(priority.equals("MAYBENEVER")){priority1=Priority.MAYBENEVER;}
                else {priority1=Priority.MAYBENEVER;}

                System.out.println(priority);
                switch (typeOfList) {
                    case "cast1":
                        toS.cast1.add(new Task(title, description, priority1, localdate));
                        break;
                    case "cast2":
                        toS.cast2.add(new Task(title, description, priority1, localdate));
                        break;
                    case "cast3":
                        toS.cast3.add(new Task(title, description, priority1, localdate));
                        break;
                }
            }
        } catch (IOException e){ }
    }

//    GSON--------------------------------------------------------------------------------------------------------------
    public void generateJSONfile(File file){
        Gson gson = new Gson();

        try(FileWriter writer = new FileWriter(file.getAbsolutePath())){
            gson.toJson(toS, writer);
        } catch (IOException e) {}
    }

    public void loadJSONfile(Reader reader){
        Gson gson = new Gson();
        toS =  gson.fromJson(reader, toSerialize.class);
    }
}