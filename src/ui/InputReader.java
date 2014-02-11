package ui;

import jmschat.utils.Stoppable;
import java.util.Scanner;
import jmschat.ChatClient;

/**
 * Liest den Input
 *
 * @author Dominik
 * @version 0.3
 */
public class InputReader implements Runnable, Stoppable {

    private ChatClient cc;
    private boolean stop = false;

    public InputReader(ChatClient cc) {
        this.cc = cc;
    }

    @Override
    public void stop() {
        stop = true;
    }

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        CommandInterpreter ce = new CommandInterpreter(cc,cc.getController());
        String input;
        
        while (!stop) {
            cc.promt();
            input = in.nextLine();
            if (input != null && input.length() > 0) {
                if (input.charAt(0) == '/') ce.parse(input); //Ist ein Command
            } else cc.err("Ungültige Eingabe, Text wurde nicht gesendet!");
        }
    }

}
