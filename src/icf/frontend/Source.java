package icf.frontend;

import icf.message.*;

import java.io.BufferedReader;
import java.io.IOException;

public class Source implements MessageProducer {
    public static final char EOL = '\n';
    public static final char EOF = (char) 0;

    private static final int UNREAD_CHAR = -1;
    private static final int INITIAL_STATE = -2;
    private BufferedReader reader;
    private String line;
    private int lineNumber;
    private int currentPosition;
    private MessageHandler messageHandler;

    public Source(BufferedReader reader) {
        this.lineNumber = 0;
        this.currentPosition = INITIAL_STATE;
        this.reader = reader;
        this.messageHandler = new MessageHandler();
    }

    public char currentChar() throws Exception {
        if (currentPosition == INITIAL_STATE) {
            readLine();
            return nextChar();
        }
        else if (line == null)
            return EOF;
        else if ((currentPosition == UNREAD_CHAR) || (currentPosition == line.length()))
            return EOL;
        else if (currentPosition > line.length()) {
            readLine();
            return nextChar();
        }
        else
            return line.charAt(currentPosition);
    }

    public char nextChar() throws Exception {
        ++currentPosition;
        return currentChar();
    }

    public char peekChar() throws Exception {
        currentChar();
        if (line == null)
            return EOF;
        int nextPosition = currentPosition + 1;
        return nextPosition < line.length() ? line.charAt(currentPosition): EOL;
    }

    private void readLine() throws IOException {
        line = reader.readLine();
        currentPosition = UNREAD_CHAR;
        if (line != null) {
            ++lineNumber;
            sendMessage(new Message(MessageType.SOURCE_LINE, new Object[] {lineNumber, line}));
        }
    }

    public void close() throws Exception {
        if (reader != null) {
            try {
                reader.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
                throw ex;
            }
        }
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getPosition() {
        return currentPosition;
    }

    public void addMessageListener(MessageListener listener) {
        messageHandler.addListener(listener);
    }

    public void removeMessageListener(MessageListener listener) {
        messageHandler.removeListener(listener);
    }

    public void sendMessage(Message message) {
        messageHandler.sendMessage(message);
    }

}
