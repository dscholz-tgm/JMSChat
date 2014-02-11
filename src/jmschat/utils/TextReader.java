package jmschat.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Liest eine Textdatei ein
 * @author Dominik
 * @version 0.1
 */
public class TextReader {
    
    public static final String DATA_PATH = "resources" + File.separator;
    public static final String FILE_WELCOME = DATA_PATH + "welcome.txt";
    public static final String FILE_HELP = DATA_PATH + "help.txt";
    
    /**
     * Liest eine Datei ein und gibt sie als String zurueck
     * @param filename der Name der Datei
     * @return das Ausgelesene File
     */
    public static String read(String filename) {
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fr = new FileReader(new File(filename));
            BufferedReader br = new BufferedReader(fr);
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s).append("\n");
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return sb.toString();
    }
    
    /**
     * Findet eine Zeile in der Datei die mit dem Uebergebenen Substring anfaengt
     * @param filename der Name der Datei
     * @param substring der Substring mit dem diese Zeile anfangen soll
     * @return die gesuchte Zeile wenn gefunden, wenn nicht ein leerstring
     */
    public static String findLine(String filename, String substring) {
        String foundLine = "";
        try {
            FileReader fr = new FileReader(new File(filename));
            BufferedReader br = new BufferedReader(fr);
            String s;
            while ((s = br.readLine()) != null) {
                if(s.startsWith(substring)) {
                    foundLine = s;
                    break;
                }
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return foundLine;
    }
}
