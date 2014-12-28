package icf.message;

import java.util.ArrayList;
import java.util.List;

public class MessageHandler {
    private Message message;
    private List<MessageListener> listeners;

    public MessageHandler() {
        this.listeners = new ArrayList<>();
    }

    public void addListener(MessageListener listener) {
        listeners.add(listener);
    }

    public void removeListener(MessageListener listener) {
        listeners.remove(listener);
    }

    public void sendMessage(Message message) {
        this.message = message;
        notifyListeners();
    }

    private void notifyListeners() {
        listeners.forEach(listener -> listener.messageReceived(message));
    }

}
