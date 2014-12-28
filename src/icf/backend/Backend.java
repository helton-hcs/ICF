package icf.backend;

import icf.message.MessageHandler;
import icf.message.MessageProducer;
import icf.middleware.IntermediateCode;
import icf.middleware.SymbolTable;

public abstract class Backend implements MessageProducer {
    protected static MessageHandler messageHandler;

    static {
        messageHandler = new MessageHandler();
    }

    protected SymbolTable symbolTable;
    protected IntermediateCode intermediateCode;

    public abstract void process(SymbolTable symbolTable, IntermediateCode intermediateCode) throws Exception;
}
