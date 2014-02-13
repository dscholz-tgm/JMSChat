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
 * @version 0.7
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
         try {
            ip = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            ip = "127.0.0.1";
        }
        controller = new ChatController(this);
        mc = new MessageConstructor(username, ip, chatroom);
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
        } else {
            System.exit(-1);
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
            return true;
        } catch (JMSException ex) {
            System.err.println("Fehler beim Verbinden zum Server " + url + ", bitte versuche es erneut");
            return false;
        }
    }
    
    /**
     * Erstellt den Chatroom oder tritt ihm bei
     */
    public void createChatroom() {
        try {
            if(consumer != null) consumer.close();
            if(producer != null) producer.close();
            destination = session.createTopic(chatroom);
            
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(mr);
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
     * Gibt das Display zurueck
     * @return das Display
     */
    public GraphicalDisplay getGraphicalDisplay() {
        return (GraphicalDisplay) display;
    }
    
    /**
     * Gibt den ChatController zurueck
     * @return den ChatController
     */
    public ChatController getController() {
        return controller;
    }
    
    /**
     * Gibt die Connection zurueck
     * @return die Connection
     */
    public Connection getConnection() {
        return connection;
    }
    
    /**
     * Gibt die Session zurueck
     * @return die Session
     */
    public Session getSession() {
        return session;
    }
    
    /**
     * Gibt den Username zurueck
     * @return der Username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Gibt die IP zurueck
     * @return die IP
     */
    public String getIP() {
        return ip;
    }
    
    /**
     * Wechselt den Chatromm
     * @param chatroom der Name des Chatrooms zu welchem gewechselt werden soll
     */
    public void changeChatroom(String chatroom) {
        this.chatroom = chatroom;
        createChatroom();
        mc.updateCache(username, ip, chatroom);
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
            if(producer != null) producer.close();
            if(consumer != null) consumer.close();
            if(session != null) session.close();
            if(connection != null) connection.close();
        } catch (JMSException ex) {
        }
    }
    
    /**
     * Beendet das Programm
     */
    public void exit() {
        close();
        display.close();
    }
}
