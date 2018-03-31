package App;

import App.Functions;
import Services.Message;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class SciCalc {
    private JPanel mainPanel;
    private JButton evalButton;
    private JTextArea historyTextArea;
    private JScrollPane scrollContainerPanel;
    private JTextField formulaInput;
    private JList functionList;

//Zastosowanie DefaultListModel, do przechowywania listy funkcji -_- --------------------------------
    DefaultListModel<Functions> listModel = new DefaultListModel<>();
    List<String> lastResult = new ArrayList();

    public SciCalc() {
//Przypisanie do function list modelu----------------------------------------------------------------
        functionList.setModel(listModel);

//Dodanie do listy FUNKCJI, na podstawie modelu
        setFunctionsToList();

//Ustawienie głównego okna, mechanizmu zamykania (na Xie), tytuł, oraz rozmiar jak i widoczność------
        JFrame frame = new JFrame("SciCalc Like NASA");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        historyTextArea.setForeground(Color.GREEN);
        historyTextArea.setBackground(Color.BLACK);

//Ustawienie paska menu, wraz z ich działaniem---------------------------------------------------------
        JMenuBar menu = new JMenuBar();
        frame.setJMenuBar(menu);
        JMenu options = new JMenu("Options");
        JMenu about = new JMenu("About");
        menu.add(options);
        menu.add(about);

//Dodanie opcji do menu:--------------------------------------------------------------------------------
        JMenuItem reset=new JMenuItem("RESET");
        JMenuItem exit= new JMenuItem("EXIT");
        JMenuItem info= new JMenuItem("About");
        options.add(reset);
        options.add(exit);
        about.add(info);

//Dodanie akcji do przycisków exit oraz reset:----------------------------------------------------------
        reset.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                historyTextArea.setText("");
                formulaInput.setText("");
                lastResult.clear();
            }
        });

        exit.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Scientific Calculator ^^\n\n" +
                                "Press UP arrow key for last input!\n\n" +
                                "By Clicking formulas from the left side,\n" +
                                "transfer them to the input area\n\n" +
                                "If any problems, feel free to contact via @",
                        "ABOUT",JOptionPane.INFORMATION_MESSAGE);
            }
        });

//Dodanie akcji do GUZIKA evalButton--------------------------------------------------------------------
        evalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(formulaInput.getText()==null){
                    errorMessage();
                }
                else {
                    lastResult.add(formulaInput.getText());
                    historyTextArea.append(new Message().msg(formulaInput.getText()));
                    formulaInput.setText(null);
                }
            }
        });

//ActionListener dla wpisywanej formuły-----------------------------------------------------------------
        formulaInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(formulaInput.getText()==null || formulaInput.getText()==""){
                    errorMessage();
                }
                else {
                    lastResult.add(formulaInput.getText());
                    historyTextArea.append(new Message().msg(formulaInput.getText()));
                    formulaInput.setText(null);
                }
            }
        });

//Akcja dla strzałki w górę (wypisanie ostatniej formuły, jeżeli istnieje)------------------------------------
        formulaInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    if(lastResult.isEmpty()){
                        errorMessage();
                    }
                    else {
                        formulaInput.setText(lastResult.get(lastResult.size()-1));
                    }
                }
            }
        });

//Mouse Listener dla wyoboru opcji z listy (function list)-----------------------------------------------
        functionList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    int positionOfFormula = functionList.getSelectedIndex();
                    formulaInput.setText(listModel.getElementAt(positionOfFormula).getEquivalent());
                    formulaInput.requestFocus();

                    if(formulaInput.getText().contains("()")){
                        formulaInput.setCaretPosition(formulaInput.getText().length()-1);
                    }
                    else if(formulaInput.getText().contains(",")){
                        formulaInput.setCaretPosition(formulaInput.getText().length()-2);
                    }
                    else if(formulaInput.getText().equals("lr")){
                        if(lastResult.isEmpty()){
                            errorMessage();
                        }
                        else if(lastResult.get(lastResult.size()-1).contains("pi")){
                            formulaInput.setText("3.142");
                        }
                        else if(lastResult.get(lastResult.size()-1).contains("3.142")){
                            formulaInput.setText("3.142");
                        }
                        else {
                            formulaInput.setText(new Message().lrmsg(lastResult.get(lastResult.size()-1)));
                        }
                    }
                    else {
                        formulaInput.setCaretPosition(formulaInput.getText().length());
                    }
                }
            }
        });
    }

//Funkcja błędu na wypadek, gdyby nie było last result, bądź nic do rozwiązania---------------------------
    public void errorMessage() {
        JOptionPane.showMessageDialog(null, "WRONG INPUT OR FORMULA",
                "ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
    }

//Zbiór funkcji typu function zapisanych według modelu pod mXparser-------------------------------------
    public void setFunctionsToList(){
        //Jedno Argumentowe funkcje
        Functions sin = new Functions("Trigonometric sine function","sin()");
        listModel.addElement(sin);
        Functions cos = new Functions("Trigonometric cosine function","cos()");
        listModel.addElement(cos);
        Functions tan = new Functions("Trigonometric tangent function","tan()");
        listModel.addElement(tan);
        //2 Argumentowe funkcje
        Functions log = new Functions("Logarithm function","log(,)");
        listModel.addElement(log);
        Functions mod = new Functions("Modulo function","mod(,)");
        listModel.addElement(mod);
        Functions C = new Functions("Binomial coefficient function","C(,)");
        listModel.addElement(C);
        //Wyswietlanie stałych
        Functions pi = new Functions("Pi","pi");
        listModel.addElement(pi);
        Functions e = new Functions("Euler's number","e");
        listModel.addElement(e);
        Functions phi = new Functions("Golden ratio", "[phi]");
        listModel.addElement(phi);
        //last result
        Functions lastRes = new Functions("Last Result","lr");
        listModel.addElement(lastRes);
    }

    public static void main(String[] args) {
        new SciCalc();
    }
}
