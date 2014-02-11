package ui;

import java.util.Arrays;
import jmschat.ChatClient;
import jmschat.ChatController;
import jmschat.utils.TextReader;

/**
 * Interpretiert die Commands und fuehrt sie aus
 * @author Dominik
 * @version 0.1
 */
public class CommandInterpreter {
    
    private ChatClient client;
    private ChatController controller;
    
    public CommandInterpreter(ChatClient client, ChatController controller) {
        this.client = client;
        this.controller = controller;
    }
    
    /**
     * Parsed den einkommenden Command string und fuehrt ihn anschliessend aus
     * @param input der eingegebene Commandstring 
     */
    public void parse(String input) {
        String[] args = input.split(" ");
        String cmd = args[0];
        args = args.length > 1 ? Arrays.copyOfRange(args, 1, args.length) : new String[0];
        execute(cmd,args);
    }

    /**
     * Fuehrt die Commands aus
     * @param cmd der Name des Commands
     * @param args die Command Argumente
     */
    public void execute(String cmd, String[] args) {
        switch (cmd) {
            case "/help":
                controller.help();
                break;
            case "/mailbox":
                controller.mailbox();
                break;
            case "/exit":
                controller.exit();
                break;
            case "/close":
                controller.close();
                break;
            case "/server":
                if(args.length == 1) controller.server(args[0]);
                else explain(cmd);
                break;
            case "/chatroom":
                if(args.length == 1) controller.chatroom(args[0]);
                else explain(cmd);
                break;
            case "/mail":
                if(args.length == 2) controller.mail(args[0]);
                else explain(cmd);
                break;
            default:
                error(cmd);
        }
    }
    
    /**
     * Erlaert die richtige nutzung des Befehles
     * @param cmd das Command welches Falsch eingegeben wurde
     */
    private void explain(String cmd) {
        client.err("Fehlerhafte Synopsis des Befehles " + cmd + ":");
        client.err(TextReader.findLine(TextReader.FILE_HELP, cmd));
    }

    /**
     * Gibt eine Fehlermeldung bei einem Falsch eingegeben Command
     * @param cmd das Command welches Falsch eingegeben wurde
     */
    private void error(String cmd) {
        client.err("Eingegebenes Command " + cmd + " wurde nicht gefunden");
    }
}
