package jmschat;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import jmschat.ui.Display;
import jmschat.ui.graphical.GraphicalDisplay;
import jmschat.utils.TextReader;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Der Client
 * @author Dominik
 * @version 0.6
 */
public class ChatClient {
    
    private String url;
    private String username;
    private String chatroom;
    private String ip;
    
    private Display display;
    private ChatController controller;
    private MessageConstructor mc;
    private MessageReader mr;
    
    Session session = null;
    Connection connection = null;
    MessageConsumer consumer = null;
    Destination destination = null;
    MessageProducer producer = null;

    /**
     * Konstruktor
     *
     * @param url die Url zum MOM Broker
     * @param username der Username des Benutzers
     */
    public ChatClient(String url, String username, String chatroom) {
        display = new GraphicalDisplay(this);
        this.url = "tcp://" + url + ":61616";
        this.username = username;
        this.chatroom = chatroom;
        controller = new ChatController(this);
         try {
            ip = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            ip = "127.0.0.1";
        }
        mc = new MessageConstructor(username, ip);
        mr = new MessageReader(this);
    }
        
    /**
     * Startet den Client
     */
    public void start() {
        display.out(TextReader.read(TextReader.FILE_WELCOME));
        display.out(TextReader.read(TextReader.FILE_HELP));
        if(connect()) {
            createChatroom();
        }
    }
       
    /**
     * Verbinden und Erstellen der Connection
     * @return ob die Verbindung erfolgreich war
     */
    public boolean connect() {
        try {
            close();
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connection = connectionFactory.createConnection();
            connection.start();
            
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            session.setMessageListener(mr);
            return true;
        } catch (JMSException ex) {
            err("Fehler beim Verbinden zum Server " + url + ", bitte versuche es erneut");
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Erstellt den Chatroom oder tritt ihm bei
     */
    public void createChatroom() {
        try {
            if(consumer != null) consumer.close();
            destination = session.createTopic(chatroom);
            
            consumer = session.createConsumer(destination);
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        } catch (JMSException ex) {
            err("Fehler beim Erstellen des Chatrooms " + chatroom + ", bitte versuche es erneut");
        }
    }
    
    /**
     * Gibt das Display zurueck
     * @return das Display
     */
    public Display getDisplay() {
        return display;
    }
    
    /**
     * Gibt den ChatController zurueck
     * @return den ChatController
     */
    public ChatController getController() {
        return controller;
    }
    
    /**
     * Wechselt den Server
     * @param url die URL zu dem Server, zu welchem gewechselt werden soll
     */
    public void changeServer(String url) {
        this.url = url;
        connect();
        createChatroom();
    }
    
    /**
     * Aendert den Usernamnen 
     * @param username der Usernamen zu welchem geandert werden soll
     */
    public void changeUsername(String username) {
        this.username = username;
        mc.updateCache(username, ip);
    }
    
    /**
     * Wechselt den Chatromm
     * @param chatroom der Name des Chatrooms zu welchem gewechselt werden soll
     */
    public void changeChatroom(String chatroom) {
        this.chatroom = chatroom;
        createChatroom();
    }

    /**
     * Gibt eine Nachricht aus
     * @param msg die Nachricht welche ausgegeben werden soll
     */
    public void out(String msg) {
        display.out(msg);
    }
    
    /**
     * Gibt eine Fehlernachricht aus
     * @param msg die Fehlernachricht welche ausgegeben werden soll
     */
    public void err(String msg) {
        display.out(msg);
    }
    
    /**
     * Sendet die angegebene Message
     * @param msg die zu sendende Nachricht
     */
    public void send(String msg) {
        try {
            TextMessage message = session.createTextMessage(mc.construct(msg));
            producer.send(message);
        } catch (JMSException ex) {
        }
    }

    /**
     * Schliesst alle Verbindungen
     */
    public void close() {
        try {
            if(consumer != null) consumer.close();
            if(session != null) session.close();
            if(connection != null) connection.close();
        } catch (JMSException ex) {
        }
    }

    /**
     * Beendet das Programm
     */
    public void shutdown() {
        close();
    }
}
