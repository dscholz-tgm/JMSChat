/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmschat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author
 * Samuel
 */
public class MessageConstructorTest {

    /**
     * Test of setFormat method, of class MessageConstructor.
     
    @Test
    public void testSetFormat() {
        System.out.println("setFormat");
        String format = "";
        MessageConstructor instance = new MessageConstructor("Alladin", "127.0.0.1", "Kairo");
        instance.setFormat(format);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    
    /**
     * Test of updateCache method, of class MessageConstructor.
     
    @Test
    public void testUpdateCache() {
        System.out.println("updateCache");
        String username = "";
        String ip = "";
        String chatroom = "";
        MessageConstructor instance = new MessageConstructor("Alladin", "127.0.0.1", "Kairo");
        instance.updateCache(username, ip, chatroom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    /**
     * Test of construct method, of class MessageConstructor.
     */
    @Test
    public void testConstruct_String() {
        System.out.println("construct");
        String msg = "Wunderlampe";
        MessageConstructor instance = new MessageConstructor("Alladin", "127.0.0.1", "Kairo");
        String expResult = "[Kairo]Alladin|127.0.0.1: Wunderlampe";
        String result = instance.construct(msg);
        assertEquals(expResult, result);
    }

    /**
     * Test of construct method, of class MessageConstructor.
     */
    @Test
    public void testConstruct_4args() {
        System.out.println("construct");
        String username = "Jon";
        String ip = "127.0.0.1";
        String chatroom = "Castle Black";
        String msg = "For the Watch!";
        MessageConstructor instance = new MessageConstructor("Egal", "666.666.666.666", "Irgendwo");
        String expResult = "[Castle Black]Jon|127.0.0.1: For the Watch!";
        String result = instance.construct(username, ip, chatroom, msg);
        assertEquals(expResult, result);
    }
}