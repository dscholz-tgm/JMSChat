package jmschat.output;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Stellt den Text dar
 * @author Dominik
 * @version 0.2
 */
public class Display {
    
    /**
     * Zeigt ein File an
     * @param filename das File welches angezeigt werden soll
     */
    public void showFile(String filename) {
        try {
            FileReader fr = new FileReader(new File(filename));
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s).append("\n");
            }
            br.close();
            fr.close();
            out(sb.toString());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Gibt eine Nachricht aus
     * @param msg die Nachricht welche ausgegeben werden soll
     */
    public void out(String msg) {
        System.out.println(msg);
    }

    /**
     * Gibt eine Nachricht ohne Linebreak aus!
     * @param msg die Nachricht welche ausgegeben werden soll
     */
    public void inlineOut(String msg) {
        System.out.print(msg);
    }
}
