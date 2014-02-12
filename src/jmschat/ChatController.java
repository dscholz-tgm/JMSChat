package jmschat;

import jmschat.utils.TextReader;

/**
 * Kontrolliert den Chat
 * @author Dominik
 * @version 0.2
 */
public class ChatController {
    
    private ChatClient client;
    
    public ChatController(ChatClient client) {
        this.client = client;
    }

    public void help() {
        client.out(TextReader.read(TextReader.FILE_HELP));
    }

    public void chatroom(String string) {
        client.changeChatroom(string);
    }

    public void mail(String string) {
    }
    
    public void mailbox() {
    }

    public void exit() {
        client.exit();
    }
}
