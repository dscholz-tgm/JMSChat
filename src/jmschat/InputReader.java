package jmschat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Liest den Input
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        CommandInterpreter ci = new CommandInterpreter();
        String input = "";
        while(!stop) {
            try {
                input = br.readLine();
                if(input != null && input.length() > 0) {
                    if (input.charAt(0) == '/') ci.read(input);//Ist ein Command
                    else cc.out(input); //Ist ein Text
                }
            } catch (IOException ex) {
            }
        }
    }

}
