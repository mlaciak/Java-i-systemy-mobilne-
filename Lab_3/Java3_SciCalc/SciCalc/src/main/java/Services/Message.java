package Services;

import org.mariuszgromada.math.mxparser.*;
import java.text.MessageFormat;
import java.util.Locale;

import App.SciCalc;

import javax.swing.*;

public class Message extends MessageFormat {

    public Message(){
        super("");
    }

    public String msg(String formula){
         /*
        Przykład! Dla wypisu CMD--------------------------------------------------------------
                Function At=new Function("At(b,h)=1/2*b*h");
         ------>Expression e1= new Expression("At(2,4)",At);
         ------>mXparser.consolePrintln(e1.getExpressionString()+" = "+e1.calculate());
         -------------------------------------------------------------------------------------
         */
        Expression expression = new Expression(formula);
        Double result = expression.calculate();

        try {
            if(Double.toString(result)=="NaN"){
                throw new ArithmeticException("Not a Number Exception!");
            }
            else {
                Object [] objects = {formula, result};
                MessageFormat messageFormat = new MessageFormat("{0}\n\t = {1} \n---\n");
                messageFormat.setLocale(Locale.US); //formatowanie z przecinka do kropki!
                return messageFormat.format(objects);
            }
        }
        catch (ArithmeticException e){
            System.err.println(e);
            errorMessage2();
        }
        return "";
    }

    public String lrmsg(String formula){
         /*
        Przykład! Dla wypisu CMD--------------------------------------------------------------
                Function At=new Function("At(b,h)=1/2*b*h");
         ------>Expression e1= new Expression("At(2,4)",At);
         ------>mXparser.consolePrintln(e1.getExpressionString()+" = "+e1.calculate());
         -------------------------------------------------------------------------------------
         */
        Expression expression = new Expression(formula);
        Double result = expression.calculate();

        try {
            if(Double.toString(result)=="NaN"){
                throw new ArithmeticException("Not a Number Exception!");
            }
            else {
                Object [] objects = {formula, result};
                MessageFormat messageFormat = new MessageFormat("{1}");
                return messageFormat.format(objects);
            }
        }
        catch (ArithmeticException e){
            System.err.println(e);
            errorMessage2();
        }
        return "";
    }

    public void errorMessage2() {
        JOptionPane.showMessageDialog(null, "WRONG INPUT OR FORMULA",
                "ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
    }
}
