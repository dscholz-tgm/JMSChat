package jmschat;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import jmschat.utils.TextReader;

/**
 * Kontrolliert den Chat
 * @author Dominik
 * @version 0.2
 */
public class ChatController {
    
    private ChatClient client;
    private MessageConstructor mc;
    
    public ChatController(ChatClient client) {
        this.client = client;
        mc = new MessageConstructor(client.getUsername(),client.getIP(),">>> MAIL");
    }

    /**
     * Help Command, zum Anzeigen der Hilfe
     */
    public void help() {
        client.out(TextReader.read(TextReader.FILE_HELP));
    }

    /**
     * Chatroom Command, zum Wechseln des CHatrooms
     * @param newRoom der neue Chatroom
     */
    public void chatroom(String newRoom) {
        client.changeChatroom(newRoom);
    }

    /**
     * Mail Command, zum Senden einer Mail
     * @param destinationIP die Zieladdresse des Mailempfaengers
     * @param msg die Mailnachricht
     */
    public void mail(String destinationIP, String msg) {
        try {
            Session session = client.getSession();
            Destination destination = session.createQueue("MAIL@" + destinationIP);
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            TextMessage message = session.createTextMessage(mc.construct(msg));
            producer.send(message);
            client.out("[TO:" + destinationIP + "]" + mc.construct(msg));
            producer.close();
        } catch (JMSException ex) {
        }
    }
    
    /**
     * Mailbox Command, zum Anzeigen der Mailbox
     */
    public void mailbox() {
        try {
            Session session = client.getConnection().createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("MAIL@" + client.getIP());
            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive(200);
            if(message == null) client.out("Keine neue Nachricht in der Mailbox!");
            else {
                while(message != null) {
                    if(message instanceof TextMessage) {
                        TextMessage txtMessage = (TextMessage) message;
                        client.out(txtMessage.getText());
                        txtMessage.acknowledge();
                    }
                    message = consumer.receive(200);
                }
            } 
            consumer.close();
            session.close();
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Exit Command, zum Beenden des Programmes
     */
    public void exit() {
        client.exit();
    }
}
