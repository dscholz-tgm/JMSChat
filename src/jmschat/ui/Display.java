package jmschat.ui;

/**
 * Stellt den Text dar
 * @author Dominik
 * @version 0.1
 */
public interface Display {
    /**
     * Gibt eine Nachricht aus
     * @param msg die Nachricht welche ausgegeben werden soll
     */
    public void out(String msg);
    
    /**
     * Dreht das Display ab
     */
    public void close();
}
