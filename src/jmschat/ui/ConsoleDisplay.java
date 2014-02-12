package jmschat.ui;

/**
 * Stellt das Display in einer Consolen Oberflaeche dar
 * @author Dominik
 * @version 0.1
 */
public class ConsoleDisplay implements Display {

    @Override
    public void out(String msg) {
        System.out.println(msg);
    }

}
