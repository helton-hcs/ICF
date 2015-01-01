package icf.backend;

import icf.message.Message;
import icf.message.MessageHandler;
import icf.message.MessageListener;
import icf.message.MessageProducer;
import icf.middleware.intermediateCode.IntermediateCode;
import icf.middleware.symbolTable.SymbolTable;
import icf.middleware.symbolTable.SymbolTableStack;

public abstract class Backend implements MessageProducer {
    protected static MessageHandler messageHandler;

    static {
        messageHandler = new MessageHandler();
    }

    protected SymbolTable symbolTable;
    protected IntermediateCode intermediateCode;

    public abstract void process(IntermediateCode intermediateCode, SymbolTableStack symbolTableStack) throws Exception;

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
