package jmschat;

import java.io.File;
import jmschat.utils.ANSIColor;

/**
 * Der Client
 * @author Dominik
 * @version 0.1
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
    
    private void changeServer(String url) {
        this.url = url;
    }
    
    private void changeUsername(String username) {
        this.username = username;
    }
    
    private void changeChatroom(String chatroom) {
        this.chatroom = chatroom;
    }
    
    /**
     * Startet den Client
     */
    public void start() {
        display.out(ANSIColor.CYAN);
        display.showFile(FILE_WELCOME);
        display.out(ANSIColor.GREEN);
        display.showFile(FILE_HELP);
        new Thread(new InputReader(this)).start();
    }

    public void out(String input) {
        display.out(input);
    }
    

}
