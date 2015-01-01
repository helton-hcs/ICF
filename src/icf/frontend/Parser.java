package icf.frontend;

import icf.message.Message;
import icf.message.MessageHandler;
import icf.message.MessageListener;
import icf.message.MessageProducer;
import icf.middleware.intermediateCode.IntermediateCode;
import icf.middleware.symbolTable.SymbolTableFactory;
import icf.middleware.symbolTable.SymbolTableStack;

public abstract class Parser implements MessageProducer {
    protected static SymbolTableStack symbolTableStack;
    protected static MessageHandler messageHandler;

    static {
        symbolTableStack = SymbolTableFactory.createSymbolTableStack();
        messageHandler = new MessageHandler();
    }

    protected Lexer lexer;
    protected IntermediateCode intermediateCode;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.intermediateCode = null;
    }

    public abstract void parse() throws Exception;

    public abstract int getErrorCount() throws Exception;

    public Token currentToken() {
        return lexer.currentToken();
    }

    public Token nextToken() throws Exception {
        return lexer.nextToken();
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

    public IntermediateCode getIntermediateCode() {
        return intermediateCode;
    }

    public SymbolTableStack getSymbolTableStack() {
        return symbolTableStack;
    }

}
