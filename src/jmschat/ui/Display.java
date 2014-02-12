package jmschat.ui;

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

/**
 * Stellt den Text dar
 * @author Dominik
 * @version 0.2
 */
public class Display {
    
    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;
    
    JFrame frame;
    JPanel mainPanel, bottomPanel;
    JTextArea textArea;
    JTextField textField;
    JButton sendButton;
    Random rand;
    Font font;
    
    public Display() {
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
        ((DefaultCaret)textArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        mainPanel = new JPanel(new BorderLayout());
        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),BorderLayout.CENTER);
        textField = new JTextField();
        sendButton = new JButton("Send");
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
    public void out(String msg) {
        System.out.println(msg);
        textArea.append(msg);
    }

    /**
     * Gibt eine Nachricht ohne Linebreak aus!
     * @param msg die Nachricht welche ausgegeben werden soll
     */
    public void inlineOut(String msg) {
        System.out.print(msg);
    }
}
