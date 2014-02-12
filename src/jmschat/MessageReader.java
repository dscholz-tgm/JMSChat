package jmschat;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Reads and Reads and Reads
 * 
 * @author Dominik
 * @version 0.2
 */
public class MessageReader implements MessageListener {

    private ChatClient cc;

    public MessageReader(ChatClient cc) {
        this.cc = cc;
    }

    /**
     * Wird ausgefuehrt wenn eine Nachricht empfangen wird
     * @param message wenn eine Nachricht empfangen wird
     */
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                cc.out(((TextMessage) message).getText());
                message.acknowledge();
            } catch (JMSException ex) {
            }
        }
    }

}
