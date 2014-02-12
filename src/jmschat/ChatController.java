package jmschat;

import jmschat.utils.TextReader;

/**
 * Kontrolliert den Chat
 * @author Dominik
 * @version 0.1
 */
public class ChatController {
    
    private ChatClient client;
    
    public ChatController(ChatClient client) {
        this.client = client;
    }

    public void help() {
        client.out(TextReader.read(TextReader.FILE_HELP));
    }
    
    public void server(String string) {
        client.changeServer(string);
    }

    public void chatroom(String string) {
        client.changeChatroom(string);
    }

    public void mail(String string) {
    }
    
    public void mailbox() {
    }

    public void exit() {
        client.close();
    }
    
    public void close() {
        client.shutdown();
    }

}
