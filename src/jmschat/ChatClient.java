package jmschat;

import ui.InputReader;
import ui.Display;
import java.io.File;
import jmschat.utils.ANSIColor;
import jmschat.utils.TextReader;

/**
 * Der Client
 * @author Dominik
 * @version 0.3
 */
public class ChatClient {
    
    private String url;
    private String username;
    private String chatroom;
    
    private Display display = new Display();
    private ChatController controller = new ChatController();

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
     * Gibt das Display zurueck
     * @return das Display
     */
    public Display getDisplay() {
        return display;
    }
    
    /**
     * Gibt den ChatController zurueck
     * @return den ChatController
     */
    public ChatController getController() {
        return controller;
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
        display.out(TextReader.read(TextReader.FILE_WELCOME));
        display.inlineOut(ANSIColor.GREEN);
        display.out(TextReader.read(TextReader.FILE_HELP));
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
        display.inlineOut(ANSIColor.RED);
        display.out(msg);
        display.inlineOut(ANSIColor.RESET);
    }

    /**
     * Gibt den Prompt aus (steht vor den eigenen Nachrichten)
     */
    public void promt() {
        display.inlineOut(ANSIColor.DARK_GRAY);
        display.inlineOut("Username: ");
    }
}
