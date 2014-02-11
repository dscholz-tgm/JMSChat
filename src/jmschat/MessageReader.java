package jmschat;

import jmschat.utils.Stoppable;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;

/**
 * Reads and Reads and Reads
 *
 * @author Dominik
 * @version 0.1
 */
public class MessageReader implements Runnable, Stoppable {

    private ChatClient cc;
    private MessageConsumer consumer;
    private boolean stop = false;

    public MessageReader(ChatClient cc, MessageConsumer consumer) {
        this.cc = cc;
        this.consumer = consumer;
    }

    @Override
    public void stop() {
        stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                Message message = consumer.receive();
                if (message instanceof TextMessage && message != null) {
                    TextMessage textMessage = (TextMessage) message;
                    cc.out(textMessage.getText());
                    message.acknowledge();
                }
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }
    }

}
