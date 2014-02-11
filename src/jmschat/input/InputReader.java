package jmschat.input;

import jmschat.utils.Stoppable;
import java.util.Scanner;
import jmschat.ChatClient;
import jmschat.utils.ANSIColor;

/**
 * Liest den Input
 *
 * @author Dominik
 * @version 0.1
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
        CommandInterpreter ci = new CommandInterpreter();
        String input = "";
        while (!stop) {
            cc.promt();
            input = in.nextLine();
            if (input != null && input.length() > 0) {
                if (input.charAt(0) == '/') {
                    ci.read(input);//Ist ein Command
                } else {
                    cc.out(">" + input); //Ist ein Textasd
                }
            } else {
                cc.err("Ungültige Eingabe, Text wurde nicht gesendet!");
            }
        }
    }

}
