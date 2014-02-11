package jmschat.utils;

/**
 * Macht Threads Stoppbar
 * @author Dominik
 * @version 0.2
 */
public interface Stoppable {
    /**
     * Sagt dem Thread auf freundliche Art und Weise,
     * dass es langsam Zeit ist ins Bett zu gehen und
     * seine Dienste einzustellen, ist der Thread ein
     * freundlicher Thread, so beendet er sich auch
     * irgendwann, davon kann aber nicht ausgegangen
     * werden.
     */
    public void stop();
}
