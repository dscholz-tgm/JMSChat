package jmschat.ui.graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jmschat.ChatClient;
import jmschat.ui.CommandInterpreter;

/**
 * @author Dominik
 * @version 0.1
 */
public class SendListener implements ActionListener {

    ChatClient cc;
    GraphicalDisplay gd;

    public SendListener(GraphicalDisplay gd, ChatClient cc) {
        this.gd = gd;
        this.cc = cc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = gd.getTextFieldText();
        gd.resetTextField();
        CommandInterpreter ce = new CommandInterpreter(cc,cc.getController());
        if (input != null && input.length() > 0) {
            if (input.charAt(0) == '/') ce.parse(input); //Ist ein Command
            else cc.send(input);
        }
    }

}
