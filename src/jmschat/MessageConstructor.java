package jmschat;

/**
 * Konstruiert die Nachrichten
 * @author Dominik
 * @version 0.4
 */
public class MessageConstructor {
    
    private static final String FORMAT_CHATROOM = "%chatroom%";
    private static final String FORMAT_USERNAME = "%username%";
    private static final String FORMAT_IP = "%ip%";
    private static final String FORMAT_MSG = "%msg%";
    
    private String format;
    private String cachedFormat;
    
    /**
     * Default Konstruktor
     * Erstellt Default Message Format nach:
     * [%username%] <%ip%>: %msg%
     */
    public MessageConstructor(String username, String ip, String chatroom) {
        format = "[" + FORMAT_CHATROOM + "]" + FORMAT_USERNAME + "|" + FORMAT_IP + ": " + FORMAT_MSG;
        updateCache(username, ip, chatroom);
    }
    
    /**
     * Erweiterter Konstruktor, gibtt ein Messageformat vor
     * @param format das Messageformat welches benutzt werden soll
     */
    public MessageConstructor(String format, String username, String ip, String chatroom) {
        this.format = format;
        updateCache(username, ip, chatroom);
    }
    
    /**
     * Setzt das Format
     * @param format das Format auf welches gesetzt werden soll
     */
    public void setFormat(String format) {
        this.format = format;
    }
   
    /**
     * Dated den Cache up <-- That anglezism
     * @param username der neue Benutzername
     * @param ip die neue IP
     * @param chatroom der Chatroom
     */
    public void updateCache(String username, String ip, String chatroom) {
        cachedFormat = format.replace(FORMAT_USERNAME, username).replace(FORMAT_IP, ip).replace(FORMAT_CHATROOM, chatroom);
    }
    
    /**
     * Konstruiert die Nachricht, Benutzt Benutzername und Ip vom Cache
     * @param msg die Nachricht
     * @return die Konstruierte Nachricht
     */
    public String construct(String msg) {
        return cachedFormat.replace(FORMAT_MSG, msg);
    }
    
    /**
     * Konstruiert die Nachricht
     * @param username der Benutzername
     * @param ip die IP
     * @param chatroom der Chatroom
     * @param msg die Nachricht
     * @return die Konstruierte Nachricht
     */
    public String construct(String username, String ip, String chatroom, String msg) {
        updateCache(username,ip,chatroom);
        return cachedFormat.replace(FORMAT_MSG, msg);
    }

}
