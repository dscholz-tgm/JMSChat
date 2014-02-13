package jmschat;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.Session;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import jmschat.ui.Display;
import jmschat.ui.graphical.GraphicalDisplay;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit-Tests
 * @author
 * Samuel
 */
public class ChatClientTest {
    
    public ChatClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of start method, of class ChatClient.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        //ChatClient instance = null;
        //instance.start();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of connect method, of class ChatClient.
     */
    @Test
    public void testConnect() {
        System.out.println("connect");
        ChatClient instance = new ChatClient("127.0.0.1","Ender","Jane");
        boolean expResult = true;
        boolean result = instance.connect();
        assertEquals(expResult, result);
    }

    /**
     * Test of createChatroom method, of class ChatClient.
     */
    @Test
    public void testCreateChatroom() {
        System.out.println("createChatroom");
        ChatClient instance = new ChatClient("127.0.0.1","Ender","Jane");
        instance.createChatroom();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
 
    /**
     * Test of changeChatroom method, of class ChatClient.
     */
    @Test
    public void testChangeChatroom() {
        System.out.println("changeChatroom");
        String chatroom = "";
        ChatClient instance = new ChatClient("127.0.0.1","Ender","Jane");
        instance.changeChatroom(chatroom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Checkt ob die gesendete Message im JTextArea angezeigt wird
     */
    @Test
    public void testSend() {
        System.out.println("send");
        String msg = "test1";
        String expResult;
        String result = "";
        ChatClient instance = new ChatClient("127.0.0.1", "Ender", "Jane");
        instance.send(msg);
        JTextArea jta = instance.getGraphicalDisplay().getTextArea();
        expResult = "[Jane]Ender|127.0.0.1: test1";
        try {
            result = jta.getText(jta.getLineStartOffset(jta.getLineCount()), jta.getLineEndOffset(jta.getLineCount()));
        } catch (BadLocationException ex) {
            Logger.getLogger(ChatClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult, result);
    }
}