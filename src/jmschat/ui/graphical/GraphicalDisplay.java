package jmschat.ui.graphical;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.DefaultCaret;
import jmschat.ChatClient;
import jmschat.ui.Display;

/**
 * Stellt das Display in einer Grafischen Oberflaeche dar
 * @author Dominik
 * @version 0.1
 */
public class GraphicalDisplay implements Display {
    
    private static final int SCREEN_WIDTH = 640;
    private static final int SCREEN_HEIGHT = 640;
    
    private ChatClient cc;
    private SendListener sl;
    
    JFrame frame;
    JPanel mainPanel, bottomPanel;
    JTextArea textArea;
    JTextField textField;
    JButton sendButton;
    Random rand;
    Font font;
    
    public GraphicalDisplay(ChatClient cc) {
        this.cc = cc;
        sl = new SendListener(this,cc);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        
        rand = new Random(System.currentTimeMillis());
        frame = new JFrame("JMS Chat");
        frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-SCREEN_WIDTH/2, 
                        Toolkit.getDefaultToolkit().getScreenSize().height/2-SCREEN_HEIGHT/2, 
                        SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textArea = new JTextArea();
        font = new Font("Lucida Console",Font.PLAIN,12);
        textArea.setFont(font);
        textArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        ((DefaultCaret)textArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        mainPanel = new JPanel(new BorderLayout());
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),BorderLayout.CENTER);
        textField = new JTextField();
        sendButton = new JButton("Send");
        sendButton.addActionListener(sl);
        textField.addActionListener(sl);
        bottomPanel.add(textField,BorderLayout.CENTER);
        bottomPanel.add(sendButton,BorderLayout.EAST);
        mainPanel.add(bottomPanel,BorderLayout.SOUTH);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    /**
     * Gibt eine Nachricht aus
     * @param msg die Nachricht welche ausgegeben werden soll
     */
    @Override
    public void out(String msg) {
        textArea.append(msg + "\n");
    }

    /**
     * Dreht das Display ab
     */
    @Override
    public void close() {
    }

    /**
     * Gibt den Inhalt des Textfeldes zurueck
     * @return den Inhalt des Textfeldes
     */
    public String getTextFieldText() {
        return textField.getText();
    }
    
    /**
     * Setzt den Text des Textfeldes zueueck
     */
    public void resetTextField() {
        textField.setText("");
    }
}
