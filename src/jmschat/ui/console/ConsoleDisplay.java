package jmschat.ui.console;

import jmschat.ChatClient;
import jmschat.ui.Display;

/**
 * Stellt das Display in einer Consolen Oberflaeche dar
 * @author Dominik
 * @version 0.1
 */
public class ConsoleDisplay implements Display {
    
    InputReader reader;
    
    public ConsoleDisplay(ChatClient cc) {
        InputReader reader = new InputReader(cc);
        new Thread(reader).start();
    }

    /**
     * Gibt eine Nachricht aus
     * @param msg die Nachricht welche ausgegeben werden soll
     */
    @Override
    public void out(String msg) {
        System.out.println(msg);
    }

    /**
     * Dreht das Display ab
     */
    @Override
    public void close() {
        reader.stop();
    }
}
