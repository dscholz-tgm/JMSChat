package jmschat;

/**
 * Konstruiert die Nachrichten
 * @author Dominik
 * @version 0.1
 */
public class MessageConstructor {
    
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
    private MessageConstructor() {
        format = "[" + FORMAT_USERNAME + "] <" + FORMAT_IP + ">: " + FORMAT_MSG;
    }
    
    /**
     * Erweiterter Konstruktor, gibtt ein Messageformat vor
     * @param format das Messageformat welches benutzt werden soll
     */
    private MessageConstructor(String format) {
        this.format = format;
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
     */
    public void updateCache(String username, String ip) {
        cachedFormat = format.replace(FORMAT_USERNAME, username).replace(FORMAT_IP, ip);
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
     * @param msg die Nachricht
     * @return die Konstruierte Nachricht
     */
    public String construct(String username, String ip, String msg) {
        updateCache(username,ip);
        return cachedFormat.replace(FORMAT_MSG, msg);
    }

}
