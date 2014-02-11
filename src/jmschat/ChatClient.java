package jmschat;

import jmschat.input.InputReader;
import jmschat.output.Display;
import java.io.File;
import jmschat.utils.ANSIColor;

/**
 * Der Client
 * @author Dominik
 * @version 0.2
 */
public class ChatClient {

    private static final String DATA_PATH = "resources" + File.separator;
    private static final String FILE_WELCOME = DATA_PATH + "welcome.txt";
    private static final String FILE_HELP = DATA_PATH + "help.txt";
    
    private String url;
    private String username;
    private String chatroom;
    
    private Display display = new Display();

    /**
     * Konstruktor
     *
     * @param url die Url zum MOM Broker
     * @param username der Username des Benutzers
     */
    public ChatClient(String url, String username) {
        this.url = url;
        this.username = username;
    }
    
    /**
     * Wechselt den Server
     * @param url die URL zu dem Server, zu welchem gewechselt werden soll
     */
    private void changeServer(String url) {
        this.url = url;
    }
    
    /**
     * Aendert den Usernamnen 
     * @param username der Usernamen zu welchem geandert werden soll
     */
    private void changeUsername(String username) {
        this.username = username;
    }
    
    /**
     * Wechselt den Chatromm
     * @param chatroom der Name des Chatrooms zu welchem gewechselt werden soll
     */
    private void changeChatroom(String chatroom) {
        this.chatroom = chatroom;
    }
    
    /**
     * Startet den Client
     */
    public void start() {
        display.inlineOut(ANSIColor.CYAN);
        display.showFile(FILE_WELCOME);
        display.inlineOut(ANSIColor.GREEN);
        display.showFile(FILE_HELP);
        display.inlineOut(ANSIColor.RESET);
        new Thread(new InputReader(this)).start();
    }

    /**
     * Gibt eine Nachricht aus
     * @param msg die Nachricht welche ausgegeben werden soll
     */
    public void out(String msg) {
        display.out(msg);
    }
    
    /**
     * Gibt eine Fehlernachricht aus
     * @param msg die Fehlernachricht welche ausgegeben werden soll
     */
    public void err(String msg) {
        display.out(ANSIColor.RED + msg + ANSIColor.RESET);
    }

    /**
     * Gibt den Prompt aus (steht vor den eigenen Nachrichten)
     */
    public void promt() {
        display.inlineOut(ANSIColor.DARK_GRAY);
        display.inlineOut("Username: ");
    }
}
