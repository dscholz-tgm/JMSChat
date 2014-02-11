package ui;

/**
 * Stellt den Text dar
 * @author Dominik
 * @version 0.2
 */
public class Display {
    
    /**
     * Gibt eine Nachricht aus
     * @param msg die Nachricht welche ausgegeben werden soll
     */
    public void out(String msg) {
        System.out.println(msg);
    }

    /**
     * Gibt eine Nachricht ohne Linebreak aus!
     * @param msg die Nachricht welche ausgegeben werden soll
     */
    public void inlineOut(String msg) {
        System.out.print(msg);
    }
}
