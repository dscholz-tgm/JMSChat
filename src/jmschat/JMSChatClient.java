package jmschat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import jmschat.utils.ANSIColor;

/**
 * Der Client
 * @author Dominik
 * @version 0.1
 */
public class JMSChatClient {

    private static final String DATA_PATH = "resources" + File.separator;
    private static final String FILE_WELCOME = "welcome.txt";
    private static final String FILE_HELP = "help.txt";

    private String url;
    private String username;
    private String chatroom;

    /**
     * Konstruktor
     *
     * @param url die Url zum MOM Broker
     * @param username der Username des Benutzers
     */
    public JMSChatClient(String url, String username) {
        this.url = url;
        this.username = username;
    }

    public void start() {
        out(ANSIColor.CYAN);
        displayFile(FILE_WELCOME);
        out(ANSIColor.GREEN);
        displayFile(FILE_HELP);
    }

    private void displayFile(String filename) {
        try {
            FileReader fr = new FileReader(new File(DATA_PATH + filename));
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s).append("\n");
            }
            br.close();
            fr.close();
            out(sb.toString());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Gibt eine Nachricht aus
     * @param msg die Nachricht welche ausgegeben werden soll
     */
    private void out(String msg) {
        System.out.println(msg);
    }

}
