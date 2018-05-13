package sample.Controllers;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import sample.Model.Equation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class DrawerTask extends Task {
    GraphicsContext gc;
    BufferedImage bufferedImage;
    private double x;
    private double y;
    private Integer povierhnia1;
    private Integer povierhnia2;
    private Random rand=new Random();
    private double ValueOfIntegral;
    private Integer NoSymulationPoints;

    public DrawerTask(GraphicsContext gc, Integer NoSymulationPoints){
        this.gc=gc;
        this.NoSymulationPoints=NoSymulationPoints;
        povierhnia1=0;
        povierhnia2=0;
        bufferedImage=new BufferedImage(800, 500, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    protected Object call() throws Exception {
        for(int i=0; i<NoSymulationPoints; i++){
            //losowa liczba rzeczywista z zakresu min max
            x = -8 + 16 *rand.nextDouble();
            y = -8 + 16 *rand.nextDouble();

            if(Equation.calc(x,y)){
                bufferedImage.setRGB((int)(400 - 400 * x / 8), (int) (250 - 250 * y / 8), Color.YELLOW.getRGB());
                povierhnia1++;
            }else {
                bufferedImage.setRGB((int) (400 - 400 * x / 8), (int) (250 - 250 * y / 8), Color.BLUE.getRGB());
                povierhnia2++;
            }

            if(i%1000==0){
                gc.drawImage(SwingFXUtils.toFXImage(bufferedImage, null), 0, 0);
                ValueOfIntegral = (double) povierhnia1 / (povierhnia1 + povierhnia2);
                sample.Controllers.Controller.ValueOfIntegral=ValueOfIntegral;
                updateProgress(i, NoSymulationPoints);
            }
            if(isCancelled()) {
                break;
            }
        }
        return ValueOfIntegral*16*16;
        }
}
