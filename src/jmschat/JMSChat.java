package jmschat;

/**
 * Startet the whole thing
 * @author Dominik
 * @version 0.1 // LET IT BEGIN!
 */
public class JMSChat {
    
    private static final String SYNOPSIS = "<broker_url> <username> [<chatroom>]";
    private String url;
    private String username;
    private String chatroom;

    /**
     * Startet das Programm
     * Synopsis: <broker_url> <username> [<chatroom>]
     * @param args die Parameter
     */
    public static void main(String[] args) {
        //Argumentcheck
        if (args.length < 2 || args.length > 3) System.err.println("Fehler bei der Angabe der Argumente!\nSynopsis: " + SYNOPSIS);
        else {
            ChatClient cc = new ChatClient(args[0],args[1]);
            cc.start();
        }
    }
    
}
