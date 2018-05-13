package sample.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Canvas CanvasScreen;
    public Button ButtonStart;
    public ProgressBar ProgressBarWhatsTheProgress;
    public Button ButtonStop;
    public Label LabelInfo;
    public TextField TextFieldNoSymulationPoints;
    public Label LabelStatusResult;
    public  TextField TextFieldResult;

    static double ValueOfIntegral;
    private DrawerTask task;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFieldNoSymulationPoints.setText("10000");
        TextFieldNoSymulationPoints.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {//9999999
                //1000000
                if ((newValue.matches("\\d*")) && (Integer.parseInt(TextFieldNoSymulationPoints.getText()))<=8999999) {
                } else { TextFieldNoSymulationPoints.setText(oldValue); }
            }
        });

        ButtonStart.setOnAction(event -> {
            TextFieldResult.setText(null);
            GraphicsContext gc=CanvasScreen.getGraphicsContext2D();
            Integer NoPoints=Integer.parseInt(TextFieldNoSymulationPoints.getText());
            task = new DrawerTask(gc,NoPoints);
            task.setOnSucceeded(event1 -> {
                ValueOfIntegral=(double) task.getValue();
                TextFieldResult.setText(String.valueOf(ValueOfIntegral));
            });
            task.setOnCancelled(event2 -> {
                TextFieldResult.setText(String.valueOf(ValueOfIntegral*16*16));
            });
            ProgressBarWhatsTheProgress.progressProperty().bind(task.progressProperty());
            new Thread(task).start();
        });

        ButtonStop.setOnAction(event -> task.cancel());
    }
//    private void drawShape(GraphicsContext gc){
//        gc.setFill(Color.BLUEVIOLET);
//        gc.fillRect(gc.getCanvas().getLayoutX(),
//                gc.getCanvas().getLayoutY(),
//                gc.getCanvas().getWidth(),
//                gc.getCanvas().getHeight());
//    }
}
